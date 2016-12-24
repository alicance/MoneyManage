package com.samego.alic.moneymanage.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.presenter.AddNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.utils.DateUtil;
import com.samego.alic.moneymanage.view.view.AddNoteView;

import java.util.Date;

/**
 *  添加便签
 * Created by alic on 16-9-20.
 */
public class AddNoteActivity extends AppCompatActivity implements AddNoteView, View.OnClickListener {
    private View backView,addView;
    private IconicFontDrawable backIcon,addIcon;
    private EditText title,content;
    private int code;

    private AddNotePresenter addNotePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initUI();
    }

    public void initUI(){
        backView = findViewById(R.id.add_note_tool_back);
        addView = findViewById(R.id.add_note_tool_add);

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

        title = (EditText) findViewById(R.id.add_note_title);
        content = (EditText) findViewById(R.id.add_note_content);

        addNotePresenter = new AddNotePresenter(this,this);
    }

    @Override
    public String getNoteTitle() {
        return title.getText().toString().trim();
    }
    @Override
    public String getNoteContent() {
           return content.getText().toString().trim();
        }

    @Override
    public Note getNote() {
        Note note = new Note();
        note.setUsername(DataUtil.readSharedPreference(this, "username", null));
        note.setTitle(title.getText().toString().trim());
        note.setContent(content.getText().toString().trim());
        note.setDatetime(DateUtil.formatDate(new Date(System.currentTimeMillis()),DateUtil.yyyyMMdd));
        return note;
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"便签添加成功~~~",Toast.LENGTH_SHORT).show();
               break;
            case 1:
                Toast.makeText(this,"便签标题或内容不允许为空",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this,"位置错误Please Again",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void toBack() {
        onBackPressed();
    }

    @Override
    public void toHomeNoteFragment() {
        DataUtil.saveSharedPreference(this,"menuItem","note");
        Intent noteIntent = new Intent(this,MainActivity.class);
        startActivity(noteIntent);
        ActivityCollector.addActivity(this);
        finish();
        //销毁所有的activity
        ActivityCollector.AppExit(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_note_tool_add:
                addNotePresenter.addNote();
//                Toast.makeText(this,"ing",Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_note_tool_back:
                addNotePresenter.toBack();
                break;
        }
    }
}
