import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VRCTSJFrame 
{
    private static JFrame frame;

    public static void main(String[] args) 
    {
        initializeGUI();
    }

    private static void initializeGUI() 
    {
        frame = new JFrame();
        frame.setSize(375, 300);
        frame.setTitle("Vehicle Cloud Real Time System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Welcome to the Vehicular Cloud Real Time System");
        label1.setBounds(50, 50, 400, 30);
        frame.add(label1);

        JLabel label2 = new JLabel("Please select one of the options below:");
        label2.setBounds(50, 80, 400, 30);
        frame.add(label2);

        JButton jobButton = new JButton("Submit a Job");
        jobButton.setBounds(50, 120, 250, 30);
        frame.add(jobButton);

        JButton ownerButton = new JButton("Login to Owner Panel");
        ownerButton.setBounds(50, 160, 250, 30);
        frame.add(ownerButton);

        frame.setLayout(null);
        frame.setVisible(true);

        jobButton.addActionListener(e -> openJobSubmission());
        ownerButton.addActionListener(f -> openOwnerPanel());
    }

    private static void openJobSubmission() 
    {
        JFrame jobFrame = new JFrame("Job Submission");
        jobFrame.setSize(300, 350);

        JLabel jobLabel = new JLabel("Client ID:");
        jobLabel.setBounds(20, 20, 200, 30);
        jobFrame.add(jobLabel);

        JTextField clientIdField = new JTextField("");
        clientIdField.setBounds(20, 60, 200, 30);
        jobFrame.add(clientIdField);

        JLabel jobDuration = new JLabel("Approximate duration of task:");
        jobDuration.setBounds(20, 90, 200, 30);
        jobFrame.add(jobDuration);

        JTextField jobDurationTextField = new JTextField("");
        jobDurationTextField.setBounds(20, 120, 200, 30);
        jobFrame.add(jobDurationTextField);

        JLabel jobDeadline = new JLabel("Job Deadline: (mm/dd/yyyy)");
        jobDeadline.setBounds(20, 150, 200, 30);
        jobFrame.add(jobDeadline);

        JTextField jobDeadlineTextField = new JTextField("");
        jobDeadlineTextField.setBounds(20, 180, 200, 30);
        jobFrame.add(jobDeadlineTextField);

        JButton submitJobButton = new JButton("Submit Job");
        submitJobButton.setBounds(20, 260, 150, 30);
        jobFrame.add(submitJobButton);

        jobFrame.setLayout(null);
        jobFrame.setVisible(true);

        submitJobButton.addActionListener(event -> 
        {
            String clientID = clientIdField.getText();
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = currentTime.format(formatter);

            String data = "Timestamp: " + timestamp + "\n" +
                    "Client ID: " + clientID + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

            JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);

            jobFrame.dispose();
        });
    }

    private static void openOwnerPanel() 
    {
        JFrame ownerFrame = new JFrame("Owner Panel");
        ownerFrame.setSize(300, 350);

        JLabel ownerIDLabel = new JLabel("Owner ID:");
        ownerIDLabel.setBounds(20, 20, 200, 30);
        ownerFrame.add(ownerIDLabel);

        JTextField ownerIDTextField = new JTextField("");
        ownerIDTextField.setBounds(20, 60, 200, 30);
        ownerFrame.add(ownerIDTextField);

        JLabel vehicleInfo = new JLabel("Vehicle Information:");
        vehicleInfo.setBounds(20, 90, 200, 30);
        ownerFrame.add(vehicleInfo);

        JTextField vehicleInfoTextField = new JTextField("");
        vehicleInfoTextField.setBounds(20, 120, 200, 30);
        ownerFrame.add(vehicleInfoTextField);

        JLabel residentTime = new JLabel("Residency Time:");
        residentTime.setBounds(20, 150, 200, 30);
        ownerFrame.add(residentTime);

        JTextField residentTimeTextField = new JTextField("");
        residentTimeTextField.setBounds(20, 180, 200, 30);
        ownerFrame.add(residentTimeTextField);

        JButton submitOwnerInfoButton = new JButton("Submit Owner Info");
        submitOwnerInfoButton.setBounds(20, 260, 250, 50);
        ownerFrame.add(submitOwnerInfoButton);

        ownerFrame.setLayout(null);
        ownerFrame.setVisible(true);

        submitOwnerInfoButton.addActionListener(event -> 
        {
            String ownerID = ownerIDTextField.getText();
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy HH:mm:ss");
            String timestamp = currentTime.format(formatter);

            String data = "Timestamp: " + timestamp + "\n" +
                    "Owner ID: " + ownerID + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

            JOptionPane.showMessageDialog(null, "The owner information was sent to the file.");

            ownerFrame.dispose();
        });
    }

    private static boolean writeToFile(String data, String fileName) 
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) 
        {
            writer.write(data);
            writer.newLine();
            return true;
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return false;
        }
    }
}