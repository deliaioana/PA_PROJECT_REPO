package com.server.graphic;

import com.server.graph.BipartiteGraph;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class GraphicApplication extends JFrame {
    UpperPanel upperPanel;
    LowerPanel lowerPanel;
    Canvas canvas;
    private BipartiteGraph graph = new BipartiteGraph();

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
}
