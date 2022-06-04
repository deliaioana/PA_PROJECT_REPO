package graphic;

import javax.swing.*;
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
    }

    private void generatePreferences(ActionEvent actionEvent) {
        graphicApplication.canvas.init(getCouplesNo());
        SwingUtilities.updateComponentTreeUI(graphicApplication);
    }

    public int getCouplesNo(){
        return (int) spinner.getValue();
    }



}
