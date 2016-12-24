package com.samego.alic.moneymanage.presenter;

import android.content.Context;

import com.samego.alic.moneymanage.module.UserModule;
import com.samego.alic.moneymanage.module.UserModuleImpl;
import com.samego.alic.moneymanage.module.listener.OnModifyPSDListener;
import com.samego.alic.moneymanage.view.view.ModifyPasswordView;

/**
 *
 * Created by alic on 16-9-20.
 */
public class ModifyPasswordPresenter {
    private UserModule userModule;
    private ModifyPasswordView modifyPasswordView;
    private Context context;

    public ModifyPasswordPresenter(Context context, ModifyPasswordView modifyPasswordView) {
        this.context = context;
        this.modifyPasswordView = modifyPasswordView;
        userModule = new UserModuleImpl();
    }

    public void modify(){
        userModule.modifyPassword(context, modifyPasswordView.getUser(), modifyPasswordView.getNewPassword(), new OnModifyPSDListener() {
            @Override
            public void success() {
                modifyPasswordView.showTip(0);
                modifyPasswordView.toLogin();
            }

            @Override
            public void failed() {
                modifyPasswordView.showTip(1);
            }
        });
    }
}
