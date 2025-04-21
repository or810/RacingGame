import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class Editor extends JPanel {
    private RaceTrack raceTrack;
    private List<Point2D> controlPoints;
    private Point2D lastControlPoint;
    private Point2D draggedPoint;
    private static final int CONTROL_POINT_RADIUS = 8;
    private static final int SELECTION_RADIUS = 10;

    public Editor() {
        raceTrack = new RaceTrack(Color.BLACK, 10);
        controlPoints = new ArrayList<>();
        lastControlPoint = null;
        draggedPoint = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D clickPoint = new Point2D.Double(e.getX(), e.getY());
                Point2D selectedPoint = findNearbyControlPoint(clickPoint);

                if (selectedPoint != null) {
                    controlPoints.add(selectedPoint);
                } else {
                    controlPoints.add(clickPoint);
                }

                if (controlPoints.size() == 4) {
                    BezierCurve curve = new BezierCurve(controlPoints.get(0), controlPoints.get(1), controlPoints.get(2), controlPoints.get(3));
                    raceTrack.addCurve(curve);
                    lastControlPoint = controlPoints.get(3);
                    controlPoints.clear();
                    controlPoints.add(lastControlPoint);
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Point2D clickPoint = new Point2D.Double(e.getX(), e.getY());
                draggedPoint = findNearbyControlPoint(clickPoint);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggedPoint = null;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedPoint != null) {
                    draggedPoint.setLocation(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }

    private Point2D findNearbyControlPoint(Point2D clickPoint) {
        for (BezierCurve curve : raceTrack.getCurves()) {
            for (Point2D point : curve.getControlPoints()) {
                if (point.distance(clickPoint) < SELECTION_RADIUS) {
                    return point;
                }
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        setBackground(Color.GREEN.darker());

        raceTrack.draw(g2d);

        g2d.setColor(Color.RED);
        for (BezierCurve curve : raceTrack.getCurves()) {
            for (Point2D point : curve.getControlPoints()) {
                g2d.fillOval((int) point.getX() - CONTROL_POINT_RADIUS / 2, (int) point.getY() - CONTROL_POINT_RADIUS / 2, CONTROL_POINT_RADIUS, CONTROL_POINT_RADIUS);
            }
        }

        g2d.setColor(Color.GREEN);
        for (Point2D point : controlPoints) {
            g2d.fillOval((int) point.getX() - CONTROL_POINT_RADIUS / 2, (int) point.getY() - CONTROL_POINT_RADIUS / 2, CONTROL_POINT_RADIUS, CONTROL_POINT_RADIUS);
        }

        //helper lines
        if (controlPoints.size() > 1) {
            g2d.setColor(Color.LIGHT_GRAY.darker());
            for (int i = 0; i < controlPoints.size() - 1; i++) {
                Point2D p1 = controlPoints.get(i);
                Point2D p2 = controlPoints.get(i + 1);
                g2d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
            }
        }

        // Draw preview Bezier curve
        if (controlPoints.size() == 4) {
            BezierCurve previewCurve = new BezierCurve(controlPoints.get(0), controlPoints.get(1), controlPoints.get(2), controlPoints.get(3));
            g2d.setColor(Color.CYAN);
            g2d.setStroke(new BasicStroke(2));
            g2d.draw(previewCurve.getCurve());
        }
    }
}
