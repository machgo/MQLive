package in.balou.mqlive.Monitor;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapShape;
import com.lynden.gmapsfx.shapes.Polyline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable, MapComponentInitializedListener, Observer
{
    @FXML
    public LineChart<Number,Number> altitudeGraph;
    @FXML
    public NumberAxis yAxis;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;
    private Polyline trackLine;

    private MqData data;

    private long startTime;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        mapView.addMapInializedListener(this);
        data = new MqData();
        startTime = 0;

        altitudeGraph.setCreateSymbols(false);
        altitudeGraph.getData().add(new XYChart.Series<>());

        //dynamic boundaries for yAxis
        yAxis.lowerBoundProperty().bind(data.lowestAltitude);
        yAxis.upperBoundProperty().bind(data.highestAltitude);

    }

    @Override
    public void mapInitialized()
    {
        MapOptions mapOptions = new MapOptions();

        //map options
        mapOptions.center(new LatLong(47.6097, -122.3331))
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(16);

        map = mapView.createMap(mapOptions);
        //adding trackline for gps-track (no points/data yet)
        trackLine = new Polyline();
        map.addMapShape(trackLine);

        //now we are ready to receive updates
        data.addObserver(this);
    }

    @Override
    public void update()
    {
        Platform.runLater(() -> {
            //if first point, start timer
            if (startTime == 0)
                startTime = System.currentTimeMillis() / 1000l;

            //calculating seconds since start of tracking
            long seconds = (System.currentTimeMillis() / 1000l)-startTime;

            TrackPoint p = data.getLatestPoint();
            LatLong ll = new LatLong(p.getLatitude(), p.getLongitude());
            trackLine.getPath().push(ll);
            map.setCenter(ll);

            //adding data to altitude-graph
            altitudeGraph.getData().get(0).getData().add(new XYChart.Data(seconds, p.getAltitude()));
        });
    }
}
