package automation_WEB_Deposit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Offline_Deposit_Function;
import functions_WEB.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_OfflineDeposit extends VariablesStorage {

	static String nameOfReport = "FE_Offline_Deposit_Site";
	
	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener result_Listener = ResultListener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	
	Login_Function function = Login_Function.getInstance();
	Offline_Deposit_Function function2 = Offline_Deposit_Function.getInstance();
	
	@Test(priority = 6, groups = { "Offline_Deposit" })
	public void depositPage() throws InterruptedException, FailedLoginException {
		createReport.generateReport(nameOfReport);
		createReport.createTest("toDepositPage");
		Thread.sleep(500);
		function2.hoverUserID(userID());
		function2.selectDepositOption();
		function2.depositOptionFromToolBar();
		Thread.sleep(500);
		takeScreenShot.getTakeScreenShotPass("toDepositPage");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "toDepositPage" + "-passed.png", "toDepositPage");
	}
	
	@Test(priority = 7, groups = { "Offline_Deposit" })
	public void offlineDepositMethods() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositMethods");
		function2.selectOfflineDepositOption(offlineDepositName());
		Thread.sleep(1000);
		function2.selectAnyDepositOption(depositOption());
		function2.setDepositAmount(depositAmount());
		function2.setDepositoryName(depositoryName());
		function2.checkRememberDepositoryName();
		function2.checkActualReceivedAmount(depositAmount());
		function2.submitOfflineDepositRequest();
		function2.dataToCompare();
		takeScreenShot.getTakeScreenShotPass("offlineDepositMethods");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositMethods" + "-passed.png", "offlineDepositMethods");
	}
	
	@Test(priority = 8, groups = { "Offline_Deposit" })
	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		createReport.createTest("confirmOfflineDepositPaid");
		function2.confirmOfflineDepositPaid();
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("confirmOfflineDepositPaid");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "confirmOfflineDepositPaid" + "-passed.png", "confirmOfflineDepositPaid");
	}

	@Ignore
	@Test(priority = 9, groups = { "Offline_Deposit_Cancel" })
	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		createReport.createTest("cancelOfflineDepositRequest");
		function2.cancelOfflineDepositRequest();
		Thread.sleep(1000);
		takeScreenShot.getTakeScreenShotPass("cancelOfflineDepositRequest");

		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "cancelOfflineDepositRequest" + "-passed.png", "cancelOfflineDepositRequest");
	}
	
	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		result_Listener.caseLogging(result);
	}

	@AfterClass
	public void ShutDown() throws InterruptedException {
		createReport.flushTest();
		Thread.sleep(2000);
	}
}