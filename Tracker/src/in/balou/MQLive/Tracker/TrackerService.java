package in.balou.MQLive.Tracker;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.IBinder;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.IOException;

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
    public void onDestroy()
    {
        super.onDestroy();
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();

        try
        {
            DataSender.getInstance().connectTo(this, "tcp://188.166.105.27:1883", "TestClient");
            DataSender.getInstance().sendData("starting Tracking");
        } catch (MqttException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new MyLocationListener();
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        sensorListener = new MySensorListener();
        sensorManager.registerListener(sensorListener, pressure, 1000000);


    }
    private MyLocationListener locationListener;
    private MySensorListener sensorListener;

}
