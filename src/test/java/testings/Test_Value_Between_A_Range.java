package testings;

public class Test_Value_Between_A_Range {

//	private static Test_Value_Between_A_Range generate_random_numbers = new Test_Value_Between_A_Range();

	public static void main(String[] args) {
		double minDeduct = 0.01;
		double maxDeduct = 0.10;
		int amt = 123;
		double amt2 = 122.96;

		double finalMin = 122.99;
		double finalMax = 122.90;

		System.out.println(finalMin);
		System.out.println(finalMax);

		if (122.90 <= 122.96) {
			if (122.96 <= 122.99) {
				System.out.println("YES");
			}
		}
	}
}
