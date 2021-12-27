

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            System.out.println("waiting for clinet...");
            Socket socket = serverSocket.accept();
            PrintWriter out =new PrintWriter(socket.getOutputStream());
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(true){
                String clientInput = input.readLine();
                if(clientInput.contains("List")){
                    String repName = clientInput.replace("List ","");
                    File file = new File(repName);
                    String[] directory = file.list();
                    String rep = String.join("         ", directory);
                    out.println(rep);
                    
                }else if(clientInput.contains("Get")){
                    String fileName = clientInput.replace("Get ","");
                    BufferedReader in = new BufferedReader(new FileReader(fileName));
                    out.println(in.readLine());
              
                }else if(clientInput.contains("QUIT")){
                    out.println("QUIT");
                    break;
                }else{
                    System.out.println("Error!");
                    out.println("ERROR!  unavailable command");
                }
                out.flush();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
