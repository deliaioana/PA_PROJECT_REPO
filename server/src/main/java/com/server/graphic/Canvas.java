package com.server.graphic;

import com.server.graph.BipartiteGraph;
import com.server.graph.Man;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private final GraphicApplication graphicApplication;

    int canvasWidth = 1000, canvasHeight = 700;
    int circleSize = 30;
    int padding = 40;
    /*private boolean solvePressed = false;
    private boolean generatePressed = false;*/
    private int couplesNo;
    private boolean edgesNeedToBeDrawn = false;
    private boolean nodesNeedToBeDrawn = true;
    private boolean loadWasPressed = false;


    public Canvas(GraphicApplication graphicApplication) {
        this.graphicApplication = graphicApplication;
        init(graphicApplication.upperPanel.getCouplesNo());
    }

    public void init(int couplesNo) {
        this.couplesNo = couplesNo;
        if(loadWasPressed) {
            graphicApplication.upperPanel.spinner.setValue(graphicApplication.getGraph().getNumberOfPairs());
            setCouplesNo(graphicApplication.getGraph().getNumberOfPairs());
        }
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        if(isNodesNeedToBeDrawn()) {
            paintCouples(g);
            paintPreferences(g);
        }

        if(isEdgesNeedToBeDrawn()) {
            paintConnections(g);
        }

        setNodesNeedToBeDrawn(false);
        setEdgesNeedToBeDrawn(false);

        /*if(solvePressed){
            paintConnections(g);
        }*/
    }

    private void paintPreferences(Graphics2D g) {
        int centerLabel = g.getFontMetrics().stringWidth("L") / 2;

        if(couplesNo == 1) {
            String manPreferences = graphicApplication.getGraph().getMen().get(0).getPreferencesAsString();
            String womanPreferences = graphicApplication.getGraph().getWomen().get(0).getPreferencesAsString();
            int y = (canvasHeight - 2 * padding) / 2 + padding;

            g.setColor(Color.BLACK);
            g.drawString(manPreferences, padding, y + centerLabel);
            g.drawString(womanPreferences, canvasWidth - padding - g.getFontMetrics().stringWidth(womanPreferences) , y + centerLabel);

        }
        else {
            for (int row = 0; row < couplesNo; row++) {
                String manPreferences = graphicApplication.getGraph().getMen().get(row).getPreferencesAsString();
                String womanPreferences = graphicApplication.getGraph().getWomen().get(row).getPreferencesAsString();
                int y = row * (canvasHeight - 2 * padding) / (couplesNo - 1) + padding;

                g.setColor(Color.BLACK);
                g.drawString(manPreferences, padding, y + centerLabel);
                g.drawString(womanPreferences, canvasWidth - padding - g.getFontMetrics().stringWidth(womanPreferences) , y + centerLabel);

            }
        }

    }

    private void paintCouples(Graphics2D g) {

        int centerLabel = g.getFontMetrics().stringWidth("L") / 2;

        if (couplesNo == 1) {
            g.setColor(Color.BLACK);
            g.drawOval(canvasWidth / 3 - circleSize / 2, canvasHeight / 2 - circleSize / 2, circleSize, circleSize);
            g.drawString("1", canvasWidth / 3 - centerLabel, canvasHeight / 2 + centerLabel);

            g.drawOval(canvasWidth * 2 / 3 - circleSize / 2, canvasHeight / 2 - circleSize / 2, circleSize, circleSize);
            g.drawString("A", canvasWidth * 2 / 3 - centerLabel, canvasHeight / 2 + centerLabel);
        } else {
            for (int row = 0; row < couplesNo; row++) {
                int y = row * (canvasHeight - 2 * padding) / (couplesNo - 1) + padding;
                g.setColor(Color.BLACK);
                g.drawOval(canvasWidth / 3 - circleSize / 2, y - circleSize / 2, circleSize, circleSize);
                g.drawOval(canvasWidth * 2 / 3 - circleSize / 2, y - circleSize / 2, circleSize, circleSize);

                Integer boyNumber = row + 1;
                g.drawString(boyNumber.toString(), canvasWidth / 3 - centerLabel, y + centerLabel);

                char girlName;
                int girlNumber = row + 65;
                girlName = (char) girlNumber;
                g.drawString(String.valueOf(girlName), canvasWidth * 2 / 3 - centerLabel, y + centerLabel);
            }
        }
    }

    public void paintConnections(Graphics2D g) {
        if(couplesNo == 1) {
            int x1 = canvasWidth/3 + circleSize/2;
            int y1 = (canvasHeight - 2 * padding) / 2 + padding;
            int x2 = canvasWidth * 2/3 - circleSize/2;
            g.setColor(Color.BLACK);
            g.drawLine(x1, y1, x2, y1) ;
        }
        else {
            for (Man man : graphicApplication.getGraph().getMen()) {
                int x1 = canvasWidth/3 + circleSize/2;
                int y1 = (Integer.parseInt(man.getName())-1) * (canvasHeight - 2 * padding) / (couplesNo - 1) + padding;
                int x2 = canvasWidth * 2/3 - circleSize/2;

                char letter = man.getPartner().charAt(0);
                int y2 = (letter-65) * (canvasHeight - 2 * padding) / (couplesNo - 1) + padding;
                g.setColor(Color.BLACK);
                g.drawLine(x1, y1, x2, y2) ;
            }
        }
    }

    /*public boolean getSolvePressed(){
        return solvePressed;
    }

    public void setSolvePressed(boolean solvePressed){
        this.solvePressed = solvePressed;
    }

    public boolean getGeneratePressed() {
        return generatePressed;
    }

    public void setGeneratePressed(boolean generatePressed) {
        this.generatePressed = generatePressed;
    }*/

    public boolean isEdgesNeedToBeDrawn() {
        return edgesNeedToBeDrawn;
    }

    public void setEdgesNeedToBeDrawn(boolean edgesNeedToBeDrawn) {
        this.edgesNeedToBeDrawn = edgesNeedToBeDrawn;
    }

    public boolean isNodesNeedToBeDrawn() {
        return nodesNeedToBeDrawn;
    }

    public void setNodesNeedToBeDrawn(boolean nodesNeedToBeDrawn) {
        this.nodesNeedToBeDrawn = nodesNeedToBeDrawn;
    }

    public boolean isLoadWasPressed() {
        return loadWasPressed;
    }

    public void setLoadWasPressed(boolean loadWasPressed) {
        this.loadWasPressed = loadWasPressed;
    }

    public void setCouplesNo(int value) {
        couplesNo = value;
    }
}
