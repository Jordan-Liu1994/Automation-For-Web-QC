package automations_WEB;

import javax.security.auth.login.FailedLoginException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functions_WEB.Login_Function;
import functions_WEB.Offline_Deposit_Function;
import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;
import utilities.Variables_Storage;

public class FE_Offline_Deposit_Site extends Variables_Storage {

	static String name_Of_Report = "FE_Offline_Deposit_Site";
	
	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Login_Function function = Login_Function.getInstance();
	Offline_Deposit_Function function2 = Offline_Deposit_Function.getInstance();
	
	@BeforeClass
	public void StartUp() {
		createReport.generateReport(name_Of_Report);
		baseDriver.setDriverProperty(driverType(), driverPath());
	}

	@Test(priority = 0)
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");
		baseDriver.startDriver(siteUrlFE());
	}

	@Test(priority = 1, dependsOnMethods = "openBrowserToSite")
	public void selectLoginOption() throws InterruptedException, FailedLoginException {
		createReport.createTest("selectLoginOption");
		function.selectLoginOption();
	}
	
	@Test(priority = 2, dependsOnMethods = "selectLoginOption")
	public void fillInLoginDataAndLogin() throws InterruptedException, FailedLoginException {
		createReport.createTest("fillInLoginDataAndLogin");
		Thread.sleep(500);
		function.setUserID(userID());
		function.setPassword(password());
		function.setCaptcha(captcha());
		Thread.sleep(500);
		function.selectLoginButton();
		function.verifyLogIn(userID());
	}
	
	@Test(priority = 3, dependsOnMethods = "fillInLoginDataAndLogin")
	public void toDepositPage() throws InterruptedException, FailedLoginException {
		createReport.createTest("toDepositPage");
		Thread.sleep(500);
		function2.hoverUserID(userID());
		function2.selectDepositOption();
		function2.depositOptionFromToolBar();
		Thread.sleep(500);
	}
	
	@Test(priority = 4, dependsOnMethods = "toDepositPage")
	public void fillInOfflineDepositPageData() throws InterruptedException, FailedLoginException {
		createReport.createTest("fillInOfflineDepositPageData");
		function2.selectOfflineDepositOption(offlineDepositName());
		Thread.sleep(1000);
		function2.selectAnyDepositOption(depositOption());
		function2.setDepositAmount(depositAmount());
		function2.setDepositoryName(depositoryName());
		function2.checkRememberDepositoryName();
		function2.checkActualReceivedAmount(depositAmount());
		function2.submitOfflineDepositRequest();
	}
	
	@Test(priority = 5, dependsOnMethods = "fillInOfflineDepositPageData")
	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		createReport.createTest("confirmOfflineDepositPaid");
		function2.confirmOfflineDepositPaid();
		Thread.sleep(1000);
	}
	
	@Test(priority = 6, dependsOnMethods = "confirmOfflineDepositPaid")
	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		createReport.createTest("cancelOfflineDepositRequest");
		function2.cancelOfflineDepositRequest();
		Thread.sleep(1000);
	}
	
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