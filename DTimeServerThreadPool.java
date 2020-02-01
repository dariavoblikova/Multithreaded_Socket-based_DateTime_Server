import java.io.*;
import java.net.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class DTimeServerThreadPool implements Runnable{
    private Socket client;

    DTimeServerThreadPool (Socket client) {
        this.client = client;
    }

    public void run(){
        System.out.println("Accepted a socket");
        System.out.println("Thread name is " + Thread.currentThread().getName());
        try {
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
            ExecutorService thread_pool = Executors.newFixedThreadPool(10);

            while (true) {
                System.out.println("Accepting ...");
                thread_pool.execute(new DTimeServerThreadPool(my_socket.accept()));
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}