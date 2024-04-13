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

public class ServeurUDPint {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(2000);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        ArrayList<Integer> odd = new ArrayList<>();
        ArrayList<Integer> even = new ArrayList<>();

        while (true) {
            
            socket.receive(packet);
            byte[] data = packet.getData();

            ObjectInputStream c = new ObjectInputStream(new ByteArrayInputStream(data));
            String data2 = (String) c.readObject();
            if (data2.equals("exit")) {
                break;
            }
            int data3 = Integer.parseInt(data2);
            if(data3 % 2 == 0){
                even.add(data3);
            }else{
                odd.add(data3);
            }
        }
        ByteArrayOutputStream a = new ByteArrayOutputStream();
        ObjectOutputStream c2 = new ObjectOutputStream(a);
        c2.writeObject(odd);
        c2.writeObject(even);
        byte[] buffer = a.toByteArray();
        DatagramPacket packet2 = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
        socket.send(packet2);

        socket.close();
    }
}
