import java.awt.Image;
import java.awt.image.BufferedImage;

public abstract class Shape {
	protected int [][] theShape;
	protected int [][] potentialShapeOne;
	protected int [][] potentialShapeTwo;
	protected int [][] potentialShapeThree;
	protected int [][] potentialShapeFour;
	abstract void rotateShape();
	abstract void printShape();
	abstract String getColor();
	abstract int getRotationLevel();
}


