import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

public class vehicle {
// store the Vehicle ID
    private int vehicleID;
// Store the owner of the vehicle
    private vehicleowner owner;

	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;

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
public static void main(String[] args) {
	
	String messageIn = "";
	String messageOut = "";
	
	
	try {

		System.out.println("-----*** This is vehicle owner side ***-----");
		System.out.println("vehicle owner started!");
		//connect the client socket to server
		Socket socket = new Socket("localhost", 9808);
		
		
		//client reads a message from Server
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		
		
		// client reads a message from keyboard
		System.out.println("Enter data you want to send to server side: ");
		keyInput = new Scanner(System.in);
		messageOut = keyInput.nextLine();
		// server sends the message to client
		outputStream.writeUTF(messageOut);
		

		while(!messageIn.equals("end")) {
			messageIn = inputStream.readUTF();
			// client prints the message received from server to console
			System.out.println("message received from server: " + "\"" + messageIn + "\"");
			
			
			// ********************************************************
			// client reads a message from keyboard
			System.out.println("Enter a message you want to send to server side: ");
			keyInput = new Scanner(System.in);
			messageOut = keyInput.nextLine();
			// server sends the message to client
			outputStream.writeUTF(messageOut);
			
		}
					

	} catch (Exception e) {
		
		e.printStackTrace();

	}
}
}
