package webAuthentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;
import webFEFunctions.Announcement;
import webFEFunctions.Login;
import webFEFunctions.LogoutFE;
import webFEFunctions.RegisterFE;

public class Authentication extends VariablesStorage {

	private static String reportName = "FrontEndAuthentication";

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cReport = new CreateReport();
	ResultListener rListener = new ResultListener();

	Announcement announce = new Announcement();
	Login log = new Login();
	LogoutFE logOut = new LogoutFE();
	RegisterFE register = new RegisterFE();

	@BeforeClass
	@Parameters({ "platform", "browser", "javaVersion", "user" })
	public void startReportAndSetProperty(String platform, String browser, String javaVersion, String user) throws InterruptedException, MalformedURLException {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
//		depends on REQUIREMENT --->
//		bDriver.setFirefoxDriverProperty(firefoxDriverType(), firefoxDriverPath());
//		bDriver.startFirefoxDriver();
		cReport.generateReport(reportName, platform, browser, javaVersion, user);
	}

	@Test(priority = 0)
	@Parameters({ "url" })
	public void toSite(String url) throws InterruptedException, FailedLoginException {
		cReport.createTest("toSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = { "announcement" })
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		cReport.createTest("closeAnnouncement");
		announce.closeAnnouncement();
		announce.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("login");
		log.loginOptionButton();
		log.setUserID(userIDFE);
		log.setPassword(passwordFE);
		log.setCaptcha(captchaFE);
		log.setSliderCaptcha();
		log.selectLoginButton();
		log.verifyLogin(userIDFE);
	}

	@Test(priority = 3, groups = { "logout" })
	@Parameters({ "userIDFE" })
	public void logout(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("logout");
		logOut.selectLogoutButton(userIDFE);
		logOut.verifyLogout();
	}

	@Test(priority = 4, groups = { "register" })
	public void register() throws InterruptedException, FailedLoginException {
		cReport.createTest("register");
		register.registerOptionButton();
		register.setNewUserID();
		register.setNewPassword(passwordFE);
		register.setDOB(year, day);
		register.setPhoneNumber();
		register.setCaptcha(captchaFE);
		register.selectRegisterButton();
		register.verifyRegister();
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) throws Exception {
		rListener.logCaseStatus(result);
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		Thread.sleep(1000);
		cReport.flushTest();
		bDriver.stopDriver();
	}
}