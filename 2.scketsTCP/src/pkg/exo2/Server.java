/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

import java.net.*;
import java.io.*;

/**
 *
 * @author tarik
 */

class Server {
    public static void main(String args[]) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                try {
                    int sendBufferSize = sock.getSendBufferSize();
                    int receiveBufferSize = sock.getReceiveBufferSize();
                    System.out.println("Send buffer size: " + sendBufferSize);
                    System.out.println("Receive buffer size: " + receiveBufferSize);
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                Long recu;
                while ((recu = sockIn.readLong()) != null) {
                    System.out.println("recu :" + recu);
                    if (recu != -1) {
                        System.out.println("recu est un nombre");
                        sockOut.writeUTF(fibonnaci(recu));

                    } else {
                        System.out.println("recu est un String");
                        sockOut.writeUTF("invalid input");
                    }
                }
                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        }
    }// fin main

    public static String fibonnaci(Long n) {
        String res = "";
        long a = 0, b = 1, c = 0;
        for (int i = 0; i <= n; i++) {
            res += a + " ";
            c = a + b;
            a = b;
            b = c;
        }
        return res;
    }

}// fin classe
