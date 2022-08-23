package automations_WEB_Authentication;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
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

public class FE_Logout extends VariablesStorage {

	private static String nameOfReport = "FE_Logout";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Announcement_Function function1 = Announcement_Function.getInstance();
	Login_Function function2 = Login_Function.getInstance();
	Logout_Function function3 = Logout_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 
	
	@Test(priority = 4, groups = { "Logout" })
	public void logout() throws InterruptedException, FailedLoginException {
		createReport.generateReport(nameOfReport);
		createReport.createTest("logout");

		Thread.sleep(500);
		function3.selectLogoutButton(userID());
		Thread.sleep(1000);
		
		takeScreenShot.getTakeScreenShotPass("logout");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "logout" + "-passed.png", "logout");
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
}