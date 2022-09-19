package utilities;

import java.util.Random;

public class GenerateRandomNumbers {

	public int generateRandomNumbers() {
		Random r = new Random();
		int setNum = 500000;
		int int_R = r.nextInt(setNum);
		return int_R;
	}
}
