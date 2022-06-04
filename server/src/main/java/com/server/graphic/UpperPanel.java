package com.server.graphic;

import com.server.graph.BipartiteGraph;
import com.server.graph.Cupid;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UpperPanel extends JPanel{
    final GraphicApplication graphicApplication;
    JButton generate;
    JSpinner spinner;
    JButton solveAndSave;
    BipartiteGraph graph;

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
        new Cupid(graph);
        showPairings(graph);
    }

    private void showPairings(BipartiteGraph graph) {
        // de implementat afisarea perechilor person - partner
        graph.printPairings();
    }

    private void generatePreferences(ActionEvent actionEvent) {
        graph = new BipartiteGraph();

        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);

        graph.generateRandom((Integer) spinner.getValue());
        //apelare functie care afsieaza preferintele din graph
    }

    public int getCouplesNo(){
        return (int) spinner.getValue();
    }



}
