import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.*;

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
        
                String messageIn= "";
		String messageOut = "";
		
		try {
			// creating the server
			serverSocket = new ServerSocket(9808);
			// sever accepts connection request from client
			socket = serverSocket.accept();

			// server reads a message from client
			inputStream = new DataInputStream(socket.getInputStream());

			// server sends a message to client
			outputStream = new DataOutputStream(socket.getOutputStream());

			while(!messageIn.equals("end")) {
			 JFrame serverChoice = new JFrame("Server choice");
		        serverChoice.setSize(300, 450);
		        
		        JButton pass = createStyledButton("Calculate completion time");
		        pass.setBounds(20, 260, 250, 30);
		        pass.setPreferredSize(new Dimension(200, 40));
		        pass.setFont(new Font("Arial", Font.BOLD, 14));
		        serverChoice.add(pass);
		        
		        JButton deny = createStyledButton("Calculate completion time");
		        deny.setBounds(20, 260, 250, 30);
		        deny.setPreferredSize(new Dimension(200, 40));
		        deny.setFont(new Font("Arial", Font.BOLD, 14));
		        serverChoice.add(deny);
		        
		        pass.addActionListener(x -> {
					try {
						outputStream.writeUTF("pass");
					} catch (IOException e) {
						e.printStackTrace();
					};
		        });
		        
		        deny.addActionListener(x -> {
					try {
						outputStream.writeUTF("deny");
					} catch (IOException e) {
						e.printStackTrace();
					}
		        
		        });
				
			}}catch (Exception e) {

			e.printStackTrace();
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
    
    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);

        // Set button dimensions and font

        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));

        // Set button background color

        button.setBackground(new Color(128, 0, 32));

        button.setForeground(new Color(255, 255, 255));

        LineBorder border = new LineBorder(Color.PINK, 2);
        button.setBorder(border);

        // Customize button appearance

        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);

        return button;
    }
    
}
