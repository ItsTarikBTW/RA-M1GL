/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;

public class UDPMulticasSocketClient {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        Entreprise entreprise = new Entreprise(10, "SOGERHWIT");
        MulticastSocket socket = new MulticastSocket();
        InetAddress group = InetAddress.getByName("225.0.0.1");
        socket.joinGroup(group);
        byte[] buffer = new byte[1024];
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeObject(entreprise);
        byte[] data = a.toByteArray();
        DatagramPacket packet = new DatagramPacket(data, data.length, group, 9000);
        socket.send(packet);
        System.out.println("Objet envoyé par le client");
        DatagramPacket recu = new DatagramPacket(buffer, buffer.length);
        socket.receive(recu);
        String response = new String(recu.getData(), 0, recu.getLength());
        System.out.println("Réponse du serveur : " + response);
        socket.leaveGroup(group);
        socket.close();
    }
}
