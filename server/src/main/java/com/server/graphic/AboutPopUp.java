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

        String aboutText = "<html>The Stable Marriage Problem states that given N men and N women, " +
                "where each person has ranked all members of the opposite sex in order of preference, " +
                "marry the men and women together such that there are no two people of opposite sex " +
                "who would both rather have each other than their current partners. If there are no " +
                "such people, all the marriages are “stable” (Source Wiki)</html>";

        JLabel applicationRules = new JLabel(aboutText);
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
