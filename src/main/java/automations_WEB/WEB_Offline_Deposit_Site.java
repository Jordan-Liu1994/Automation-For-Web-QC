package automations_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Logout_Function;
import functions_WEB.Offline_Deposit_Function;
import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;

public class WEB_Offline_Deposit_Site {

	static String site_Url = "https://wl003.the777888.com/";
	static String driver_Type = "webdriver.chrome.driver";
	static String driver_Path = "chromedriver.exe";
	static String name_Of_Report = "WEB_Offline_Deposit_Site";
	static String browser_Name = "Chrome";
	static String userID = "qctester400870";
	static String password = "test123";
	static String captcha = "123456";
	static String offlineDepositName = "公司入款";
	static String dpstOption = "线下入款";
	static int depositAmount = 123;
	static String depositoryName = "测试员";

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Login_Function function = Login_Function.getInstance();
	Offline_Deposit_Function functionDPST = Offline_Deposit_Function.getInstance();
	
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
//		function.ClickPasswordEyeIcon();
		Thread.sleep(500);
		function.SetCaptcha(captcha);
		Thread.sleep(500);
//		function.ClickRememberMeButton();
//		Thread.sleep(500);
		function.ClickLoginButton(userID);
	}
	
	@Test(priority = 3, dependsOnMethods = "EnterLoginData")
	public void ToDepositPage() throws InterruptedException, FailedLoginException {
		create_Report.createTest("ToDepositPage");
		Thread.sleep(500);
//		functionDPST.ClickDepositOption(userID);
//		Thread.sleep(500);
		functionDPST.ClickDepositOptionFromToolBar();
		Thread.sleep(500);
	}
	
	@Test(priority = 4, dependsOnMethods = "ToDepositPage")
	public void FillInOfflineDepositPageData() throws InterruptedException, FailedLoginException {
		create_Report.createTest("FillInOfflineDepositPageData");
		functionDPST.SelectOfflineDepositOption(offlineDepositName);
		Thread.sleep(1000);
		functionDPST.SelectAnyDepositOption(dpstOption);
		Thread.sleep(500);
		functionDPST.SetDepositAmount(depositAmount);
		Thread.sleep(250);
		functionDPST.SetDepositoryName(depositoryName);
		Thread.sleep(500);
//		functionDPST.CheckRememberDepositoryName();
//		Thread.sleep(250);
		functionDPST.CheckActualReceivedAmount(depositAmount);
		Thread.sleep(500);
		functionDPST.SubmitOfflineDepositRequest();
		Thread.sleep(500);
	}
	
	@Test(priority = 5, dependsOnMethods = "FillInOfflineDepositPageData")
	public void ConfirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		create_Report.createTest("ConfirmOfflineDepositPaid");
		functionDPST.ConfirmOfflineDepositPaid();
		Thread.sleep(1000);
	}
	
	@Test(priority = 6, dependsOnMethods = "ConfirmOfflineDepositPaid")
	public void CancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		create_Report.createTest("CancelOfflineDepositRequest");
		functionDPST.CancelOfflineDepositRequest();
		Thread.sleep(1000);
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