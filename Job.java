import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

class Job {
    public Job(Client client) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = currentTime.format(formatter);

        String data = "Timestamp: " + timestamp + "\n" +
                "Client Name: " + client.getName() + "\n";

        String fileName = "actionlog.txt";
        writeToFile(data, fileName);

        // Writing the data to the file and display a confirmation dialog
        JOptionPane.showMessageDialog(null, "Client data submitted and saved to " + fileName);
    }

    private static void writeToFile(String data, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
