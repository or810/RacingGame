import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

public class BezierCurve {
    private Point2D[] controlPoints;

    public BezierCurve(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
        controlPoints = new Point2D[]{p0, p1, p2, p3};
    }

    public Path2D getCurve() {
        Path2D path = new Path2D.Double();
        path.moveTo(controlPoints[0].getX(), controlPoints[0].getY());
        path.curveTo(controlPoints[1].getX(), controlPoints[1].getY(),
                controlPoints[2].getX(), controlPoints[2].getY(),
                controlPoints[3].getX(), controlPoints[3].getY());
        return path;
    }

    public Point2D[] getControlPoints() {
        return controlPoints;
    }
}
