package com.server.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LowerPanel extends JPanel{

    final GraphicApplication graphicApplication;

    //JButton exitBtn = new JButton("Exit");

    public LowerPanel(GraphicApplication graphicApplication) {
        this.graphicApplication = graphicApplication;
        init();
    }
    private void init() {

        setLayout(new GridLayout(1, 4));
        JButton aboutButton = new JButton("About");
        JButton exitButton = new JButton("Exit");

        add(aboutButton);
        add(exitButton);
        exitButton.addActionListener(this::exitGame);
        aboutButton.addActionListener(this::showDetails);

    }

    private void showDetails(ActionEvent actionEvent) {
        AboutPopUp aboutPopUp = new AboutPopUp();
        //aboutPopUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aboutPopUp.setVisible(true);

    }

    private void exitGame(ActionEvent e) {
        graphicApplication.dispose();
    }


}
