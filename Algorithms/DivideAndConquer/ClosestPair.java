package Algorithms.DivideAndConquer;

import java.util.Arrays;




public class ClosestPair {

    // Helper function to calculate distance between two points
    private static double bruteForce(Point[] points, int start, int end) {
        double minDistance = Double.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            for (int j = i + 1; j <= end; j++) {
                double distance = points[i].distance(points[j]);
                minDistance = Math.min(minDistance, distance);
            }
        }
        return minDistance;
    }

    // Helper function to find the minimum of two double values
    private static double min(double x, double y) {
        return (x < y) ? x : y;
    }

    // Helper function to find the closest pair of points in the strip
    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;

        Arrays.sort(strip, 0, size, (a, b) -> Double.compare(a.y, b.y));

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                double distance = strip[i].distance(strip[j]);
                min = min(min, distance);
            }
        }

        return min;
    }

    // Main function to find the closest pair of points
    private static double closestUtil(Point[] pointsX, Point[] pointsY, int start, int end) {
        if (end - start <= 3) {
            return bruteForce(pointsX, start, end);
        }

        int mid = (start + end) / 2;
        Point midPoint = pointsX[mid];

        // Divide points into two halves
        Point[] pointsYl = new Point[mid - start + 1];
        Point[] pointsYr = new Point[end - mid];
        int l = 0, r = 0;
        for (int i = 0; i < pointsY.length; i++) {
            if (pointsY[i].x <= midPoint.x && l < pointsYl.length)
                pointsYl[l++] = pointsY[i];
            else
                pointsYr[r++] = pointsY[i];
        }

        // Recursively find closest pairs in left and right halves
        double dl = closestUtil(pointsX, pointsYl, start, mid);
        double dr = closestUtil(pointsX, pointsYr, mid + 1, end);

        // Find the minimum distance among left and right halves
        double d = min(dl, dr);

        // Find the closest pair of points in the strip
        Point[] strip = new Point[end - start + 1];
        int j = 0;
        for (int i = 0; i < pointsY.length; i++) {
            if (Math.abs(pointsY[i].x - midPoint.x) < d)
                strip[j++] = pointsY[i];
        }

        // Compute the closest pair of points in the strip
        return min(d, stripClosest(strip, j, d));
    }

    // Main function to find the closest pair of points
    public static double closestPairOfPoints(Point[] points) {
        // Sort points by x-coordinate
        Arrays.sort(points, (a, b) -> Double.compare(a.x, b.x));

        // Create a copy of points sorted by y-coordinate
        Point[] pointsY = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsY, (a, b) -> Double.compare(a.y, b.y));

        // Call the recursive function to find the closest pair of points
        return closestUtil(points, pointsY, 0, points.length - 1);
    }

    public static void main(String[] args) {
        Point[] points = { new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4) };
        double minDistance = closestPairOfPoints(points);
        System.out.println("The smallest distance is " + minDistance);
    }
}
