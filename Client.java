import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

class Client {
	static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;

	
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static void main(String[] args) {
		
		String messageIn = "";
		String messageOut = "";
		Scanner keyInput;	
		try {

			System.out.println("----*** This is client side ***----");
			System.out.println("client started!");
			
			//connect the client socket to server
			Socket socket = new Socket("localhost", 9808);
			
			
			//client reads a message from Server
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());


			// client sends data
		System.out.println("Enter data you want to send to server side: ");
		keyInput = new Scanner(System.in);
		messageOut = keyInput.nextLine();
		// server sends the message to client
		outputStream.writeUTF(messageOut);
		

		while(!messageIn.equals("end")) {
			messageIn = inputStream.readUTF();
			// client prints the message received from server to console
			System.out.println("message received from server: " + "\"" + messageIn + "\"");
			
		}
							

		} catch (Exception e) {
			
			e.printStackTrace();

		}
	}
}
