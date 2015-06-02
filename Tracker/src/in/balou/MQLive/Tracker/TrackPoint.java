package in.balou.MQLive.Tracker;

import android.location.Location;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by marco on 02/06/15.
 */
public class TrackPoint
{
    public static String getJson (Location loc)
    {
        JSONObject obj = new JSONObject();
        try
        {
            obj.put("Longitude", loc.getLongitude());
            obj.put("Latitude", loc.getLatitude());
            obj.put("Accuracy", loc.getAccuracy());
            obj.put("Altitude", loc.getAltitude());
            obj.put("Speed", loc.getSpeed());
            obj.put("Bearing", loc.getBearing());
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        return obj.toString();
    }
}
