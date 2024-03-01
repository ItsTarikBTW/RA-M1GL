/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo5;

/**
 *
 * @author tarik
 */
import java.io.*;

public class B {
    public static void main(String[] args) {
        File fichier = new File("./src/test1.txt");
        try {
            FileWriter flotEcriture = new FileWriter(fichier);
            for (int i = 1; i <= 9; i++)
                //flotEcriture.write(i+""); // one
                //flotEcriture.write(String.valueOf(i)); // two
                flotEcriture.write(Integer.toString(i)); // tree
            flotEcriture.close();
        } catch (IOException e) {
        }
    }
}
