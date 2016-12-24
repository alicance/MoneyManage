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
import android.widget.Toast;

import com.samego.alic.moneymanage.R;
import com.samego.alic.moneymanage.bean.Note;
import com.samego.alic.moneymanage.presenter.HomeNotePresenter;
import com.samego.alic.moneymanage.utils.ActivityCollector;
import com.samego.alic.moneymanage.utils.Colors;
import com.samego.alic.moneymanage.view.activity.AddNoteActivity;
import com.samego.alic.moneymanage.view.activity.DetailNoteActivity;
import com.samego.alic.moneymanage.view.adapter.NoteBaseAdapter;
import com.samego.alic.moneymanage.view.view.HomeNoteView;
import com.shamanland.fab.FloatingActionButton;

import java.util.List;

/**
 *
 * Created by alic on 16-9-15.
 */
public class HomeNoteFragment extends Fragment implements HomeNoteView, View.OnClickListener, AdapterView.OnItemClickListener {
    private View noteView;
    private FloatingActionButton actionButton;
    private HomeNotePresenter homeNotePresenter;

    private ListView listView;
    private List<Note> notes;
    private NoteBaseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        noteView = inflater.inflate(R.layout.home_note,container,false);
        initUI();
        return noteView;
    }

    public void initUI(){
        actionButton = (FloatingActionButton) noteView.findViewById(R.id.add_note);
        actionButton.setColor(Colors.color_theme);
        actionButton.setSize(FloatingActionButton.SIZE_MINI);
        actionButton.setOnClickListener(this);

        homeNotePresenter = new HomeNotePresenter(noteView.getContext(),this);

        listView = (ListView) noteView.findViewById(R.id.note_notes_list);
        homeNotePresenter.display();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_note:
//                Toast.makeText(noteView.getContext(),"add note",Toast.LENGTH_SHORT).show();
                homeNotePresenter.toAddNoteActivity();
                break;
        }
    }

    @Override
    public void toAddActivity() {
        Intent addIntent = new Intent(noteView.getContext(), AddNoteActivity.class);
        startActivity(addIntent);
        ActivityCollector.addActivity(getActivity());
    }

    @Override
    public void displayNoted(List<Note> notes) {
        this.notes = notes;
        adapter = new NoteBaseAdapter(notes,getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(),position+"",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getContext(), DetailNoteActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note",notes.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
        ActivityCollector.addActivity(getActivity());
//        getActivity().finish();
    }
}
