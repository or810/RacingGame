import java.awt.*;
import java.util.List;
import java.awt.geom.Path2D;
import java.util.ArrayList;

class RaceTrack {
    private List<BezierCurve> curves;
    private Color color;
    private float width;

    public RaceTrack(Color color, float width) {
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
            g2d.setStroke(new BasicStroke(width));
            g2d.draw(path);
        }
    }

    public List<BezierCurve> getCurves() {
        return curves;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}