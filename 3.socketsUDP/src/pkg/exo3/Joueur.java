/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

/**
 *
 * @author tarik
 */
public class Joueur {
    int numero;
    String nom;
    String equipe;

    public Joueur(int numero, String nom, String equipe) {
        this.numero = numero;
        this.nom = nom;
        this.equipe = equipe;
    }

    //toString
    @Override
    public String toString() {
        return "Joueur{" + "numero=" + numero + ", nom=" + nom + ", equipe=" + equipe + '}';
    }
    
}
