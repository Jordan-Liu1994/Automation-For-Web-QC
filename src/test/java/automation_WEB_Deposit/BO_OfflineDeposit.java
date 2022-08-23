package automation_WEB_Deposit;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import functions_WEB_BO.Login_BO_Function;
import functions_WEB_BO.Offline_Deposit_Verify_BO_Function;
import functions_WEB_FE.Offline_Deposit_Function;
import functions_WEB_FE.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class BO_OfflineDeposit extends VariablesStorage {

	static String nameOfReport = "BO_OfflineDeposit";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener result_Listener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();

	Login_BO_Function function = Login_BO_Function.getInstance();
	Offline_Deposit_Function function2 = Offline_Deposit_Function.getInstance();
	Offline_Deposit_Verify_BO_Function function3 = Offline_Deposit_Verify_BO_Function.getInstance();

//	@BeforeSuite
//	public void startUp() throws InterruptedException, MalformedURLException {
//		baseDriver.setDriverProperty(driverType(), driverPath());
//		baseDriver.startDriver(siteUrlBO());
//	}

	@Test(priority = 10, groups = { "BO_Offline_Deposit" })
	public void loginBOPage() throws InterruptedException, FailedLoginException {
		createReport.generateReport(nameOfReport);
		createReport.createTest("toBOPage");
		baseDriver.getDriver().navigate().to(siteUrlBO());
		verifySite.verifySite(siteUrlBO());
		Thread.sleep(500);
		function.setUserID(userIDBO());
		function.setPassword(passwordBO());
		function.setCaptcha(otpBO());
		function.selectLoginButton();
		function.verifyLogIn(userIDBO());
		takeScreenShot.getTakeScreenShotPass("loginBOPage");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "loginBOPage" + "-passed.png", "loginBOPage");
	}

	@Test(priority = 11, groups = { "BO_Offline_Deposit" })
	public void offlineDepositVerification() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositVerification");
		function3.selectOfflineDepositVerification();
		Thread.sleep(500);
		function3.offlineDepositVerificationSubModule();
		Thread.sleep(1000);
		function3.filterUserAccount(userID());
		Thread.sleep(500);
		function3.verifyDepositID();
		Thread.sleep(500);
//		function2.setDepositoryName(depositoryName());
//		function2.checkRememberDepositoryName();
//		function2.checkActualReceivedAmount(depositAmount());
//		function2.submitOfflineDepositRequest();
		takeScreenShot.getTakeScreenShotPass("offlineDepositVerification");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositVerification" + "-passed.png", "offlineDepositVerification");
	}

//	@Test(priority = 12, groups = { "Offline_Deposit" })
//	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
//		createReport.createTest("confirmOfflineDepositPaid");
//		function2.confirmOfflineDepositPaid();
//		Thread.sleep(1000);
//		takeScreenShot.getTakeScreenShotPass("confirmOfflineDepositPaid");
//
//		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "confirmOfflineDepositPaid" + "-passed.png", "confirmOfflineDepositPaid");
//	}
//	
//	@Test(priority = 13, groups = { "Offline_Deposit" })
//	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
//		createReport.createTest("cancelOfflineDepositRequest");
//		function2.cancelOfflineDepositRequest();
//		Thread.sleep(1000);
//		takeScreenShot.getTakeScreenShotPass("cancelOfflineDepositRequest");
//
//		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "cancelOfflineDepositRequest" + "-passed.png", "cancelOfflineDepositRequest");
//	}

	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		result_Listener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(2000);
		baseDriver.stopDriver();
	}
}