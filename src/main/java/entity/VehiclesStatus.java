package entity;

import java.util.ArrayList;

public class VehiclesStatus {

    private String lastUpdateData;
    private ArrayList<Vehicle> vehicles;

    public VehiclesStatus(String lastUpdateData, ArrayList<Vehicle> vehicles){
        this.lastUpdateData = lastUpdateData;
        this.vehicles = new ArrayList<Vehicle>(vehicles);
    }

    public String getLastUpdateData() {
        return lastUpdateData;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle getVehicleById(int Id){

        for(Vehicle vehicle : vehicles) {
            if(vehicle.getVehicleId() == Id) {
                return vehicle;
            }
        }
        return null;
    }
}
