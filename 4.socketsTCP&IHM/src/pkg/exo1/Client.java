/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg.exo1;

/**
 *
 * @author tarik
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener {
    Socket sock = null;
    DataOutputStream sockOut = null;
    DataInputStream sockIn = null;
    private JButton jbtGetMoy = new JButton("Afficher la moyenne");
    private JTextField jtfNom = new JTextField();
    private JTextField jtfMoy = new JTextField();

    public Client() {
        JPanel panneau = new JPanel();
        panneau.setLayout(new GridLayout(2, 2));
        panneau.add(new JLabel("Nom"));
        panneau.add(jtfNom);
        panneau.add(new JLabel("Moyenne"));
        panneau.add(jtfMoy);
        add(panneau, BorderLayout.CENTER);
        add(jbtGetMoy, BorderLayout.SOUTH);
        jbtGetMoy.addActionListener(this);
    }

    public void init() {
        try {
            sock = new Socket("localhost", 7777);
            sockOut = new DataOutputStream(sock.getOutputStream());
            sockIn = new DataInputStream(sock.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("host non atteignable : localhost");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("connection impossible avec : localhost");
            System.exit(1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Boolean isGE10 = false;
        try {
            sockOut.writeUTF(jtfNom.getText().trim());
            sockOut.flush();
        } catch (IOException ex) {
        }
        try {
            isGE10 = sockIn.readBoolean();
        } catch (IOException ex) {
        }
        jtfMoy.setText(Boolean.toString(isGE10));
    }

    public static void main(String[] args) {
        Client a = new Client();
        a.setTitle("Rechercher La Moyenne");
        a.setSize(350, 150);
        a.init();
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }
}
