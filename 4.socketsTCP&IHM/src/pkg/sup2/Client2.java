package pkg.sup2;

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
public class Client2 {
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
        // Create JTextField
        JTextField textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(150, 20));

        // Create JLabel
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(150, 20));

        // Create JButton
        JButton button = new JButton("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = textField.getText().toLowerCase()+" "+textField2.getText().toLowerCase();
                sockOut.println(message);
                try {
                    Object recu = sockIn.readBoolean();
                    label.setText("ret?: " + recu);
                } catch (IOException ex) {
                    System.err.println("Classe inconnue : " + hostName);
                    System.exit(1);
                }
                textField.setText("");
                textField2.setText("");
            }
        });

        // Create JPanel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(textField);
        panel.add(textField2);

        // Add JPanel, JLabel and JButton to JFrame
        frame.getContentPane().add(panel, BorderLayout.NORTH);
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}