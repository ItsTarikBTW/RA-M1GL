package pkg.exo3;
import java.io.*;
import java.net.*;
public class Serveur{ 
    public static void main(String []args) throws IOException  { 
        Socket sock = new ServerSocket(9001).accept();
        ABC A = new ABC();
        A.mystere(sock.getInputStream(),new FileOutputStream("./src/test2.txt"));
        sock.close(); 
    } 
}
