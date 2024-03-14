/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientEcho {
    public static void main(String[] args) throws IOException {
        Socket sock = null;
        OutputStream sockOut = null;
        InputStream sockIn = null;
        try {
            sock = new Socket("localhost", 7777);
            sockOut = sock.getOutputStream();
            sockIn = sock.getInputStream();
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(2);
        }
        System.out.println("tapez non pour terminer");
        Scanner scan = new Scanner(System.in);
        String message = scan.nextLine().toLowerCase();
        byte[] buffer1 = new byte[1024];
        buffer1 = message.getBytes();
        try {
            sockOut.write(buffer1);
            sockOut.flush();
        } catch (IOException e) {
            System.err.println("erreur d'envoi de message");
            System.exit(3);
        }
        byte[] buffer2 = new byte[1024];
        int lu = sockIn.read(buffer2);
        System.out.println("Mot envoyé par le serveur est : " + new String(buffer2).trim());
        System.out.println("Nombre d'octets lu : " + lu);
        scan.close();
        sockOut.close();
        sockIn.close();
        sock.close();
    }
}
