package puzzle;

/*
 * MIT License
 * 
 * Copyright (c) 2017 Sander Verdonschot
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ValtrAlgorithm {

    private static final Random RAND = new Random();

    public static List<Point> generateRandomConvexPolygon(int n) {
        // Generate two lists of random X and Y coordinates
        List<Integer> xPool = new ArrayList<>(n);
        List<Integer> yPool = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            xPool.add(RAND.nextInt(100));
            yPool.add(RAND.nextInt(100));
        }

        // Sort them
        Collections.sort(xPool);
        Collections.sort(yPool);

        // Isolate the extreme points
        Integer minX = xPool.get(0);
        Integer maxX = xPool.get(n - 1);
        Integer minY = yPool.get(0);
        Integer maxY = yPool.get(n - 1);

        // Divide the interior points into two chains & Extract the vector components
        List<Integer> xVec = new ArrayList<>(n);
        List<Integer> yVec = new ArrayList<>(n);

        int lastTop = minX, lastBot = minX;

        for (int i = 1; i < n - 1; i++) {
            int x = xPool.get(i);

            if (RAND.nextBoolean()) {
                xVec.add(x - lastTop);
                lastTop = x;
            } else {
                xVec.add(lastBot - x);
                lastBot = x;
            }
        }

        xVec.add(maxX - lastTop);
        xVec.add(lastBot - maxX);

        int lastLeft = minY, lastRight = minY;

        for (int i = 1; i < n - 1; i++) {
            int y = yPool.get(i);

            if (RAND.nextBoolean()) {
                yVec.add(y - lastLeft);
                lastLeft = y;
            } else {
                yVec.add(lastRight - y);
                lastRight = y;
            }
        }

        yVec.add(maxY - lastLeft);
        yVec.add(lastRight - maxY);

        // Randomly pair up the X- and Y-components
        Collections.shuffle(yVec);

        // Combine the paired up components into vectors
        List<Point> vec = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            vec.add(new Point(xVec.get(i), yVec.get(i)));
        }

        // Sort the vectors by angle
        Collections.sort(vec, Comparator.comparingDouble(v -> Math.atan2(v.getY(), v.getX())));

        // Lay them end-to-end
        int x = 0, y = 0;
        int minPolygonX = 0;
        int minPolygonY = 0;
        List<Point> points = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            points.add(new Point(x, y));

            x += vec.get(i).getX();
            y += vec.get(i).getY();

            minPolygonX = Math.min(minPolygonX, x);
            minPolygonY = Math.min(minPolygonY, y);
        }

        // Move the polygon to the original min and max coordinates
        int xShift = minX - minPolygonX;
        int yShift = minY - minPolygonY;

        for (int i = 0; i < n; i++) {
            Point p = points.get(i);
            points.set(i, new Point(p.getX() + xShift, p.getY() + yShift));
        }

        return points;
    }
}