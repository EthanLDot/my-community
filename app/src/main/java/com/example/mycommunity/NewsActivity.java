package com.example.mycommunity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsActivity extends AppCompatActivity {
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    Button signOutBtn;
    ImageButton newsArticleBtn;
    ImageButton volunteerBtn;
    ImageButton homeBtn;
    ImageButton myTroopBtn;

    TextView rTitle, rSubtitle;
    ImageView rImage;

    String title, subtitle, image;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        ((TextView)getSupportActionBar().getCustomView().findViewById(R.id.tvTitle)).setText("News");


//        ArrayList<String> titles = new ArrayList<>();
//        ArrayList<String> subtitles = new ArrayList<>();
//        ArrayList<String> images = new ArrayList<>();

        TextView title1 = findViewById(R.id.title1);
        TextView title2 = findViewById(R.id.title2);
        TextView title3 = findViewById(R.id.title3);
        TextView title4 = findViewById(R.id.title4);

        TextView subtitle1 = findViewById(R.id.description1);
        TextView subtitle2 = findViewById(R.id.description2);
        TextView subtitle3 = findViewById(R.id.description3);
        TextView subtitle4 = findViewById(R.id.description4);

        title1.setText("Putin’s Beast That Would Now Devour Him");
        title2.setText("Pope Offers Prayers for ‘Vatican Girl’ Who Disappeared 40 Years Ago");
        title3.setText("Hollywood Tycoon Testifies in Netanyahu’s Corruption Trial");
        title4.setText("Guatemala Picks a New President: What You Need to Know");

        subtitle1.setText("Yevgeny V. Prigozhin, the founder of the paramilitary Wagner Group, has been driven to fury by a mismanaged war in Ukraine. He turned on his creator, before apparently reversing course.");
        subtitle2.setText("Francis expressed “closeness” to the family of Emanuela Orlandi, whose disappearance has captivated Italians for decades — and now the world, thanks to Netflix.");
        subtitle3.setText("Arnon Milchan, a movie mogul, billionaire spy and old friend of Prime Minister Benjamin Netanyahu of Israel, is a key witness. He gave the Netanyahus gifts, he said, sometimes at their request.");
        subtitle4.setText("The election in the Central American nation is marked by the exclusion of top candidates and calls to crack down on violent crime.");

        Glide.with(NewsActivity.this).load("https://static01.nyt.com/images/2023/06/25/multimedia/25russia-wagner-01-wqvp/25russia-wagner-01-wqvp-superJumbo.jpg").centerCrop().into((ImageView) findViewById(R.id.image1));
        Glide.with(NewsActivity.this).load("https://static01.nyt.com/images/2023/06/25/multimedia/25vatican-investigate-lede/hfcv-superJumbo.jpg").centerCrop().into((ImageView) findViewById(R.id.image2));
        Glide.with(NewsActivity.this).load("https://static01.nyt.com/images/2023/06/25/multimedia/25israel-milchan-01-gzvf/25israel-milchan-01-gzvf-superJumbo.jpg").centerCrop().into((ImageView) findViewById(R.id.image3));
        Glide.with(NewsActivity.this).load("https://static01.nyt.com/images/2023/06/25/world/25guatemala-election/25guatemala-election-superJumbo.jpg").centerCrop().into((ImageView) findViewById(R.id.image4));

//        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(NewsActivity.this));
//        mRecyclerView.setHasFixedSize(true);
//
//        List<News> data = new ArrayList<>();
//        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(NewsActivity.this, data);
//        mRecyclerView.setAdapter(recyclerViewAdapter);

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.nytimes.com/svc/topstories/v2/world.json?api-key=YvJKaX77YUAGLwkBlGlqKApoBWbWULPH";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    JsonObject jsonObject = new JsonParser().parse(myResponse).getAsJsonObject();

                    JsonArray results = jsonObject.getAsJsonArray("results");

                    for (int i = 0; i < results.size(); i++) {
                        JsonElement jTitle = results.get(i).getAsJsonObject().get("title");
                        JsonElement jSubtitle = results.get(i).getAsJsonObject().get("abstract");
                        JsonElement multimedia = null;
                        JsonElement url = null;

                        if (jTitle == null) {
                            title = "No title available";
                        } else {
                            title = jTitle.toString();
                        }

                        if (jSubtitle == null) {
                            subtitle = "No subtitle available";
                        } else {
                            subtitle = jSubtitle.toString();
                        }

                        if ((results.get(i).getAsJsonObject().get("multimedia") != null) && (!(results.get(i).getAsJsonObject().get("multimedia").toString().equals("null")))) {
                            multimedia = results.get(i).getAsJsonObject().get("multimedia").getAsJsonArray().get(0).getAsJsonObject().get("url");
                            image = multimedia.toString();
                        } else {
                            image = "No image available";
                        }

//                        data.add(new News(title, subtitle, image));
                    }

                    NewsActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        }
                    });
                }

            }
        });

        volunteerBtn = findViewById(R.id.volunteer_btn);
        homeBtn = findViewById(R.id.home_btn);
        myTroopBtn = findViewById(R.id.my_troop_btn);
        newsArticleBtn = findViewById(R.id.news_article_btn);

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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}