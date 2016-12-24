package com.samego.alic.moneymanage.view.activity;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.atermenji.android.iconicdroid.icon.IconicIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.adapter.HomeFragmentAdapter;
import com.samego.alic.moneymanage.view.view.MainView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener,ViewPager.OnPageChangeListener  {
    private LinearLayout item_income,item_outcome,item_note,item_system;
    private TextView title_income,title_outcome,title_note,title_system;
    private View view_income,view_outcome,view_note,view_system;
    private IconicFontDrawable icon_income,icon_outcome,icon_note,icon_system;
    private ViewPager viewPager;
    private HomeFragmentAdapter adapter;
    private FragmentManager fragmentManager;
    private String menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initIcon();
        initHandler();
        defaultMenuItem();
    }

    @Override
    public void initUI(){
        item_income = (LinearLayout) findViewById(R.id.home_menu_item_income);
        item_outcome= (LinearLayout) findViewById(R.id.home_menu_item_outcome);
        item_note = (LinearLayout) findViewById(R.id.home_menu_item_note);
        item_system = (LinearLayout) findViewById(R.id.home_menu_item_system);

        item_income.setOnClickListener(this);
        item_outcome.setOnClickListener(this);
        item_note.setOnClickListener(this);
        item_system.setOnClickListener(this);

        title_income = (TextView) findViewById(R.id.home_menu_item_income_title);
        title_outcome = (TextView) findViewById(R.id.home_menu_item_outcome_title);
        title_note = (TextView) findViewById(R.id.home_menu_item_note_title);
        title_system = (TextView) findViewById(R.id.home_menu_item_system_title);

        view_income = findViewById(R.id.home_menu_item_income_icon);
        view_outcome = findViewById(R.id.home_menu_item_outcome_icon);
        view_note = findViewById(R.id.home_menu_item_note_icon);
        view_system = findViewById(R.id.home_menu_item_system_icon);

        viewPager = (ViewPager) findViewById(R.id.home_main_viewPager);
    }

    @Override
    public void initIcon(){
        icon_income = new IconicFontDrawable(this);
        icon_income .setIcon(EntypoIcon.DOWNLOAD);
        icon_income.setIconColor(Colors.color_theme);

        icon_outcome = new IconicFontDrawable(this);
        icon_outcome .setIcon(EntypoIcon.UPLOAD);
        icon_outcome.setIconColor(Colors.color_theme);

        icon_note = new IconicFontDrawable(this);
        icon_note .setIcon(FontAwesomeIcon.EDIT);
        icon_note.setIconColor(Colors.color_theme);

        icon_system = new IconicFontDrawable(this);
        icon_system .setIcon(EntypoIcon.TOOLS);
        icon_system.setIconColor(Colors.color_theme);

        view_income.setBackground(icon_income);
        view_outcome.setBackground(icon_outcome);
        view_note.setBackground(icon_note);
        view_system.setBackground(icon_system);
    }

    void initHandler(){
        fragmentManager = getSupportFragmentManager();
        adapter = new HomeFragmentAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_menu_item_income:
                incomeHandler();
                break;
            case R.id.home_menu_item_outcome:
                outcomeHandler();
                break;
            case R.id.home_menu_item_note:
                noteHandler();
                break;
            case R.id.home_menu_item_system:
                systemHandler();
                break;
        }
    }

    void incomeHandler(){
        icon_income.setIconColor(Colors.color_theme);
        title_income.setSelected(true);
        icon_outcome.setIconColor(Color.GRAY);
        title_outcome.setSelected(false);
        icon_note.setIconColor(Color.GRAY);
        title_note.setSelected(false);
        icon_system.setIconColor(Color.GRAY);
        title_system.setSelected(false);

        viewPager.setCurrentItem(0, false);
    }
    void outcomeHandler(){
        icon_outcome.setIconColor(Colors.color_theme);
        title_outcome.setSelected(true);
        icon_income.setIconColor(Color.GRAY);
        title_income.setSelected(false);
        icon_note.setIconColor(Color.GRAY);
        title_note.setSelected(false);
        icon_system.setIconColor(Color.GRAY);
        title_system.setSelected(false);

        viewPager.setCurrentItem(1, false);
    }
    void noteHandler(){
        icon_note.setIconColor(Colors.color_theme);
        title_note.setSelected(true);
        icon_income.setIconColor(Color.GRAY);
        title_income.setSelected(false);
        icon_outcome.setIconColor(Color.GRAY);
        title_outcome.setSelected(false);
        icon_system.setIconColor(Color.GRAY);
        title_system.setSelected(false);

        viewPager.setCurrentItem(2, false);
    }
    void systemHandler(){
        icon_system.setIconColor(Colors.color_theme);
        title_system.setSelected(true);
        icon_income.setIconColor(Color.GRAY);
        title_income.setSelected(false);
        icon_outcome.setIconColor(Color.GRAY);
        title_outcome.setSelected(false);
        icon_note.setIconColor(Color.GRAY);
        title_note.setSelected(false);

        viewPager.setCurrentItem(3, false);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                incomeHandler();
                break;
            case 1:
                outcomeHandler();
                break;
            case 2:
                noteHandler();
                break;
            case 3:
                systemHandler();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void defaultMenuItem(){
        menuItem = DataUtil.readSharedPreference(this,"menuItem","income");
        if(menuItem.equals("income")){
            incomeHandler();
        }else if (menuItem.equals("outcome")){
            outcomeHandler();
        }
        else if (menuItem.equals("note")){
            noteHandler();
        }
        else if (menuItem.equals("system")){
            systemHandler();
        }
    }
}
