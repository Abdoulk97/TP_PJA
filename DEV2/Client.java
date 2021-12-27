


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Client {
    
    public static void main(String args[]){
        
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            Socket socket = new Socket(serverAddress, 9090);
            Scanner scanner =new Scanner(System.in);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            String line = null;
            while(true){
                System.out.print("command:");
                line = scanner.nextLine();
                printWriter.println(line);
                printWriter.flush();
                String respond = bufferedReader.readLine() ;
                if(respond.contains("QUIT")){
                    break;
                }
                System.out.println(respond);
            }
  
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
    }
}
