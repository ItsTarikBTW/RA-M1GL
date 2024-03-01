/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author tarik
 */
public class LirePersonne {
    public static void main(String[] args) {
        File file = new File("./src/personnes.txt");
        try {
            ObjectInputStream flotLecture = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Personne> liste = (ArrayList<Personne>) flotLecture.readObject();
            for (Object elem : liste) {
                System.out.println(elem);
            }
            flotLecture.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
