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

public class UDPMulticastSocketServer {
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception {
        MulticastSocket socket = new MulticastSocket(9000);
        InetAddress group = InetAddress.getByName("225.0.0.1");
        socket.joinGroup(group);
        byte[] buffer = new byte[1024];
        DatagramPacket recu = new DatagramPacket(buffer, buffer.length);
        socket.receive(recu);
        byte[] data = recu.getData();
        // ByteArrayInputStream classe fille de InputStream
        ObjectInputStream a = new ObjectInputStream(new ByteArrayInputStream(data));
        Entreprise entreprise = (Entreprise) a.readObject();
        System.out.println("L'objet entreprise reçu : " + entreprise);
        InetAddress IPAddress = recu.getAddress();
        int port = recu.getPort();
        String reponse = "J'ai bien reçu l'objet";
        byte[] d = reponse.getBytes();
        DatagramPacket envoyer = new DatagramPacket(d, d.length, IPAddress, port);
        socket.send(envoyer);
        socket.leaveGroup(group);
        socket.close();
    }
}
