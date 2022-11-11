package com.example.cgtask;


public class Triangle {
    private Point pointA;
    private Point pointB;
    private Point pointC;
    public class Edge {
        private double k;
        private double b;

        public Edge(double k, double b) {
            this.k = k;
            this.b = b;
        }

        public double getK() {
            return k;
        }

        public double getB() {
            return b;
        }
    }
    private Edge AB;
    private Edge AC;
    private Edge BC;

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public Edge getAB() {
        return AB;
    }

    public Edge getAC() {
        return AC;
    }

    public Edge getBC() {
        return BC;
    }

    public Triangle(Point pointA, Point pointB, Point pointC) {

        if (pointA.getY() > pointB.getY()) {
            Point tmp = pointA;
            pointA = pointB;
            pointB = tmp;
        }
        if (pointA.getY() > pointC.getY()) {
            Point tmp = pointA;
            pointA = pointC;
            pointC = tmp;
        }
        if (pointB.getY() > pointC.getY()) {
            Point tmp = pointB;
            pointB = pointC;
            pointC = tmp;
        }

        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;

        AB = new Edge((pointA.getY() * 1.0 - pointB.getY()) / (pointA.getX() * 1.0 - pointB.getX()),
                (pointB.getX() * pointA.getY() * 1.0 - pointA.getX() * pointB.getY())
                        / ((pointB.getX() - pointA.getX()) * 1.0));

        AC = new Edge((pointA.getY() * 1.0 - pointC.getY()) / (pointA.getX() * 1.0 - pointC.getX()),
                (pointC.getX() * pointA.getY() * 1.0 - pointA.getX() * pointC.getY())
                        / ((pointC.getX() - pointA.getX()) * 1.0));

        BC = new Edge((pointB.getY() * 1.0 - pointC.getY()) / (pointB.getX() * 1.0 - pointC.getX()),
                (pointC.getX() * pointB.getY() * 1.0 - pointB.getX() * pointC.getY())
                        / ((pointC.getX() - pointB.getX()) * 1.0));
    }


    public double[] FindBarC (Point pointZ) {
        double[] barC = {0, 0, 0};
        int[] pointVector = {pointZ.getX() - pointC.getX(), pointZ.getY() - pointC.getY()};
        int[][] matrix = {{pointA.getX() - pointC.getX(), pointB.getX() - pointC.getX()},
                {pointA.getY() - pointC.getY(), pointB.getY() - pointC.getY()}};
        int det = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        if (det != 0) {
            double[][] reverseMatrix = new double[2][2];
            reverseMatrix[0][0] = matrix [1][1] * 1.0 / det;
            reverseMatrix[0][1] = -matrix [0][1] * 1.0 / det;
            reverseMatrix[1][0] = -matrix [1][0] * 1.0 / det;
            reverseMatrix[1][1] = matrix[0][0] * 1.0 / det;

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++){
                    barC[i]+= reverseMatrix[i][j] * pointVector[j];
                }
            }
            barC[2] = 1 - barC[0] - barC[1];
        }
        return barC;
    }



}
