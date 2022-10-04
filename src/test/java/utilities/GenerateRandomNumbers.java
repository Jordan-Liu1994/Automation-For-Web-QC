package utilities;

import java.util.Random;

public class GenerateRandomNumbers {

	public int generateRandomNumbers() {
		Random random = new Random();
		int setNumber = 500000;
		int newNumber = random.nextInt(setNumber);
		return newNumber;
	}
}
