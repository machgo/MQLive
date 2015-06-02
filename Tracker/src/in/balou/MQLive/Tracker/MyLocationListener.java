package in.balou.MQLive.Tracker;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;

/**
 * Created by marco on 18/05/15.
 */
public class MyLocationListener implements LocationListener
{
    @Override
    public void onLocationChanged(Location location)
    {
        Log.i("location Changed", location.toString());
        try
        {
            DataSender.getInstance().sendData(TrackPoint.getJson(location));

        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (MqttException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
        Log.d("location status:", String.valueOf(status));
    }

    @Override
    public void onProviderEnabled(String provider)
    {
        Log.d("location:", "provider enabled");
    }

    @Override
    public void onProviderDisabled(String provider)
    {
        Log.d("location:", "provider disabled");
    }
}
