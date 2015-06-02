package in.balou.MQLive.Tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        setContentView(layout.main);

        Intent i = new Intent(this, TrackerService.class);
        try
        {
            DataSender.getInstance().connectTo(this, "tcp://188.166.105.27:1883", "TestClient");
            DataSender.getInstance().sendData("aaaaaaaa");
        } catch (MqttException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        startService(i);

    }
}
