import data.VehicleData;
import entity.Vehicle;
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
        GeoPosition bus = new GeoPosition(54.372158, 18.638306);// = new GeoPosition(lat, lon);
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
        GeoPosition bus2;
        mapViewer.setAddressLocation(bus);
        ArrayList<MyWaypoint> busList = new ArrayList();

        while(true){
            data = new VehicleData(6000,6000);
            vehiclesStatus = data.getVehiclesStatus();
            //System.out.println(vehiclesStatus.getVehicleById(145645));
            //lat = vehiclesStatus.getVehicleById(145645).getLatitude();
            //lon = vehiclesStatus.getVehicleById(145645).getLongitude();
            //System.out.println(vehiclesStatus.getVehicleById(145653));
            System.out.println("Seconds passed: "+secondsPassed+"       Data generated: "+vehiclesStatus.getLastUpdateData());
            //bus = new GeoPosition(lat, lon);
            //bus2 = new GeoPosition(vehiclesStatus.getVehicleById(145653).getLatitude(), vehiclesStatus.getVehicleById(145653).getLongitude());

            for(Vehicle x : vehiclesStatus.getVehicles()) {
                busList.add(new MyWaypoint(x.getLine(), Color.RED, new GeoPosition(x.getLatitude(), x.getLongitude())));
            }

            waypoints = new HashSet<MyWaypoint>(busList);
            waypointPainter = new WaypointPainter<MyWaypoint>();
            waypointPainter.setWaypoints(waypoints);
            waypointPainter.setRenderer(new FancyWaypointRenderer());
            //mapViewer.setAddressLocation(bus);
            mapViewer.setOverlayPainter(waypointPainter);
            Thread.sleep(5000);
            secondsPassed = secondsPassed + 5;
            busList.clear();
            //new MyWaypoint("205", Color.RED, bus),
            //                    new MyWaypoint("200", Color.BLUE, bus2)
        }


    }

}
