import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VRCTSJFrame {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setTitle("Vehicle Cloud Real Time System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label1 = new JLabel("Welcome to the Vehicular Cloud Real Time System");
        label1.setBounds(50, 50, 200, 30);
        frame.add(label1);

        JLabel label2 = new JLabel("Please select one of the options below");
        label2.setBounds(50, 80, 200, 30);
        frame.add(label2);

        JButton jobButton = new JButton("Submit a Job");
        jobButton.setBounds(50, 120, 150, 30);
        frame.add(jobButton);

        JButton ownerButton = new JButton("Login to Owner Panel");
        ownerButton.setBounds(50, 160, 150, 30);
        frame.add(ownerButton);

        frame.setLayout(null);
        frame.setVisible(true);

        //alex section(note to self: double check for spelling erro)
        jobButton.addActionListener(e -> {
            
            JFrame jobFrame = new JFrame("Job Submission");
            jobFrame.setSize(300, 350);

            JLabel jobLabel = new JLabel("Cilent ID:");
            jobLabel.setBounds(20, 20, 200, 30);
            jobFrame.add(jobLabel);

            JTextField ownerIdField = new JTextField("");
            ownerIdField.setBounds(20, 60, 200, 30);
            jobFrame.add(ownerIdField);
            
            JLabel jobDuration = new JLabel("Approximate duration of task:");
            jobDuration.setBounds(20, 90, 200, 30);
            jobFrame.add(jobDuration);
            
            JTextField jobDurationTextField = new JTextField("");
            jobDurationTextField.setBounds(20, 120, 200, 30);
            jobFrame.add(jobDurationTextField);
            
            JLabel jobDeadline = new JLabel ("Job Deadline: (mm/dd/yyyy)");
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
               
                String ownerID = ownerIdField.getText();
                
                
                
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timestamp = currentTime.format(formatter);

                
                String data = "Timestamp: " + timestamp + "\n"
                        + "Owner ID: " + ownerID + "\n";
                        

                
                        String fileName = "actionlog.txt"; 
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                            writer.write(data);
                            writer.newLine(); 
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        

                
                JOptionPane.showMessageDialog(null, "Data submitted and saved to " + fileName);

            
                jobFrame.dispose();
            });
        });
        
    }
}
