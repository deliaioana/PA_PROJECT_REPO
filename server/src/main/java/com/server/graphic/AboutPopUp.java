package com.server.graphic;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.BorderLayout.*;

public class AboutPopUp extends JFrame {

    public AboutPopUp() {
        super("Application Details" );
        init();
    }

    private void init() {
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));

        JLabel applicationRules = new JLabel("Aici text \n Scris frumos");
        JButton exitButton = new JButton("Exit");

        add(applicationRules, CENTER);
        add(exitButton, SOUTH);
        exitButton.addActionListener(this::exitAbout);

        pack();
    }

    private void exitAbout(ActionEvent actionEvent) {
        this.dispose();
    }
}
