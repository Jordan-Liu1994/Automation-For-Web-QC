package automations_WEB_Authentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functions_WEB.Announcement_Function;
import functions_WEB.Login_Function;
import functions_WEB.Logout_Function;
import functions_WEB.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_LoginLogout extends VariablesStorage {

	private static String nameOfReport = "FE_LoginLogout";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();

	Login_Function function = Login_Function.getInstance();
	Announcement_Function function2 = Announcement_Function.getInstance();
	Logout_Function function3 = Logout_Function.getInstance();

	@BeforeSuite
	public void startUp() throws InterruptedException, MalformedURLException {
		baseDriver.setDriverProperty(driverType(), driverPath());
		baseDriver.startDriver(siteUrlFE());
		// baseDriver.startDriverRemote(siteUrlFE());
	}

	@Test(priority = 0, groups = { "Authentication" })
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.generateReport(nameOfReport);
		createReport.createTest("openBrowserToSite");

		verifySite.VerifySite(siteUrlFE());
		Thread.sleep(500);
	}

	@Test(priority = 1, groups = { "Announcement" })
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");

		function2.selectDoNotShowAgainTodayCheckBox();
	}

//	 ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

	@Test(priority = 2, groups = { "Login" })
	public void login() throws InterruptedException, FailedLoginException {
		createReport.createTest("login");

		function.selectLoginOption();
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
		takeScreenShot.getTakeScreenShotPass("login");

		// createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent()
		// + "login" + " " + takeScreenShot.timestamp() + "-passed.png", "login");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "login" + "-passed.png", "login");
	}

	@Test(priority = 3, groups = { "Login" })
	public void closeAnnouncementAfterLogin() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncementAfterLogin");

		function2.selectDoNotShowAgainTodayCheckBox();
	}

//	 ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

	@Test(priority = 3, groups = { "Logout" })
	public void logout() throws InterruptedException, FailedLoginException {
		createReport.createTest("logout");

		Thread.sleep(1000);
		function3.selectLogoutButton(userID());
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("logout");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "logout" + "-passed.png", "logout");
	}

//	 ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(500);
	}

	@AfterSuite
	public void shutDownAll() throws InterruptedException {
		baseDriver.stopDriver();
	}
}