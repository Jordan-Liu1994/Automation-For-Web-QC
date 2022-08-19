package utilities;

public class Variables_Storage {
	
	private String siteUrlFE = "https://wl003.the777888.com/";
	private String driverType = "webdriver.chrome.driver";
	private String driverPath = "chromedriver.exe";
	private String browserName = "Chrome";
	
	private String platformName = "Windows";
	private String javaVersion = "JDK 17";
	private String userName = "Jordan";
	
	static String userID = "qctester400870";
	static String password = "test123";
	static String captcha = "123456";
	static String referral = "ref123";
	static String year = "1994";
	static String day = "2";
	
	static String offlineDepositName = "公司入款";
	static String depositOption = "线下入款";
	static int depositAmount = 123;
	static String depositoryName = "测试员";
		
	Generate_Random_Numbers generateRandomNumbers = Generate_Random_Numbers.getInstance();

	int randomNumbers = generateRandomNumbers.generateRandomNumbers();
	String userIDRegister = "qctester" + randomNumbers;
	
	public String siteUrlFE() {
		return siteUrlFE;
	}
	
	public String driverType() {
		return driverType;
	}
	
	public String driverPath() {
		return driverPath;
	}
	
	public String browserName() {
		return browserName;
	}
	
	public String platformName() {
		return platformName;
	}
	
	public String javaVersion() {
		return javaVersion;
	}
	
	public String userName() {
		return userName;
	}
	
	public String userID() {
		return userID;
	}
	
	public String password() {
		return password;
	}
	
	public String captcha() {
		return captcha;
	}
	
	public String referral() {
		return referral;
	}
	
	public String year() {
		return year;
	}
	
	public String day() {
		return day;
	}
	
	public String userIDRegister() {
		return userIDRegister;
	}
	
	public String offlineDepositName() {
		return offlineDepositName;
	}
	
	public String depositOption() {
		return depositOption;
	}
	
	public String depositoryName() {
		return depositoryName;
	}
	
	public int depositAmount() {
		return depositAmount;
	}
}
