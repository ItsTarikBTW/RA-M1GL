/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServeurUDPint {
    static ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
    static Joueur be = new Joueur(9, "BENZEMA", "REAL");
    static Joueur mo = new Joueur(10, "MODRIC", "REAL");
    static Joueur ma = new Joueur(26, "MAHREZ", "CITY");
    static Joueur me = new Joueur(30, "MESSI", "PSG");
    static Joueur ne = new Joueur(10, "NEYMAR", "PSG");

    public static void main(String[] args) throws Exception {
        joueurs.add(be);
        joueurs.add(mo);
        joueurs.add(ma);
        joueurs.add(me);
        joueurs.add(ne);
        DatagramSocket socket = new DatagramSocket(2000);
        DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);
        while (true) {
            socket.receive(packet);
            byte[] data = packet.getData();

            ObjectInputStream c = new ObjectInputStream(new ByteArrayInputStream(data));
            String data2 = (String) c.readObject();
            System.out.println("Serveur a reçu : " + data2);

            if (data2.equals("exit")) {
                break;
            }

            ArrayList<Joueur> joueurs;
            if (data2.length() == 1) {
                joueurs = getJoueursByN(data2);
                System.out.println("n");
            } else {
                joueurs = getJoueursByE(data2);
                System.out.println("e");
            }
            
            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream c2 = new ObjectOutputStream(a);
            c2.writeObject(joueurs);
            byte[] buffer = a.toByteArray();
            DatagramPacket packet2 =new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
            socket.send(packet2);
        }

        socket.close();
    }

    public static ArrayList<Joueur> getJoueursByE(String equipe) {
        ArrayList<Joueur> res = new ArrayList<Joueur>();
        for (Joueur joueur : joueurs) {
            if (joueur.equipe.equals(equipe.toUpperCase())) {
                res.add(joueur);
            }
        }
        return res;
    }

    public static ArrayList<Joueur> getJoueursByN(String nom) {
        ArrayList<Joueur> res = new ArrayList<Joueur>();
        for (Joueur joueur : joueurs) {
            if (joueur.nom.startsWith(nom.toUpperCase())) {
                res.add(joueur);
            }
        }
        return res;
    }

}
