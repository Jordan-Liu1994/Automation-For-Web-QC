package utilities;

public class VariablesStorage {

	private String siteUrlFE = "https://wl003.the777888.com/";
	private String driverType = "webdriver.chrome.driver";
	private String driverPath = "chromedriver.exe";
	private String browserName = "Chrome";

	GenerateRandomNumbers generateRandomNumbers = GenerateRandomNumbers.getInstance();

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

	private String platformName = "Windows";
	private String javaVersion = "JDK 17";
	private String userName = "Jordan";

	public String platformName() {
		return platformName;
	}

	public String javaVersion() {
		return javaVersion;
	}

	public String userName() {
		return userName;
	}

	static String userID = "qctester400870";
	static String password = "test123";
	static String captcha = "123456";
	static String referral = "ref123";
	static String year = "1994";
	static String day = "2";

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

	int randomNumbers = generateRandomNumbers.generateRandomNumbers();
	String userIDRegister = "qctester" + randomNumbers;

	public String userIDRegister() {
		return userIDRegister;
	}

	static String offlineDepositName = "公司入款";
	static String depositOption = "线下入款";
	static int depositAmount = 123;
	static String depositoryName = "测试员";

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
	
	private String siteUrlBO = "https://wl003-bo.the777888.com/";
	private String userIDBO = "jordan";
	private String passwordBO = "test123";
	private String otpBO = "123456";
	
	public String siteUrlBO() {
		return siteUrlBO;
	}
	
	public String userIDBO() {
		return userIDBO;
	}
	
	public String passwordBO() {
		return passwordBO;
	}
	
	public String otpBO() {
		return otpBO;
	}
}
