package utilities;

import webFEFunctions.Announcement;
import webFEFunctions.LoginFrontEnd;
import webFEFunctions.LogoutFrontEnd;
import webFEFunctions.RegisterFrontEnd;

public class VariablesStorage {
		
	protected Driver driver = new Driver();
	protected DateFormatting dateFormatting = new DateFormatting();
	protected CreateReport createReport = new CreateReport();
	protected ResultListener resultListener = new ResultListener();
	protected Announcement announcement = new Announcement();
	protected LoginFrontEnd loginFrontEnd = new LoginFrontEnd();
	protected LogoutFrontEnd logoutFrontEnd = new LogoutFrontEnd();
	protected RegisterFrontEnd registerFrontEnd = new RegisterFrontEnd();
	
	protected static String driverTypeChrome = "webdriver.chrome.driver";
	protected static String driverPathChrome = "chromedriver.exe";
	
	protected static String firefoxDriverType = "webdriver.gecko.driver";
	protected static String firefoxDriverPath = "geckodriver.exe";

	protected static String keyIn = " keyed in ";
	protected static String keyOut = " removed from ";
	
	protected static String passwordFE = "test123";
	protected static String captchaFE = "123456";
	protected static String year = "1994";
	protected static String day = "2";
	
	protected static String fail;
	protected static String skip;
}