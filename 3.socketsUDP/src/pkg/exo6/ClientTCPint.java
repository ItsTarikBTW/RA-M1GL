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
import java.util.Scanner;

public class ClientTCPint {
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        PrintWriter socketOut = null;
        BufferedReader socketIn = null;

        try {
            socket = new Socket("localhost", 2000);
            socketOut = new PrintWriter(socket.getOutputStream(), true);
            socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host not reachable: localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Cannot connect to: localhost");
            System.exit(1);
        }

        // Get user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        while (!userInput.toLowerCase().equals("exit")) {
            System.out.println("Enter a string to send to the server (type 'exit' to quit):");
            userInput = scanner.nextLine();

            socketOut.println(userInput);
            System.out.println("Client sent: " + userInput);

            if (userInput.toLowerCase().equals("exit")) {
                break;
            }

            String serverResponse = socketIn.readLine();
            System.out.println("Server response: " + serverResponse);
        }

        scanner.close();
        socketOut.close();
        socketIn.close();
        socket.close();
    }
}