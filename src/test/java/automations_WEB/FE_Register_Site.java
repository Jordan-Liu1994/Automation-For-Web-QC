package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class FE_Register_Site extends VariablesStorage {

	static String name_Of_Report = "FE_Register_Site";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	
	Register_Function function = Register_Function.getInstance();

	@BeforeClass
	public void startUp() {
		createReport.generateReport(name_Of_Report);
		baseDriver.setDriverProperty(driverType(), driverPath());
	}

	@Test(priority = 0)
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");
		baseDriver.startDriver(siteUrlFE());
	}

	@Test(priority = 1, dependsOnMethods = "openBrowserToSite")
	public void selectRegisterOptionButton() throws InterruptedException, FailedLoginException {
		createReport.createTest("selectRegisterOptionButton");
		function.selectRegisterOption();
	}

	@Test(priority = 2, dependsOnMethods = "selectRegisterOptionButton")
	public void fillInRegisterData() throws InterruptedException, FailedLoginException {
		createReport.createTest("fillInRegisterData");
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
		takeScreenShot.getTakeScreenShotPass("fillInRegisterData");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "fillInRegisterData" + " " + takeScreenShot.timestamp() + "-passed.png", "fillInRegisterData");
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