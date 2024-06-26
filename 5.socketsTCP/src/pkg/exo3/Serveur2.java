package pkg.exo3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Serveur2 {
    private static Map<String, String> users = new HashMap<>();

    static {
        users.put("admin", "123");
        users.put("tarik", "0000");
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9001);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                String type = in.readUTF();
                String direction = in.readUTF(); // c->s ou s->c
                if (type.equals("auth")) {
                    String username = in.readUTF();
                    String password = in.readUTF();

                    // Replace with your actual username and password
                    DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
                    if (!users.containsKey(username) || !users.get(username).equals(password)) {
                        out.writeUTF("Authentication failed");
                    } else {
                        out.writeUTF("Authentication successful");
                    }
                } else if (direction.equals("c->s")) {
                    if (type.equals("image")) {
                        BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(clientSocket.getInputStream()));
                        ImageIO.write(img, "JPG", new File("./src/testS.JPG"));
                        System.out.println("Image reçue");
                    } else if (type.equals("txt")) {
                        ABC A = new ABC();
                        A.mystere(clientSocket.getInputStream(), new FileOutputStream("./src/testS.txt"));
                        System.out.println("Fichier texte reçu");
                    }
                } else if (direction.equals("s->c")) {
                    String filePath = in.readUTF();
                    if (type.equals("image")) {
                        BufferedImage img = ImageIO.read(new File(filePath));
                        ImageIO.write(img, "JPG", clientSocket.getOutputStream());
                        System.out.println("Image envoyée");                        
                    } else if (type.equals("txt")) {
                        ABC A = new ABC();
                        A.mystere(new FileInputStream(filePath), clientSocket.getOutputStream());
                        System.out.println("Fichier texte envoyé");                        
                    }
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}