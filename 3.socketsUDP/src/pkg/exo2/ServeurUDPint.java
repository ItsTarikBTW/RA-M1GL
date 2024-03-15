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

public class ServeurUDPint {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2000);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        socket.receive(packet);
        byte[] data = packet.getData();
        ByteArrayInputStream a = new ByteArrayInputStream(data);
        DataInputStream b = new DataInputStream(a);
        int mode = 2;
        if (mode == 1) {
            ObjectInputStream c = new ObjectInputStream(new ByteArrayInputStream(data));
            int[] data2 = (int[]) c.readObject();
            for (int i = 0; i < data2.length; i++) {
                System.out.println("Entier recu : " + data2[i]);
            }
        } else {
            int readValue = 0;
            while ((readValue = b.readInt()) != 0) {
                System.out.println("Entier recu : " + readValue);
            }
        }

        socket.close();
    }
}
