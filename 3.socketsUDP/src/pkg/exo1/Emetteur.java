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

public class Emetteur {
    public static void main(String argv[]) throws IOException {
        InetAddress group = InetAddress.getByName("235.1.1.1");
        Entreprise e = new Entreprise (10, "SOGERHWIT");
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream b = new ObjectOutputStream(a);
        b.writeObject(e);
        byte[] data = a.toByteArray();
        DatagramPacket packet = new DatagramPacket(data, data.length, group, 4000);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        System.out.println("Fin emission");
        socket.close();
    }
}
/*QUESTION: Modifier l’adresse IP "230.0.0.0" par "200.0.0.0"
 * 200.0.0.0 n'est pas unclue dans [224.0.0.0 , 239.255.255.255 ]
 */