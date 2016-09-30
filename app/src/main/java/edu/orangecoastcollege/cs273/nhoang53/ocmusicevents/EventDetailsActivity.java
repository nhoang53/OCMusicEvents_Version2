package edu.orangecoastcollege.cs273.nhoang53.ocmusicevents;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView eventTitleTextView;
    private TextView eventDateDateTextView;
    private TextView eventDayTextView;
    private TextView eventTimeTextView;
    private TextView eventLocationTextView;
    private TextView eventAddress1TextView;
    private TextView eventAddress2TextView;

    private ImageView eventImageView;
    private Context context = (Context) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        // Connect View and Controller
        eventImageView = (ImageView) findViewById(R.id.eventImageView);
        eventTitleTextView = (TextView) findViewById(R.id.eventTitleTextView);
        eventDateDateTextView = (TextView) findViewById(R.id.eventDateDayTextView);
        //eventDayTextView = (TextView) findViewById(R.id.eventDayTextView);
        eventTimeTextView = (TextView) findViewById(R.id.eventTimeTextView);
        eventLocationTextView = (TextView) findViewById(R.id.eventLocationTextView);
        eventAddress1TextView = (TextView) findViewById(R.id.eventAddress1TextView);
        eventAddress2TextView = (TextView) findViewById(R.id.eventAddress2TextView);


        // Get data from Intent
        Intent detailsIntent = getIntent();
        String title = detailsIntent.getStringExtra("Title");
        String date = detailsIntent.getStringExtra("Date");
        String day = detailsIntent.getStringExtra("Day");
        String time = detailsIntent.getStringExtra("Time");
        String location = detailsIntent.getStringExtra("Location");
        String address1 = detailsIntent.getStringExtra("Address1");
        String address2 = detailsIntent.getStringExtra("Address2");
        String imageFileName = title.replace(" ", "") + ".jpeg";

        // Load the image from the assets folder
        AssetManager am = context.getAssets();
        /// Try to load the image file
        try{
            InputStream stream = am.open(imageFileName);
            Drawable image = Drawable.createFromStream(stream, title);
            eventImageView.setImageDrawable(image);
        } catch (IOException ex){
            Log.e("OC Music events", "Cannot load image:" + imageFileName + ex);
        }

        // Load title and detail
        eventTitleTextView.setText(title);
        eventDateDateTextView.setText(date + " - " + day);
        //eventDayTextView.setText(day);
        eventTimeTextView.setText(time);
        eventLocationTextView.setText(location);
        eventAddress1TextView.setText(address1);
        eventAddress2TextView.setText(address2);
    }

}
