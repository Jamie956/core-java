package com.jamie.pattern;

public class BridgeDemo {
    public static void main(String[] args) {

        Circle redCircle = new Circle(100, 100, 10, new RedCircle());
        redCircle.draw();

        Circle greenCircle = new Circle(100, 100, 10, new GreenCircle());
        greenCircle.draw();
    }

    interface DrawAPI {
        void drawCircle(int radius, int x, int y);
    }

    static class RedCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing red circle radius=" + radius + ", x=" + x + ", y=" + y);
        }
    }

    static class GreenCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing green circle radius=" + radius + ", x=" + x + ", y=" + y);
        }
    }

    abstract static class Shape {
        protected DrawAPI drawAPI;

        protected Shape(DrawAPI drawAPI) {
            this.drawAPI = drawAPI;
        }

        abstract void draw();
    }

    static class Circle extends Shape {
        private int x, y, radius;

        protected Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        void draw() {
            drawAPI.drawCircle(radius, x, y);
        }
    }

}
