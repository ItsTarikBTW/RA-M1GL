package pkg.sup3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author tarik
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client1 {
    public static void main(String[] args) throws IOException {
    String hostName = "localhost";
    Socket sock = new Socket(hostName, 7777);
    PrintWriter sockOut = new PrintWriter(sock.getOutputStream(), true);
    DataInputStream sockIn = new DataInputStream(sock.getInputStream());

    // Create JFrame
    JFrame frame = new JFrame("Client");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);

    // Create JTextField
    JTextField textField = new JTextField();
    textField.setPreferredSize(new Dimension(150, 20));

    // Create JLabel
    JLabel label = new JLabel();
    label.setPreferredSize(new Dimension(150, 20));

    // Create JButton
    JButton button = new JButton("Send");
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        String message = textField.getText().toLowerCase();
        sockOut.println(message);
        try {
            String recu = sockIn.readUTF();
            String[] parts = recu.split(" ");
            if (parts[1].charAt(0) == '-') {
                label.setText("nes : " + parts[0]);
            }else
            label.setText("nes : " + parts[0]+ " | emb: "+parts[1]);
        } catch (IOException ex) {
            System.err.println("Classe inconnue : " + hostName);
            System.exit(1);
        }
        textField.setText("");
        }
    });

    // Add JTextField, JLabel and JButton to JFrame
    frame.getContentPane().add(textField, BorderLayout.NORTH);
    frame.getContentPane().add(label, BorderLayout.CENTER);
    frame.getContentPane().add(button, BorderLayout.SOUTH);

    // Center the window
    frame.setLocationRelativeTo(null);

    frame.setVisible(true);
    }
}