/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;

public class ClientUDPint {
    public static void main(String[] args) throws Exception {
        int[] data = { 10, 20, 30, 40, 50 };
        DatagramSocket socket = new DatagramSocket();
        InetAddress serveur = InetAddress.getByName("localhost");
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        DataOutputStream b = new DataOutputStream(a);
        //INFO: mode = 1 pour envoyer l'objet tableau d'entiers , mode = 2 pour envoyer un entier à la fois
        int mode = 2;
        if (mode == 1) {
            ObjectOutputStream c = new ObjectOutputStream(a);
            c.writeObject(data);
        } else {
            for (int i = 0; i < data.length; i++) {
                b.writeInt(data[i]);
            }
            b.flush();
        }
        byte[] buffer = a.toByteArray();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serveur, 2000);
        socket.send(packet);
        System.out.println("Client a envoyé tableau d'entiers");
        socket.close();
    }
}
