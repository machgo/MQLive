package in.balou.MQLive.Tracker;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by marco on 18/05/15.
 */
public class MyLocationListener implements LocationListener
{
    @Override
    public void onLocationChanged(Location location)
    {
        Log.i("location Changed", location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {
    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }
}
