/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo6;

/**
 *
 * @author tarik
 */
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ServeurTCPint {
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
        ServerSocket serverSocket = new ServerSocket(2000);
        Socket socket = serverSocket.accept();

        BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String data2 = socketIn.readLine();
            System.out.println("Server received: " + data2);

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

            socketOut.println(joueurs);
        }

        socketIn.close();
        socketOut.close();
        socket.close();
        serverSocket.close();
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