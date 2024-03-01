/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
/**
 *
 * @author tarik
 */

public class EcrirePersonne {
    public static void main(String[] args) {
        File file = new File("./src/personnes.txt");
        try {
            ObjectOutputStream flotEcriture = new ObjectOutputStream(new FileOutputStream(file));

            ArrayList<Personne> personnes = new ArrayList<Personne>();
            personnes.add(new Personne("HAMMOUMI", "Tarik", 21));
            personnes.add(new Personne("Stark", "Arya", 18));
            personnes.add(new Personne("Lannister", "Tyrion", 30));
            personnes.add(new Personne("Snow", "Jon", 22));
            personnes.add(new Personne("Stark", "Sansa", 20));
            personnes.add(new Personne("Lannister", "Jaime", 35));
            personnes.add(new Personne("Stark", "Robb", 23));

            flotEcriture.writeObject(personnes);
            flotEcriture.close();
            System.out.printf("Serialisation dans ./personnes.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
