package com.example.mycommunity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {

    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    Button signOutBtn;
    Button troopFinderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);
        signOutBtn = findViewById(R.id.sign_out_btn);


        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setTitle("Home Page");
        getSupportActionBar().setCustomView(R.layout.actionbar_center);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    }

    public void onTroopBtnClicked(View view) {
        Intent intent = new Intent(this, BoyScoutFinderActivity.class);
        startActivity(intent);
    }

    public void signOut (View view) {
            gsc.signOut()
                    .addOnCompleteListener(this, task -> {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    });
    }
}