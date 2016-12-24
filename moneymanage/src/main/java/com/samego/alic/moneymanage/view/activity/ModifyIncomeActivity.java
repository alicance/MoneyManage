package com.samego.alic.moneymanage.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.presenter.ModifyIncomePresenter;
import com.samego.alic.moneymanage.presenter.ModifyNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.ModifyIncomeView;

import java.util.Calendar;

/**
 *
 * Created by alic on 16-9-22.
 */
public class ModifyIncomeActivity extends AppCompatActivity implements ModifyIncomeView, View.OnClickListener, View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private View backView,enterView;
    private IconicFontDrawable backIcon,enterIcon;
    private TextView title,payer,money,datetime,description;

    private Income income;
    private ModifyIncomePresenter modifyIncomePresenter;
    public static final String DATEPICKER_TAG = "datepicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_income);
        initUI();
        modifyIncomePresenter.display();
    }

    @Override
    public void initUI() {
        title = (EditText) findViewById(R.id.modify_income_input_title);
        payer = (EditText) findViewById(R.id.modify_income_input_payer);
        money = (EditText) findViewById(R.id.modify_income_input_money);
        datetime = (EditText) findViewById(R.id.modify_income_input_datetime);
        description = (EditText) findViewById(R.id.modify_income_input_description);
        datetime.setOnFocusChangeListener(this);

        backView = findViewById(R.id.modify_income_tool_back);
        enterView = findViewById(R.id.modify_income_tool_add);

        backIcon = new IconicFontDrawable(this);
        backIcon.setIcon(EntypoIcon.REPLY);
        backIcon.setIconColor(Colors.color_pure_white);
        backView.setBackground(backIcon);
        backView.setOnClickListener(this);

        enterIcon = new IconicFontDrawable(this);
        enterIcon.setIcon(EntypoIcon.CYCLE);
        enterIcon.setIconColor(Colors.color_pure_white);
        enterView.setBackground(enterIcon);
        enterView.setOnClickListener(this);

        modifyIncomePresenter = new ModifyIncomePresenter(this,this);
    }

    @Override
    public void getIncome() {
        this.income = (Income) getIntent().getSerializableExtra("income");
    }

    @Override
    public void display() {
        getIncome();
        title.setText(income.getTitle());
        payer.setText(income.getPayer());
        datetime.setText(income.getDatetime());
        money.setText(String.valueOf(income.getMoney()));
        description.setText(income.getDescription());
    }

    @Override
    public boolean checkInput() {
        if (title.getText().toString().trim().length() == 0){
            showTip(1);
            return false;
        }
        if (payer.getText().toString().trim().length() == 0){
            showTip(2);
            return false;
        }
        if (datetime.getText().toString().trim().length() == 0){
            showTip(3);
            return false;
        }
        if (money.getText().toString().trim().length() == 0){
            showTip(4);
            return false;
        }
        income.setTitle(title.getText().toString().trim());
        income.setPayer(payer.getText().toString().trim());
        income.setDatetime(datetime.getText().toString().trim());
        income.setMoney(money.getText().toString());
        income.setDescription(description.getText().toString().trim());
        return true;
    }

    @Override
    public Income getAfterIncome() {
        return income;
    }

    @Override
    public void toIncomeListFragment() {
        DataUtil.saveSharedPreference(this, "menuItem", "income");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
        //销毁所有的activity
        ActivityCollector.AppExit(this);
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"修改信息成功",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"类型不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"付款方不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "日期不允许为空", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "金额不允许为空", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_income_tool_back:
                onBackPressed();
                finish();
                ActivityCollector.addActivity(this);
                break;
            case R.id.modify_income_tool_add:
                modifyIncome();
                break;
            default:
                break;
        }
    }

    void modifyIncome(){
        if (checkInput()){
            modifyIncomePresenter.modifyIncome();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            final Calendar calendar = Calendar.getInstance();

            final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), false);

            datePickerDialog.setVibrate(false);
            datePickerDialog.setYearRange(2016, 2028);
            datePickerDialog.setCloseOnSingleTapDay(true);
            datePickerDialog.show(getSupportFragmentManager(), DATEPICKER_TAG);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        datetime.setText(String.valueOf(year+"-"+month+"-"+day));
    }
}
