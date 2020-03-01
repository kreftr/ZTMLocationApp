package entity;

public class Vehicle {

    private String dataGenerated;
    private String line;
    private String route;
    private int vehicleCode;
    private String vehicleService;
    private int vehicleId;
    private int speed;
    private int delay;
    private Double latitude;
    private Double longitude;
    private int GPSQuality;

    public Vehicle(String dataGenerated, String line, String route, int vehicleCode, String vehicleService,
                   int vehicleId, int speed, int delay, Double latitude, Double longitude, int GPSQuality) {
        this.dataGenerated = dataGenerated;
        this.line = line;
        this.route = route;
        this.vehicleCode = vehicleCode;
        this.vehicleService = vehicleService;
        this.vehicleId = vehicleId;
        this.speed = speed;
        this.delay = delay;
        this.latitude = latitude;
        this.longitude = longitude;
        this.GPSQuality = GPSQuality;
    }

    public String getDataGenerated() {
        return dataGenerated;
    }

    public String getLine() {
        return line;
    }

    public String getRoute() {
        return route;
    }

    public int getVehicleCode() {
        return vehicleCode;
    }

    public String getVehicleService() {
        return vehicleService;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDelay() {
        return delay;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getGPSQuality() {
        return GPSQuality;
    }

    public String toString(){
        return "ID: "+vehicleId+"   Line: "+line+"  Speed: "+speed+"  Latitude: "+latitude+"  Longitude: "+longitude;
    }
}
