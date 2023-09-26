import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
    }
}
