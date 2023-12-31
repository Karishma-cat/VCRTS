//  Importing necessary libraries for the GUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.*;

//  Creating a JFrame for the GUI
class VRCTSJFrame extends JFrame{
    private static JFrame frame;
    private static JTextField vehicleInfoTextField;
    static Job subJob1;
    static double complete;
    private static ArrayList<vehicleowner> ownerList = new ArrayList<>();
    private static boolean isOwner;
    static Connection connection = null;
    static String url = "jdbc:mysql://localhost:3306/VC3";
    static String user = "root";
    static String pass = "rootuser1";

    
    static ServerSocket serverSocket;
	static Socket socket;
	static DataInputStream inputStream;
	static DataOutputStream outputStream;
	private static JTextField usernameField;
    private static JCheckBox ownerCheckBox;
	
	public static void main(String[]args) {
		try {
			socket = new Socket("localhost", 9808);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

        try{
            connection = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            e.getMessage();
        }
		
		ArrayList<vehicleowner> ownerList = new ArrayList<>();
        Logingui logingui = new Logingui(ownerList);
	}
	
	private void initializeLoginGUI() {
		setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        ownerCheckBox = new JCheckBox("Owner");
        JButton loginButton = new JButton("Login");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(ownerCheckBox);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        add(loginPanel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                boolean isOwner = ownerCheckBox.isSelected();
                authenticateUser(username, isOwner);
            }
        });

