package in.balou.mqlive.Monitor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marco on 07/06/15.
 */
public class TrackPoint
{
    private double longitude;
    public double getLongitude()
    {
        return longitude;
    }
    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    private double latitude;
    public double getLatitude()
    {
        return latitude;
    }
    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    private double altitude;
    public double getAltitude()
    {
        return altitude;
    }

    public void setAltitude(double altitude)
    {
        this.altitude = altitude;
    }

    public TrackPoint(String jsonData)
    {
        try
        {
            JSONObject obj = new JSONObject(jsonData);
            longitude = obj.getDouble("Longitude");
            latitude = obj.getDouble("Latitude");
            altitude = obj.getDouble("Altitude");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "TrackPoint{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
