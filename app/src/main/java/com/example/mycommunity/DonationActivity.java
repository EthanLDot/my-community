package com.example.mycommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class DonationActivity extends AppCompatActivity {
    ImageButton newsArticleBtn;
    ImageButton volunteerBtn;
    ImageButton homeBtn;
    ImageButton donationBtn;
    ImageButton myTroopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        volunteerBtn = findViewById(R.id.volunteer_btn);
        homeBtn = findViewById(R.id.home_btn);
        donationBtn = findViewById(R.id.donation_btn);
        myTroopBtn = findViewById(R.id.my_troop_btn);
        newsArticleBtn = findViewById(R.id.new_article_btn);

        volunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationActivity.this, VolunteerActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationActivity.this, MainActivity.class));
            }
        });

        donationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationActivity.this, DonationActivity.class));
            }
        });

        myTroopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationActivity.this, MyTroopActivity.class));
            }
        });

        newsArticleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationActivity.this, NewsActivity.class));
            }
        });

    }
}