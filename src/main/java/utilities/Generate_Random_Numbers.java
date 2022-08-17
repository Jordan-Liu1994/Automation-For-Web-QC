package utilities;

import java.util.Random;

public class Generate_Random_Numbers {

	private static Generate_Random_Numbers generateRandomNumbers = new Generate_Random_Numbers();

	public static Generate_Random_Numbers getInstance() {
		return generateRandomNumbers;
	}

	private int setNumber = 500000;
	
	public int generateRandomNumbers() {
		Random random = new Random();
		int setNumber = 500000;
		int int_random = random.nextInt(setNumber);
		return int_random;
	}
}
