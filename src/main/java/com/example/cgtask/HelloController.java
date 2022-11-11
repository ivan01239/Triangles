package com.example.cgtask;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class HelloController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    Point[] points = new Point[3];
    Color[] pointColors = new Color[]
            {new Color(1, 0, 0, 1),
                    new Color(0, 1, 0, 1),
                    new Color(0, 0, 1, 1)};
    int numP = 0;

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
            }
        });
    }


    private void handlePrimaryClick(GraphicsContext graphicsContext2D, MouseEvent event) {
        final Point clickPoint = new Point((int)event.getX(), (int)event.getY(), pointColors[numP]);

        final int POINT_RADIUS = 3;
        graphicsContext2D.fillOval(
                clickPoint.getX() - POINT_RADIUS, clickPoint.getY() - POINT_RADIUS,
                2 * POINT_RADIUS, 2 * POINT_RADIUS);

        points[numP] = clickPoint;

        if (numP == 2) {
            Rasterization.fillTriangle(graphicsContext2D, points);
            numP = 0;
        } else numP++;
    }


}