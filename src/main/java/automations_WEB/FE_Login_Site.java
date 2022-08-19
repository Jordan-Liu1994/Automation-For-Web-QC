package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;
import utilities.Variables_Storage;

public class FE_Login_Site extends Variables_Storage {

	static String nameOfReport = "FE_Login_Site";

	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();
	Result_Listener resultListener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	
	Login_Function function = Login_Function.getInstance();

	@BeforeClass
	public void startUp() {
		createReport.generateReport(nameOfReport);
		baseDriver.setDriverProperty(driverType(), driverPath());
	}

	@Test(priority = 0)
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");
		baseDriver.startDriver(siteUrlFE());
	}

	@Test(priority = 1, dependsOnMethods = "openBrowserToSite")
	public void selectLoginOption() throws InterruptedException, FailedLoginException {
		createReport.createTest("selectLoginOption");
		function.selectLoginOption();
	}

	@Test(priority = 2, dependsOnMethods = "selectLoginOption")
	public void fillInLoginDataAndLogin() throws InterruptedException, FailedLoginException {
		createReport.createTest("fillInLoginDataAndLogin");
		Thread.sleep(500);
		function.setUserID(userID());
		function.setPassword(password());
		Thread.sleep(500);
		function.clickPasswordEyeIcon();
		function.setCaptcha(captcha());
		function.selectRememberMeButton();
		Thread.sleep(500);
		function.selectLoginButton();
		function.verifyLogIn(userID());
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(2000);
		baseDriver.stopDriver();
	}
}