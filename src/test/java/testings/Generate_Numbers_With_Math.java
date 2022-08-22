package testings;

public class Generate_Numbers_With_Math {

	private static Generate_Numbers_With_Math generate_random_numbers = new Generate_Numbers_With_Math();

	public static void main(String[] args) {
		System.out.println(generate_random_numbers.generateRandomNumbers());
	}

	public int generateRandomNumbers() {
		int min = 100000;
		int max = 300000;

		int random_number = (int) Math.floor(Math.random() * (max - min) + min);
		return random_number;
	}
}
