
public class vehicle {
// store the Vehicle ID
    private int vehicleID;
// Store the owner of the vehocle
private vehicleowner owner;

// constructor to initialize the vehicle with a ID and owner
public vehicle(int vehicleID, vehicleowner owner) {
    this.vehicleID = vehicleID;
    this.owner = owner;
}
// gets the vehicle ID
public int getVehicleID(){
    return vehicleID;
}
// sets that vehicle to a ID
public void setVehicleID(int vehicleID){
    this.vehicleID = vehicleID;
}
// gets the vehicles owner
public vehicleowner getOwner(){
    return owner;
}
// sets the vehicles owner
public void setVehicleOwner(vehicleowner owner){
    this.owner = owner;
}
// method to show when the vehicle arrives
public void arrive(){
    System.out.println("Vehicle " + vehicleID + "has arrived ");
}
// method to show when vehicle departs
public void depart(){
    System.out.println("Vehicle " + vehicleID + "has departed ");
}
}
