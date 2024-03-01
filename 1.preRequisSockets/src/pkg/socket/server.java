/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.socket;

/**
 * @author tarik
 */

import java.net.*;
import java.io.*;

class Server {
    public static void main(String args[]) {
        ServerSocket server = null;
        File outputFile = new File("./src/socket.txt");
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String recu;
                while ((recu = sockIn.readLine()) != null) {
                    PrintWriter out = new PrintWriter(new FileWriter(outputFile, true));
                    System.out.println("recu :" + recu);
                    out.println(recu);
                    sockOut.println("suivant ? ");
                    out.close();
                } // fin while
                sockOut.close();
                sockIn.close();
                sock.close();
            } // fin while (true)
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        } // fin premier catch
    }// fin main
} // fin classe
