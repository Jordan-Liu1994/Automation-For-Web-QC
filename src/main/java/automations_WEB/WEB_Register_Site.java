package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Register_Function;
import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Generate_Random_Numbers;
import utilities.Result_Listener;

public class WEB_Register_Site {

	static String site_Url = "https://wl003.the777888.com/";
	static String driver_Type = "webdriver.chrome.driver";
	static String driver_Path = "chromedriver.exe";
	static String name_Of_Report = "WEB_Register_Site";
	static String browser_Name = "Chrome";
	static String password = "test123";
	static String captcha = "123456";
	static String referral = "ref123";
	static String year = "1994";
	static String day = "2";

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Generate_Random_Numbers generateRandomNumbers = Generate_Random_Numbers.getInstance();
	Register_Function function = Register_Function.getInstance();
	
	int randomNumbers = generateRandomNumbers.generateRandomNumbers();
	String userID = "qctester" + randomNumbers;
	
	@BeforeClass
	public void StartUp() {
		create_Report.generateReport(name_Of_Report, browser_Name);
		base_Driver.setDriverProperty(driver_Type, driver_Path);
	}

	String current_Url;
	String current_WindowHandle;

	@Test(priority = 0)
	public void OpenBrowserToSite() throws InterruptedException, FailedLoginException {
		create_Report.createTest("OpenBrowserToSite");
		base_Driver.startDriver(site_Url);
		verifySite.VerifySite(site_Url);
	}

	@Test(priority = 1, dependsOnMethods = "OpenBrowserToSite")
	public void ClickRegisterOptionButton() throws InterruptedException, FailedLoginException {
		create_Report.createTest("ClickRegisterOptionButton");
		Thread.sleep(500);
		function.ClickRegisterOptionButton();
	}
	
	@Test(priority = 2, dependsOnMethods = "ClickRegisterOptionButton")
	public void EnterRegisterData() throws InterruptedException, FailedLoginException {
		create_Report.createTest("EnterRegisterData");
		Thread.sleep(500);
		function.SetNewUserID(userID);
		Thread.sleep(500);
		function.SetNewPassword(password);
		function.ClickPasswordEyeIcon();
		Thread.sleep(500);
		function.SetReferralOptional(referral);
		Thread.sleep(500);
		function.SetCaptcha(captcha);
		Thread.sleep(500);
		function.SetDateOfBirth(year, day);
	}

	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		result_Listener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		create_Report.flushTest();
		Thread.sleep(2000);
		base_Driver.stopDriver();
	}
}