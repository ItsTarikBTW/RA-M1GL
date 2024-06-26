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
import java.util.ArrayList;
import java.util.Scanner;

public class ClientUDPint {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");

        // Get user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.toLowerCase().equals("exit")) {
            System.out.println("Enter a string to send to the server (type 'exit' to quit):");
            userInput = scanner.nextLine();

            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream c = new ObjectOutputStream(a);
            c.writeObject(userInput);
            byte[] buffer = a.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
            socket.send(packet);
            System.out.println("Client sent: " + userInput);
            if (userInput.toLowerCase().equals("exit")) {
                break;
            }
            DatagramPacket packet2 = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet2);
            byte[] data = packet2.getData();
            ObjectInputStream c2 = new ObjectInputStream(new ByteArrayInputStream(data));
            Object obj = c2.readObject();
            ArrayList<Joueur> data2;
            System.out.println("Server response: ");
            data2 = (ArrayList<Joueur>) obj;
            for (Joueur joueur : data2) {
                System.out.println("\t"+joueur);
            }
            System.out.println("End of server response");
        }
        scanner.close();
        socket.close();
    }
}
