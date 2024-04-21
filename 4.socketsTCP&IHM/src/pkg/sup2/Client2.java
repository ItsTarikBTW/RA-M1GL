package pkg.sup2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author tarik
 */

public class Client2 {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        Socket sock = null;
        PrintWriter sockOut = null;
        DataInputStream sockIn = null;
        try {
            sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("localhost", 8888);
            sock.bind(sockaddr);
            sock.connect(new InetSocketAddress(hostName, 7777));

            sockOut = new PrintWriter(sock.getOutputStream(), true);
            sockIn = new DataInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : " + hostName);
            System.exit(1);
        }
        System.out.println("tapez non pour terminer");
        Scanner scan = new Scanner(System.in);
        String message = scan.nextLine().toLowerCase();
        while (!message.equals("exit")) { 
            sockOut.println(message);
            try {
                Object recu = sockIn.readBoolean();

                System.out.println("ret? :");
                System.out.println(recu);
            } catch (IOException e) {
                System.err.println("Classe inconnue : " + hostName);
                System.exit(1);
            }
            
            message = scan.nextLine().toLowerCase();
            sockOut.flush();
        }
        sockOut.close();
        sockIn.close();
        sock.close();
        scan.close();
    }
}
