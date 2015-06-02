package in.balou.MQLive.Tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;

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
        isOn = false;
        setContentView(layout.main);
    }

    private Intent backgroundService;
    private boolean isOn;


    public void buttonOn(View view)
    {
        if (!isOn)
        {
            Log.d("buttonOn", "starting");
            isOn = true;
            backgroundService = new Intent(this, TrackerService.class);
            startService(backgroundService);
        }
    }

    public void buttonOff(View view)
    {
        if (isOn)
        {
            Log.d("buttonOff", "stoping");
            isOn = false;
            stopService(backgroundService);
        }
    }
}
