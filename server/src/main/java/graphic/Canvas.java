package graphic;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {

    private final GraphicApplication graphicApplication;

    int canvasWidth = 700, canvasHeight = 700;

    int circleSize = 30;
    int padding = 40;
    private int couplesNo;

    public Canvas(GraphicApplication graphicApplication){
        this.graphicApplication = graphicApplication;
        init(graphicApplication.upperPanel.getCouplesNo());
    }

    final void init(int couplesNo) {
        this.couplesNo = couplesNo;

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintCouples(g);
    }

    private void paintCouples(Graphics2D g) {

        int centerLabel = g.getFontMetrics().stringWidth("L")/2;

        if(couplesNo == 1){
            g.setColor(Color.BLACK);
            g.drawOval(canvasWidth/3 - circleSize/2 , canvasHeight/2 - circleSize/2, circleSize, circleSize);
            g.drawString("1", canvasWidth/3 - centerLabel, canvasHeight/2 + centerLabel);

            g.drawOval(canvasWidth * 2/3 - circleSize/2 , canvasHeight/2 - circleSize/2, circleSize, circleSize);
            g.drawString("A", canvasWidth * 2/3 - centerLabel, canvasHeight/2 + centerLabel);
        }
        else{
            for (int row = 0; row < couplesNo; row++){
                int y = row * (canvasHeight - 2 * padding) / (couplesNo-1) + padding;
                g.setColor(Color.BLACK);
                g.drawOval(canvasWidth/3 - circleSize/2 , y - circleSize/2, circleSize, circleSize);
                g.drawOval(canvasWidth* 2/3 - circleSize/2 , y - circleSize/2, circleSize, circleSize);

                Integer boyNumber = row + 1;
                g.drawString( boyNumber.toString(), canvasWidth/3 - centerLabel, y + centerLabel);

                char girlName;
                int girlNumber = row + 65;
                girlName = (char) girlNumber;
                g.drawString(String.valueOf(girlName), canvasWidth * 2/3 - centerLabel, y + centerLabel);
            }
        }


    }

}
