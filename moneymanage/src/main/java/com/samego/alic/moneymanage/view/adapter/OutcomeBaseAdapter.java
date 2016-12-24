package com.samego.alic.moneymanage.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Outcome;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.utils.DataUtil;

import java.util.List;

/**
 *
 * Created by alic on 16-9-22.
 */
public class OutcomeBaseAdapter extends BaseAdapter {
    private List<Outcome> list;
    private Context context;

    public OutcomeBaseAdapter(List<Outcome> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.outcome_item, null);
        Outcome outcome = list.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.outcome_item_adapter_title);
        TextView money = (TextView) convertView.findViewById(R.id.outcome_item_adapter_money);
        TextView datetime = (TextView) convertView.findViewById(R.id.outcome_item_adapter_datetime);
        View icon = convertView.findViewById(R.id.outcome_item_adapter_icon);

        title.setText(DataUtil.subString(outcome.getTitle(), 6, "."));
        money.setText(String.valueOf("￥"+outcome.getMoney()));
        datetime.setText(outcome.getDatetime());

        AppUtil.LogMessage(outcome.getDatetime());
        //icon
        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
        iconicFontDrawable.setIcon(EntypoIcon.TAG);
        iconicFontDrawable.setIconColor(R.color.color_gray_font);
        icon.setBackground(iconicFontDrawable);
        return convertView;
    }
}
