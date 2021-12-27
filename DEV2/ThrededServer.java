

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThrededServer {
    public static void main(String args[]){
        try {
            ServerSocket serverSocket =new ServerSocket(9090);
            System.out.println("waiting for clients...");
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("client connected!");
                ThredTask t = new ThredTask(socket);
                Thread thread = new Thread(t);
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ThrededServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
