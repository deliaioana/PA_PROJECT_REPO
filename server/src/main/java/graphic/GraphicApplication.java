package graphic;

import javax.swing.*;

import static java.awt.BorderLayout.*;

public class GraphicApplication extends JFrame {
    UpperPanel upperPanel;
    LowerPanel lowerPanel;
    Canvas canvas;

    public GraphicApplication(int panelNo) {
        super("Application number " + panelNo);
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        upperPanel = new UpperPanel(this);
        lowerPanel = new LowerPanel(this);
        canvas = new Canvas(this);

        add(canvas, CENTER);
        add(lowerPanel, SOUTH);
        add(upperPanel, NORTH);

        pack();
    }
}
