import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class VC { 
    static private ArrayList<Job> jobs;
    private static int compTime = 0;
    Client client;
    static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;

    public VC(Client client) {
        this.client = client;
        jobs = new ArrayList<Job>();
    }
    static public void addJob(Job j) {
        jobs.add(j);
        compTime += j.getDuration();
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public static int calcCompTime(int time) {
        return compTime + time;
     }
     
     static public int getSize() {
    	 return jobs.size();
     }


    public static void main(String[] args) {
        VRCTSJFrame.initializeGUI();
        String messageIn = "";
		String messageOut = "";
		Scanner keyInput;

		try {

			System.out.println("----------$$$ This is server side $$$--------");
			System.out.println("wating to connect...");
			// creating the server
			serverSocket = new ServerSocket(9806);
			// sever accepts connection request from client
			socket = serverSocket.accept();
			System.out.println("client is connected!");
			System.out.println("go to client side and send me a message");

			// server reads a message message from client
			inputStream = new DataInputStream(socket.getInputStream());

			// server sends a message to client
			outputStream = new DataOutputStream(socket.getOutputStream());

			// as long as message is not exit keep reading and sending message to client
			while (!messageIn.equals("exit")) {

				// extract the message from client
				messageIn = inputStream.readUTF();
				// server prints the message received from client to console
				System.out.println("message received from client: " + "\"" + messageIn + "\"");

				// ********************************************************
				// server reads a message from keyboard
				System.out.println("Enter a message you want to send to client side: ");
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

    public static void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JLabel createStyledLabel(String text) 
    {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(128, 0, 32));

        return label;
    }
}

