package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapShape;
import com.lynden.gmapsfx.shapes.Polyline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, MapComponentInitializedListener
{

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized()
    {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        Polyline p = new Polyline();
        p.getPath().push(new LatLong(40, -120));
        p.getPath().push(new LatLong(40, -110));
        map.addMapShape(p);

        p.getPath().push(new LatLong(10, 200));



    }
}
