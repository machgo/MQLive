package in.balou.MQLive.Tracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.IBinder;

/**
 * Created by marco on 18/05/15.
 */
public class TrackerService extends Service
{
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        MyLocationListener listener = new MyLocationListener();

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 8000, 0, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, listener);


        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        MySensorListener sensorListener = new MySensorListener();
        sensorManager.registerListener(sensorListener, pressure,1000000);
    }
}
