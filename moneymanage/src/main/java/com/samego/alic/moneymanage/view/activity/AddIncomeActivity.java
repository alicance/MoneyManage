package com.samego.alic.moneymanage.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.fourmob.datetimepicker.date.DatePickerDialog;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.presenter.AddIncomePresenter;
import com.samego.alic.moneymanage.presenter.AddNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.AddIncomeView;
import com.sleepbot.datetimepicker.time.TimePickerDialog;

import java.util.Calendar;

/**
 * 编辑增加收入Activity
 * Created by alic on 16-9-21.
 */
public class AddIncomeActivity extends AppCompatActivity implements AddIncomeView, View.OnClickListener, View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private View backView,addView;
    private IconicFontDrawable backIcon,addIcon;
    private EditText title,payer,money,datetime,description;

    public static final String DATEPICKER_TAG = "datepicker";

    private AddIncomePresenter addIncomePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_note);
        initUI();
    }

    void initUI(){
        backView = findViewById(R.id.add_income_tool_back);
        addView = findViewById(R.id.add_income_tool_add);

        addIcon = new IconicFontDrawable(this);
        addIcon.setIcon(EntypoIcon.PLUS);
        addIcon.setIconColor(Colors.color_pure_white);

        backIcon = new IconicFontDrawable(this);
        backIcon.setIcon(EntypoIcon.REPLY);
        backIcon.setIconColor(Colors.color_pure_white);

        addView.setBackground(addIcon);
        backView.setBackground(backIcon);
        addView.setOnClickListener(this);
        backView.setOnClickListener(this);

        title = (EditText) findViewById(R.id.add_income_input_title);
        payer = (EditText) findViewById(R.id.add_income_input_payer);
        money = (EditText) findViewById(R.id.add_income_input_money);
        datetime = (EditText) findViewById(R.id.add_income_input_datetime);
        description = (EditText) findViewById(R.id.add_income_input_description);

        datetime.setOnFocusChangeListener(this);
        addIncomePresenter = new AddIncomePresenter(this,this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_income_tool_back:
                onBackPressed();
                break;
            case R.id.add_income_tool_add:
                if (checkInput()) {
                    addIncomePresenter.addIncome();
                    addIncomePresenter.toHomeIncomeItem();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Income getIncome() {
        Income income = new Income();
        income.setTitle(title.getText().toString().trim());
        income.setPayer(payer.getText().toString().trim());
        income.setMoney(money.getText().toString().trim());
        income.setDatetime(datetime.getText().toString().trim());
        income.setDescription(description.getText().toString().trim());
        income.setUsername(DataUtil.readSharedPreference(this,"username",null));
        return income;
    }

    @Override
    public boolean checkInput() {
        if (title.getText().toString().equals("")){
            showTip(1);
            return false;
        }
        if (payer.getText().toString().equals("")){
            showTip(2);
            return false;
        }
        if (datetime.getText().toString().equals("")){
            showTip(3);
            return false;
        }
        if (money.getText().toString().equals("")){
            showTip(4);
            return false;
        }
        if (description.getText().toString().equals(""))
            description.setText("亲没有输入备注噢~~~");
        return true;
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"添加收入成功",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"类型不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"付款方不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"时间不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this,"金额不允许为空",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void toHomeIncomeItem() {
        DataUtil.saveSharedPreference(this, "menuItem", "income");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
        //销毁所有的activity
        ActivityCollector.AppExit(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            final Calendar calendar = Calendar.getInstance();

            final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), false);

            datePickerDialog.setVibrate(false);
            datePickerDialog.setYearRange(2016, 2028);
            datePickerDialog.setCloseOnSingleTapDay(true);
            datePickerDialog.show(getSupportFragmentManager(),DATEPICKER_TAG);
        }
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int year, int month, int day) {
        datetime.setText(String.valueOf(year+"-"+month+"-"+day));
    }
}
