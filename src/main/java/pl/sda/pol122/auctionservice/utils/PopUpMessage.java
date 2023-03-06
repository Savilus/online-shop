package pl.sda.pol122.auctionservice.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUpMessage {


    public static void createPopUpMessage(String message){

        JFrame frame = new JFrame("Something is wrong!");
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel(message);
        JButton button = new JButton();
        button.setText("Ok");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setVisible(true);
        button.addActionListener(e -> frame.dispose());


    }

}
