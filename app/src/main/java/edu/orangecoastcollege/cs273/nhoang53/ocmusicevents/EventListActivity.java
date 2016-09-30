package edu.orangecoastcollege.cs273.nhoang53.ocmusicevents;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;


public class EventListActivity extends ListActivity { // must extends ListActivity

    private ListView eventsListView;
    private Context context = this;
    private ArrayList<MusicEvent> allMusicEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        eventsListView = (ListView) findViewById(R.id.eventsListView);

        // Set the adapter (which binds the ListView with the data in MusicEvent.java)
        // SInce the data is in an array, we use an ArrayAdapter:

        try{
            allMusicEvents = JSONLoader.loadJSONFromAsset(context);
        } catch (IOException ex){
            Log.e("OC Music Events", "Error loading JSON data." + ex.getMessage());
        }

        // add Custom Adapter to the ListAdapter
        setListAdapter(new MusicEventAdapter(context,
                R.layout.music_event_list_item_layout, allMusicEvents));

        // program will crash
        //setContentView(R.layout.activity_event_list);
    }

    // hold Ctrl and mouse hover ListActivity to see the functions of ListActivity class
    @Override
    protected void onListItemClick(ListView l, View v, int pos, long id)
    {
        Intent detailsIntent = new Intent(this, EventDetailsActivity.class);
        MusicEvent clickedEvent = allMusicEvents.get(pos);

        detailsIntent.putExtra("Title", clickedEvent.getTitle());
        detailsIntent.putExtra("Date", clickedEvent.getDate());
        detailsIntent.putExtra("Day", clickedEvent.getDay());
        detailsIntent.putExtra("Time", clickedEvent.getTime());
        detailsIntent.putExtra("Location", clickedEvent.getLocation());
        detailsIntent.putExtra("Address1", clickedEvent.getAddress1());
        detailsIntent.putExtra("Address2", clickedEvent.getAddress2());

        startActivity(detailsIntent);
    }
}
