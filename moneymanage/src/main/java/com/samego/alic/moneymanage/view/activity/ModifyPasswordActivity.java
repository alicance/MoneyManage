package com.samego.alic.moneymanage.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.User;
import com.samego.alic.moneymanage.presenter.ModifyPasswordPresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.ModifyPasswordView;

/**
 *
 * Created by alic on 16-9-19.
 */
public class ModifyPasswordActivity extends AppCompatActivity implements ModifyPasswordView, View.OnClickListener {
    private EditText oldPassword,newPassword;
    private Button modifyBotton;
    private ModifyPasswordPresenter modifyPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_password);
        init();
    }

    void init(){
        oldPassword = (EditText) findViewById(R.id.old_password);
        newPassword = (EditText) findViewById(R.id.new_password);
        modifyBotton = (Button) findViewById(R.id.modify_password_button);
        modifyPasswordPresenter = new ModifyPasswordPresenter(this,this);
        modifyBotton.setOnClickListener(this);
    }

    @Override
    public User getUser() {
        User user = new User();
        user.setPassword(oldPassword.getText().toString().trim());
        user.setUsername(DataUtil.readSharedPreference(this,"username",null));
        return user;
    }

    @Override
    public String getNewPassword() {
        return newPassword.getText().toString().trim();
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"修改密码成功",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this,"原密码错误",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"原密码或新密码不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this,"原密码与新密码相同",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void toLogin() {
        Intent loginIntent = new Intent();
        loginIntent.setClass(ModifyPasswordActivity.this,LoginActivity.class);
        startActivity(loginIntent);
        ActivityCollector.addActivity(this);
        finish();
        //销毁所有的activity
        ActivityCollector.AppExit(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_password_button:
                if(oldPassword.getText().toString().equals("")||newPassword.getText().toString().equals("")){
                    showTip(2);
                }else if (oldPassword.getText().toString().equals(newPassword.getText().toString())){
                    showTip(3);
                }else {
                    modifyPasswordPresenter.modify();
                }
                break;
        }
    }
}
