import java.net.*;
import java.io.*;
import java.net.InetAddress;

/**
 * This is the chat client program.
 * Type 'bye' to terminte the program.
 *
 * @author www.codejava.net
 */
public class ChatClient {
    private String hostname; 
    private String hostname2;
    private int port;
    private String userName;
    private InetAddress ip;
 
    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }
 
    public void execute() {
        
         
        try {
            Socket socket = new Socket(hostname, port);
             
       
                System.out.println("Connected to the chat server");
           
            System.out.println("Your current port number : " + port);
                   System.out.println("Syntax: java ChatServer <port-number>");
             InetAddress localhost = InetAddress.getLocalHost();
             System.out.println(localhost);
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
 
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
 
    void setUserName(String userName) {
        this.userName = userName;
    }
 
    String getUserName() {
        return this.userName;
    }
 
 
    public static void main(String[] args) {
        if (args.length < 2) return;
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        ChatClient client = new ChatClient(hostname, port);
        client.execute();
    }
}
