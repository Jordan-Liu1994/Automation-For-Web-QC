package utilities;

public class VariablesStorage {
		
	static String siteUrlFE = "https://wl003.the777888.com/";
	static String driverType = "webdriver.chrome.driver";
	static String driverPath = "chromedriver.exe";
	static String browserName = "Chrome";
	static String platformName = "Windows";
	static String javaVersion = "JDK 17";
	static String automationAuthor = "Jordan";
	static String userIDFE = "qctester001";
	static String passwordAll = "test123";
	static String captchaOtpAll = "123456";
	static String year = "1994";
	static String day = "2";
	static String offlineDepositName = "公司入款";
	static String depositOptionName = "线下入款";
	static int depositAmount = 123;
	static String depositoryName = "测试员";
	static String siteUrlBO = "https://wl003-bo.the777888.com/";
	static String userIDBO = "jordan";
	static String withdrawPinNum = "1234";
	static String step = "STEP = ";
	static String passSS = "-passed.png";
	static String failSS = "-failed.png";

	GenerateRandomNumbers gRN = GenerateRandomNumbers.getInstance();
	int randomNum = gRN.generateRandomNumbers();

	private String rData;

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

	public String automationAuthor() {
		return automationAuthor;
	}

	public String userIDFE() {
		return userIDFE;
	}

	public String passwordAll() {
		return passwordAll;
	}

	public String captchaOtpAll() {
		return captchaOtpAll;
	}

	public String year() {
		return year;
	}

	public String day() {
		return day;
	}

	public String userIDRegister() {
		rData = "qctester" + randomNum;
		return rData;
	}

	public String phoneNumber() {
		rData = "13131" + randomNum;
		return rData;
	}

	public String bankAccountNumber() {
		rData = "123450000" + randomNum;
		return rData;
	}

	public String offlineDepositName() {
		return offlineDepositName;
	}

	public String depositOptionName() {
		return depositOptionName;
	}

	public String depositoryName() {
		return depositoryName;
	}

	public int depositAmount() {
		return depositAmount;
	}

	public String siteUrlBO() {
		return siteUrlBO;
	}

	public String userIDBO() {
		return userIDBO;
	}

	public String withdrawPinNum() {
		return withdrawPinNum;
	}

	public String step() {
		return step;
	}

	public String passSS() {
		return passSS;
	}

	public String failSS() {
		return failSS;
	}
}
