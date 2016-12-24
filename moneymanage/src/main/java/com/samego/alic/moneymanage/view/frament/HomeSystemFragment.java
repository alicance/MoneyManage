package com.samego.alic.moneymanage.view.frament;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.presenter.HomeSystemPresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.view.activity.LoginActivity;
import com.samego.alic.moneymanage.view.activity.ModifyPasswordActivity;
import com.samego.alic.moneymanage.view.adapter.SystemBaseAdapter;
import com.samego.alic.moneymanage.view.view.HomeSystemView;

/**
 *
 * Created by alic on 16-9-15.
 */
public class HomeSystemFragment extends Fragment implements HomeSystemView, AdapterView.OnItemClickListener {
    private View systemView;
    private ListView listView;
    private AlertDialog logoutAlert;
    private AlertDialog.Builder logoutBuilder;
    private SystemBaseAdapter adapter;

    private HomeSystemPresenter homeSystemPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        systemView = inflater.inflate(R.layout.home_system,container,false);
        initUI();
        return systemView;
    }
    void initUI(){
        listView = (ListView) systemView.findViewById(R.id.setting_items);
        listView.setFooterDividersEnabled(true);
        adapter = new SystemBaseAdapter(systemView.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        homeSystemPresenter = new HomeSystemPresenter(systemView.getContext(),this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (adapter.getSelectedItemName(position)) {
            case "修改密码":
                homeSystemPresenter.modifyPassword();
                break;
            case "切换账号":
                homeSystemPresenter.changeUser();
            break;
            case "退出程序":
                homeSystemPresenter.logout();
                break;
        }
    }


    //退出程序
    @Override
    public void logout() {
        logoutBuilder = new AlertDialog.Builder(systemView.getContext());
        logoutAlert = logoutBuilder.setTitle("提示")
                .setMessage("亲~~确定退出理财助手")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCollector.AppExit(systemView.getContext());
                        getActivity().finish();
                    }
                })
                .create();
        logoutAlert.show();
    }

    @Override
    public void modifyPassword() {
        Intent remindIntent = new Intent(systemView.getContext(), ModifyPasswordActivity.class);
        startActivity(remindIntent);
        ActivityCollector.addActivity(getActivity());
    }

    @Override
    public void changeUser() {
        Intent loginIntent = new Intent(systemView.getContext(), LoginActivity.class);
        startActivity(loginIntent);
        ActivityCollector.addActivity(getActivity());
    }
}
