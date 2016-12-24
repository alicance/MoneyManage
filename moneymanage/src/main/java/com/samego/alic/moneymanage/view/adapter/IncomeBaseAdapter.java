package com.samego.alic.moneymanage.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.utils.AppUtil;
import com.samego.alic.moneymanage.utils.DataUtil;

import java.util.List;

/**
 * 收入显示适配器
 * Created by alic on 16-9-22.
 */
public class IncomeBaseAdapter extends BaseAdapter {
    private List<Income> incomes;
    private Context context;

    public IncomeBaseAdapter(List<Income> incomes, Context context) {
        this.incomes = incomes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return incomes.size();
    }

    @Override
    public Object getItem(int position) {
        return incomes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = LayoutInflater.from(context).inflate(R.layout.income_item, null);
        Income income = incomes.get(position);

        TextView title = (TextView) convertView.findViewById(R.id.income_item_adapter_title);
        TextView money = (TextView) convertView.findViewById(R.id.income_item_adapter_money);
        TextView datetime = (TextView) convertView.findViewById(R.id.income_item_adapter_datetime);
        View icon = convertView.findViewById(R.id.income_item_adapter_icon);

        title.setText(DataUtil.subString(income.getTitle(), 6, "."));
        money.setText(String.valueOf("￥"+income.getMoney()));
        datetime.setText(income.getDatetime());

        AppUtil.LogMessage(income.getDatetime());
        //icon
        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);
        iconicFontDrawable.setIcon(EntypoIcon.TAG);
        iconicFontDrawable.setIconColor(R.color.color_gray_font);
        icon.setBackground(iconicFontDrawable);
        return convertView;
    }
}
