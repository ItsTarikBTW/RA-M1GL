/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo2;

import java.io.Serializable;

/**
 *
 * @author tarik
 */
public class Personne implements Serializable{
    public String nom;
    public transient String prenom;
    public transient int age;
    public Personne(String nom,String prenom,int age){
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
    }
    public String toString(){
        return "Nom: "+nom+"\t Prenom: "+prenom+"\t Age: "+age+" ans";
    }
}
