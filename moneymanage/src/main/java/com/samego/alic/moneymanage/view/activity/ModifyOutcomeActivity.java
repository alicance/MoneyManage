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
import com.samego.alic.moneymanage.bean.Outcome;
import com.samego.alic.moneymanage.presenter.ModifyIncomePresenter;
import com.samego.alic.moneymanage.presenter.ModifyOutcomePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.ModifyIncomeView;
import com.samego.alic.moneymanage.view.view.ModifyOutcomeView;

import java.util.Calendar;

/**
 *
 * Created by alic on 16-9-22.
 */
public class ModifyOutcomeActivity extends AppCompatActivity implements ModifyOutcomeView, View.OnClickListener, View.OnFocusChangeListener, DatePickerDialog.OnDateSetListener {
    private View backView,enterView;
    private IconicFontDrawable backIcon,enterIcon;
    private TextView title,place,money,datetime,description;

    private Outcome outcome;
    private ModifyOutcomePresenter modifyOutcomePresenter;
    public static final String DATEPICKER_TAG = "datepicker";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_outcome);
        initUI();
        modifyOutcomePresenter.display();
    }

    @Override
    public void initUI() {
        title = (EditText) findViewById(R.id.modify_outcome_input_title);
        place = (EditText) findViewById(R.id.modify_outcome_input_place);
        money = (EditText) findViewById(R.id.modify_outcome_input_money);
        datetime = (EditText) findViewById(R.id.modify_outcome_input_datetime);
        description = (EditText) findViewById(R.id.modify_outcome_input_description);
        datetime.setOnFocusChangeListener(this);

        backView = findViewById(R.id.modify_outcome_tool_back);
        enterView = findViewById(R.id.modify_outcome_tool_add);

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

        modifyOutcomePresenter = new ModifyOutcomePresenter(this,this);
    }

    @Override
    public void getOutcome() {
        this.outcome = (Outcome) getIntent().getSerializableExtra("outcome");
    }

    @Override
    public void display() {
        getOutcome();
        title.setText(outcome.getTitle());
        place.setText(outcome.getPlace());
        datetime.setText(outcome.getDatetime());
        money.setText(String.valueOf(outcome.getMoney()));
        description.setText(outcome.getDescription());
    }

    @Override
    public boolean checkInput() {
        if (title.getText().toString().trim().length() == 0){
            showTip(1);
            return false;
        }
        if (place.getText().toString().trim().length() == 0){
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
        outcome.setTitle(title.getText().toString().trim());
        outcome.setPlace(place.getText().toString().trim());
        outcome.setDatetime(datetime.getText().toString().trim());
        outcome.setMoney(money.getText().toString());
        outcome.setDescription(description.getText().toString().trim());
        return true;
    }

    @Override
    public Outcome getAfterOutcome() {
        return outcome;
    }

    @Override
    public void toOutcomeListFragment() {
        DataUtil.saveSharedPreference(this, "menuItem", "outcome");
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
            case R.id.detail_outcome_tool_back:
                onBackPressed();
                finish();
                ActivityCollector.addActivity(this);
                break;
            case R.id.modify_outcome_tool_add:
                modifyIncome();
                break;
            default:
                break;
        }
    }

    void modifyIncome(){
        if (checkInput()){
            modifyOutcomePresenter.modifyOutcome();
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
