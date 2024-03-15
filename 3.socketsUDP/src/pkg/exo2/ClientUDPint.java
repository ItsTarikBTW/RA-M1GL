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
        DatagramSocket socket = new DatagramSocket();                                
        InetAddress serveur = InetAddress.getByName("localhost");
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        DataOutputStream b = new DataOutputStream(a);
        // Ecrire : 10 true bonjour 1.2 dans le outputstream
        b.writeInt(10);
        b.writeBoolean(true);
        b.writeUTF("bonjour");
        b.writeDouble(1.2);
        b.flush();
       byte[] buffer = a.toByteArray();
       DatagramPacket packet = new DatagramPacket(buffer,buffer.length,serveur ,2000); 
       socket.send(packet);
       System.out.println("Client a envoyé 10 true bonjour 1.2 au serveur");
}
}

