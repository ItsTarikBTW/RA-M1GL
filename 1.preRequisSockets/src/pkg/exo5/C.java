/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo5;

/**
 *
 * @author tarik
 */
import java.io.*;

public class C {
    public static void main(String[] args) throws IOException {
        File inputFile1 = new File("./src/s1.txt");
        File inputFile2 = new File("./src/s2.txt");
        File outputFile = new File("./src/s3.txt");

        BufferedReader in1 = new BufferedReader(new FileReader(inputFile1));
        BufferedReader in2 = new BufferedReader(new FileReader(inputFile2));
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        int step = 2;
        String line1 = null;
        String line2 = null;
        boolean end = false;
        while (!end) {
            for(int i = 0; i < step && ((line1 = in1.readLine()) != null); i++){
                out.println(line1);
                System.err.println("1:" + i + " " + line1);
            }
            for(int i = 0; i < step && ((line2 = in2.readLine()) != null); i++){
                out.println(line2);
                System.err.println("2:" + i + " " + line2);
            }
            end = (line1 == null) && (line2 == null);
        }
        in1.close();
        in2.close();
        out.close();
    }
}