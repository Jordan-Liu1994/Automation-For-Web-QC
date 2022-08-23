package utilities;

import java.util.Random;

public class GenerateRandomNumbers {

	private static GenerateRandomNumbers generateRandomNumbers = new GenerateRandomNumbers();

	public static GenerateRandomNumbers getInstance() {
		return generateRandomNumbers;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private int setNumber;
	
	public int generateRandomNumbers() {
		Random random = new Random();
		setNumber = 500000;
		int int_random = random.nextInt(setNumber);
		return int_random;
	}
}
