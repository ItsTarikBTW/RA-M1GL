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

public class Etudiant implements Serializable {
	String nom;
	String specialite;
	int moy;

	Etudiant(String nom, String specialite, int moy) {
		this.nom = nom;
		this.specialite = specialite;
		this.moy = moy;
	}

	String getNom() {
		return nom;
	}

	public String toString() {
		return "Etudiant : " + nom + "   " + specialite + " : " + moy;
	}
}
