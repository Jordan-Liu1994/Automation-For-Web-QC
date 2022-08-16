package testings;

public class Singleton_Variables {

	private static Singleton_Variables singleton = new Singleton_Variables();

	public static Singleton_Variables getInstance() {
		return singleton;
	}

	public static void runSingletonMethod() {
		System.out.println("Run method for singleton");
	}
}
