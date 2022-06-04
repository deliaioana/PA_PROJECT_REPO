package com.server.graphic;

import com.server.graph.BipartiteGraph;
import com.server.graph.Cupid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpperPanel extends JPanel{
    final GraphicApplication graphicApplication;
    JButton generate;
    JSpinner spinner;
    JButton solveAndSave;


    public UpperPanel(GraphicApplication graphicApplication) {
        this.graphicApplication = graphicApplication;
        init();
    }

    private void init() {
        generate = new JButton("Generate");
        spinner = new JSpinner (new SpinnerNumberModel(5, 1, 20, 1));
        solveAndSave = new JButton("Solve and Save");

        add(generate);
        add(spinner);
        add(solveAndSave);

        generate.addActionListener(this :: generatePreferences);
        solveAndSave.addActionListener(this :: addConnectionsAndSave);
    }

    private void addConnectionsAndSave(ActionEvent actionEvent) {
        graphicApplication.canvas.setSolvePressed(true);

        //generatePreferences(actionEvent);

        new Cupid(graphicApplication.getGraph());
        showPairings(graphicApplication.getGraph());

        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);
    }

    private void showPairings(BipartiteGraph graph) {
        // de implementat afisarea perechilor person - partner
        graph.printPairings();
    }

    private void generatePreferences(ActionEvent actionEvent) {

        graphicApplication.canvas.setSolvePressed(false);

        graphicApplication.setGraph(new BipartiteGraph());

        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);

        graphicApplication.getGraph().generateRandom((Integer) spinner.getValue());
    }

    public int getCouplesNo(){
        return (int) spinner.getValue();
    }



}
