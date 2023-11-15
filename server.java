import java.io.*;
import java.net.*;
public class server {
    private ServerSocket serverSocket;

    public server(int port){
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void startServer(){
        while(true){
            try{
                Socket clientSocket= serverSocket.accept();
                handleClient(clientSocket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
           private void handleClient(Socket clientSocket) {
            try (
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                PrintWriter writer = new PrintWriter(outputStream, true);
            ) {
                
                String clientData = reader.readLine();
                
                
                boolean isAuthorized = authorizeData(clientData);
        
                
                if (isAuthorized) {
                    writer.println("Accept"); 
                } else {
                    writer.println("Reject"); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private boolean authorizeData(String data) {
            return true;
        }

    }

