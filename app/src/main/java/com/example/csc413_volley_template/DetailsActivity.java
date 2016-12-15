package com.example.csc413_volley_template;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    TextView eventTitle;
    TextView groupTitle;
    TextView cityView;
    TextView membersNumber;
    TextView description;
    ImageView imageView;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        eventTitle = (TextView) findViewById(R.id.event_title);
        groupTitle = (TextView) findViewById(R.id.group_title);
        cityView = (TextView) findViewById(R.id.city_view);
        membersNumber = (TextView) findViewById(R.id.members_number);
        description = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String host = intent.getStringExtra("host");
        String city = intent.getStringExtra("city");
        String members = intent.getStringExtra("members");
        String descr = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");



        eventTitle.setText(title);
        groupTitle.setText(host);
        cityView.setText(city);
        membersNumber.setText(members);
        description.setText(descr);
        try {
            Picasso.with(getBaseContext()).load(imageUrl.replace(".png", ".jpeg")).into(imageView);
        }
        catch(Exception e){

        }


    }
}
