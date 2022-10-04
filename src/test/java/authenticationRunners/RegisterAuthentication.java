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

public class RegisterAuthentication extends VariablesStorage {

	static String reportName = "RegisterAuthentication";

	@BeforeClass(groups = { "register" })
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
	public void register() throws InterruptedException, FailedLoginException {
		createReport.createTest("register");
		registerFrontEnd.selectRegisterOption();
		registerFrontEnd.setUsername();
		registerFrontEnd.setPassword();
		registerFrontEnd.setCaptcha();
		registerFrontEnd.selectRegister();
		registerFrontEnd.getVerifyRegister();
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) throws Exception {
		resultListener.logStatus(result);
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		Thread.sleep(1000);
		createReport.flushReport();
		driver.stopDriver();
	}
}