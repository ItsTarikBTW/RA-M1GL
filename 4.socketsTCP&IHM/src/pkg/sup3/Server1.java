package pkg.sup3;

import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
/**
 *
 * @author tarik
 */

 class Server1 {
    public static void main(String args[]) {
        File dbFile = new File("C:/Users/tarik/OneDrive/Documents/S2/RA/TP/4.socketsTCP&IHM/src/pkg/sup2.csv");

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
                        int countN = 0;
                        int countE = 0;
                        System.out.println("Received :" + recu);
                        // read from file dbFile
                        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
                            System.out.println("Reading file");
                            String line;
                            System.out.println("recu : " + recu);
                            while ((line = reader.readLine()) != null) {
                                System.out.println("line : " + line);
                                String[] parts = line.toLowerCase().split(",");
                                //if recu is 2  integers separated by a space
                                if(recu.contains(" ")){
                                    String[] partsRecu = recu.split(" ");
                                    int year1= Integer.parseInt(partsRecu[0]);
                                    int year2= Integer.parseInt(partsRecu[1]);
                                    if(checkRet(line,year1) == 1 && checkRet(line,year2) == 2)
                                        countN++;
                                        countE=-1;
                                }else{
                                    int year= Integer.parseInt(recu);
                                    if(checkRet(line,year) == 1)
                                    countN++;
                                    else if(checkRet(line,year) == 2)
                                    countE++;
                                }
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + e.getMessage());
                        }

                        System.out.println("Count : " + countN + " " + countE);
                        sockOut.writeUTF(countN+" "+countE);
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
    public static int checkRet(String line,int input) {
        String[] parts = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dn = LocalDate.parse(parts[3], formatter); // date of birth
        LocalDate dr = LocalDate.parse(parts[4], formatter); // date of joining the company
        System.out.println("dn : " + dn.getYear() + " dr : " + dr.getYear());
        if(input == dn.getYear())
            return 1;
        else if(input == dr.getYear())
            return 2;
        return 0;
    }

}// end class