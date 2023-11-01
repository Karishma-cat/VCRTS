import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

public class VC { 
    static private ArrayList<Job> jobs;
    Client client;

    public VC(Client client) {
        this.client = client;
        jobs = new ArrayList<Job>();
    }
    static public void addJob(Job j) {
        jobs.add(j);
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }
    public static int calcCompTime(int duration) {
         int compTime = 0;
         for (Job job : jobs) {
             compTime += job.getDuration();
         }
         return compTime + duration;
     }
     
     static public int getSize() {
    	 return jobs.size();
     }


    public static void main(String[] args) {
        VRCTSJFrame.initializeGUI();
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
