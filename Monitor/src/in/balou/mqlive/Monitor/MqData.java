package in.balou.mqlive.Monitor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.eclipse.paho.client.mqttv3.*;

import java.util.ArrayList;

/**
 * Created by marco on 07/06/15.
 */
public class MqData
{
    private MqttClient client;
    private ArrayList<Observer> observers;
    private ArrayList<TrackPoint> points;

    public DoubleProperty lowestAltitude;
    public DoubleProperty highestAltitude;

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
        lowestAltitude = new SimpleDoubleProperty(0.0);
        highestAltitude = new SimpleDoubleProperty(2000.0);

        try
        {
            client = new MqttClient(serverName, MqttClient.generateClientId(), null);
            client.connect();
            client.setCallback(new MqttCallback()
            {
                @Override
                public void connectionLost(Throwable throwable)
                {
                    System.out.println("Connection lost...");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception
                {
                    System.out.printf("From (%s):", s);
                    String message = new String(mqttMessage.getPayload());
                    System.out.println(message);

                    TrackPoint t = new TrackPoint(message);
                    points.add(t);
                    if (lowestAltitude.getValue() > t.getAltitude() || lowestAltitude.getValue() == 0.0)
                        lowestAltitude.setValue(t.getAltitude());


                    //inform Observers
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
            System.out.println("Connection to mqtt-broker failed, please check connection...");
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