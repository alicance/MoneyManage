package com.samego.alic.moneymanage.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.presenter.DetailIncomePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.DetailIncomeView;

/**
 *
 * Created by alic on 16-9-22.
 */
public class DetailIncomeActivity extends AppCompatActivity implements DetailIncomeView, View.OnClickListener {
    private LinearLayout backPlace;
    private View backView;
    private IconicFontDrawable backIcon,titleIcon,payerIcon,moneyIcon,datetimeIcon;
    private View titleView,payerView,moneyView,datetimeView;
    private TextView title,payer,money,datetime,description;
    private Button deleteButton,modifyButton;

    private AlertDialog deleteAlert;
    private AlertDialog.Builder deleteBuilder;

    private Income income;
    private DetailIncomePresenter detailIncomePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_detail);
        initUI();
        getIncome();
        detailIncomePresenter.displayIncome();
    }

    @Override
    public void initUI() {
        backPlace = (LinearLayout) findViewById(R.id.detail_income_tool_back);
        backView = findViewById(R.id.detail_income_tool_back_icon);
        backIcon = new IconicFontDrawable(this);
        backIcon.setIcon(EntypoIcon.REPLY);
        backIcon.setIconColor(Colors.color_pure_white);
        backView.setBackground(backIcon);
        backPlace.setOnClickListener(this);

        //main
        titleView = findViewById(R.id.detail_income_title_icon);
        payerView = findViewById(R.id.detail_income_payer_icon);
        moneyView = findViewById(R.id.detail_income_money_icon);
        datetimeView = findViewById(R.id.detail_income_datetime_icon);

        title = (TextView) findViewById(R.id.detail_income_title_message);
        payer = (TextView) findViewById(R.id.detail_income_payer_message);
        money = (TextView) findViewById(R.id.detail_income_money_message);
        datetime = (TextView) findViewById(R.id.detail_income_datetime_message);
        description = (TextView) findViewById(R.id.detail_income_description_message);

        titleIcon = new IconicFontDrawable(this);
        titleIcon.setIcon(FontAwesomeIcon.REORDER);
        titleIcon.setIconColor(Colors.color_theme);
        titleView.setBackground(titleIcon);

        payerIcon = new IconicFontDrawable(this);
        payerIcon.setIcon(FontAwesomeIcon.SCREENSHOT);
        payerIcon.setIconColor(Colors.color_theme);
        payerView.setBackground(payerIcon);

        moneyIcon = new IconicFontDrawable(this);
        moneyIcon.setIcon(FontAwesomeIcon.STRIKETHROUGH);
        moneyIcon.setIconColor(Colors.color_theme);
        moneyView.setBackground(moneyIcon);

        datetimeIcon = new IconicFontDrawable(this);
        datetimeIcon.setIcon(EntypoIcon.CALENDAR);
        datetimeIcon.setIconColor(Colors.color_theme);
        datetimeView.setBackground(datetimeIcon);

        deleteButton = (Button) findViewById(R.id.detail_income_button_delete);
        modifyButton = (Button) findViewById(R.id.detail_income_button_modify);
        deleteButton.setOnClickListener(this);
        modifyButton.setOnClickListener(this);


        detailIncomePresenter = new DetailIncomePresenter(this,this);
    }

    @Override
    public Income getIncome() {
        this.income = (Income) getIntent().getSerializableExtra("income");
        return this.income;
    }

    @Override
    public void displayIncome() {
        title.setText(income.getTitle());
        payer.setText(income.getPayer());
        money.setText(String.valueOf(income.getMoney()));
        datetime.setText(income.getDatetime());
        description.setText(income.getDescription());
    }

    @Override
    public void deleteIncome() {
        deleteBuilder = new AlertDialog.Builder(this);
        deleteAlert = deleteBuilder.setTitle("提示")
                .setMessage("亲~~确定删除此收入")
                .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detailIncomePresenter.deleteNote();
                        showTip(0);
                        toIncomeListFragment();
                    }
                })
                .create();
        deleteAlert.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_income_tool_back:
                onBackPressed();
                finish();
                ActivityCollector.addActivity(this);
                break;
            case R.id.detail_income_button_delete:
                deleteIncome();
                break;
            case R.id.detail_income_button_modify:
                toModifyIncomeActivity();
                break;
            default:
                break;
        }
    }
    @Override
    public void toIncomeListFragment() {
        DataUtil.saveSharedPreference(this, "menuItem", "income");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"删除此收入成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void toModifyIncomeActivity() {
        Intent intent = new Intent(this,ModifyIncomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("income",getIncome());
        intent.putExtras(bundle);
        startActivity(intent);
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
