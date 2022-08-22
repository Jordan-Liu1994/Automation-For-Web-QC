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
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Logout_Site extends VariablesStorage {

	static String nameOfReport = "FE_Logout_Site";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();

	Login_Function function = Login_Function.getInstance();
	Logout_Function function2 = Logout_Function.getInstance();

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
		function.setCaptcha(captcha());
		Thread.sleep(500);
		function.selectLoginButton();
		function.verifyLogIn(userID());
	}

	@Test(priority = 3, dependsOnMethods = "fillInLoginDataAndLogin")
	public void logout() throws InterruptedException, FailedLoginException {
		createReport.createTest("Logout");
		Thread.sleep(1000);
		function2.selectLogoutButton(userID());
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("Logout");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "Logout" + " " + takeScreenShot.timestamp() + "-passed.png", "Logout");
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(1000);
		baseDriver.stopDriver();
	}
}