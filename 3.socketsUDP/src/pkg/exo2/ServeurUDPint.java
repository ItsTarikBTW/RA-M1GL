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
        DatagramPacket packet = new DatagramPacket(new byte[1024] , 1024);
        socket.receive(packet);
        byte[] data = packet.getData();
        ByteArrayInputStream a = new ByteArrayInputStream(data);
         DataInputStream b = new DataInputStream(a);
         System.out.println("Entier recu : "+b.readInt());
         System.out.println("Boolean recu : "+b.readBoolean());
         System.out.println("String recu : "+b.readUTF());
         System.out.println("Double recu : "+b.readDouble());
}
}

