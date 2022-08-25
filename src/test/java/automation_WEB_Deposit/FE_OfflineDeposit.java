package automation_WEB_Deposit;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.WebDriver.Window;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import functions_WEB_BO.Login_BO_Function;
import functions_WEB_BO.Offline_Deposit_Verify_BO_Function;
import functions_WEB_FE.Announcement_Function;
import functions_WEB_FE.Deposit_WalletHistory_Function;
import functions_WEB_FE.Login_Function;
import functions_WEB_FE.Logout_Function;
import functions_WEB_FE.Offline_Deposit_Function;
import functions_WEB_FE.Verify_Site;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_OfflineDeposit extends VariablesStorage {

	private static String nameOfReport = "FE_OfflineDeposit";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	Announcement_Function functionAnnouncement = Announcement_Function.getInstance();
	Login_Function functionLoginFE = Login_Function.getInstance();
	Offline_Deposit_Function functionOfflineDeposit = Offline_Deposit_Function.getInstance();
	Login_BO_Function functionBOLogin = Login_BO_Function.getInstance();
	Offline_Deposit_Verify_BO_Function functionOfflineDepositVerify = Offline_Deposit_Verify_BO_Function.getInstance();
	Deposit_WalletHistory_Function functionDepositWalletHistory = Deposit_WalletHistory_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "Offline_Deposit")
	public void setReport() throws InterruptedException, MalformedURLException {
		baseDriver.setDriverProperty(driverType(), driverPath());
		baseDriver.startDriver();
		createReport.generateReport(nameOfReport);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 0)
	public void toSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");

		baseDriver.getURL(siteUrlFE());
		System.out.println(baseDriver.getDriver().getWindowHandle());
		Thread.sleep(3000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", priority = 1)
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		createReport.createTest("closeAnnouncement");

		functionAnnouncement.doNotShowAgainTodayRadioButton();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", priority = 2)
	public void login() throws InterruptedException, FailedLoginException {
		createReport.createTest("login");

		Thread.sleep(500);
		functionLoginFE.selectLoginOption();
		Thread.sleep(1000);
		functionLoginFE.setUserID(userID());
		functionLoginFE.setPassword(password());
		Thread.sleep(500);
		functionLoginFE.setCaptcha(captcha());
		Thread.sleep(500);
		functionLoginFE.selectLoginButton();
		Thread.sleep(1000);
		functionLoginFE.verifyLogIn(userID());
		Thread.sleep(1000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "login", groups = "Offline_Deposit", priority = 3)
	public void depositPage() throws InterruptedException, FailedLoginException {
		createReport.createTest("toDepositPage");

		Thread.sleep(500);
//		function2.hoverUserID(userID());
//		function2.selectDepositOption();
		functionOfflineDeposit.depositOptionFromToolBar();
		Thread.sleep(500);
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
	@Test(dependsOnMethods = "depositPage", groups = "Offline_Deposit", priority = 4)
	public void offlineDepositMethods() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositMethods");

		functionOfflineDeposit.selectOfflineDepositOption(offlineDepositName());
		Thread.sleep(1000);
		functionOfflineDeposit.selectAnyDepositOption(depositOption());
		functionOfflineDeposit.setDepositAmount(depositAmount());
		functionOfflineDeposit.setDepositoryName(depositoryName());
		functionOfflineDeposit.checkRememberDepositoryName();
		Thread.sleep(1000);
		functionOfflineDeposit.joinPromoOrNotRadioButton();
		functionOfflineDeposit.checkActualReceivedAmount(depositAmount());
		functionOfflineDeposit.compareActualReceivedAmount();
		functionOfflineDeposit.submitOfflineDepositRequest();
		functionOfflineDeposit.compareDepositID();
	}

//	= = = = = = = = = = = = = = = = = = = = 
	@Test(dependsOnMethods = "offlineDepositMethods", groups = "Offline_Deposit", priority = 5)
	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		createReport.createTest("confirmOfflineDepositPaid");

		functionOfflineDeposit.confirmOfflineDepositPaid();
		Thread.sleep(1000);

		takeScreenShot.getTakeScreenShotPass("confirmOfflineDepositPaid");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "confirmOfflineDepositPaid" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "confirmOfflineDepositPaid", groups = { "Offline_Deposit_Cancel" }, priority = 6)
	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		createReport.createTest("cancelOfflineDepositRequest");

		functionOfflineDeposit.cancelOfflineDepositRequest();
		Thread.sleep(1000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "confirmOfflineDepositPaid", priority = 7, groups = { "BO_Login" })
	public void loginBOPage() throws InterruptedException, FailedLoginException {
		createReport.createTest("loginBOPage");
		baseDriver.getDriver().get(siteUrlBO());
		verifySite.verifySite(siteUrlBO());
		Thread.sleep(500);
		functionBOLogin.setUserID(userIDBO());
		functionBOLogin.setPassword(passwordBO());
		functionBOLogin.setCaptcha(otpBO());
		functionBOLogin.selectLoginButton();
		functionBOLogin.verifyLogIn(userIDBO());
		Thread.sleep(1000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "loginBOPage", priority = 8, groups = { "BO_Offline_Deposit" })
	public void offlineDepositVerification() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositVerification");
		functionOfflineDepositVerify.selectOfflineDepositVerification();
		Thread.sleep(1000);
		functionOfflineDepositVerify.offlineDepositVerificationSubModule();
		Thread.sleep(1000);
		functionOfflineDepositVerify.filterUserAccount(userID());
		Thread.sleep(500);
		functionOfflineDepositVerify.verifyDepositID();
		Thread.sleep(1000);
		
		takeScreenShot.getTakeScreenShotPass("offlineDepositVerification");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositVerification" + "-passed.png");
		
		functionOfflineDepositVerify.rejectOfflineDepositAfterVerified();
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
	@Test(dependsOnMethods = "offlineDepositVerification", priority = 9, groups = { "BO_Offline_Deposit" })
	public void offlineDepositVerificationFEWalletHistory() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositVerificationFEWalletHistory");
		baseDriver.getURL(siteUrlFE());
		functionDepositWalletHistory.selectWalletHistoryButton(userID());
		Thread.sleep(1000);
		functionDepositWalletHistory.selectFundsDetails();
		Thread.sleep(2000);
		
		takeScreenShot.getTakeScreenShotPass("offlineDepositVerificationFEWalletHistory");
		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositVerificationFEWalletHistory" + "-passed.png");
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
		baseDriver.stopDriver();
	}
}