package in.balou.MQLive.Tracker;

import android.content.Context;
import android.util.Log;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by marco on 22/05/15.
 */
public class DataSender
{
    private DataSender()
    {

    }

    private static DataSender instance;

    public static DataSender getInstance()
    {
        if (instance == null)
            instance = new DataSender();
        return instance;
    }

    private MqttClient client;

    public boolean connectTo(Context con, String serverName, String clientId) throws MqttException
    {
        client = new MqttClient(serverName, MqttClient.generateClientId(), null);
        client.setCallback(new MyCallback());
        client.connect();
        return true;
    }

    public void sendData (String payload) throws IOException, MqttException
    {
        String topic = "ch/mqsender/Test1";
        client.publish(topic, payload.getBytes(), 0, false);
    }

    public class MyCallback implements MqttCallback
    {
        @Override
        public void connectionLost(Throwable throwable)
        {
            Log.d(getClass().getCanonicalName(), "connection Lost");
        }

        @Override
        public void messageArrived(String s, MqttMessage mqttMessage) throws Exception
        {
            Log.d(getClass().getCanonicalName(), "message Arrived");
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)
        {
            Log.d(getClass().getCanonicalName(), "Delivery complete");
        }
    }
}
