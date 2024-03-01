/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

import java.io.Serializable;
/**
 *
 * @author tarik
 */

public class Employee implements Serializable {
    public String nom; 
	static String prenom;
	transient final String adresse="CCC";  // une constante
	transient static String affiliation;

}