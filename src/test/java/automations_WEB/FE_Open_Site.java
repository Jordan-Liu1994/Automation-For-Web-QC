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

public class FE_Open_Site extends VariablesStorage {

	private static String nameOfReport = "FE_Open_Site";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	
	Login_Function function = Login_Function.getInstance();
	Announcement_Function function2 = Announcement_Function.getInstance();
	
	@BeforeClass
	public void startUp() {
		createReport.generateReport(nameOfReport);
		baseDriver.setDriverProperty(driverType(), driverPath());
	}

	
	@Test(priority = 0)
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");
		baseDriver.startDriver(siteUrlFE());
		verifySite.VerifySite(siteUrlFE());
		Thread.sleep(500);
	}
	
	@Test(priority = 1)
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");
		function2.selectDoNotShowAgainTodayCheckBox();
	}

	@Test(priority = 1, dependsOnMethods = "openBrowserToSite")
	public void selectLoginOption() throws InterruptedException, FailedLoginException {
		createReport.createTest("selectLoginOption");
		function.selectLoginOption();
	}
	
	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		Thread.sleep(3000);
		baseDriver.stopDriver();
		createReport.flushTest();
	}
}