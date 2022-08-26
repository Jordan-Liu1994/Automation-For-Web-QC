package automation_WEB_Withdraw;

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
import functions_WEB_FE.Announcement;
import functions_WEB_FE.Deposit_WalletHistory_Function;
import functions_WEB_FE.LoginFE;
import functions_WEB_FE.LogoutFE;
import functions_WEB_FE.Offline_Deposit_Function;
import functions_WEB_FE.Verify_Site;
import functions_WEB_FE.Withdraw_AddBankCard_Function;
import functions_WEB_FE.Withdraw_AddPin_Function;
import functions_WEB_FE.Withdraw_Function;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FE_Withdraw extends VariablesStorage {

	private static String nameOfReport = "FE_Withdraw";

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	ResultListener resultListener = ResultListener.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();
	
	Announcement functionAnnouncement = Announcement.getInstance();
	LoginFE functionLoginFE = LoginFE.getInstance();
	Withdraw_Function functionWithdraw = Withdraw_Function.getInstance();
	Withdraw_AddPin_Function functionWithdrawAddPin = Withdraw_AddPin_Function.getInstance();
	Withdraw_AddBankCard_Function functionWithdrawAddBankCard = Withdraw_AddBankCard_Function.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "Withdraw")
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

		functionAnnouncement.closeAnnouncement();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "toSite", priority = 2)
	public void login() throws InterruptedException, FailedLoginException {
		createReport.createTest("login");

		Thread.sleep(500);
		functionLoginFE.loginOptionButton();
		Thread.sleep(1000);
		functionLoginFE.setUserID(userIDFE());
		functionLoginFE.setPassword(passwordAll());
		Thread.sleep(500);
		functionLoginFE.setCaptcha(captchaOtpAll());
		Thread.sleep(500);
		functionLoginFE.selectLoginButton();
		Thread.sleep(1000);
		functionLoginFE.verifyLogIn(userIDFE());
		Thread.sleep(1000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "login", groups = "Withdraw_page", priority = 3)
	public void withdrawPage() throws InterruptedException, FailedLoginException {
		createReport.createTest("toWithdrawPage");

		Thread.sleep(500);
		functionWithdraw.withdrawOptionFromToolBar();
		Thread.sleep(500);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(dependsOnMethods = "withdrawPage", groups = "Withdraw_page", priority = 4)
	public void withdrawMethods() throws InterruptedException, FailedLoginException {
		createReport.createTest("offlineDepositMethods");

		functionWithdraw.selectWithdrawOption();
		Thread.sleep(1000);
		functionWithdraw.fillInWithdrawAmount(Integer.toString(depositAmount()));
		
//		ONLY ONCE ADD BANK CARD
		functionWithdrawAddBankCard.addBankCardFromWithdrawPage();
		functionWithdrawAddBankCard.selectAddBankCardOption();
		Thread.sleep(500);
		functionWithdrawAddBankCard.fillInBankCardDetails(depositoryName(), phoneNumber());
		Thread.sleep(1000);
		functionWithdrawAddBankCard.generateOTPButton(captchaOtpAll());
		Thread.sleep(1000);
		functionWithdrawAddBankCard.withdrawBankVendorOption();
		Thread.sleep(1000);
		functionWithdrawAddBankCard.filterBankVendorOption("X");
		Thread.sleep(1000);
		functionWithdrawAddBankCard.inputBankAccountNumber(bankAccountNumber());
		Thread.sleep(1000);
		functionWithdrawAddBankCard.confirmAddBankCardButton();
//		ONLY ONCE ADD BANK CARD
		
//		ONLY ONCE ADD PIN
//		  functionWithdrawAddPin.addPinFromWithdrawPage();
//		  functionWithdrawAddPin.addPin(pinNumber());
//		ONLY ONCE ADD PIN
		
		Thread.sleep(1000);
		functionWithdraw.fillInPinNumber(withdrawPinNum());
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
//	@Test(dependsOnMethods = "withdrawMethods", groups = "Withdraw", priority = 5)
//	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
//		createReport.createTest("confirmOfflineDepositPaid");
//
////		functionWithdraw.confirmOfflineDepositPaid();
//		Thread.sleep(1000);
//
//		takeScreenShot.getTakeScreenShotPass("confirmOfflineDepositPaid");
//		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "confirmOfflineDepositPaid" + "-passed.png");
//	}
//
////	= = = = = = = = = = = = = = = = = = = = 
//
//	@Test(dependsOnMethods = "confirmOfflineDepositPaid", groups = { "Offline_Deposit_Cancel" }, priority = 6)
//	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
//		createReport.createTest("cancelOfflineDepositRequest");
//
////		functionWithdraw.cancelOfflineDepositRequest();
//		Thread.sleep(1000);
//	}
//
////	= = = = = = = = = = = = = = = = = = = = 
//
//	@Test(dependsOnMethods = "confirmOfflineDepositPaid", priority = 7, groups = { "BO_Login" })
//	public void loginBOPage() throws InterruptedException, FailedLoginException {
//		createReport.createTest("loginBOPage");
//		baseDriver.getDriver().get(siteUrlBO());
//		verifySite.verifySite(siteUrlBO());
//		Thread.sleep(500);
//		functionBOLogin.setUserID(userIDBO());
//		functionBOLogin.setPassword(passwordBO());
//		functionBOLogin.setCaptcha(otpBO());
//		functionBOLogin.selectLoginButton();
//		functionBOLogin.verifyLogIn(userIDBO());
//		Thread.sleep(1000);
//	}
//
////	= = = = = = = = = = = = = = = = = = = = 
//
//	@Test(dependsOnMethods = "loginBOPage", priority = 8, groups = { "BO_Offline_Deposit" })
//	public void offlineDepositVerification() throws InterruptedException, FailedLoginException {
//		createReport.createTest("offlineDepositVerification");
//		functionOfflineDepositVerify.selectOfflineDepositVerification();
//		Thread.sleep(1000);
//		functionOfflineDepositVerify.offlineDepositVerificationSubModule();
//		Thread.sleep(1000);
//		functionOfflineDepositVerify.filterUserAccount(userID());
//		Thread.sleep(500);
//		functionOfflineDepositVerify.verifyDepositID();
//		Thread.sleep(1000);
//
//		takeScreenShot.getTakeScreenShotPass("offlineDepositVerification");
//		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositVerification" + "-passed.png");
//
//		functionOfflineDepositVerify.rejectOfflineDepositAfterVerified();
//	}
//
////	= = = = = = = = = = = = = = = = = = = = 
//
//	@Test(dependsOnMethods = "offlineDepositVerification", priority = 9, groups = { "BO_Offline_Deposit" })
//	public void offlineDepositVerificationFEWalletHistory() throws InterruptedException, FailedLoginException {
//		createReport.createTest("offlineDepositVerificationFEWalletHistory");
//		baseDriver.getURL(siteUrlFE());
//		functionDepositWalletHistory.selectWalletHistoryButton(userID());
//		Thread.sleep(1000);
//		functionDepositWalletHistory.selectFundsDetails();
//		Thread.sleep(2000);
//
//		takeScreenShot.getTakeScreenShotPass("offlineDepositVerificationFEWalletHistory");
//		createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + "offlineDepositVerificationFEWalletHistory" + "-passed.png");
//	}

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