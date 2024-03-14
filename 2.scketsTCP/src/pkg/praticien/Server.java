/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.praticien;

/**
 * @author tarik
 */

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

class Server {
    public static void main(String args[]) {
        HashMap<Integer,Practicien> practicien = new HashMap<>();
        practicien.put(1, new Practicien("femme", 57, "Note : Le patient déclare qu'il se sent très bien. Poids égal ou inférieur au poids recommandé."));
        practicien.put(2, new Practicien("homme", 78, "Note : Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement.        Note : Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois. Il remarque également que son audition continue d'être anormale."));
        practicien.put(3, new Practicien("homme", 19, "Note : Le patient déclare qu'il fume depuis peu.        Note : Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d’apnée respiratoire anormales. Tests de laboratoire indiquant un taux de cholestérol LDL élevé."));
        practicien.put(4, new Practicien("femme", 21, "Note : Le patient déclare qu'il lui est devenu difficile de monter les escaliers. Il se plaint également d être essoufflé. Tests de laboratoire indiquant que les anticorps sont élevés. Réaction aux médicaments.Note : Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps.Note : Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé. Taille, Poids, Cholestérol, Vertige et Réaction."));

        HashSet<String> symptomes = new HashSet<>();
    
        symptomes.add("émoglobine A1C");
        symptomes.add("Microalbumine");
        symptomes.add("Taille");
        symptomes.add("Poids");
        symptomes.add("Fumeur");
        symptomes.add("Fumeuse");
        symptomes.add("normale(s)");
        symptomes.add("cholestérol");
        symptomes.add("Cholestérol");
        symptomes.add("Vertiges");
        symptomes.add("Rechute");
        symptomes.add("Réaction");
        symptomes.add("réaction");
        symptomes.add("Anticorps");
        symptomes.add("anticorps");
        
        ServerSocket server = null;
        try {
            server = new ServerSocket(7777);
            while (true) {
                Socket sock = server.accept();
                System.out.println("connecte");
                PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                String recu;
                int id;
                int count = 0;
                Practicien thisPracticien;
                while ((recu = sockIn.readLine()) != null) {
                    id = Integer.parseInt(recu);

                    thisPracticien = practicien.get(id);
                    for (String decl : symptomes) {
                        //count the number of occurences of the string in the note
                        if(thisPracticien.Note.contains(decl)){
                            count++;
                        }
                    }
                    if(count == 1)
                    {
                        sockOut.println("aucun risque");
                    }
                    else if(count <= 5 && thisPracticien.age > 30)
                    {
                        sockOut.println("risque limité");
                    }
                    else if(thisPracticien.sexe.equals("femme") && thisPracticien.age < 30 && count == 4)
                    {
                        sockOut.println("danger");
                    }
                    else if(thisPracticien.sexe.equals("homme") && thisPracticien.age < 30 && count == 3)
                    {
                        sockOut.println("danger");
                    }
                    else if(thisPracticien.age > 30 && count >= 6 && count <=7)
                    {
                        sockOut.println("danger");
                    }
                    else if(thisPracticien.sexe.equals("homme") && thisPracticien.age < 30 && count >= 5){
                        sockOut.println("appartion précoce");
                    }
                    else if(thisPracticien.sexe.equals("femme") && thisPracticien.age < 30 && count >= 7){
                        sockOut.println("appartion précoce");
                    }
                    else if(thisPracticien.age > 30 && count >= 8)
                    {
                        sockOut.println("appartion précoce");
                    }
                    else{
                        sockOut.println("else");
                    }


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
} // fin classe

      

     

    

    

    

    
    
    

    
    
