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

public class A {
	public static void main(String[] args) {
		File fichier = new File("./src/test3.txt");
		String s = "mirsdglop";
		try {
			FileWriter flotEcriture = new FileWriter(fichier);
			/*
			 * for (int i = 1; i <= 7; i++) {
			 * flotEcriture.write(s, 0, 8 - i);
			 * flotEcriture.write("\n");
			 * }
			 */
			// Q3.1
			
			for (int i = 0; i <= s.length() / 2; i++) {
			  for (int j = 0; j < i; j++)
			  flotEcriture.write(" ");
			  
			  flotEcriture.write(s, 0 + i, s.length() - 2 * i);
			  
			  for (int j = 0; j < i; j++)
			  flotEcriture.write(" ");
			  
			  flotEcriture.write("\n");
			  }
			  for (int i = s.length() / 2 - 1; i >= 0; i--) {
			  for (int j = 0; j < i; j++)
			  flotEcriture.write(" ");
			  
			  flotEcriture.write(s, 0 + i, s.length() - 2 * i);
			  
			  for (int j = 0; j < i; j++)
			  flotEcriture.write(" ");
			  flotEcriture.write("\n");
			  }
			 
			// Q3.2
                        /*
			for (int i = 0; i <= s.length() / 2; i++) {
				if (i != s.length() / 2)
					flotEcriture.write(s, 0, 1 + i);
				else
					flotEcriture.write(s, 0, i);

				for (int j = 0; j < s.length() - 2 * i - 2; j++)
					flotEcriture.write(" ");

				flotEcriture.write(s, s.length() - i - 1, i + 1);
				flotEcriture.write("\n");
			}
			for (int i = s.length() / 2; i > 0; i--) {
				flotEcriture.write(s, 0, 0 + i);

				for (int j = 0; j < s.length() - 2 * i; j++)
					flotEcriture.write(" ");

				flotEcriture.write(s, s.length() - i, i);
				flotEcriture.write("\n");
			}
*/
			flotEcriture.close();
		} catch (IOException e) {
		}
	}
}
