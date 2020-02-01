import java.io.*;
import java.net.*;

public class DTimeServerThreaded implements Runnable{
    private Socket client;

    DTimeServerThreaded (Socket client) {
        this.client = client;
    }

    public void run(){
        try {
            System.out.println("Accepted a socket");
            PrintWriter pout = new PrintWriter(client.getOutputStream(), true); // writing
            pout.println(new java.util.Date().toString());
        }
        catch (Exception e) {
            System.out.println("Error:" + client);
        } finally {
            try { 
                client.close();
            } catch (IOException e) {}
                System.out.println("Closed: " + client);
            }
    }
    public static void main(String[] args) {
        try {
            ServerSocket my_socket = new ServerSocket(6013);
            while (true) {
                System.out.println("Accepting ...");
                Socket client = my_socket.accept();

                // new Thread(new DTimeServerThreaded(client)).start();
                Thread t = new Thread(new DTimeServerThreaded(client));
                t.start();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}