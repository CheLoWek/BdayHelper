package com.lowek.che.bdayhelper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.lowek.che.bdayhelper.R;
import com.lowek.che.bdayhelper.adapter.CardAdapter;


public class RecyclerviewFragment extends Fragment {
    public static final int LAYOUT = R.layout.fragment_recyclerview;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private FloatingActionButton fab;
    private View view;

    public RecyclerviewFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

        fab = (FloatingActionButton) view.findViewById(R.id.events_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fab clicked
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentFrame, new RecyclerviewFragment())
                        .addToBackStack(null)
                        .commit();
                getActivity().getSupportFragmentManager().executePendingTransactions();
            }
        });
        return view;
    }
}
