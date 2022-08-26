package webAuthentication;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement;
import functions_WEB_FE.LoginFE;
import functions_WEB_FE.LogoutFE;
import functions_WEB_FE.RegisterFE;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FrontEndAuthentication extends VariablesStorage {

	private static String nameOfReport = "FE_Auth";

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	ResultListener rListener = ResultListener.getInstance();
	TakeScreenShot takeSS = TakeScreenShot.getInstance();

	Announcement aF = Announcement.getInstance();
	LoginFE loginF = LoginFE.getInstance();
	LogoutFE logoutF = LogoutFE.getInstance();
	RegisterFE registerF = RegisterFE.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "Authentication")
	public void setReport() throws InterruptedException, MalformedURLException {
		bDriver.setDriverProperty(driverType(), driverPath());
		bDriver.startDriver();
		cR.generateReport(nameOfReport);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 0, groups = "Authentication")
	public void toSite() throws InterruptedException, FailedLoginException {
		cR.createTest("openBrowserToSite");
		bDriver.getURL(siteUrlFE());
		bDriver.getDriver().navigate().refresh();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 1, groups = "Announcement")
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		cR.createTest("closeAnnouncement");
		aF.closeAnnouncement();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "Login", priority = 2)
	public void login() throws InterruptedException, FailedLoginException {
		cR.createTest("login");
		loginF.loginOptionButton();
		loginF.setUserID(userIDFE());
		loginF.setPassword(passwordAll());
		loginF.setCaptcha(captchaOtpAll());
		loginF.selectLoginButton();
		loginF.verifyLogIn(userIDFE());
		takeSS.getPassScreenShot("login");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "login" + "-passed.png");
	}

	@Test(groups = { "Login", "Logout" }, priority = 3)
	public void logout() throws InterruptedException, FailedLoginException {
		cR.createTest("logout");
		logoutF.selectLogoutButton(userIDFE());
		logoutF.verifyLogout();
		takeSS.getPassScreenShot("logout");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "logout" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "Register", priority = 4)
	public void register() throws InterruptedException, FailedLoginException {
		cR.createTest("register");
		registerF.registerOptionButton();
		registerF.setNewUserID(userIDRegister());
		registerF.setNewPassword(passwordAll());
		registerF.setDOB(year(), day());
		registerF.setCaptcha(captchaOtpAll());
		registerF.selectRegisterButton();
		registerF.verifyRegister(userIDRegister());
		takeSS.getPassScreenShot("register");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "register" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 
//
//	@Test(dependsOnMethods = "register", groups = "Register", priority = 5)
//	public void logoutAfterRegister() throws InterruptedException, FailedLoginException {
//		createReport.createTest("logoutAfterRegister");
//
//		Thread.sleep(500);
//		function3.selectLogoutButtonAfterRegister();
//		Thread.sleep(1000);
//	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		rListener.caseLogging(result);
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		cR.flushTest();
		Thread.sleep(1000);
		bDriver.stopDriver();
	}
}