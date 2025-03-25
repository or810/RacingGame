package view;

import java.awt.*;
import java.awt.geom.CubicCurve2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BezierCurve {
    private Point[] points;
    private BasicStroke stroke;
    private Color color;
    private TexturePaint texturePaint;

    public BezierCurve(Point[] points) {
        this.points = points;
        this.stroke = new BasicStroke(1); // Default stroke width is 1
        this.color = Color.BLACK; // Default color is black
        this.texturePaint = null; // No texture by default
    }

    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setTexture(BufferedImage image) {
        this.texturePaint = new TexturePaint(image, new Rectangle(image.getWidth(), image.getHeight()));
    }

    public void render(Graphics2D g2d) {
        if (points.length != 4) {
            throw new IllegalArgumentException("BezierCurve requires exactly 4 points.");
        }

        CubicCurve2D cubicCurve = new CubicCurve2D.Float(
                points[0].x, points[0].y,
                points[1].x, points[1].y,
                points[2].x, points[2].y,
                points[3].x, points[3].y
        );

        g2d.setStroke(stroke);
        if (texturePaint != null) {
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(color);
        }
        g2d.draw(cubicCurve);
    }
    
    public void renderDynamic(Graphics2D g2d) {
        if (points.length != 4) {
            throw new IllegalArgumentException("BezierCurve requires exactly 4 points.");
        }

        CubicCurve2D cubicCurve = new CubicCurve2D.Float(
                points[0].x, points[0].y,
                points[1].x, points[1].y,
                points[2].x, points[2].y,
                points[3].x, points[3].y
        );

        g2d.setStroke(stroke);
        if (texturePaint != null) {
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(color);
        }
        g2d.draw(cubicCurve);
        
        g2d.setColor(Color.BLACK);
        if(color.equals(Color.BLACK))
        	g2d.setColor(color.WHITE);
        for(Point point : points) {
        	g2d.fillOval(point.x, point.y, 8, 8);
        }
    }
}