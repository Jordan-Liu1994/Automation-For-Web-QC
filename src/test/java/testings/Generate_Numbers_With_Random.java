package testings;

import java.util.Random;

public class Generate_Numbers_With_Random {

	private static Generate_Numbers_With_Random generate_random_numbers = new Generate_Numbers_With_Random();

	public static void main(String[] args) {
		System.out.println(generate_random_numbers.generateRandomNumbers());
	}

	public int generateRandomNumbers() {
		Random random = new Random();
		int max = 500000;
		int int_random = random.nextInt(max);
		return int_random;
	}
}