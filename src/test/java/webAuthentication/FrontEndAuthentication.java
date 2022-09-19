package webAuthentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement;
import functions_WEB_FE.LoginFE;
import functions_WEB_FE.LogoutFE;
import functions_WEB_FE.RegisterFE;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FrontEndAuthentication extends VariablesStorage {

	private static String reportName = "FrontEndAuthentication";

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cReport = new CreateReport();
	ResultListener rListener = new ResultListener();

	Announcement announcementF = new Announcement();
	LoginFE loginF = new LoginFE();
	LogoutFE logoutF = new LogoutFE();
	RegisterFE registerF = new RegisterFE();

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

	@Test(priority = 1)
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		cReport.createTest("closeAnnouncement");
		announcementF.closeAnnouncement();
		announcementF.closeAnnouncementOverview();
//		depends on REQUIREMENT --->
//		languageSF.languageSelectionButton();
//		languageSF.selectSpecificLanguage(languageSelection());
	}

	@Test(priority = 2, groups = { "login" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("login");
		loginF.loginOptionButton();
		loginF.setUserID(userIDFE);
		loginF.setPassword(passwordFE);
		loginF.setCaptcha(captchaFE);
		loginF.selectLoginButton();
		loginF.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = { "logout" })
	@Parameters({ "userIDFE" })
	public void logout(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("logout");
		logoutF.selectLogoutButton(userIDFE);
		logoutF.verifyLogout();
	}

	@Test(priority = 4, groups = { "register" })
	public void register() throws InterruptedException, FailedLoginException {
		cReport.createTest("register");
		registerF.registerOptionButton();
		registerF.setNewUserID();
		registerF.setNewPassword(passwordFE);
		registerF.setDOB(year, day);
		registerF.setPhoneNumber();
		registerF.setCaptcha(captchaFE);
		registerF.selectRegisterButton();
		registerF.verifyRegister();
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