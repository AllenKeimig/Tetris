
public class OrangeShape extends Shape{
	public OrangeShape(){
		super();
		theShape = new int[4][4];
		theShape[0][0] = 0;
		theShape[0][1] = 0;
		theShape[0][2] = 1;
		theShape[1][0] = 1;
		theShape[1][1] = 1;
		theShape[1][2] = 1;
		theShape[2][0] = 0;
		theShape[2][1] = 0;
		theShape[2][2] = 0;
		potentialShapeOne = new int[4][4];
		potentialShapeOne[0][0] = 0;
		potentialShapeOne[0][1] = 1;
		potentialShapeOne[0][2] = 0;
		potentialShapeOne[1][0] = 0;
		potentialShapeOne[1][1] = 1;
		potentialShapeOne[1][2] = 0;
		potentialShapeOne[2][0] = 0;
		potentialShapeOne[2][1] = 1;
		potentialShapeOne[2][2] = 1;
		potentialShapeTwo = new int[4][4];
		potentialShapeTwo[0][0] = 0;
		potentialShapeTwo[0][1] = 0;
		potentialShapeTwo[0][2] = 0;
		potentialShapeTwo[1][0] = 1;
		potentialShapeTwo[1][1] = 1;
		potentialShapeTwo[1][2] = 1;
		potentialShapeTwo[2][0] = 1;
		potentialShapeTwo[2][1] = 0;
		potentialShapeTwo[2][2] = 0;
		potentialShapeThree = new int[4][4];
		potentialShapeThree [0][0] = 1;
		potentialShapeThree [0][1] = 1;
		potentialShapeThree [0][2] = 0;
		potentialShapeThree [1][0] = 0;
		potentialShapeThree [1][1] = 1;
		potentialShapeThree [1][2] = 0;
		potentialShapeThree [2][0] = 0;
		potentialShapeThree [2][1] = 1;
		potentialShapeThree [2][2] = 0;
		potentialShapeFour = theShape;
		
	}

	void rotateShape() {
		if (theShape == potentialShapeFour)
			theShape = potentialShapeOne;
		else if (theShape == potentialShapeOne)
			theShape = potentialShapeTwo;
		else if (theShape == potentialShapeTwo)
			theShape = potentialShapeThree;
		else if (theShape == potentialShapeThree)
			theShape = potentialShapeFour;
		
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
		return "Orange";
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



