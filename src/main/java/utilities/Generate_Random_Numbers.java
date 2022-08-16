package utilities;

import java.util.Random;

public class Generate_Random_Numbers {

	private static Generate_Random_Numbers generate_Random_Numbers = new Generate_Random_Numbers();

	public static Generate_Random_Numbers getInstance() {
		return generate_Random_Numbers;
	}

	private int setNumber = 500000;
	
	public int generateRandomNumbers() {
		Random random = new Random();
		int setNumber = 500000;
		int int_random = random.nextInt(setNumber);
		return int_random;
	}
}
