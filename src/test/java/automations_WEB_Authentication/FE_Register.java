package automations_WEB_Authentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement_Function;
import functions_WEB_FE.Register_Function;
import functions_WEB_FE.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Register extends VariablesStorage {

	static String name_Of_Report = "FE_Register";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Register_Function function1 = Register_Function.getInstance();
	Announcement_Function function2 = Announcement_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

//	USE BELOW BEFORECLASS IF YOU WANT TO TEST REGISTER ONLY!!

//	@BeforeClass
//	public void startUp() throws MalformedURLException, InterruptedException {
//		baseDriver.setDriverProperty(driverType(), driverPath());
//		baseDriver.startDriver();
//		baseDriver.getDriver().get(siteUrlFE());
//	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 5, groups = { "Register" })
	public void register() throws InterruptedException, FailedLoginException {
		createReport.generateReport(name_Of_Report);
		createReport.createTest("register");

		Thread.sleep(500);
		function1.selectRegisterOption();
		Thread.sleep(500);
		function1.setNewUserID(userIDRegister());
		function1.setNewPassword(password());
		function1.selectPasswordEyeIcon();
		function1.setReferralOptional(referral());
		function1.setCaptcha(captcha());
		Thread.sleep(500);
		function1.setDateOfBirth(year(), day());
		Thread.sleep(500);
		function1.selectRegisterButton();
		Thread.sleep(1000);
		function1.verifyRegisterUserID(userIDRegister());

		takeScreenShot.getTakeScreenShotPass("register");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "register" + "-passed.png", "register");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "register")
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");

		function2.doNotShowAgainTodayRadioButton();
	}

	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(500);
	}
}