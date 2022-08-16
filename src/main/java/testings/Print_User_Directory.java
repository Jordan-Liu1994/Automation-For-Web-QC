package testings;

public class Print_User_Directory {

	public static void main(String[] args) {
		String userDirectory = System.getProperty("user.dir");
		System.out.println(userDirectory);
	}
}