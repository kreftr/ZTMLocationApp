import data.VehicleData;
import entity.VehiclesStatus;
import jxmapviewer.FancyWaypointRenderer;
import jxmapviewer.MyWaypoint;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.*;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Main {



    public static void main(String[] args) throws IOException, InterruptedException {

        //Data
        VehicleData data = new VehicleData(6000,6000);
        System.out.println("Connection = "+data.isConnected());
        VehiclesStatus vehiclesStatus;// = data.getVehiclesStatus();
       // System.out.println(vehiclesStatus.getVehicleById(184));

        //JXMap instance
        JXMapViewer mapViewer = new JXMapViewer();

        // Display the viewer in a JFrame
        JFrame frame = new JFrame("Bus map viewer");
        frame.getContentPane().add(mapViewer);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //Title factory - ???
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);

        // Add interactions
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        //--------------------------------------------------------------------------------------

        //Bus position
        Double lat;// = vehiclesStatus.getVehicleById(184).getLatitude();
        Double lon;// = vehiclesStatus.getVehicleById(184).getLongitude();
        GeoPosition bus;// = new GeoPosition(lat, lon);
        //Zoom on busPosition
        //mapViewer.setZoom(4);
        //mapViewer.setAddressLocation(bus);



        Set<MyWaypoint> waypoints; //= new HashSet<MyWaypoint>(Arrays.asList(
                //new MyWaypoint("", Color.RED, bus)));

        // Create a waypoint painter that takes all the waypoints
        WaypointPainter<MyWaypoint> waypointPainter;// = new WaypointPainter<MyWaypoint>();
       // waypointPainter.setWaypoints(waypoints);
       // waypointPainter.setRenderer(new FancyWaypointRenderer());



        //mapViewer.setOverlayPainter(waypointPainter);

        mapViewer.setZoom(4);
        int secondsPassed = 0;

        while(true){
            data = new VehicleData(6000,6000);
            vehiclesStatus = data.getVehiclesStatus();
            System.out.println(vehiclesStatus.getVehicleById(145669));
            lat = vehiclesStatus.getVehicleById(145669).getLatitude();
            lon = vehiclesStatus.getVehicleById(145669).getLongitude();
            bus = new GeoPosition(lat, lon);
            waypoints = new HashSet<MyWaypoint>(Arrays.asList(new MyWaypoint("", Color.RED, bus)));
            waypointPainter = new WaypointPainter<MyWaypoint>();
            waypointPainter.setWaypoints(waypoints);
            waypointPainter.setRenderer(new FancyWaypointRenderer());
            mapViewer.setAddressLocation(bus);
            mapViewer.setOverlayPainter(waypointPainter);
            Thread.sleep(5000);
        }


    }

}
