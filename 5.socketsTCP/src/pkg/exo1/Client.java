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
  private JButton jbtGetMoy = new JButton("Afficher l'occurence");
  private JTextField jtfNom = new JTextField();
  private JTextField jtfMoy = new JTextField();
  private JComboBox<String> modeSelector = new JComboBox<>(new String[]{"Mode 1", "Mode 2"});
    private JLabel label1 = new JLabel("symptôme");
    private JLabel label2 = new JLabel("occurence");

  public Client() {
    JPanel panneau = new JPanel();
    panneau.setLayout(new GridLayout(3, 2));
        panneau.add(new JLabel("Mode"));
        panneau.add(modeSelector);
        panneau.add(label1);
        panneau.add(jtfNom);
        panneau.add(label2);
        panneau.add(jtfMoy);
        add(panneau, BorderLayout.CENTER);
        add(jbtGetMoy, BorderLayout.SOUTH);
        jbtGetMoy.addActionListener(this);
        modeSelector.addActionListener(this);
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
    if (e.getSource() == modeSelector) {
      switchMode();
  } else if (e.getSource() == jbtGetMoy) {
      if (modeSelector.getSelectedItem().equals("Mode 1")) {
        int score = 0;
        try {
          sockOut.writeUTF("1**"+jtfNom.getText().trim());
          sockOut.flush();
        } catch (IOException ex) {
        }
        try {
          score = sockIn.readInt();
        } catch (IOException ex) {
        }
        if (score < 0)
          jtfMoy.setText("N'est pas trouvé");
        else
          jtfMoy.setText(Integer.toString(score));
      } else {
        String res = null;
        try {
          sockOut.writeUTF("2**"+jtfNom.getText().trim());
          sockOut.flush();
        } catch (IOException ex) {
        }
        try {
          res = sockIn.readUTF();
        } catch (IOException ex) {
        }
        if (res.equals("N'est pas trouvé"))
          jtfMoy.setText("N'est pas trouvé");
        else
          jtfMoy.setText(res);
      }
  }
    
  }
  private void switchMode() {
    if (modeSelector.getSelectedItem().equals("Mode 1")) {
        label1.setText("symptôme");
        label2.setText("occurence");
    } else {
        label1.setText("nom");
        label2.setText("symptôme");
    }
}
  public static void main(String[] args) {
    Client a = new Client();
    a.setTitle("symptôme");
    a.setSize(450, 150);
    a.init();
    a.setLocationRelativeTo(null);
    a.setVisible(true);
  }
}
