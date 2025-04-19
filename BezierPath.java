import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class BezierPath {
    private List<BezierCurve> curves;
    private Color color;
    private float width;

    public BezierPath(Color color, float width) {
        this.curves = new ArrayList<>();
        this.color = color;
        this.width = width;
    }

    public void addCurve(BezierCurve curve) {
        curves.add(curve);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        for (BezierCurve curve : curves) {
            Path2D path = curve.getCurve();
            g2d.setStroke(new java.awt.BasicStroke(width));
            g2d.draw(path);
        }
    }

    public List<BezierCurve> getCurves() {
        return curves;
    }
}
