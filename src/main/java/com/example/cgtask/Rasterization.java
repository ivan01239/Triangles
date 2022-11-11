package com.example.cgtask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;


public class Rasterization {

    public static void fillTriangle(GraphicsContext graphicsContext, Point[] points) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        Triangle triangle = new Triangle(points[0], points[1], points[2]);

            for (int y = triangle.getPointA().getY(); y < triangle.getPointB().getY(); y++){
                int xAB = (int) ((y - triangle.getAB().getB()) / triangle.getAB().getK());
                int xAC = (int) ((y - triangle.getAC().getB()) / triangle.getAC().getK());
                if (xAB <= xAC){
                    paintPoints(triangle, y, xAB, xAC, pixelWriter);
                } else {
                    paintPoints(triangle, y, xAC, xAB, pixelWriter);
                }
        }
            for (int y = triangle.getPointB().getY(); y <= triangle.getPointC().getY(); y++) {
                int xBC = (int) ((y - triangle.getBC().getB()) / triangle.getBC().getK());
                int xAC = (int) ((y - triangle.getAC().getB()) / triangle.getAC().getK());
                if (xBC <= xAC){
                    paintPoints(triangle, y, xBC, xAC, pixelWriter);
                } else {
                    paintPoints(triangle, y, xAC, xBC, pixelWriter);
                }
            }
}

    private static void paintPoints(Triangle triangle, int y, int leftEdge, int rightEdge, PixelWriter pw) {

        for (int x = leftEdge; x <= rightEdge; x++) {
            double[] barC = triangle.FindBarC(new Point(x, y, null));
            double red = barC[0] * triangle.getPointA().getColor().getRed() +
                    barC[1] * triangle.getPointB().getColor().getRed() +
                    barC[2] * triangle.getPointC().getColor().getRed();
            if (red > 1) {
                red = 1;
            }
            if (red < 0) {
                red = 0;
            }
            double green = barC[0] * triangle.getPointA().getColor().getGreen() +
                    barC[1] * triangle.getPointB().getColor().getGreen() +
                    barC[2] * triangle.getPointC().getColor().getGreen();
            if (green > 1) {
                green = 1;
            }
            if (green < 0) {
                green = 0;
            }
            double blue = barC[0] * triangle.getPointA().getColor().getBlue() +
                    barC[1] * triangle.getPointB().getColor().getBlue() +
                    barC[2] * triangle.getPointC().getColor().getBlue();
            if (blue > 1) {
                blue = 1;
            }
            if (blue < 0) {
                blue = 0;
            }
            Color color = new Color(red, green, blue, 1);
            pw.setColor(x, y, color);
        }
    }
}
