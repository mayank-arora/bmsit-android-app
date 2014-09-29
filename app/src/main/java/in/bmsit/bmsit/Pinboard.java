package in.bmsit.bmsit;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;


public class Pinboard extends ActionBarActivity {
    ListView lv;
    String title[] ={"Internal Test Dates","Holidays in 2014-2015",
            "Second Internal Test Dates","Seminars And Events","Revaluation Results Announced",
            "Test Announcment One","Test Announcment Two","Test Announcment Three",
            "Test Announcment Four"};
    String body[]={"It is hereby informed to the students that the First Internal Tests dates" +
            " have been finalized and is attached to this announcement as an image.","The following" +
            " attached picture is the holiday schedule for the 2013-2015 session","It is hereby" +
            " informed to the students that the Second Internal Tests dates have been finalized" +
            " and is attached to this announcement as an image.","The following Talks and Events" +
            " are scheduled to be organized in our college by various organizations","Test" +
            " Announcement One","Test Announcement Two","Test Announcement Three","Test" +
            " Announcement Four"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinboard);
         lv= (ListView)findViewById(R.id.listView);
        ArrayList<Card> arrayList = new ArrayList<Card>();
        CustomAdapter customAdapter = new CustomAdapter(this,arrayList);
        arrayList.add(0,new Card("Internal Test Dates","It is hereby informed to the students" +
                " that the First Internal Tests dates" +
                " have been finalized and is attached to this announcement as an image."));
        arrayList.add(1,new Card("Holidays in 2014-2015","The following" +
                " attached picture is the holiday schedule for the 2013-2015 session"));
        arrayList.add(2,new Card("Second Internal Test Dates","It is hereby" +
                " informed to the students that the Second Internal Tests dates have been finalized" +
                " and is attached to this announcement as an image."));
        arrayList.add(3,new Card("Seminars And Events","The following Talks and Events" +
                " are scheduled to be organized in our college by various organizations"));
        arrayList.add(4,new Card("Revaluation Results Announced","It is hereby informed to the" +
                "students that the revaluation results have been announced and the attached link is" +
                "provided"));
        arrayList.add(4,new Card("Test Announcment One","Test Announcment One"));
        arrayList.add(5,new Card("Test Announcment Two","Test Announcment Two"));
        arrayList.add(6,new Card("Test Announcment Three","Test Announcment Three"));
        arrayList.add(7,new Card("Test Announcment Four","Test Announcment Four"));

        lv.setAdapter(customAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pinboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
