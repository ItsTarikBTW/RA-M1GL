package pkg.exo3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client2 {
    static String username;
    static String password;

    public static void main(String[] args) throws IOException {
        // Create JFrame for login
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);

        // Create JLabels and fields for username and password
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                password = new String(passwordField.getPassword());

                try {
                    Socket socket = new Socket("localhost", 9001);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                    out.writeUTF("auth");
                    out.writeUTF("c->s");
                    out.writeUTF(username);
                    out.writeUTF(password);
                    // get the response from the server
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    String response = in.readUTF();
                    if (response.equals("Authentication failed")) {
                        System.out.println("Authentication failed");
                        JOptionPane.showMessageDialog(loginFrame, "Invalid username or password.");

                    } else {
                        System.out.println("Authentication successful");
                        // Open main application window
                        loginFrame.setVisible(false);
                        openMainWindow();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Create JPanel and add components to it
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel()); // Empty label for alignment
        loginPanel.add(loginButton);

        // Add JPanel to JFrame
        loginFrame.getContentPane().add(loginPanel);

        // Center the window
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

    }

    private static void openMainWindow() {// Create JFrame
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create JLabels
        JLabel label1 = new JLabel("File Type:");
        JLabel label2 = new JLabel("File Path:");
        JLabel label3 = new JLabel("Operation:");

        // Create JComboBox and JTextField
        JComboBox<String> comboBox = new JComboBox<>(new String[] { "txt", "image" });
        JComboBox<String> operationComboBox = new JComboBox<>(new String[] { "c->s", "s->c" });
        JTextField textField = new JTextField();

        // Create JButton
        JButton button = new JButton("Submit");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Socket socket = null;
                try {
                    socket = new Socket("localhost", 9001);
                } catch (IOException ex) {
                    Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
                }
                DataOutputStream out = null;
                try {
                    out = new DataOutputStream(socket.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(Client2.class.getName()).log(Level.SEVERE, null, ex);
                }

                String fileType = comboBox.getSelectedItem().toString();
                String operation = operationComboBox.getSelectedItem().toString();
                String filePath = textField.getText();
                try {
                    out.writeUTF(fileType);
                    out.writeUTF(operation);
                    if (operation.equals("c->s")) {
                        if (fileType.equals("image")) {
                            BufferedImage bimg = ImageIO.read(new File(filePath));
                            ImageIO.write(bimg, "JPG", socket.getOutputStream());
                            System.out.println("Image sent");
                        } else if (fileType.equals("txt")) {
                            ABC.mystere(new FileInputStream(filePath), socket.getOutputStream());
                            System.out.println("Text file sent");
                        }
                    } else {
                        //TODO: s->c
                        //send the file path
                        out.writeUTF(filePath);
                        if (fileType.equals("image")) {
                            BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
                            ImageIO.write(img, "JPG", new File("./src/testC.JPG"));
                            System.out.println("Image received");
                        } else if (fileType.equals("txt")) {
                            ABC.mystere(socket.getInputStream(), new FileOutputStream("./src/testC.txt"));
                            System.out.println("Text file received");
                        }
                    }
                    out.close();
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });/*
            * ./src/whiplash.jpg
            * ./src/test1.txt
            */

        // Create JPanel and add components to it
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(label1);
        panel.add(comboBox);
        panel.add(label2);
        panel.add(textField);
        panel.add(label3);
        panel.add(operationComboBox);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(button);

        // Add JPanel to JFrame
        frame.getContentPane().add(panel);

        // Center the window
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}