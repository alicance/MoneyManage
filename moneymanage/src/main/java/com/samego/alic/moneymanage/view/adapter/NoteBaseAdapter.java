package com.samego.alic.moneymanage.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.utils.DataUtil;

import java.util.List;

/**
 *
 * Created by alic on 16-9-21.
 */
public class NoteBaseAdapter extends BaseAdapter {
    private List<Note> notes;
    private Context context;

    public NoteBaseAdapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.note_item, null);
        Note note = notes.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.note_list_title);
        TextView datetime = (TextView) convertView.findViewById(R.id.note_list_datetime);
        View icon = convertView.findViewById(R.id.note_list_icon);

        title.setText(DataUtil.subString(note.getTitle(), 13, "..."));
        datetime.setText(note.getDatetime());
        AppUtil.LogMessage(note.getDatetime());
        //icon
        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
        iconicFontDrawable.setIcon(FontAwesomeIcon.TAG);
        iconicFontDrawable.setIconColor(R.color.color_gray_font);
        icon.setBackground(iconicFontDrawable);
        return convertView;
    }
}
