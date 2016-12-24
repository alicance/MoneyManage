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
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.presenter.DetailNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;
import com.samego.alic.moneymanage.view.view.DetailNoteView;

/**
 *
 * Created by alic on 16-9-21.
 */
public class DetailNoteActivity extends AppCompatActivity implements DetailNoteView, View.OnClickListener {
    private LinearLayout backDir;
    private View backView,datetimeView,titleView;
    private IconicFontDrawable backIcon,datetimeIcon,titleIcon;
    private TextView title,datetime,content;
    private Button deleteButton,modifyButton;
    private AlertDialog deleteAlert;
    private AlertDialog.Builder deleteBuilder;

    private Note note;
    private DetailNotePresenter detailNotePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        initUI();
        initIcon();
        displayMessage();
    }

    @Override
    public void initUI() {
        backDir = (LinearLayout) findViewById(R.id.detail_note_tool_back);
        assert backDir != null;
        backDir.setOnClickListener(this);
        backView = findViewById(R.id.detail_note_tool_back_icon);

        datetimeView = findViewById(R.id.detail_note_datetime_icon);
        titleView = findViewById(R.id.detail_note_title_icon);

        title = (TextView) findViewById(R.id.detail_note_title_message);
        datetime = (TextView) findViewById(R.id.detail_note_datetime_message);
        content = (TextView) findViewById(R.id.detail_note_content_message);

        deleteButton = (Button) findViewById(R.id.detail_note_button_delete);
        modifyButton = (Button) findViewById(R.id.detail_note_button_modify);
        deleteButton.setOnClickListener(this);
        modifyButton.setOnClickListener(this);

        detailNotePresenter = new DetailNotePresenter(this,this);
    }

    @Override
    public void initIcon() {
        backIcon = new IconicFontDrawable(this);
        backIcon.setIcon(EntypoIcon.REPLY);
        backIcon.setIconColor(Colors.color_pure_white);
        backView.setBackground(backIcon);

        datetimeIcon = new IconicFontDrawable(this);
        datetimeIcon.setIcon(EntypoIcon.CALENDAR);
        datetimeIcon.setIconColor(Colors.color_theme);
        datetimeView.setBackground(datetimeIcon);

        titleIcon = new IconicFontDrawable(this);
        titleIcon.setIcon(FontAwesomeIcon.TEXT_HEIGHT);
        titleIcon.setIconColor(Colors.color_theme);
        titleView.setBackground(titleIcon);
    }

    @Override
    public void displayMessage() {
        note = (Note) getIntent().getSerializableExtra("note");
            title.setText(note.getTitle());
            datetime.setText(note.getDatetime());
            content.setText(note.getContent());
    }

    @Override
    public void deleteNote() {
        deleteBuilder = new AlertDialog.Builder(DetailNoteActivity.this);
        deleteAlert = deleteBuilder.setTitle("提示")
                .setMessage("亲~~确定删除此便签")
                .setNegativeButton("不要", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        detailNotePresenter.deleteNote();
                    }
                })
                .create();
        deleteAlert.show();
    }

    @Override
    public void modifyNote() {

    }

    @Override
    public void toNoteListFragment() {
        DataUtil.saveSharedPreference(this,"menuItem","note");
        Intent intent = new Intent(DetailNoteActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        ActivityCollector.addActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.detail_note_tool_back:
                onBackPressed();
                break;
            case R.id.detail_note_button_delete:
                this.deleteNote();
                break;
            case R.id.detail_note_button_modify:
                detailNotePresenter.toModifyActivity();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public int getId(){
        AppUtil.LogMessage("--------------------"+note.getId());
        return note.getId();
    }

    @Override
    public void showTip() {
        Toast.makeText(this,"删除便签成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toModifyActivity() {
        Intent intent = new Intent(DetailNoteActivity.this,ModifyNoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note",note);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
