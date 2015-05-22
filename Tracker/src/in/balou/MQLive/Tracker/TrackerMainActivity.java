package in.balou.MQLive.Tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static in.balou.MQLive.Tracker.R.*;

public class TrackerMainActivity extends Activity
{
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(layout.main);

        Intent i = new Intent(this, TrackerService.class);
        startService(i);
    }
}
