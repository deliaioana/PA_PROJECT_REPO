package com.server.graphic;

import com.server.apiCallers.AddCaller;
import com.server.apiCallers.ICaller;
import com.server.apiCallers.LoadCaller;
import com.server.apis.AddController;
import com.server.graph.BipartiteGraph;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class GraphicApplication extends JFrame {
    public UpperPanel upperPanel;
    public LowerPanel lowerPanel;
    public Canvas canvas;
    private BipartiteGraph graph = new BipartiteGraph();
    private ICaller addCaller = new AddCaller();
    private ICaller loadCaller = new LoadCaller();

    public String getInfoAsString() {
        return graph.getInfoAsJson(); //de impl
    }

    public GraphicApplication(int panelNo) {
        super("Application number " + panelNo);
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        upperPanel = new UpperPanel(this);
        lowerPanel = new LowerPanel(this);
        canvas = new Canvas(this);

        graph.generateRandom((Integer) upperPanel.spinner.getValue());
        add(canvas, CENTER);
        add(lowerPanel, SOUTH);
        add(upperPanel, NORTH);

        pack();
    }

    public BipartiteGraph getGraph() {
        return graph;
    }

    public void setGraph(BipartiteGraph graph) {
        this.graph = graph;
    }

    public ICaller getAddCaller() {
        return addCaller;
    }

    public void setAddCaller(ICaller addCaller) {
        this.addCaller = addCaller;
    }

    public ICaller getLoadCaller() {
        return loadCaller;
    }

    public void setLoadCaller(ICaller loadCaller) {
        this.loadCaller = loadCaller;
    }
}
