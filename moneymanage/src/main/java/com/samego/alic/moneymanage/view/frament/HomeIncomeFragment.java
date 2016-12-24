package com.samego.alic.moneymanage.view.frament;


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
import com.samego.alic.moneymanage.bean.Income;
import com.samego.alic.moneymanage.presenter.HomeIncomePresenter;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.view.activity.AddIncomeActivity;
import com.samego.alic.moneymanage.view.activity.DetailIncomeActivity;
import com.samego.alic.moneymanage.view.adapter.IncomeBaseAdapter;
import com.samego.alic.moneymanage.view.view.HomeIncomeView;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by alic on 16-9-15.
 */
public class HomeIncomeFragment extends Fragment implements HomeIncomeView, View.OnClickListener, AdapterView.OnItemClickListener {
    private View incomeView;
    private ListView listView;
    private FloatingActionButton actionButton;
    private IncomeBaseAdapter incomeBaseAdapter;

    private List<Income> incomes;
    private HomeIncomePresenter homeIncomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        incomeView = inflater.inflate(R.layout.home_income,container,false);
        initUI();
        homeIncomePresenter.display();
        return incomeView;
    }

    void initUI(){
        listView = (ListView) incomeView.findViewById(R.id.income_income_list);
        actionButton = (FloatingActionButton) incomeView.findViewById(R.id.add_income);
        actionButton.setColor(Colors.color_theme);
        actionButton.setSize(FloatingActionButton.SIZE_MINI);
        actionButton.setOnClickListener(this);
        homeIncomePresenter = new HomeIncomePresenter(this,getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_income:
                homeIncomePresenter.toAddIncomeActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public void toAddIncomeActivity() {
        Intent intent = new Intent(getContext(), AddIncomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayIncomeItems(List<Income> list) {
        this.incomes = new ArrayList<>();
        this.incomes = list;
        incomeBaseAdapter = new IncomeBaseAdapter(list,getContext());
        listView.setAdapter(incomeBaseAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), DetailIncomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("income",incomes.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
