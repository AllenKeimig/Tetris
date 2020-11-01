import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BlueShape extends Shape{
	public BlueShape(){
		theShape = new int[4][4];
		theShape[0][0] = 0;
		theShape[0][1] = 1;
		theShape[0][2] = 0;
		theShape[0][3] = 0;
		theShape[1][0] = 0;
		theShape[1][1] = 1;
		theShape[1][2] = 0;
		theShape[1][3] = 0;
		theShape[2][0] = 0;
		theShape[2][1] = 1;
		theShape[2][2] = 0;
		theShape[2][3] = 0;
		theShape[3][0] = 0;
		theShape[3][1] = 1;
		theShape[3][2] = 0;
		theShape[3][3] = 0;
		potentialShapeTwo = theShape;
		potentialShapeOne = new int[4][4];
		potentialShapeOne[0][0] = 0;
		potentialShapeOne[0][1] = 0;
		potentialShapeOne[0][2] = 0;
		potentialShapeOne[0][3] = 0;
		potentialShapeOne[1][0] = 1;
		potentialShapeOne[1][1] = 1;
		potentialShapeOne[1][2] = 1;
		potentialShapeOne[1][3] = 1;
		potentialShapeOne[2][0] = 0;
		potentialShapeOne[2][1] = 0;
		potentialShapeOne[2][2] = 0;
		potentialShapeOne[2][3] = 0;
		potentialShapeOne[3][0] = 0;
		potentialShapeOne[3][1] = 0;
		potentialShapeOne[3][2] = 0;
		potentialShapeOne[3][3] = 0;

	}
	void rotateShape() {
		if (theShape == potentialShapeTwo)
			theShape = potentialShapeOne;
		else{
			theShape = potentialShapeTwo;
		}
		
	}

	void printShape() {
		for(int x = 0; x<=3; x++){
			System.out.println();
			for(int y = 0; y<=3; y++){
				System.out.print(theShape[x][y]);
			}
		}
		
	}

	String getColor() {
		return "Blue";
	}

	int getRotationLevel() {
		if (theShape == potentialShapeFour)
			return 4;
		else if (theShape == potentialShapeOne)
			return 1;
		else if (theShape == potentialShapeTwo)
			return 2;
		else if (theShape == potentialShapeThree)
			return 3;
		return 0;
	}
}



