package testings;

public class Singleton_Variables_Access {

	public static void main(String[] args) {
		Singleton_Variables singletonVar = Singleton_Variables.getInstance();
		singletonVar.runSingletonMethod();
	}
}