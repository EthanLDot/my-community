package com.example.mycommunity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    Button signOutBtn;
    ImageButton newsArticleBtn;
    ImageButton volunteerBtn;
    ImageButton homeBtn;
    ImageButton donationBtn;
    ImageButton myTroopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boy_scout_finder);

        Button homePage = this.findViewById(R.id.home_btn);

        RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> data = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            data.add("bob" + i);
        }

        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(data);
        mRecyclerView.setAdapter(mAdapter);

        volunteerBtn = findViewById(R.id.volunteer_btn);
        homeBtn = findViewById(R.id.home_btn);
        donationBtn = findViewById(R.id.donation_btn);
        myTroopBtn = findViewById(R.id.my_troop_btn);
        newsArticleBtn = findViewById(R.id.new_article_btn);

        volunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, VolunteerActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MainActivity.class));
            }
        });

        donationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, DonationActivity.class));
            }
        });

        myTroopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, MyTroopActivity.class));
            }
        });

        newsArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsActivity.this, NewsActivity.class));
            }
        });

    }

    public void onBtnClicked(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }
}