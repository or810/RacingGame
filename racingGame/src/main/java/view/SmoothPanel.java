package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import core.ResourceManager;

public class SmoothPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        RenderingHints hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);

        g2d.setColor(Color.GREEN);
        g2d.drawOval(50, 50, 50, 50);

        Point[] points = new Point[] {
                new Point(50, 20),
                new Point(100, 70),
                new Point(200, 90),
                new Point(80, 10)
        };	

        BezierCurve bezierCurve = new BezierCurve(points);
        BufferedImage texture = ResourceManager.getInstance().getTexture("track");
        if (texture != null) {
            bezierCurve.setTexture(resize(texture, 100, 100));
        }
        bezierCurve.setStroke(new BasicStroke(25)); // Set stroke width to 25
        bezierCurve.setColor(Color.RED); // Set color to red
        bezierCurve.renderDynamic(g2d);
        
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        int w = img.getWidth();
        int h = img.getHeight();
        BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
        Graphics2D g = dimg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return dimg;
    }
}
