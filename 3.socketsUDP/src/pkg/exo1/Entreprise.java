/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

/**
 *
 * @author tarik
 */
import java.io.Serializable;

public class Entreprise implements Serializable {
    private int id;
    private String name;

    public Entreprise(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Id = " + getId() + " Name = " + getName();
    }
}
