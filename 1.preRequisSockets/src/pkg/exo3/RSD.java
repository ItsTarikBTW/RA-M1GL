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

public class RSD {
    public static void main(String[] args) throws IOException {
        String host = "www.itstarik.me"; // ou n’importe quelle autre machine (localhost en local par ex.)
        InetAddress ip = InetAddress.getByName(host);
        NetworkInterface netif = NetworkInterface.getByInetAddress(ip);
        int ttl = 128;
        int timeout = 5000; 
        int debut = (int) System.currentTimeMillis();
        boolean var = ip.isReachable(netif, ttl, timeout);
        if (var)
            System.out.println("OK dans " + ((int) System.currentTimeMillis() - debut) + " ms");
        else
            System.out.println("Not OK");
    }
}
