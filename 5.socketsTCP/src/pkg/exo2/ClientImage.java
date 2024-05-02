package pkg.exo2;

import java.io.*;
import java.awt.image.BufferedImage;
import java.net.Socket;
import javax.imageio.ImageIO;

public class ClientImage {
   static BufferedImage bimg;

   public static void main(String[] args) {
      try {
         Socket client = new Socket("localhost", 6066);
         bimg = ImageIO.read(new File("./src/whiplash.jpg"));
         ImageIO.write(bimg, "JPG", client.getOutputStream());
         client.close();
      } catch (IOException e) {
      }
   }
}
