/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo5;

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
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        File evenFile = new File("./src/even.txt");
        File oddFile = new File("./src/odd.txt");

        while (!userInput.toLowerCase().equals("exit")) {
            do {
                System.out.println("Enter int to send to the server (type 'exit' to quit):");
                userInput = scanner.nextLine();
                ByteArrayOutputStream a = new ByteArrayOutputStream();
                ObjectOutputStream c = new ObjectOutputStream(a);
                c.writeObject(userInput);
                byte[] buffer = a.toByteArray();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
                socket.send(packet);
            } while (!userInput.toLowerCase().equals("exit"));

            System.out.println("Client a envoyé une list int");

            DatagramPacket packet2 = new DatagramPacket(new byte[1024], 1024);
            socket.receive(packet2);
              byte[] data = packet2.getData();
                ObjectInputStream c2 = new ObjectInputStream(new ByteArrayInputStream(data));
                ArrayList<Integer> odd = (ArrayList<Integer>) c2.readObject();
                ArrayList<Integer> even = (ArrayList<Integer>) c2.readObject();
                PrintWriter oddf = new PrintWriter(new FileWriter(oddFile, true));
                PrintWriter evenf = new PrintWriter(new FileWriter(evenFile, true));
                for (Integer integer : odd) {
                    oddf.println(integer);
                }
                for (Integer integer : even) {
                    evenf.println(integer);
                }
                System.out.println("success");
                oddf.close();
                evenf.close();
        }

        scanner.close();
        socket.close();
    }
}
