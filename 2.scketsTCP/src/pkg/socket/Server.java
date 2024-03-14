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
import java.util.HashMap;

class Server {
    public static void main(String args[]) {
        ServerSocket server = null;
        File outputFile = new File("./src/socket.txt");
        HashMap<Integer,Integer> fact= new HashMap<>();
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String recu;
                int number;
                while ((recu = sockIn.readLine()) != null) {
                    number = Integer.parseInt(recu);
                    PrintWriter out = new PrintWriter(new FileWriter(outputFile, true));
                    if(existInHashMap(number,fact)){
                        sockOut.println("error : number already exist in file");
                    }else{
                        fact.put(number,factorielle(number));
                        out.println(factorielle(number));
                        sockOut.println("number added to file");
                        
                    }
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
    public static int factorielle(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorielle(n - 1);
        }
    }
    public static Boolean existInFile(int recu,File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int number ;
            while ((line = br.readLine()) != null) {
                number = Integer.parseInt(line);
                if (number == factorielle(recu)) {
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Boolean existInHashMap(int recu,HashMap<Integer,Integer> fact) {
        if(fact.containsKey(recu)){
            return true;
        }
        return false;
    }
} // fin classe