        setVisible(true);
    }

	// Giving the GUI a title, a welcome message and dimentions
    public static void initializeGUI() {
    	/*
    	try {
			socket = new Socket("localhost", 9808);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	*/

    	
    	JFrame frame = new JFrame("Vehicle Cloud Real Time System");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Created a main panel to hold all the components

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        JLabel welcomeLabel = createStyledLabel("Welcome to the Vehicular Cloud Real Time System");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create a styled label for the message

        JLabel messageLabel = createStyledLabel("Please select one of the options below:");
        buttonPanel.add(messageLabel);

        // Create a button for submitting a job
        JButton jobButton = createStyledButton("Submit a Job");
        buttonPanel.add(jobButton);

        // Create a button for logging into the owner panel

        JButton ownerButton = createStyledButton("Login to Owner Panel");
        buttonPanel.add(ownerButton);
        
        JButton ownerRegister = createStyledButton("Owner Registration");
        buttonPanel.add(ownerRegister);

        frame.setVisible(true);

        ImageIcon imageIcon = new ImageIcon("FordMustang.png");
        JLabel imageLabel = new JLabel(imageIcon);
        mainPanel.add(imageLabel, BorderLayout.SOUTH);
        mainPanel.updateUI();
        //Calls to the job method when the button is clicked

        jobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client("");
                VC VC = new VC(client);
                openJobSubmission();
                

                
            }
           // public void VRCTSJFrame(boolean isOwner) {
               //this.isOwner = isOwner;
         //alex this is for gui owner checker and it being a little bit annoying
            //}
        });
       
        ownerButton.addActionListener(f -> openOwnerPanel());
        
        ownerRegister.addActionListener(g -> ownerRegisterClick());
        


    }
    // Creates a new JFrame for Owner submission with a title and dimensions

    private static void openOwnerPanel() {
            //JOptionPane.showMessageDialog(null, "Error: Only owners can access the Owner Panel.");
            //return;
        // Creates a panel for Owner Panel
        JFrame ownerFrame = new JFrame("Owner Panel");
        ownerFrame.setSize(300, 350);

        // Creates a panel for Owner ID
        JLabel ownerIDLabel = createStyledLabel("Owner ID:");
        ownerIDLabel.setBounds(20, 20, 400, 30);
        ownerFrame.add(ownerIDLabel);

        // Creates a text field for entering Owner ID
        JTextField ownerIDTextField = new JTextField("");
        ownerIDTextField.setBounds(20, 60, 200, 30);
        ownerFrame.add(ownerIDTextField);

        // Create and configure a label for "Vehicle Information"

        JLabel vehicleInfoLabel = createStyledLabel("Vehicle Information:");
        vehicleInfoLabel.setBounds(20, 90, 200, 30);
        ownerFrame.add(vehicleInfoLabel);

        // Creates a text field to enter Vehicle Info
        vehicleInfoTextField = new JTextField("");
        vehicleInfoTextField.setBounds(20, 120, 200, 30);
        ownerFrame.add(vehicleInfoTextField);

        // Creates and configues a label for Residency Time
        JLabel residentTime = createStyledLabel("Residency Time: in hours");
        residentTime.setBounds(20, 150, 200, 30);
        ownerFrame.add(residentTime);

        // Creates a text field to enter Residency Time
        JTextField residentTimeTextField = new JTextField("");
        residentTimeTextField.setBounds(20, 180, 200, 30);
        ownerFrame.add(residentTimeTextField);

        // Creates a button for Submit Owner Info
        JButton submitOwnerInfoButton = createStyledButton("Submit Owner Info");
        submitOwnerInfoButton.setBounds(20, 260, 250, 50);
        ownerFrame.add(submitOwnerInfoButton);

        ownerFrame.setLayout(null);
        ownerFrame.setVisible(true);

        submitOwnerInfoButton.addActionListener(event -> {

            // Get values from the input fields
            String ownerID = ownerIDTextField.getText();
            String vehicleInf = vehicleInfoTextField.getText();
            String residencyTime = residentTimeTextField.getText();
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
            String timestamp = currentTime.format(formatter);
            int ownerNumber = Integer.parseInt(ownerID);
            if(checkForId(ownerNumber)==false) {   	
            	JOptionPane.showMessageDialog(null, "Invalid Login");  
            }
            else {
            	String messageIn = "";
            	String messageOut = "";
            	
            	try {
            		inputStream = new DataInputStream(socket.getInputStream());
            		outputStream = new DataOutputStream(socket.getOutputStream());
            		
            		messageOut = ownerID;
            		outputStream.writeUTF(messageOut);
            		
            		messageIn = inputStream.readUTF();
            		
            		if(messageIn.equals("pass")) {
            			
                        // Prepare data for writing to a file
                        String data = "Timestamp: " + timestamp + "\n" +
                                "Owner ID: " + ownerID + "\n" +
                                "Vehicle Information: " + vehicleInf + "\n" +
                                "Residency Time (hours): " + residencyTime + "\n";

                        String fileName = "actionlog.txt";
                        writeToFile(data, fileName);
                        
                        JOptionPane.showMessageDialog(null, "Data was saved to " +fileName );
                        ownerFrame.dispose();
            		}
            		else {
            			JOptionPane.showMessageDialog(null, "Data was not saved");
            			ownerFrame.dispose();
            			
            		}
            		
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            }
        });
    }
    private static void ownerRegisterClick() {
    	 
        JFrame ownerRegisterFrame = new JFrame("Owner Registration");
        ownerRegisterFrame.setSize(300, 350);

        
        JLabel ownerNameLabel = createStyledLabel("Please enter your full name:");
        ownerNameLabel.setBounds(20, 20, 400, 30);
        ownerRegisterFrame.add(ownerNameLabel);

        
        JTextField ownerNameTextField = new JTextField("");
        ownerNameTextField.setBounds(20, 60, 200, 30);
        ownerRegisterFrame.add(ownerNameTextField);
        
        JLabel setOwnerId = createStyledLabel("Please enter an ID (numbers only)");
        setOwnerId.setBounds(20, 90, 200, 30);
        ownerRegisterFrame.add(setOwnerId);

        
        JTextField ownerIdTextField = new JTextField("");
        ownerIdTextField.setBounds(20, 120, 200, 30);
        ownerRegisterFrame.add(ownerIdTextField);
        
        JButton submitOwnerRegisInfo = createStyledButton("Submit Owner Info");
        submitOwnerRegisInfo.setBounds(20, 260, 250, 50);
        ownerRegisterFrame.add(submitOwnerRegisInfo);
        
        ownerRegisterFrame.setLayout(null);
        ownerRegisterFrame.setVisible(true);
        
        submitOwnerRegisInfo.addActionListener(x -> {
        	
        	String ownerName = ownerNameTextField.getText();
            String ownerIdString = ownerIdTextField.getText();
            int ownerId = Integer.parseInt(ownerIdString); 
            
            if(checkForId(ownerId)) {
            	JOptionPane.showMessageDialog(null, "ID number already in use, please select another");
            }
            else {
            vehicleowner ownerRegistered = new vehicleowner(ownerId, ownerName);
            
            JOptionPane.showMessageDialog(null, "You have been registered");
            
            String ownerData = "Owner ID: " + ownerId + "\n" + "Owner Name: " +ownerName + "\n";
            String filename = "actionlog.txt";
            writeToFile(ownerData, filename);
            ownerList.add(ownerRegistered);
            try{
                            String sql = "INSERT INTO table1"+ "(ClientID, name)"+"VALUES(ownerId, ownerName)";
                            Statement statement = connection.createStatement();
                            int row=statement.executeUpdate(sql);
        
                        }catch(SQLException e){
                            e.getMessage();
                        }
            
            ownerRegisterFrame.dispose();
            }
        	
        });     
        
    }

 // Creates a new JFrame for job submission with a title and dimensions
    public static void openJobSubmission() {
        JFrame jobFrame = new JFrame("Job Submission");
        jobFrame.setSize(300, 450);

        JLabel jobLabel = createStyledLabel("Client ID (numeric values only):");
        jobLabel.setBounds(20, 20, 200, 30);
        jobFrame.add(jobLabel);

        JTextField clientIdField = new JTextField("");
        clientIdField.setBounds(20, 60, 200, 30);
        jobFrame.add(clientIdField);

        JLabel jobDuration = createStyledLabel("Approximate duration of task (in minutes):");
        jobDuration.setBounds(20, 90, 400, 30);
        jobFrame.add(jobDuration);

        JTextField jobDurationTextField = new JTextField("");
        jobDurationTextField.setBounds(20, 120, 200, 30);
        jobFrame.add(jobDurationTextField);

        JLabel jobDeadline = createStyledLabel("Job Deadline: (mm/dd/yyyy)");
        jobDeadline.setBounds(20, 150, 200, 30);
        jobFrame.add(jobDeadline);

        JLabel timeLine = createStyledLabel("**Must calculate completion time before submitting**");
        timeLine.setBounds(20, 140, 450, 200);
        timeLine.setFont(new Font("Arial", Font.BOLD, 11));
        timeLine.setForeground(new Color(128, 0, 32));
        jobFrame.add(timeLine);

        JTextField jobDeadlineTextField = new JTextField("");
        jobDeadlineTextField.setBounds(20, 180, 200, 30);
        jobFrame.add(jobDeadlineTextField);
        
        JButton calButton = createStyledButton("Calculate completion time");
        calButton.setBounds(20, 260, 250, 30);
        calButton.setPreferredSize(new Dimension(200, 40));
        calButton.setFont(new Font("Arial", Font.BOLD, 14));
        jobFrame.add(calButton);

        JButton submitJobButton = createStyledButton("Submit Job");
        submitJobButton.setBounds(20, 320, 250, 30);
        submitJobButton.setPreferredSize(new Dimension(200, 40));
        submitJobButton.setFont(new Font("Arial", Font.BOLD, 14));
        jobFrame.add(submitJobButton);

        jobFrame.setLayout(null);
        jobFrame.setVisible(true);

        calButton.addActionListener(event ->{
            int clientID = Integer.parseInt(clientIdField.getText());
            String deadline = jobDeadlineTextField.getText();
            int duration = Integer.parseInt(jobDurationTextField.getText());
            int completionTime = VC.calcCompTime(duration);
            Job subJob = new Job(clientID,VC.getSize() + 1, duration, deadline, completionTime);
            if (!subJob.equals(subJob1)) {
                subJob1 = subJob;
            }
    		timeLine.setText("Calculated Time: " + subJob1.getCompletionTime());
        });

        submitJobButton.addActionListener(event -> {
            if (subJob1 == null) {
                JOptionPane.showMessageDialog(null, "You must calculate the sub job first");
                return;
            }
            
            else {
            String messageIn = "";
    		String messageOut = "";
    		
    		try {    			
    			
    			Socket socket1=socket;
    			
    			//client reads a message from Server
    			inputStream = new DataInputStream(socket.getInputStream());
    			outputStream = new DataOutputStream(socket.getOutputStream());
    			
    			
    			messageOut = String.valueOf(subJob1.getId());
    			// server sends the message to client
    			outputStream.writeUTF(messageOut);
    			
    	
    				messageIn = inputStream.readUTF();
    				
    				if(messageIn.equals("pass")) {
    					LocalDateTime currentTime = LocalDateTime.now();
    		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy HH:mm:ss");
    		            String timestamp = currentTime.format(formatter);

    		            String data = "Timestamp: " + timestamp + "\n" +
    		                    "Client ID: " + subJob1.getId() + "\n" +
    		                    "Job Duration (in minutes): " + subJob1.getDuration() + "\n" +
    		                    "Job Deadline: " + subJob1.getDeadline() + "\n"+
    		                    "Job Completion time: " + subJob1.getCompletionTime();
    		            		VC.addJob(subJob1);
    		           
    		            		
    		            		String fileName = "actionlog.txt";
    		            writeToFile(data, fileName);

                        try{
                            String sql = "INSERT INTO table2"+ "(JobID, jobDuration, jobDeadline, jobCompletion)"+"VALUES(subJob1.getId(), subJob1.getDuration(), subJob1.getDeadline(), subJob1.getCompletionTime())";
                            Statement statement = connection.createStatement();
                            int row=statement.executeUpdate(sql);
        
                        }catch(SQLException e){
                            e.getMessage();
                        }

    		            JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);
    		            JLabel jobCompletion = createStyledLabel("Job Completion Time");
    		            jobCompletion.setBounds(20, 150, 200, 30);
    		            jobFrame.add(jobCompletion);

    		            jobFrame.dispose();
    		            subJob1 = null;
    				}
    				else {
    					JOptionPane.showMessageDialog(null, "Data was not saved");
     		            JLabel noSave = createStyledLabel("Data not saved");
     		            noSave.setBounds(20, 150, 200, 30);
     		            jobFrame.add(noSave);

     		            jobFrame.dispose();
    				}			
    						

    		} catch (Exception e) {
    			
    			e.printStackTrace();

    		}
            
            }});

        
    }
    
    private void authenticateUser(String username, boolean isOwner) {
        if (isOwner) {
            // Show Owner Panel
            showOwnerPanel();
        } else {
            // Show Client Panel
            showClientPanel();
        }
    
        // Open the VRCTSJFrame GUI and pass the isOwner information
        VRCTSJFrame vrctsJFrame = new VRCTSJFrame();
        vrctsJFrame.initializeGUI();
    
        // Save user input to the action log file
        saveUserInfoToLogFile(username, isOwner);
    
        // Close the login window
        dispose();
    }
    

    private void saveUserInfoToLogFile(String username, boolean isOwner) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        String timestamp = currentTime.format(formatter);

        String userType = isOwner ? "Owner" : "Client";

        // Prepare data for writing to a file
        String data = "Timestamp: " + timestamp + "\n" +
                "Username: " + username + "\n" +
                "User Type: " + userType + "\n";

        String fileName = "actionlog.txt";
        writeToFile(data, fileName);
    }

    private void showOwnerPanel() {
        JOptionPane.showMessageDialog(null, "Welcome, Owner!");
        dispose(); // Closes the login window
    }

    private void showClientPanel() {
        JOptionPane.showMessageDialog(null, "Welcome, Client!");
    }

    private static boolean writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // This method creates and configures a styled JButton.
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

    // This method creates and configures a styled JLabel.
    private static JLabel createStyledLabel(String text) 
    {

        JLabel label = new JLabel(text);

        // Set label font and text color
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(new Color(128, 0, 32));

        return label;
    }
    
    private static boolean checkForId (int ownerId) {
    	for(vehicleowner vowner: ownerList) {
    		if (vowner.getOwnerId()==ownerId) {
    			return true;
    		}
    	}
    	return false;
    }

}