
public class RedShape extends Shape{
	public RedShape(){
		theShape = new int[4][4];
		theShape[0][0] = 0;
		theShape[0][1] = 0;
		theShape[0][2] = 0;
		theShape[1][0] = 1;
		theShape[1][1] = 1;
		theShape[1][2] = 0;
		theShape[2][0] = 0;
		theShape[2][1] = 1;
		theShape[2][2] = 1;
		potentialShapeOne = new int[4][4];
		potentialShapeOne[0][0] = 0;
		potentialShapeOne[0][1] = 0;
		potentialShapeOne[0][2] = 1;
		potentialShapeOne[1][0] = 0;
		potentialShapeOne[1][1] = 1;
		potentialShapeOne[1][2] = 1;
		potentialShapeOne[2][0] = 0;
		potentialShapeOne[2][1] = 1;
		potentialShapeOne[2][2] = 0;
		potentialShapeTwo = theShape;
		
	}

	void rotateShape() {
		if (theShape == potentialShapeTwo)
			theShape = potentialShapeOne;
		else 
			theShape = potentialShapeTwo;
		
	}

	void printShape() {
		for(int x = 0; x<=2; x++){
			System.out.println();
			for(int y = 0; y<=2; y++){
				System.out.print(theShape[x][y]);
			}
		}
		
	}
	String getColor() {
		return "Red";
	}

	int getRotationLevel() {
		if (theShape == potentialShapeOne)
			return 1;
		else if (theShape == potentialShapeTwo)
			return 2;
		return 0;
	}
}




