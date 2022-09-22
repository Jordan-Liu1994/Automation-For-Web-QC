package utilities;

import org.openqa.selenium.support.ui.WebDriverWait;

public class VariablesStorage {
		
	protected BaseDriver bDriver = new BaseDriver();
	
	protected static String driverTypeChrome = "webdriver.chrome.driver";
	protected static String driverPathChrome = "chromedriver.exe";
	
	protected static String firefoxDriverType = "webdriver.gecko.driver";
	protected static String firefoxDriverPath = "geckodriver.exe";

	protected static String step = "Step = ";
	protected static String attribute = "placeholder";
	protected static String keyIn = " keyed in ";
	protected static String keyOut = " removed from ";
	
	protected static String passwordFE = "test123";
	protected static String captchaFE = "123456";
	protected static String year = "1994";
	protected static String day = "2";
	
	protected static WebDriverWait wait;
	protected static String fail;
	protected static String skip;
}