package pkg.exo3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Serveur2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            String type = new DataInputStream(clientSocket.getInputStream()).readUTF();
            if (type.equals("image")) {
                BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(clientSocket.getInputStream()));
                ImageIO.write(img, "JPG", new File("./src/test.JPG"));
                System.out.println("Image reçue");
            } else if (type.equals("txt")) {
                ABC A = new ABC();
                A.mystere(clientSocket.getInputStream(), new FileOutputStream("./src/test2.txt"));
                System.out.println("Fichier texte reçu");
            }
            clientSocket.close();
        }
    }
}
