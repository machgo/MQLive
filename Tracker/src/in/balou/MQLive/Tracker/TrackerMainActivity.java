package in.balou.MQLive.Tracker;

import android.app.Activity;
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
    }
}
