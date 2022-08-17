package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Logout_Function;
import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;

public class WEB_Logout_Site {

	static String site_Url = "https://wl003.the777888.com/";
	static String driver_Type = "webdriver.chrome.driver";
	static String driver_Path = "chromedriver.exe";
	static String name_Of_Report = "WEB_Logout_Site";
	static String browser_Name = "Chrome";
	static String userID = "qctester0101";
	static String password = "test123";
	static String captcha = "123456";

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Login_Function function = Login_Function.getInstance();
	Logout_Function function2 = Logout_Function.getInstance();
	
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
	public void ClickLoginOptionButton() throws InterruptedException, FailedLoginException {
		create_Report.createTest("ClickLoginOptionButton");
		Thread.sleep(500);
		function.ClickLoginOptionButton();
	}
	
	@Test(priority = 2, dependsOnMethods = "ClickLoginOptionButton")
	public void EnterLoginData() throws InterruptedException, FailedLoginException {
		create_Report.createTest("EnterLoginData");
		Thread.sleep(500);
		function.SetUserID(userID);
		Thread.sleep(500);
		function.SetPassword(password);
		function.ClickPasswordEyeIcon();
		Thread.sleep(500);
		function.SetCaptcha(captcha);
		Thread.sleep(500);
		function.ClickRememberMeButton();
		Thread.sleep(500);
		function.ClickLoginButton(userID);
	}
	
	@Test(priority = 3, dependsOnMethods = "EnterLoginData")
	public void Logout() throws InterruptedException, FailedLoginException {
		create_Report.createTest("Logout");
		Thread.sleep(500);
		function2.ClickLogoutButton();
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