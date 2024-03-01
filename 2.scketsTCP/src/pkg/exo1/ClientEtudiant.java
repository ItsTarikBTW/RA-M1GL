/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author tarik
 */

public class ClientEtudiant {
  public static void main(String[] args) throws IOException {
    String hostName = "localhost";
    // String NomEtudiant = "A";
    Socket sock = null;
    PrintWriter sockOut = null;
    ObjectInputStream sockIn = null;
    try {
      // ? QUESTION 2.1
      /*
       * InetAddress localAddr = InetAddress.getByName("localhost");
       * sock = new Socket(hostName, 7777, localAddr, 8888);
       */
      // ? QUESTION 3
      sock = new Socket();
      SocketAddress sockaddr = new InetSocketAddress("localhost", 8888);
      sock.bind(sockaddr);
      sock.connect(new InetSocketAddress(hostName, 7777));

      sockOut = new PrintWriter(sock.getOutputStream(), true);
      sockIn = new ObjectInputStream(sock.getInputStream());
      try {
        // ? QUESTION 1: get size of send and receive buffers
        int sendBufferSize = sock.getSendBufferSize();
        int receiveBufferSize = sock.getReceiveBufferSize();
        System.out.println("Send buffer size: " + sendBufferSize);
        System.out.println("Receive buffer size: " + receiveBufferSize);

        // ? QUESTION 2
        int localPortRead = sock.getLocalPort();
        int remotePortRead = sock.getPort();
        System.out.println("Local port: " + localPortRead);
        System.out.println("Remote port: " + remotePortRead);
      } catch (SocketException e) {
        e.printStackTrace();
      }
    } catch (UnknownHostException e) {
      System.err.println("host non atteignable : " + hostName);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("connection impossible avec : " + hostName);
      System.exit(1);
    }
    System.out.println("tapez non pour terminer");
    Scanner scan = new Scanner(System.in);
    String message = scan.next().toLowerCase();
    while (!message.equals("exit")) {
      sockOut.println(message); // envoyer le nom au serveur
      try {
        Object recu = sockIn.readObject(); // récupérer l’objet Etudiant envoyé par le serveur
        if (recu == null)
          System.out.println("erreur de connection");
        else if (recu instanceof String) 
          System.out.println("ERROR:" + recu);
        else if (recu instanceof Etudiant[]) {
          Etudiant[] etudiants = (Etudiant[]) recu;
          for (Etudiant etudiant : etudiants) {
            System.out.println("serveur -> client : " + etudiant);
          }
        }
        else if(recu instanceof Etudiant){
          Etudiant etudiant = (Etudiant) recu;
          System.out.println("serveur -> client : " + etudiant);
        }
        else {
          System.out.println("unexpected object");
        }
      } catch (ClassNotFoundException e) {
        System.err.println("Classe inconnue : " + hostName);
        System.exit(1);
      }
      message = scan.next().toLowerCase();
    }
    sockOut.close();
    sockIn.close();
    sock.close();
    scan.close();
  }
}
