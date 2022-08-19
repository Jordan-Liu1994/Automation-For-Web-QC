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
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Generate_Random_Numbers;
import utilities.Result_Listener;
import utilities.Variables_Storage;

public class FE_Register_Site extends Variables_Storage {

	static String name_Of_Report = "FE_Register_Site";

	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();
	Result_Listener resultListener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
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
	}

	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(2000);
		baseDriver.stopDriver();
	}
}