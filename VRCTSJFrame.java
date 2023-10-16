
//  Importing necessary libraries for the GUI
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
public class VRCTSJFrame {
    private static JFrame frame;

    public static void main(String[] args) {
        initializeGUI();
    }
// Giving the GUI a title, a welcome message and dimentions 
    private static void initializeGUI() {
        JFrame frame = new JFrame("Vehicle Cloud Real Time System");
        frame.setSize(400, 200);
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

        jobButton.addActionListener(e -> openJobSubmission());
        ownerButton.addActionListener(f -> openOwnerPanel());
    }
    // Creates a new JFrame for job submission with a title and dimensions

    private static void openJobSubmission() {
        JFrame jobFrame = new JFrame("Job Submission");
        jobFrame.setSize(300, 350);
        
        // Created and configure a label for "Client ID"

        JLabel jobLabel = createStyledLabel("Client ID:");
        jobLabel.setBounds(20, 20, 200, 30);
        jobFrame.add(jobLabel);

         // Create a text field for entering the client ID

        JTextField clientIdField = new JTextField("");
        clientIdField.setBounds(20, 60, 200, 30);
        jobFrame.add(clientIdField);

        // Create and configure a label for "Approximate duration of task (in minutes)"

        JLabel jobDuration = createStyledLabel("Approximate duration of task (in minutes):");
        jobDuration.setBounds(20, 90, 400, 30);
        jobFrame.add(jobDuration);

        // Created a text field for entering the job duration

        JTextField jobDurationTextField = new JTextField("");
        jobDurationTextField.setBounds(20, 120, 200, 30);
        jobFrame.add(jobDurationTextField);

        // Created and configure a label for "Job Deadline (mm/dd/yyyy)"

        JLabel jobDeadline = createStyledLabel("Job Deadline: (mm/dd/yyyy)");
        jobDeadline.setBounds(20, 150, 200, 30);
        jobFrame.add(jobDeadline);

        // Created a text field for entering the job deadline

        JTextField jobDeadlineTextField = new JTextField("");
        jobDeadlineTextField.setBounds(20, 180, 200, 30);
        jobFrame.add(jobDeadlineTextField);

        // Created a button for submitting the job

        JButton submitJobButton = new JButton("Submit Job");
        submitJobButton.setBounds(20, 260, 150, 30);
        jobFrame.add(submitJobButton);

        jobFrame.setLayout(null);
        jobFrame.setVisible(true);

        submitJobButton.addActionListener(event -> {
            String clientID = clientIdField.getText();
            LocalDateTime currentTime = LocalDateTime.now();
            
            // Get values from the input fields
          
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = currentTime.format(formatter);

             // Get the  data for writing to a file
            String data = "Timestamp: " + timestamp + "\n" + 
                    "Client ID: " + clientID + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

            // Writing the data to the file and display a confirmation dialog
            JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);

            jobFrame.dispose();
        });
    }

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

        JLabel vehicleInfo = createStyledLabel("Vehicle Information:");
        vehicleInfo.setBounds(20, 90, 200, 30);
        ownerFrame.add(vehicleInfo);

        // Creates a text field to enter Vehicle Info
        JTextField vehicleInfoTextField = new JTextField("");
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

        // Creates  a button for Submit Owner Info
        JButton submitOwnerInfoButton = new JButton("Submit Owner Info");
        submitOwnerInfoButton.setBounds(20, 260, 250, 50);
        ownerFrame.add(submitOwnerInfoButton);

        ownerFrame.setLayout(null);
        ownerFrame.setVisible(true);

        submitOwnerInfoButton.addActionListener(event -> {

            // Get values from the input fields
    String ownerID = ownerIDTextField.getText();
    //String vehicleInfo = vehicleInfoTextField.getText();
    String residencyTime = residentTimeTextField.getText();
    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
    String timestamp = currentTime.format(formatter);

             // Prepare data for writing to a file
    String data = "Timestamp: " + timestamp + "\n" +
    "Owner ID: " + ownerID + "\n" +
    "Vehicle Information: " + vehicleInfo + "\n" +
    "Residency Time: " + residencyTime + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

             // Write data to the file and display a confirmation dialog

            JOptionPane.showMessageDialog(null, "The owner information was sent to the file.");

            ownerFrame.dispose();
        });
    }

    // This method writes data to a file and returns a boolean indicating success or failure.
    private static boolean writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
     // This method creates and configures a styled JButton.
    private static JButton createStyledButton(String text) 
    {
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
