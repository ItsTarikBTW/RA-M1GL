package pkg.sup1;

import java.net.*;
import java.io.*;

/**
 *
 * @author tarik
 */

 class Server1 {
    public static void main(String args[]) {
        File dbFile = new File("C:/Users/tarik/OneDrive/Documents/S2/RA/TP/4.socketsTCP&IHM/src/pkg/sup1.txt");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                try (Socket sock = server.accept();
                     DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                     BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {

                    System.out.println("Connected");

                    String recu;
                    while ((recu = sockIn.readLine()) != null) {
                        int count = 0;
                        System.out.println("Received :" + recu);
                        // read from file dbFile
                        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
                            System.out.println("Reading file");
                            String line;
                            while ((line = reader.readLine()) != null) {
                                count += occ(recu, line);
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + e.getMessage());
                        }

                        System.out.println("Count : " + count);
                        sockOut.writeLong(count);
                    }
                } catch (IOException e) {
                    System.err.println("Error with client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    System.err.println("Error closing server: " + e.getMessage());
                }
            }
        }
    }// end main

    public static int occ(String lookup, String line) {
        int count = 0;
        int index = 0;
        while ((index = line.indexOf(lookup, index)) != -1) {
            count++;
            index += lookup.length();
        }
        System.out.println("lookup : " + lookup + " line : " + line+ " count : " + count);
        
        return count;
    }

}// end class