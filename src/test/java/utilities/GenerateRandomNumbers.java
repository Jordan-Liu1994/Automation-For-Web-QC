package utilities;

import java.util.Random;

public class GenerateRandomNumbers {

	private static GenerateRandomNumbers gRN = new GenerateRandomNumbers();

	public static GenerateRandomNumbers getInstance() {
		return gRN;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private int setNum;
	
	public int generateRandomNumbers() {
		Random r = new Random();
		setNum = 500000;
		int int_R = r.nextInt(setNum);
		return int_R;
	}
}
