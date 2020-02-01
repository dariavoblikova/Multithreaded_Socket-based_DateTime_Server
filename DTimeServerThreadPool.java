import java.io.*;
import java.net.*;

public class DTimeServerThreadPool implements Runnable{
    private Socket client;

    DTimeServerThreadPool (Socket client) {
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
            System.out.println("Accepting ...");
            var thread_pool = Executors.newFixedThreadPool(20);

            while (true) {
                thread_pool.execute(new DTimeServerThreadPool(my_socket.accept()));

            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}