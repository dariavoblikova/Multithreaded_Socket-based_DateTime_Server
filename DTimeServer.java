import java.io.*;
import java.net.*;

public class DTimeServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            while (true) {
                System.out.println("Accepting ...");
                Socket client = sock.accept(); // blocking call
                System.out.println("Accepted a socket");
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true); // writing
                pout.println(new java.util.Date().toString());
                pout.println("blblallalal");
                client.close(); // now need to accept again if somebody else wants to connect
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }
}