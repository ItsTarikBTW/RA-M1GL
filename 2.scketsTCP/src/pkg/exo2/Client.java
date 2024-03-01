/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author tarik
 */

public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        Socket sock = null;
        DataOutputStream sockOut = null;
        DataInputStream sockIn = null;
        try {
            sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("localhost", 8888);
            sock.bind(sockaddr);
            sock.connect(new InetSocketAddress(hostName, 7777));

            sockOut = new DataOutputStream(sock.getOutputStream());
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
        String message = scan.next().toLowerCase();
        while (!message.equals("exit")) {
            if (!message.matches("\\d+")) {
                System.out.println("invalid input");
                sockOut.writeLong(-1);
            } else {
                int input = Integer.parseInt(message);
                sockOut.writeLong(input);
                try {
                    Object recu = sockIn.readUTF();
                    if (recu == null)
                        System.out.println("erreur de connection");
                    else {
                        System.out.println("Les nombres de Fibonacci sont :");
                        System.out.println(recu);
                    }
                } catch (IOException e) {
                    System.err.println("Classe inconnue : " + hostName);
                    System.exit(1);
                }
            }
            message = scan.next().toLowerCase();
            sockOut.flush();
        }
        sockOut.close();
        sockIn.close();
        sock.close();
        scan.close();
    }
}
