package in.balou.mqlive.Monitor;

import org.eclipse.paho.client.mqttv3.*;
import org.json.JSONObject;

import javax.sound.midi.Track;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by marco on 07/06/15.
 */
public class MqData
{
    private MqttClient client;
    private ArrayList<Observer> observers;
    private ArrayList<TrackPoint> points;

    public ArrayList<TrackPoint> getPoints()
    {
        return points;
    }
    public TrackPoint getLatestPoint()
    {
        return points.get(points.size()-1);
    }

    public MqData()
    {
        String serverName = "tcp://188.166.105.27:1883";
        observers = new ArrayList<Observer>();
        points = new ArrayList<TrackPoint>();

        try
        {
            client = new MqttClient(serverName, MqttClient.generateClientId(), null);
            client.connect();
            client.setCallback(new MqttCallback()
            {
                @Override
                public void connectionLost(Throwable throwable)
                {

                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception
                {
                    System.out.printf("From (%s):", s);
                    String message = new String(mqttMessage.getPayload());
                    System.out.println(message);
                    points.add(new TrackPoint(message));
                    informAll();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken)
                {

                }
            });
            client.subscribe("ch/mqsender/Test1");
        } catch (MqttException e)
        {
            e.printStackTrace();
        }
    }

    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    private void informAll()
    {
        observers.forEach(x -> x.update());
    }
}