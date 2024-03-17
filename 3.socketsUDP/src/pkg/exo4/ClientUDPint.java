/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo4;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientUDPint {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.toLowerCase().equals("exit")) {
            do {
                System.out.println("Enter 'String int1 int2' to send to the server (type 'exit' to quit):");
                userInput = scanner.nextLine();
            } while (!userInput.matches("^[a-zA-Z]+\\s\\d+\\s\\d+$") && !userInput.toLowerCase().equals("exit"));
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream c = new ObjectOutputStream(a);
            c.writeObject(userInput);

            byte[] buffer = a.toByteArray();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
            socket.send(packet);
            if (userInput.toLowerCase().equals("exit")) {
                break;
            }
            System.out.println("Client a envoyé une opération");

            DatagramPacket packet2 = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet2);
            byte[] data = packet2.getData();
            ObjectInputStream c2 = new ObjectInputStream(new ByteArrayInputStream(data));
            String data2 = (String) c2.readObject();
            System.out.println("Server response: " + data2);
        }
        scanner.close();
        socket.close();
    }
}
