package com.example.csc413_volley_template;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    TextView eventTitle;
    TextView groupTitle;
    TextView cityView;
    TextView groupLat;
    TextView groupLong;
    TextView membersNumber;
    TextView description;
    ImageView imageView;
    Button btnShowLocation;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        eventTitle = (TextView) findViewById(R.id.event_title);
        groupTitle = (TextView) findViewById(R.id.group_title);
        cityView = (TextView) findViewById(R.id.city_view);
        groupLat = (TextView) findViewById(R.id.group_lat);
        groupLong = (TextView) findViewById(R.id.group_long);
        membersNumber = (TextView) findViewById(R.id.members_number);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                GPSTracker gps = new GPSTracker(DetailsActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double lat = gps.getLatitude();
                    double lon = gps.getLongitude();


                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + lat + "\nLong: " + lon
                            , Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });

        description = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String host = intent.getStringExtra("host");
        String city = intent.getStringExtra("city");
        String members = intent.getStringExtra("members");
        String latitude = intent.getStringExtra("latitude");
        String longitude = intent.getStringExtra("longitude");
        String descr = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("imageUrl");



        eventTitle.setText(title);
        groupTitle.setText(host);
        cityView.setText(city);
        membersNumber.setText(members);
        groupLat.setText(latitude);
        groupLong.setText(longitude);
        description.setText(descr);
        try {
            Picasso.with(getBaseContext()).load(imageUrl.replace(".png", ".jpeg")).into(imageView);
        }
        catch(Exception e){

        }


    }
}
