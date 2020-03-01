package data;


import entity.Vehicle;
import entity.VehiclesStatus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VehicleData {

    private HttpURLConnection connection;
    private URL url;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent;

    public VehicleData(int connectTimeout, int readTimeout) throws IOException {
        this.url = new URL("https://ckan2.multimediagdansk.pl/gpsPositions");
        responseContent = new StringBuffer();
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
    }


    public boolean isConnected() throws IOException {
        int status = connection.getResponseCode();
        if(status > 299) {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            while((line = reader.readLine())!=null){
                responseContent.append(line);
            }
            responseContent.setLength(0);
            reader.close();
            return false;
        }
        else return true;
    }



    public VehiclesStatus getVehiclesStatus() throws IOException {

        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null){
            responseContent.append(line);
        }
        reader.close();

        JSONObject data = new JSONObject(responseContent.toString());
        String lastUpdateData = data.getString("LastUpdateData");

        JSONArray vehicles = data.getJSONArray("Vehicles");

        ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();

        for (int i = 0; i < vehicles.length(); i++) {
            JSONObject vehicle = vehicles.getJSONObject(i);
            vehicleList.add(new Vehicle(vehicle.getString("DataGenerated"), vehicle.getString("Line"),
                    vehicle.getString("Route"), vehicle.getInt("VehicleCode"),
                    vehicle.getString("VehicleService"), vehicle.getInt("VehicleId"),
                    vehicle.getInt("Speed"), vehicle.getInt("Delay"),
                    vehicle.getDouble("Lat"), vehicle.getDouble("Lon"),
                    vehicle.getInt("GPSQuality")));
        }

        responseContent.setLength(0);
        return new VehiclesStatus(lastUpdateData, vehicleList);

    }



}
