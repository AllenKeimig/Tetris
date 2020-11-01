
public class YellowShape extends Shape {
    public YellowShape() {
        theShape = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
    }

    void rotateShape() {

    }

    void printShape() {
        for (int x = 0; x <= 1; x++) {
            System.out.println();
            for (int y = 0; y <= 1; y++) {
                System.out.print(theShape[x][y]);
            }
        }

    }

    String getColor() {
        return "Yellow";
    }

    int getRotationLevel() {
        return 0;
    }
}



