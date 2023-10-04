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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class VRCTSJFrame {
    private static JFrame frame;

    public static void main(String[] args) {
        initializeGUI();
    }

    private static void initializeGUI() {
        JFrame frame = new JFrame("Vehicle Cloud Real Time System");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel);

        JLabel welcomeLabel = createStyledLabel("Welcome to the Vehicular Cloud Real Time System");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        JLabel messageLabel = createStyledLabel("Please select one of the options below:");
        buttonPanel.add(messageLabel);

        JButton jobButton = createStyledButton("Submit a Job");
        buttonPanel.add(jobButton);

        JButton ownerButton = createStyledButton("Login to Owner Panel");
        buttonPanel.add(ownerButton);

        frame.setVisible(true);

        jobButton.addActionListener(e -> openJobSubmission());
        ownerButton.addActionListener(f -> openOwnerPanel());
    }

    private static void openJobSubmission() {
        JFrame jobFrame = new JFrame("Job Submission");
        jobFrame.setSize(300, 350);

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

        JTextField jobDeadlineTextField = new JTextField("");
        jobDeadlineTextField.setBounds(20, 180, 200, 30);
        jobFrame.add(jobDeadlineTextField);

        JButton submitJobButton = new JButton("Submit Job");
        submitJobButton.setBounds(20, 260, 150, 30);
        jobFrame.add(submitJobButton);

        jobFrame.setLayout(null);
        jobFrame.setVisible(true);

        submitJobButton.addActionListener(event -> {
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

    private static void openOwnerPanel() {
        JFrame ownerFrame = new JFrame("Owner Panel");
        ownerFrame.setSize(300, 350);

        JLabel ownerIDLabel = createStyledLabel("Owner ID:");
        ownerIDLabel.setBounds(20, 20, 400, 30);
        ownerFrame.add(ownerIDLabel);

        JTextField ownerIDTextField = new JTextField("");
        ownerIDTextField.setBounds(20, 60, 200, 30);
        ownerFrame.add(ownerIDTextField);

        JLabel vehicleInfo = createStyledLabel("Vehicle Information:");
        vehicleInfo.setBounds(20, 90, 200, 30);
        ownerFrame.add(vehicleInfo);

        JTextField vehicleInfoTextField = new JTextField("");
        vehicleInfoTextField.setBounds(20, 120, 200, 30);
        ownerFrame.add(vehicleInfoTextField);

        JLabel residentTime = createStyledLabel("Residency Time:");
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

        submitOwnerInfoButton.addActionListener(event -> {
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

    private static JButton createStyledButton(String text) 
    {
        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(200, 40));
        button.setFont(new Font("Arial", Font.BOLD, 14));

        button.setBackground(new Color(255,255,255));

        button.setForeground(Color.PINK);

        LineBorder border = new LineBorder(Color.PINK, 2);
        button.setBorder(border);

        button.setFocusPainted(false);
        button.setBorderPainted(true); 
        button.setOpaque(true); 

        return button;
    }

    private static JLabel createStyledLabel(String text) 
    {
        JLabel label = new JLabel(text);

        label.setFont(new Font("Arial", Font.BOLD, 12)); 
        label.setForeground(Color.PINK);

        return label;
    }
}
