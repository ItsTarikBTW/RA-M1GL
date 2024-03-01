/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

import java.io.*;
/**
 *
 * @author tarik
 */

public class Serialization {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.nom = "BENMAMMAR";
        emp.prenom = "BADR";
        //emp.adresse = "TLEMCEN";
        emp.affiliation = "UABT";
        try {
            FileOutputStream fileOut = new FileOutputStream("./src/employee.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(emp);
            out.close();
            fileOut.close();
            System.out.printf("Serialisation dans ./employee.txt");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}