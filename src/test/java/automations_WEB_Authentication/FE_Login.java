package automations_WEB_Authentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement_Function;
import functions_WEB_FE.Login_Function;
import functions_WEB_FE.Logout_Function;
import functions_WEB_FE.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Login extends VariablesStorage {

	private static String nameOfReport = "FE_Login";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Announcement_Function function1 = Announcement_Function.getInstance();
	Login_Function function2 = Login_Function.getInstance();
	Logout_Function function3 = Logout_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 
	
	@BeforeSuite
	public void startUp() throws InterruptedException, MalformedURLException {
		baseDriver.setDriverProperty(driverType(), driverPath());
		baseDriver.startDriver();
		// baseDriver.startDriverRemote(siteUrlFE());
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 0, groups = { "URL" })
	public void toSite() throws InterruptedException, FailedLoginException {
		createReport.generateReport(nameOfReport);
		createReport.createTest("openBrowserToSite");

		baseDriver.getURL(siteUrlFE());
		verifySite.verifySite(siteUrlFE());
		Thread.sleep(500);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 1, groups = { "Announcement" })
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");
		
		function1.doNotShowAgainTodayRadioButton();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 2, groups = { "Login" })
	public void login() throws InterruptedException, FailedLoginException {
		createReport.createTest("login");

		Thread.sleep(500);
		function2.selectLoginOption();
		Thread.sleep(1000);
		function2.setUserID(userID());
		function2.setPassword(password());
		Thread.sleep(500);
		function2.clickPasswordEyeIcon();
		function2.setCaptcha(captcha());
		function2.selectRememberMeButton();
		Thread.sleep(500);
		function2.selectLoginButton();
		Thread.sleep(1000);
		function2.verifyLogIn(userID());
		
		takeScreenShot.getTakeScreenShotPass("login");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "login" + "-passed.png", "login");
	}

	@Test(priority = 3, groups = { "Login" })
	public void closeAnnouncementAfterLogin() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncementAfterLogin");

		function1.doNotShowAgainTodayRadioButton();
	}

//	= = = = = = = = = = = = = = = = = = = = 

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