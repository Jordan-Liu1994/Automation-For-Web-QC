package utilities;

public class VariablesStorage {
		
	static String siteUrlFEPreLive = "https://pr3l1v3.com/";
	static String driverType = "webdriver.chrome.driver";
	static String firefoxDriverType = "webdriver.gecko.driver";
	static String driverPath = "chromedriver.exe";
	static String firefoxDriverPath = "geckodriver.exe";
	static String browserName = "Chrome";
	static String firefoxBrowserName = "Firefox";
	static String platformName = "Windows";
	static String javaVersion = "JDK 17";
	static String automationAuthor = "Jordan";
	static String userIDFE = "qctester001";
	static String passwordAll = "test123";
	static String captchaOtpAll = "123456";
	static String year = "1994";
	static String day = "2";
	static String languageSelection = "简体中文";
	
	static String depositOptionName = "Bank Transfer";
//	static String depositOptionName = "erc";
//	static String depositOptionName = "trc";
	
	static int depositAmount = 123;
	static String depositoryName = "测试员";
	static String siteUrlBO = "https://backoffice.pr3l1v3.com/";
	static String userIDBO = "automation001";
	static String withdrawPinNum = "1234";
	static String step = "STEP = ";
	static String passSS = "-passed.png";
	static String failSS = "-failed.png";

	static String financeModule = "财务管理";
	static String financeSubModule = "公司入款审核";
	
	GenerateRandomNumbers gRN = GenerateRandomNumbers.getInstance();
	int randomNum = gRN.generateRandomNumbers();

	private String rData;
	
	public String siteUrlFEPreLive() {
		return siteUrlFEPreLive;
	}

	public String driverType() {
		return driverType;
	}
	
	public String firefoxDriverType() {
		return firefoxDriverType;
	}

	public String driverPath() {
		return driverPath;
	}
	
	public String firefoxDriverPath() {
		return firefoxDriverPath;
	}

	public String browserName() {
		return browserName;
	}
	
	public String firefoxBrowserName() {
		return firefoxBrowserName;
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
	
	public String languageSelection() {
		return languageSelection;
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
	
	public String financeModule() {
		return financeModule;
	}
	
	public String financeSubModule() {
		return financeSubModule;
	}
}
