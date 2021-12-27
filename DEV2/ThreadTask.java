
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ThreadTask implements Runnable {
     public Socket socket ;
     public ThreadTask(Socket socket){
         this.socket = socket;
     }
    
    @Override
    public void run() {
         try {
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             String line = null;
             while((line = bufferedReader.readLine())!= null){
                 if(line.contains("List")){
                    String repName = line.replace("List ","");
                    File file = new File(repName);
                    String[] directory = file.list();
                    String rep = String.join("         ", directory);
                    printWriter.println(rep);
                }else if(line.contains("Get")){
                    String fileName = line.replace("Get ","");
                    BufferedReader in = new BufferedReader(new FileReader(fileName));
                    printWriter.println(in.readLine());
                    
                }else if(line.contains("QUIT")){
                    printWriter.println("QUIT");
                    break;
                }else{
                    System.out.println("Error!");
                    printWriter.println("ERROR! unavailable command");
                }
                 printWriter.flush();
             }
         } catch (IOException ex) {
             Logger.getLogger(ThredTask.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }

   
}
