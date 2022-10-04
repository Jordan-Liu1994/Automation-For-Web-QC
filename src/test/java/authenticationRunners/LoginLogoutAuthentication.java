package authenticationRunners;

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
import webFEFunctions.LoginFrontEnd;
import webFEFunctions.LogoutFrontEnd;

public class LoginLogoutAuthentication extends VariablesStorage {

	private static String reportName = "LoginLogoutAuthentication";

	@BeforeClass(groups = { "login" })
	@Parameters({ "platform", "browser", "javaVersion", "user" })
	public void startReportAndSetProperty(String platform, String browser, String javaVersion, String user) throws InterruptedException, MalformedURLException {
		createReport.generateReport(reportName, platform, browser, javaVersion, user);
		driver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		driver.startChromeDriver();
	}

	@Test(priority = 0)
	@Parameters({ "url" })
	public void toSite(String url) {
		createReport.createTest("toSite");
		driver.getURL(url);
	}

	@Test(priority = 1)
	public void closeAnnouncement() throws InterruptedException {
		createReport.createTest("closeAnnouncement");
		announcement.closeAnnouncement();
		announcement.closeAnnouncementOverview();
	}

	@Test(priority = 2)
	@Parameters({ "username", "password" })
	public void login(String username, String password) throws FailedLoginException {
		createReport.createTest("login");
		loginFrontEnd.selectloginOption();
		loginFrontEnd.setUsername(username);
		loginFrontEnd.setPassword(password);
		loginFrontEnd.selectLogin();
		loginFrontEnd.getVerifyLogin(username);
	}

	@Test(priority = 3, groups = { "logout" })
	@Parameters({ "username" })
	public void logout(String username) throws FailedLoginException {
		createReport.createTest("logout");
		logoutFrontEnd.setHoverUsername(username);
		logoutFrontEnd.selectLogout();
		logoutFrontEnd.getVerifyLogout();
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		Thread.sleep(1000);
		driver.stopDriver();
		createReport.flushReport();
	}
	
	@AfterMethod
	public void getlogs(ITestResult result) throws Exception {
		dateFormatting.dateAndTime();
		resultListener.logStatus(result);
	}
}