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
    public static void main(String[] args) throws IOException {
        
        // Create JFrame
        JFrame frame = new JFrame("Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create JLabels
        JLabel label1 = new JLabel("File Type:");
        JLabel label2 = new JLabel("File Path:");

        // Create JComboBox and JTextField
        JComboBox<String> comboBox = new JComboBox<>(new String[] { "txt", "image" });
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
                String filePath = textField.getText();
                try {
                    out.writeUTF(fileType);
                    if (fileType.equals("image")) {
                        BufferedImage bimg = ImageIO.read(new File(filePath));
                        ImageIO.write(bimg, "JPG", socket.getOutputStream());
                        System.out.println("Image sent");
                    } else if (fileType.equals("txt")) {
                        ABC.mystere(new FileInputStream(filePath), socket.getOutputStream());
                        System.out.println("Text file sent");
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
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(label1);
        panel.add(comboBox);
        panel.add(label2);
        panel.add(textField);
        panel.add(new JLabel()); // Empty label for alignment
        panel.add(button);

        // Add JPanel to JFrame
        frame.getContentPane().add(panel);

        // Center the window
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}