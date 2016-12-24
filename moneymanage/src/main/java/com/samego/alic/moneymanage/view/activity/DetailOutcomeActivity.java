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
import com.samego.alic.moneymanage.bean.Outcome;
import com.samego.alic.moneymanage.presenter.DetailIncomePresenter;
import com.samego.alic.moneymanage.presenter.DetailOutcomePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.DetailIncomeView;
import com.samego.alic.moneymanage.view.view.DetailOutcomeView;

/**
 *
 * Created by alic on 16-9-22.
 */
public class DetailOutcomeActivity extends AppCompatActivity implements DetailOutcomeView, View.OnClickListener {
    private LinearLayout backPlace;
    private View backView;
    private IconicFontDrawable backIcon,titleIcon,placeIcon,moneyIcon,datetimeIcon;
    private View titleView,placeView,moneyView,datetimeView;
    private TextView title,place,money,datetime,description;
    private Button deleteButton,modifyButton;

    private AlertDialog deleteAlert;
    private AlertDialog.Builder deleteBuilder;

    private Outcome outcome;
    private DetailOutcomePresenter detailOutcomePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcome_detail);
        initUI();
        getOutcome();
        detailOutcomePresenter.displayOutcome();
    }

    @Override
    public void initUI() {
        backPlace = (LinearLayout) findViewById(R.id.detail_outcome_tool_back);
        backView = findViewById(R.id.detail_outcome_tool_back_icon);
        backIcon = new IconicFontDrawable(this);
        backIcon.setIcon(EntypoIcon.REPLY);
        backIcon.setIconColor(Colors.color_pure_white);
        backView.setBackground(backIcon);
        backPlace.setOnClickListener(this);

        //main
        titleView = findViewById(R.id.detail_outcome_title_icon);
        placeView = findViewById(R.id.detail_outcome_place_icon);
        moneyView = findViewById(R.id.detail_outcome_money_icon);
        datetimeView = findViewById(R.id.detail_outcome_datetime_icon);

        title = (TextView) findViewById(R.id.detail_outcome_title_message);
        place = (TextView) findViewById(R.id.detail_outcome_place_message);
        money = (TextView) findViewById(R.id.detail_outcome_money_message);
        datetime = (TextView) findViewById(R.id.detail_outcome_datetime_message);
        description = (TextView) findViewById(R.id.detail_outcome_description_message);

        titleIcon = new IconicFontDrawable(this);
        titleIcon.setIcon(FontAwesomeIcon.REORDER);
        titleIcon.setIconColor(Colors.color_theme);
        titleView.setBackground(titleIcon);

        placeIcon = new IconicFontDrawable(this);
        placeIcon.setIcon(FontAwesomeIcon.SCREENSHOT);
        placeIcon.setIconColor(Colors.color_theme);
        placeView.setBackground(placeIcon);

        moneyIcon = new IconicFontDrawable(this);
        moneyIcon.setIcon(FontAwesomeIcon.STRIKETHROUGH);
        moneyIcon.setIconColor(Colors.color_theme);
        moneyView.setBackground(moneyIcon);

        datetimeIcon = new IconicFontDrawable(this);
        datetimeIcon.setIcon(EntypoIcon.CALENDAR);
        datetimeIcon.setIconColor(Colors.color_theme);
        datetimeView.setBackground(datetimeIcon);

        deleteButton = (Button) findViewById(R.id.detail_outcome_button_delete);
        modifyButton = (Button) findViewById(R.id.detail_outcome_button_modify);
        deleteButton.setOnClickListener(this);
        modifyButton.setOnClickListener(this);


        detailOutcomePresenter = new DetailOutcomePresenter(this,this);
    }

    @Override
    public Outcome getOutcome() {
        this.outcome = (Outcome) getIntent().getSerializableExtra("outcome");
        return this.outcome;
    }

    @Override
    public void displayOutcome() {
        title.setText(outcome.getTitle());
        place.setText(outcome.getPlace());
        money.setText(outcome.getMoney());
        datetime.setText(outcome.getDatetime());
        description.setText(outcome.getDescription());
    }

    @Override
    public void deleteOutcome() {
        deleteBuilder = new AlertDialog.Builder(this);
        deleteAlert = deleteBuilder.setTitle("提示")
                .setMessage("亲~~确定删除此支出")
                .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detailOutcomePresenter.deleteOutcome();
                        showTip(0);
                        toOutcomeListFragment();
                    }
                })
                .create();
        deleteAlert.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_outcome_tool_back:
                onBackPressed();
                finish();
                ActivityCollector.addActivity(this);
                break;
            case R.id.detail_outcome_button_delete:
                deleteOutcome();
                break;
            case R.id.detail_outcome_button_modify:
                toModifyOutcomeActivity();
                break;
            default:
                break;
        }
    }
    @Override
    public void toOutcomeListFragment() {
        DataUtil.saveSharedPreference(this, "menuItem", "outcome");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"删除此支出成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void toModifyOutcomeActivity() {
        Intent intent = new Intent(this,ModifyOutcomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("outcome",getOutcome());
        intent.putExtras(bundle);
        startActivity(intent);
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
