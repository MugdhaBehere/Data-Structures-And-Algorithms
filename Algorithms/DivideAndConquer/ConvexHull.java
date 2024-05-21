package Algorithms.DivideAndConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

class Point implements Comparable<Point> {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if (this.y == p.y) {
            return Double.compare(this.x, p.x);
        }
        return Double.compare(this.y, p.y);
    }

    // Calculate the distance between this point and another point
    public double distance(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

public class ConvexHull {

    public static List<Point> convexHull(Point[] points) {
        if (points.length < 3) {
            throw new IllegalArgumentException("Convex hull is not possible with less than 3 points");
        }

        // Step 1: Find the point with the lowest y-coordinate, ties are broken by the
        // lowest x-coordinate
        Point start = points[0];
        for (Point point : points) {
            if (point.compareTo(start) < 0) {
                start = point;
            }
        }
        final Point startFinal = start; // Make start effectively final

        Arrays.sort(points, (a, b) -> {
            double angleA = Math.atan2(a.y - startFinal.y, a.x - startFinal.x);
            double angleB = Math.atan2(b.y - startFinal.y, b.x - startFinal.x);
            if (angleA == angleB) {
                return Double.compare(startFinal.distance(a), startFinal.distance(b));
            } else {
                return Double.compare(angleA, angleB);
            }
        });

        // Step 2: Create a stack and push the first three points onto the stack
        Stack<Point> hull = new Stack<>();
        hull.push(points[0]);
        hull.push(points[1]);
        hull.push(points[2]);

        // Step 3: Process the remaining points
        for (int i = 3; i < points.length; i++) {
            Point top = hull.pop();
            while (ccw(hull.peek(), top, points[i]) <= 0) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }

        return new ArrayList<>(hull);
    }

    private static double ccw(Point a, Point b, Point c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public static void main(String[] args) {
        Point[] points = {
                new Point(0, 3), new Point(2, 2), new Point(1, 1),
                new Point(2, 1), new Point(3, 0), new Point(0, 0),
                new Point(3, 3)
        };

        List<Point> hull = convexHull(points);
        System.out.println("Points in the Convex Hull:");
        for (Point p : hull) {
            System.out.println(p);
        }
    }
}
