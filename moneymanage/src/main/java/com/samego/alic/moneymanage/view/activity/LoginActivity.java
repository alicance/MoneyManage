package com.samego.alic.moneymanage.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.User;
import com.samego.alic.moneymanage.presenter.LoginPresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.utils.NetWorkUtils;
import com.samego.alic.moneymanage.view.view.LoginView;

/**
 *
 * Created by alic on 16-9-13.
 */
public class LoginActivity extends Activity implements LoginView, View.OnClickListener {
    private EditText username, password;
    private View usernameIconView, passwordIconView;
    private Button loginButton;
    private IconicFontDrawable usernameIcon, passwordIcon;
    private ProgressBar progressBar;
    private int failedCode = 0; //0-用户民或密码不允许为空 1-无网络

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化UI组件
        initUI();
    }

    public void initUI() {
        //
        username = (EditText) findViewById(R.id.login_idCard);
        password = (EditText) findViewById(R.id.login_password);

        //test
        username.setText("alic");
//        password.setText("admin");

        //
        usernameIconView = findViewById(R.id.login_username_icon);
        passwordIconView = findViewById(R.id.login_password_icon);

        usernameIcon = new IconicFontDrawable(this);
        usernameIcon.setIcon(EntypoIcon.USER);
        usernameIcon.setIconColor(Colors.color_theme);

        passwordIcon = new IconicFontDrawable(this);
        passwordIcon.setIcon(EntypoIcon.LOCK);
        passwordIcon.setIconColor(Colors.color_theme);

        usernameIconView.setBackground(usernameIcon);
        passwordIconView.setBackground(passwordIcon);

        //
        progressBar = (ProgressBar) findViewById(R.id.login_progressbar);

        //
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(this);

        loginPresenter = new LoginPresenter(this, this);
        loginPresenter.defaultUser();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                //检测用户名
                if (getUsername().trim().length() != 0 && getPassword().trim().length() != 0) {
                    loginPresenter.login();
                } else {
                    Toast.makeText(this, "账号或密码不允许为空", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public String getUsername() {
        return username.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return password.getText().toString().trim();
    }

    @Override
    public void saveUserMessage() {
        DataUtil.saveSharedPreference(this, "username", getUsername());
    }

    @Override
    public void readUserMessage() {
        if (DataUtil.readSharedPreference(this,"username",null)!=null){
            username.setText(DataUtil.readSharedPreference(this,"username",null));
        }
    }

    @Override
    public void displayFace() {

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public void toMainActivity() {
        Intent mainIntent = new Intent();
        mainIntent.setClass(this, MainActivity.class);
        startActivity(mainIntent);
        ActivityCollector.addActivity(this);
        finish();
    }

    @Override
    public void showFailed() {
        switch (failedCode) {
            case 0:
                Toast.makeText(LoginActivity.this, "用户民或密码不允许为空", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(LoginActivity.this, "当前网络不可用", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(LoginActivity.this, "用户民或密码错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void setFailedCode(int code) {
        this.failedCode = code;
    }

    @Override
    public void onBackPressed() {
        ActivityCollector.AppExit(this);
        finish();
    }
}
