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

public class A {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("./src/test1.txt");
        File outputFile = new File("./src/test2.txt");
        FileReader in = new FileReader(inputFile);
        FileWriter out = new FileWriter(outputFile,true);
        int c;
        while ((c = in.read()) != -1)
            out.write(c);
        in.close();
        out.close();
    }
}
