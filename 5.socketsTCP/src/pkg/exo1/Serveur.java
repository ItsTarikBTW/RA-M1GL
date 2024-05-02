/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

/**
 *
 * @author tarik
 */
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Serveur {
    private HashMap<String, Integer> table;
    private HashMap<String, String> table2;

    public Serveur() throws FileNotFoundException {
        table = new HashMap<>();
        try (Scanner a = new Scanner(new File("./src/symptomes.txt"))) {
            while (a.hasNextLine())
                ajouter(table, a.nextLine());
        }
        table2 = new HashMap<>();
        try (Scanner a = new Scanner(new File("./src/symptomesnom.txt"))) {
            while (a.hasNextLine()) {
                String[] parts = a.nextLine().split(" ", 2);
                ajouter(table2, parts[0], parts[1]);
            }
        }
    }

    public static int TrouverSymp(String symp,HashMap<String, Integer> table) {
        if (table.containsKey(symp))
            return (table.get(symp));
        else
            return -1;
    }
    public static String TrouverSymp1(String symp,HashMap<String, String> table) {
        if (table.containsKey(symp))
            return (table.get(symp));
        else
            return "N'est pas trouvé";
    }

    public static void main(String args[]) throws FileNotFoundException {
        
        Serveur b = new Serveur();
        System.out.println("Serveur en attente de connexion ...");
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                DataOutputStream sockOut = new DataOutputStream(sock.getOutputStream());
                DataInputStream sockIn = new DataInputStream(sock.getInputStream());
                String symp;

                while ((symp = sockIn.readUTF()) != null) {
                    System.out.println("symptome recu : " + symp);
                    String[] parts = symp.split("\\*\\*");
                    String mode = parts[0];
                    String symptom = parts[1];
        
                    if (mode.equals("1")) {
                        sockOut.writeInt(TrouverSymp(symptom,b.table));
                    } else if (mode.equals("2")) {
                        sockOut.writeUTF(TrouverSymp1(symptom,b.table2));
                    }
        
                    sockOut.flush();
                }

                sockOut.close();
                sock.close();
            }
        } catch (IOException e) {
            try {
                server.close();
            } catch (IOException e2) {
            }
        }
    }

    static void ajouter(Map<String, Integer> map, String symptome) {
        map.compute(symptome, (key, value) -> (value == null) ? 1 : value + 1);
    }
    static void ajouter(Map<String, String> map,String nom, String symptome) {
        if (map.containsKey(nom))
            map.put(nom, map.get(nom) + " " + symptome);
        else
            map.put(nom, symptome);
    }
}
