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

    	//Creates gui
        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setTitle("Vehicle Cloud Real Time System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creates a prompt on the gui to welcome the user
        JLabel label1 = new JLabel("Welcome to the Vehicular Cloud Real Time System");
        label1.setBounds(50, 50, 200, 30);
        frame.add(label1);

        //creates a prompt telling the user to make a selection
        JLabel label2 = new JLabel("Please select one of the options below");
        label2.setBounds(50, 80, 200, 30);
        frame.add(label2);

        //creates a button to submit jobs
        JButton jobButton = new JButton("Submit a Job");
        jobButton.setBounds(50, 120, 150, 30);
        frame.add(jobButton);

        //creates a button for car owners
        JButton ownerButton = new JButton("Login to Owner Panel");
        ownerButton.setBounds(50, 160, 150, 30);
        frame.add(ownerButton);

        //makes the gui visible
        frame.setLayout(null);
        frame.setVisible(true);

        //changes the gui to next screen when the submit job button is pressed
        //alex section(note to self: double check for spelling erro)
        jobButton.addActionListener(e -> {
            
            JFrame jobFrame = new JFrame("Job Submission");
            jobFrame.setSize(300, 350);

            //Asks user to enter information for the client id
            JLabel jobLabel = new JLabel("Cilent ID:");
            jobLabel.setBounds(20, 20, 200, 30);
            jobFrame.add(jobLabel);

            //text field for user to enter client id 
            JTextField clientIdField = new JTextField("");
            clientIdField.setBounds(20, 60, 200, 30);
            jobFrame.add(clientIdField);
            
            //Asks user to enter the approximate duration of the task being entered
            JLabel jobDuration = new JLabel("Approximate duration of task:");
            jobDuration.setBounds(20, 90, 200, 30);
            jobFrame.add(jobDuration);
            
            //text field for user to enter time duration
            JTextField jobDurationTextField = new JTextField("");
            jobDurationTextField.setBounds(20, 120, 200, 30);
            jobFrame.add(jobDurationTextField);
            
            //Asks user to enter dead line date for the job
            JLabel jobDeadline = new JLabel ("Job Deadline: (mm/dd/yyyy)");
            jobDeadline.setBounds(20, 150, 200, 30);
            jobFrame.add(jobDeadline);
            
            //text field for user to enter dead line date
            JTextField jobDeadlineTextField = new JTextField("");
            jobDeadlineTextField.setBounds(20, 180, 200, 30);
            jobFrame.add(jobDeadlineTextField);      

            //submit button to enter the information entered
            JButton submitJobButton = new JButton("Submit Job");
            submitJobButton.setBounds(20, 260, 150, 30);
            jobFrame.add(submitJobButton);

            jobFrame.setLayout(null);
            jobFrame.setVisible(true);

           
            //submits the information that was provided for CLIENT
            submitJobButton.addActionListener(event -> {
               
                String clientID = clientIdField.getText();
                
                LocalDateTime currentTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timestamp = currentTime.format(formatter);

                
                String data = "Timestamp: " + timestamp + "\n"
                        + "Client ID: " + clientID + "\n";
                        

                
                        String fileName = "clientlog.txt"; 
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                            writer.write(data);
                            writer.newLine(); 
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        

                //Allowing the user to know that the information was successfully proccessed
                JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);

            
                jobFrame.dispose();
            });

ownerButton.addActionListener(f -> {
    JFrame ownerFrame = new JFrame("Owner Panel");
    ownerFrame.setSize(300, 350);
    
  // creates Owner ID 
    JLabel ownerIDLabel = new JLabel("Owner ID:");
    ownerIDLabel.setBounds(20, 20, 200, 30);
    ownerFrame.add(ownerIDLabel);
//text field to enter the Owner ID 
    JTextField ownerIDTextField = new JTextField("");
    ownerIDTextField.setBounds(20, 60, 200, 30);
    ownerFrame.add(ownerIDTextField);

    JButton submitOwnerInfoButton = new JButton("Submit Owner Info");
    submitOwnerInfoButton.setBounds(20, 260, 150, 30);
    ownerFrame.add(submitOwnerInfoButton);

    ownerFrame.setLayout(null);
    ownerFrame.setVisible(true);

    submitOwnerInfoButton.addActionListener(event -> {
        String ownerID = ownerIDTextField.getText();
        // Extract and process other owner information fields as needed...

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = currentTime.format(formatter);

        String data = "Timestamp: " + timestamp + "\n" +
                "Owner ID: " + ownerID + "\n";
        // Add other owner information to the data...

        String fileName = "ownerlog.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Owner information submitted and saved to " + fileName);

        ownerFrame.dispose();
    });
});





        });
        
    }
}
