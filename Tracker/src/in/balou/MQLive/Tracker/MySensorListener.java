package in.balou.MQLive.Tracker;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

/**
 * Created by marco on 22/05/15.
 */
public class MySensorListener implements SensorEventListener
{
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        Log.i("Pressure changed ", String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}
