/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

import java.net.*;
import java.util.HashSet;
import java.io.*;

/**
 *
 * @author tarik
 */

class ServerEtudiant {
	public static void main(String args[]) {
		Etudiant[] tabEtudiant = {
				new Etudiant("A", "GL", 13),
				new Etudiant("B", "RSD", 12),
				new Etudiant("C", "SIC", 14),
				new Etudiant("D", "MID", 15),
				new Etudiant("E", "GL", 16),
				new Etudiant("F", "RSD", 17),
				new Etudiant("G", "SIC", 12),
				new Etudiant("H", "MID", 6),
				new Etudiant("I", "GL", 17),
				new Etudiant("J", "RSD", 4),
				new Etudiant("K", "SIC", 8),
				new Etudiant("L", "MID", 9)
		};
		ServerSocket server = null;
		try {
			server = new ServerSocket(7777);
			while (true) {
				Socket sock = server.accept();
				System.out.println("connecte");
				ObjectOutputStream sockOut = new ObjectOutputStream(sock.getOutputStream());
				BufferedReader sockIn = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				// ? QUESTION 1: get size of send and receive buffers
				try {
					int sendBufferSize = sock.getSendBufferSize();
					int receiveBufferSize = sock.getReceiveBufferSize();
					System.out.println("Send buffer size: " + sendBufferSize);
					System.out.println("Receive buffer size: " + receiveBufferSize);
				} catch (SocketException e) {
					e.printStackTrace();
				}
				String recu;
				while ((recu = sockIn.readLine()) != null) {
					System.out.println("recu :" + recu);
					// if resu is a number
					if (recu.matches("\\d+")) {
						System.out.println("recu est un nombre");
						float minGrade = Float.parseFloat(recu);
						Etudiant[] etudiants = getEtudiantsMinGrade(minGrade, tabEtudiant);
						if (etudiants.length == 0)
							sockOut.writeObject("aucun etudiant trouve");
						else
							sockOut.writeObject(etudiants);

					} else {
						System.out.println("recu est un String");
						String specialite = recu.trim();
						Etudiant etudiant = getHigherGrade(specialite, tabEtudiant);
						if (etudiant == null)
							sockOut.writeObject("specialite non trouvee");
						else
							sockOut.writeObject(etudiant);
					}
				}
				sockOut.close();
				sock.close();
			}
		} catch (IOException e) {
			try {
				server.close();
			} catch (IOException e2) {
			}
		}
	}// fin main
		// ? QUESTION 4:

	public static Etudiant[] getEtudiantsMinGrade(float minGrade, Etudiant[] tabEtudiant) {
		HashSet<Etudiant> etudiants = new HashSet<Etudiant>();
		for (Etudiant etudiant : tabEtudiant) {
			if (etudiant.moy >= minGrade) {
				etudiants.add(etudiant);
			}
		}
		return etudiants.toArray(new Etudiant[etudiants.size()]);
	}

	public static Etudiant getHigherGrade(String specialite, Etudiant[] tabEtudiant) {
		int maxGrade = 0;
		Etudiant magor = null;
		for (Etudiant etudiant : tabEtudiant) {
			if (etudiant.specialite.toLowerCase().equals(specialite.toLowerCase()) && etudiant.moy > maxGrade) {
				maxGrade = etudiant.moy;
				magor = etudiant;
			}
		}
		return magor;
	}
}// fin classe
