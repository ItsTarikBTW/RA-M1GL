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

public class Recepteur {
    @SuppressWarnings("deprecation")
    public static void main(String argv[]) throws IOException, ClassNotFoundException {
        InetAddress group = InetAddress.getByName("235.1.1.1");
        MulticastSocket socket = new MulticastSocket(4000);
        socket.joinGroup(group);
        byte[] buf = new byte[1024];
        DatagramPacket recv = new DatagramPacket(buf, buf.length);
        System.out.println("En attente de reception ...");
        socket.receive(recv);
        byte[] data = recv.getData();
        ObjectInputStream a = new ObjectInputStream(new ByteArrayInputStream(data));
        Entreprise entreprise = (Entreprise) a.readObject();

        System.out.println("Debut reception\n " + entreprise + " \nFin reception");
        socket.leaveGroup(group);
        socket.close();
    }
}
