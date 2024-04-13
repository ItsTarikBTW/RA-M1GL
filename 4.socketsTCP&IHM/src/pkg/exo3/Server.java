/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        while (true) {      
            Socket socket = server.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); 
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String requete = ""; 
            try {
                while ((requete = dis.readUTF()) != null) {
                    String[] parts = requete.split(" ");
                    String opp = parts[0].toUpperCase();
                    int opd1 = Integer.parseInt(parts[1]);
                    int opd2 = Integer.parseInt(parts[2]);
                    double result = 0;
                    switch (opp) {
                        case "ADD":
                            result = opd1 + opd2;
                            break;
                        case "MUL":
                            result = opd1 * opd2;
                            break;
                        case "SOUS":
                            result = opd1 - opd2;
                            break;
                        case "DIV":
                            if (opd2 == 0) {
                                dos.writeUTF("Division par zero");
                                dos.flush();
                                continue;
                            }
                            result = (double) opd1 / opd2;
                            break;
                        case "PUIS":
                            result = Math.pow(opd1, opd2);
                            break;
                    }
                    dos.writeUTF(String.valueOf(result));
                    dos.flush();
                }                 
            } catch (EOFException e) {
                System.out.println("Client disconnected");
            }
        }
    }    
}
