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
import com.samego.alic.moneymanage.presenter.ModifyNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.ModifyNoteView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class ModifyNoteActivity extends AppCompatActivity implements ModifyNoteView, View.OnClickListener {
    private Note note;
    private EditText title,content;
    private View backView,enterView;
    private IconicFontDrawable backIcon,enterIcon;

    private ModifyNotePresenter modifyNotePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_note);
        initUI();
    }

    @Override
    public void initUI() {
        title = (EditText) findViewById(R.id.modify_note_title);
        content = (EditText) findViewById(R.id.modify_note_content);

        backView = findViewById(R.id.modify_note_tool_back);
        enterView = findViewById(R.id.modify_note_tool_add);

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


        modifyNotePresenter = new ModifyNotePresenter(this,this);
        modifyNotePresenter.display();
    }

    @Override
    public void getNote() {
        note = (Note) getIntent().getSerializableExtra("note");
    }

    @Override
    public void displayMessage() {
        title.setText(note.getTitle());
        content.setText(note.getContent());
    }

    @Override
    public Note getAfterNote() {
        note.setTitle(title.getText().toString().trim());
        note.setContent(content.getText().toString().trim());
        return note;
    }

    @Override
    public void showTip(int code) {
        switch (code){
            case 0:
                Toast.makeText(this,"修改便签成功",Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(this,"便签的标题或内容不允许为空",Toast.LENGTH_LONG).show();
                break;
        }
    }
    @Override
    public void toNoteListFragment() {
        DataUtil.saveSharedPreference(this, "menuItem", "note");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
        //销毁所有的activity
        ActivityCollector.AppExit(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_note_tool_back:
                onBackPressed();
                break;
            case R.id.modify_note_tool_add:
                if (!title.getText().toString().trim().equals("") && !content.getText().toString().trim().equals("")){
                    modifyNotePresenter.modifyNote();
                }else {
                    showTip(1);
                }
                break;
            default:
                break;
        }
    }
}
