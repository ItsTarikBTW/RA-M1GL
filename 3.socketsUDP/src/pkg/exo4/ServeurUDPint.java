/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo4;

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
        while (true) {
            socket.receive(packet);
            byte[] data = packet.getData();

            ObjectInputStream c = new ObjectInputStream(new ByteArrayInputStream(data));
            String data2 = (String) c.readObject();
            if (data2.equals("exit")) {
                break;
            }
            String[] data3 = data2.split(" ");

            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream c2 = new ObjectOutputStream(a);
            int res = 0;
            float res2 = 0;
            switch (data3[0]) {
                case "ADD":
                    res = Integer.parseInt(data3[1]) + Integer.parseInt(data3[2]);
                    c2.writeObject(res+"");
                    break;
                case "MUL":
                    res = Integer.parseInt(data3[1]) * Integer.parseInt(data3[2]);
                    c2.writeObject(res+"");
                    break;
                case "SOUS":
                    res = Integer.parseInt(data3[1]) - Integer.parseInt(data3[2]);
                    c2.writeObject(res+"");
                    break;
                case "DIV":
                    res2 = Float.parseFloat(data3[1]) / Float.parseFloat(data3[2]);
                    c2.writeObject(res2+"");
                    break;
                case "PUIS":
                    res = (int) Math.pow(Integer.parseInt(data3[1]), Integer.parseInt(data3[2]));
                    c2.writeObject(res+"");
                    break;

                default:
                    c2.writeObject("Invalid operation");
                    break;
            }
            byte[] buffer = a.toByteArray();
            DatagramPacket packet2 =new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.send(packet2);
        }
        socket.close();
    }
}
