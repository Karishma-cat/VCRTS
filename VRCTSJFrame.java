
//  Importing necessary libraries for the GUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

//  Creating a JFrame for the GUI
class VRCTSJFrame {
    private static JFrame frame;
    private static JTextField vehicleInfoTextField;
    static Job subJob1;
    static double complete;

    // Giving the GUI a title, a welcome message and dimentions
    public static void initializeGUI() {
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
        });
        

        ownerButton.addActionListener(f -> openOwnerPanel());
    }
    // Creates a new JFrame for Owner submission with a title and dimensions

    private static void openOwnerPanel() {

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
        JLabel residentTime = createStyledLabel("Residency Time:");
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

            // Prepare data for writing to a file
            String data = "Timestamp: " + timestamp + "\n" +
                    "Owner ID: " + ownerID + "\n" +
                    "Vehicle Information: " + vehicleInf + "\n" +
                    "Residency Time: " + residencyTime + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

            // Write data to the file and display a confirmation dialog

            JOptionPane.showMessageDialog(null, "The owner information was sent to the file.");

            ownerFrame.dispose();
        });
    }

 // Creates a new JFrame for job submission with a title and dimensions
    public static void openJobSubmission() {
        JFrame jobFrame = new JFrame("Job Submission");
        jobFrame.setSize(300, 450);

        JLabel jobLabel = createStyledLabel("Client ID:");
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
        timeLine.setFont(new Font("Arial", Font.ITALIC, 11));
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
            System.out.println("Deadline: " + deadline);
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

            JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);
            JLabel jobCompletion = createStyledLabel("Job Completion Time");
            jobCompletion.setBounds(20, 150, 200, 30);
            jobFrame.add(jobCompletion);

            jobFrame.dispose();
            subJob1 = null;
        });

       
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

}
