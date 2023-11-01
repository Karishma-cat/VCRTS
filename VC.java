import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VC {
   
    private static String[] clientIds = new String[100];
    private static String[] jobDurations = new String[100];
    private static String[] jobDeadlines = new String[100];
    private static int currentIndex = 0;
    private Client client; 
    private ArrayList<Job> jobs;

    public VC(Client client) {
        this.client = client;
        jobs = new ArrayList<Job>();
    }
    public void addJob(Job j) {
        jobs.add(j);
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
     public double calcCompTime(Job j) {
         double compTime = 0;

         for (Job job : jobs) {
             compTime += job.getDuration();
         }
         return compTime;
     }


    public static void main(String[] args) {
        VRCTSJFrame.initializeGUI();
    }

    public static void openJobSubmission() {
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
            String jobdeadline = jobDeadlineTextField.getText();
            String jobduration = jobDurationTextField.getText();

            clientIds[currentIndex] = clientID;
            jobDurations[currentIndex] = jobduration;
            jobDeadlines[currentIndex] = jobdeadline;
            currentIndex++;

            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy HH:mm:ss");
            String timestamp = currentTime.format(formatter);

            String data = "Timestamp: " + timestamp + "\n" +
                    "Client ID: " + clientID + "\n" +
                    "Job Duration: " + jobduration + "\n" +
                    "Job Deadline: " + jobdeadline + "\n";

            String fileName = "actionlog.txt";
            writeToFile(data, fileName);

            JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);
            JLabel compTime = createStyledLabel("Job Completion Time");
            jobCompletion.setBounds(20, 150, 200, 30);
            jobFrame.add(jobCompletion);

            jobFrame.dispose();
        });

       
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

