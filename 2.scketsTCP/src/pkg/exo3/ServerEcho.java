/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

/**
 *
 * @author tarik
 */
import java.net.*;
import java.io.*;

class ServerEcho {
    public static void main(String args[]) {
        try (ServerSocket server = new ServerSocket(7777)) {
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                new ClientHandler(sock).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
