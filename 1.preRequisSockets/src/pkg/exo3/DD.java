/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

import java.net.*;

/**
 *
 * @author tarik
 */
public class DD {
    public static void main(String[] args) {
        String host = "www.itstarik.me";
        //String host = "localhost";
        // String host = "www.facebook.com"; si vous êtes connectés à internet
        mystere(host);
    }

    private static void mystere(String host) {
        try {
            InetAddress b = InetAddress.getByName(host);
            boolean var = b.isReachable(2000);
            if (var)
                System.out.println("OK : " + host);
            else
                System.out.println("Not OK : " + host);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
