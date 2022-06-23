package com.example.we_play;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.we_play.Recyclerview.reserved_Adapter;
import com.example.we_play.Recyclerview.reserved_data;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

public class Reserved_page extends AppCompatActivity {
    private ArrayList<Dictionary> mArrayList;
    private reserved_Adapter mAdapter;
    private int count = -1;

    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserved);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_reserved_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new reserved_Adapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

    }
}
