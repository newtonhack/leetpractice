package code.leet.hard.string;

import java.util.HashMap;
import java.util.Map;

public class MaxPoint2DPlane_149 {
    public static void main(String[] args) {
        Point[] points = createPoints();
        System.out.println(maxPoints(points));
    }

    static Point[] createPoints() {
        int[][] pointData = new int[][]{
                new int[]{-4, 2},
                new int[]{2, 3},
                new int[]{3, 3}, // [[0,0],[1,65536],[65536,0]]
                new int[]{-5, 3},
                new int[]{-4, 3}
//                new int[]{2, 2},
//                new int[]{3, 2},
//                new int[]{5, 3},
//                new int[]{4, 1},
//                new int[]{2, 3},
//                new int[]{1, 4}
        };
        return pointsCreator(pointData);
    }

    static Point[] pointsCreator(int[][] pointData) {
        Point[] points = new Point[pointData.length];
        for (int i = 0; i < pointData.length; i++) {
            points[i] = new Point(pointData[i][0], pointData[i][1]);
        }
        return points;
    }

    public static int maxPoints(Point[] points) {
        if (points.length > 0) {

            Map<Double, Integer> countMap = new HashMap<>();
            int max = 0;
            for (int i = 0; i < points.length; i++) {
                int dup = 0;
                for (int j = i + 1; j < points.length; j++) {
                    if ((points[i].y == points[j].y) && (points[j].x == points[i].x)) {
                        dup++;
                        continue;
                    } else {
                        Double slope = getSlope(points[i], points[j]);
                        if (countMap.containsKey(slope)) {
                            countMap.put(slope, countMap.get(slope) + 1);
                        } else {
                            countMap.put(slope, 1);
                        }
                    }
                }
                for (Integer count : countMap.values()) {
                    if (count + dup > max) {
                        max = count + dup;
                    }
                }
                max = Math.max(max, dup);
                countMap.clear();
            }
            return max+1;
        }
        return 0;
    }

    public static double getSlope(Point a, Point b) {
        double d = (b.x - a.x);
        double n = (b.y - a.y);
        return (n==0.0)?0.0:((d != 0.0) ? n / d : Double.MAX_VALUE);
    }
}

class Point {
    int x;
    int y;

    Point() {
        x = 0;
        y = 0;
    }

    Point(int a, int b) {
        x = a;
        y = b;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}