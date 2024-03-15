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

public class UDPSocketClient {
    public static void main(String[] args) throws Exception {
        Entreprise entreprise = new Entreprise(10, "SOGERHWIT");
        DatagramSocket Socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");
        byte[] buffer = new byte[1024];
        // définir une structure dans laquelle les données sont écrites dans un tableau
        // d'octets
        ByteArrayOutputStream a = new ByteArrayOutputStream(); // classe fille de OutputStream
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeObject(entreprise);
        // copier le contenu de a (OutputStream) dans un tableau d'octets pour
        // l'utiliser dans DatagramPacket
        byte[] data = a.toByteArray();
        DatagramPacket envoyer = new DatagramPacket(data, data.length, serveur, 9000);
        Socket.send(envoyer);
        System.out.println("Objet envoyé par le client");
        DatagramPacket recu = new DatagramPacket(buffer, buffer.length);
        Socket.receive(recu);
        String response = new String(recu.getData());
        System.out.println("Réponse du serveur : " + response);
        Socket.close();
    }
}
