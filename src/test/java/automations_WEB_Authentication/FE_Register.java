package automations_WEB_Authentication;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Register_Function;
import functions_WEB.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.GenerateRandomNumbers;
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

	Register_Function function = Register_Function.getInstance();

	@Test(priority = 4, groups = { "Register_test" })
	public void startNext() {
		createReport.generateReport(name_Of_Report);
	}

	@Test(priority = 5, groups = { "Register_test" })
	public void register() throws InterruptedException, FailedLoginException {
		createReport.createTest("register");
		
		function.selectRegisterOption();
		Thread.sleep(500);
		function.setNewUserID(userIDRegister());
		function.setNewPassword(password());
		function.selectPasswordEyeIcon();
		function.setReferralOptional(referral());
		function.setCaptcha(captcha());
		Thread.sleep(500);
		function.setDateOfBirth(year(), day());
		Thread.sleep(500);
		function.selectRegisterButton();
		function.verifyRegisterUserID(userIDRegister());
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("register");
		
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "register" + "-passed.png", "register");
	}

	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(1000);
		baseDriver.stopDriver();
	}
}