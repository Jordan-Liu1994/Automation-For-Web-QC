package automations_WEB_Authentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.WebDriver.Window;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement_Function;
import functions_WEB_FE.Login_Function;
import functions_WEB_FE.Logout_Function;
import functions_WEB_FE.Register_Function;
import functions_WEB_FE.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Authentication extends VariablesStorage {

	private static String nameOfReport = "FE_Auth";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Announcement_Function function1 = Announcement_Function.getInstance();
	Login_Function function2 = Login_Function.getInstance();
	Logout_Function function3 = Logout_Function.getInstance();
	Register_Function function4 = Register_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "Authentication")
	public void setReport() throws InterruptedException, MalformedURLException {
		baseDriver.setDriverProperty(driverType(), driverPath());
		baseDriver.startDriver();
		createReport.generateReport(nameOfReport);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 0)
	public void toSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");

		baseDriver.getURL(siteUrlFE());
		verifySite.verifySite(siteUrlFE());
		System.out.println(baseDriver.getDriver().getWindowHandle());
		Thread.sleep(500);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", priority = 1)
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");

		function1.doNotShowAgainTodayRadioButton();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", groups = "Login", priority = 2)
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
		Thread.sleep(1000);

		takeScreenShot.getTakeScreenShotPass("Login");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "Login" + "-passed.png");
	}

	@Test(dependsOnMethods = "login", groups = "Login", priority = 3)
	public void logout() throws InterruptedException, FailedLoginException {
		createReport.createTest("logout");

		Thread.sleep(500);
		function3.selectLogoutButton(userID());
		Thread.sleep(1000);

		takeScreenShot.getTakeScreenShotPass("Logout");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "Logout" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", groups = "Register", priority = 4)
	public void register() throws InterruptedException, FailedLoginException {
		createReport.createTest("register");

		Thread.sleep(500);
		function4.selectRegisterOption();
		Thread.sleep(500);
		function4.setNewUserID(userIDRegister());
		function4.setNewPassword(password());
		function4.selectPasswordEyeIcon();
		function4.setReferralOptional(referral());
		function4.setCaptcha(captcha());
		Thread.sleep(500);
		function4.setDateOfBirth(year(), day());
		Thread.sleep(500);
		function4.selectRegisterButton();
		Thread.sleep(1000);
		function4.verifyRegisterUserID(userIDRegister());

		takeScreenShot.getTakeScreenShotPass("Register");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "Register" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "register", groups = "Register", priority = 5)
	public void logoutAfterRegister() throws InterruptedException, FailedLoginException {
		createReport.createTest("logoutAfterRegister");

		Thread.sleep(500);
		function3.selectLogoutButtonAfterRegister();
		Thread.sleep(1000);
	}
	
	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(1000);
		baseDriver.stopDriver();
	}
}