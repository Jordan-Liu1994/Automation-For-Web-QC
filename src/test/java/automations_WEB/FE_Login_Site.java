package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Announcement_Function;
import functions_WEB.Login_Function;
import functions_WEB.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Login_Site extends VariablesStorage {

	static String nameOfReport = "FE_Login_Site";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();

	Announcement_Function function2 = Announcement_Function.getInstance();
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
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");
		function2.selectDoNotShowAgainTodayCheckBox();
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
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("fillInLoginDataAndLogin");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "fillInLoginDataAndLogin" + " " + takeScreenShot.timestamp() + "-passed.png", "fillInLoginDataAndLogin");
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