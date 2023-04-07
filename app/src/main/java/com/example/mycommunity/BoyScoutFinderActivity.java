package com.example.mycommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class BoyScoutFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy_scout_finder);

        Button homePage = this.findViewById(R.id.home_btn);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add("bob" + i);
        }

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onBtnClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}