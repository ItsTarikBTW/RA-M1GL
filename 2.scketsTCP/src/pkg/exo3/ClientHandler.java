/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author tarik
 */
public class ClientHandler extends Thread{
    private final Socket sock;
    private final OutputStream sockOut;
    private final InputStream sockIn;

    public ClientHandler(Socket sock) throws IOException {
        this.sock = sock;
        this.sockOut = sock.getOutputStream();
        this.sockIn = sock.getInputStream();
    }

    @Override
    public void run() {
        try {
            byte[] buffer1 = new byte[1024];
            int lu = sockIn.read(buffer1);
            String message = new String(buffer1, 0, lu);
            System.out.println("Mot envoyé par le client est : " + message);
            System.out.println("Nombre d'octets lu : " + lu);
            String[] messageSplited = message.split(" ");
            String lastChars = "";
            for (String word : messageSplited) {
                if (!word.isEmpty()) {
                    lastChars += word.charAt(word.length() - 1);
                }
            }
            byte[] buffer2 = lastChars.getBytes();
            sockOut.write(buffer2);
            sockOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                sockOut.close();
                sock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
