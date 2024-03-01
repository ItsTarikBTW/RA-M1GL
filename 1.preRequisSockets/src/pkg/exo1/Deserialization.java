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

public class Deserialization {
	public static void main(String[] args) {
		Employee emp = null;
		try {
			FileInputStream fileIn = new FileInputStream("./src/employee.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			emp = (Employee) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}
		System.out.println("Deserialisation...");
		System.out.println("Nom: " + emp.nom);
		System.out.println("Prenom: " + emp.prenom);
		System.out.println("Adresse: " + emp.adresse);
		System.out.println("Affiliation: " + emp.affiliation);
	}
}
