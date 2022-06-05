package com.server.graphic;

import com.server.apis.AddController;
import com.server.graph.BipartiteGraph;
import com.server.graph.Cupid;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UpperPanel extends JPanel{
    final GraphicApplication graphicApplication;
    JButton generate;
    JSpinner spinner;
    JButton solveAndSave;
    private boolean spinnerChanged;


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
        spinner.addChangeListener(this :: isChangedSpinner);
    }

    private void isChangedSpinner(ChangeEvent changeEvent) {
        if(!graphicApplication.canvas.getGeneratePressed()) {
            setSpinnerChanged(true);
        }
    }

    private void addConnectionsAndSave(ActionEvent actionEvent) {
        if(isSpinnerChanged()) {
            generatePreferences(actionEvent);
            setSpinnerChanged(false);
        }

        graphicApplication.canvas.setSolvePressed(true);
        graphicApplication.canvas.setGeneratePressed(false);

        new Cupid(graphicApplication.getGraph());
        //showPairings(graphicApplication.getGraph());

        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);

        graphicApplication.getAddCaller().setGraphicApplication(graphicApplication);
        graphicApplication.getAddCaller().execute();
    }

    private void showPairings(BipartiteGraph graph) {
        graph.printPairings();
    }

    private void generatePreferences(ActionEvent actionEvent) {

        graphicApplication.canvas.setGeneratePressed(true);
        graphicApplication.canvas.setSolvePressed(false);

        graphicApplication.setGraph(new BipartiteGraph());

        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);

        graphicApplication.getGraph().generateRandom((Integer) spinner.getValue());
    }

    public int getCouplesNo(){
        return (int) spinner.getValue();
    }


    public boolean isSpinnerChanged() {
        return spinnerChanged;
    }

    public void setSpinnerChanged(boolean spinnerChanged) {
        this.spinnerChanged = spinnerChanged;
    }
}
