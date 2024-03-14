/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.socket;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) throws IOException {
    Socket sock = null;
    PrintWriter sockOut = null;
    BufferedReader sockIn = null;
    try {
      sock = new Socket("localhost", 7777);
      sockOut = new PrintWriter(sock.getOutputStream(), true);
      sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
    } catch (UnknownHostException e) {
      System.err.println("host non atteignable : localhost");
      System.exit(1);
    } catch (IOException e) {
      System.err.println("connection impossible avec : localhost");
      System.exit(1);
    }
    System.out.println("tapez non pour terminer");
    Scanner scan = new Scanner(System.in);
    String message;
    do {
      message = scan.next().trim();
    } while (!message.matches("\\d+"));
    int number = Integer.parseInt(message);
    while (number != 0) {
      sockOut.println(message);
      String recu = sockIn.readLine();
      System.out.println("serveur -> client :" + recu);
      do {
        message = scan.next().trim();
      } while (!message.matches("\\d+"));
      number = Integer.parseInt(message);
    }
    sockOut.close();
    sockIn.close();
    sock.close();
    scan.close();
  }

}
