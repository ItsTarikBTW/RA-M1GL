package pkg.sup2;

import java.net.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

/**
 *
 * @author tarik
 */

class Server2 {
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
                        System.out.println("Received :" + recu);
                        // read from file dbFile
                        boolean result = false;
                        try (BufferedReader reader = new BufferedReader(new FileReader(dbFile))) {
                            System.out.println("Reading file");
                            String line;
                            boolean found = false;
                            System.out.println("recu : " + recu);
                            while ((line = reader.readLine()) != null) {
                                System.out.println("line : " + line);
                                String[] parts = line.toLowerCase().split(",");
                                String[] lookup = recu.split(" ");
                                System.out.println("parts[0] : " + parts[0] + " parts[1] : " + parts[1]);
                                System.out.println("lookup[0] : " + lookup[0] + " lookup[1] : " + lookup[1]);
                                if (parts[0].equals(lookup[0])
                                        && parts[1].equals(lookup[1])) {
                                    System.out.println("Found");
                                    found = true;
                                    result = checkRet(line);
                                    break;
                                }
                            }
                            if (!found) {
                                System.out.println("Not found");
                            }
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + e.getMessage());
                        }

                        sockOut.writeBoolean(result);
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

    public static boolean checkRet(int age, int yearsWorked, int gender) {

        if (gender == 1) {// h
            if (age >= 60 || yearsWorked >= 35)
                return true;
        } else {// f
            if (age >= 55 || yearsWorked >= 30)
                return true;

        }
        return false;
    }

    public static boolean checkRet(String line) {
        String[] parts = line.split(",");
        int gender = Integer.parseInt(parts[2].equals("homme") ? "1" : "0"); // 1: homme, 0: femme
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dn = LocalDate.parse(parts[3], formatter); // date of birth
        LocalDate dr = LocalDate.parse(parts[4], formatter); // date of joining the company

        Period age = Period.between(dn, LocalDate.now()); // calculate age
        Period yearsWorked = Period.between(dr, LocalDate.now()); // calculate years worked

        System.out.println("Age: " + age.getYears());
        System.out.println("Years worked: " + yearsWorked.getYears());
        System.out.println(gender);

        return checkRet(age.getYears(), yearsWorked.getYears(), gender);
    }

}// end class