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
import com.samego.alic.moneymanage.bean.Outcome;
import com.samego.alic.moneymanage.presenter.HomeOutcomePresenter;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.view.activity.AddOutcomeActivity;
import com.samego.alic.moneymanage.view.activity.DetailIncomeActivity;
import com.samego.alic.moneymanage.view.activity.DetailOutcomeActivity;
import com.samego.alic.moneymanage.view.adapter.OutcomeBaseAdapter;
import com.samego.alic.moneymanage.view.view.HomeOutcomeView;
import com.shamanland.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by alic on 16-9-15.
 */
public class HomeOutcomeFragment extends Fragment implements HomeOutcomeView, AdapterView.OnItemClickListener, View.OnClickListener {
    private View outcomeView;
    private List<Outcome> outcomes;
    private OutcomeBaseAdapter outcomeBaseAdapter;
    private ListView listView;
    private FloatingActionButton actionButton;

    private HomeOutcomePresenter homeOutcomePresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        outcomeView = inflater.inflate(R.layout.home_outcome,container,false);
        initUI();
        homeOutcomePresenter.display();
        return outcomeView;
    }

    void initUI(){
        listView = (ListView) outcomeView.findViewById(R.id.outcome_outcome_list);
        actionButton = (FloatingActionButton) outcomeView.findViewById(R.id.add_outcome);
        actionButton.setColor(Colors.color_theme);
        actionButton.setSize(FloatingActionButton.SIZE_MINI);
        actionButton.setOnClickListener(this);
        homeOutcomePresenter = new HomeOutcomePresenter(this,getContext());
    }

    @Override
    public void toAddOutcomeActivity() {
        Intent intent = new Intent(getContext(), AddOutcomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void displayOutcomeItems(List<Outcome> list) {
        this.outcomes = new ArrayList<>();
        this.outcomes = list;
        outcomeBaseAdapter = new OutcomeBaseAdapter(list,getContext());
        listView.setAdapter(outcomeBaseAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), DetailOutcomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("outcome",outcomes.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_outcome:
                homeOutcomePresenter.toAddOutcomeActivity();
                break;
            default:
                break;
        }
    }
}
