package webOfflineDeposit;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB_BO.LoginBO;
import functions_WEB_BO.OfflineDepositVerifyBO;
import functions_WEB_FE.Announcement;
import functions_WEB_FE.LoginFE;
import functions_WEB_FE.OfflineDepositFE;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FEOfflineDeposit extends VariablesStorage {

	private static String nameOfReport = "FEOfflineDeposit";

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	ResultListener rListener = ResultListener.getInstance();
	TakeScreenShot takeSS = TakeScreenShot.getInstance();

	Announcement aF = Announcement.getInstance();
	LoginFE loginF = LoginFE.getInstance();

	OfflineDepositFE offlineD = OfflineDepositFE.getInstance();
	LoginBO loginBOF = LoginBO.getInstance();
	OfflineDepositVerifyBO offlineDVBOF = OfflineDepositVerifyBO.getInstance();

//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "OfflineDeposit")
	public void setReport() throws InterruptedException, MalformedURLException {
		bDriver.setChromeDriverProperty(driverType(), driverPath());
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 0, groups = "Authentication")
	public void toSite() throws InterruptedException, FailedLoginException {
		cR.createTest("openBrowserToSite");
		bDriver.getURL(siteUrlFEPreLive());
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 1, groups = "Announcement")
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		cR.createTest("closeAnnouncement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "Login", priority = 2)
	public void login() throws InterruptedException, FailedLoginException {
		cR.createTest("login");
		loginF.loginOptionButton();
		loginF.setUserID(userIDFE());
		loginF.setPassword(passwordAll());
		loginF.setCaptcha(captchaOtpAll());
		loginF.selectLoginButton();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "OfflineDepositMethod", priority = 3)
	public void toDepositPage() throws InterruptedException, FailedLoginException {
		cR.createTest("toDepositPage");
		offlineD.hoverUserID(userIDFE());
		offlineD.selectDepositOptionFromDropdown();
//		depends on CLIENT
		offlineD.closeBeforeDepositInfoPopUp();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "OfflineDepositMethod", priority = 4)
	public void offlineDepositMethods() throws InterruptedException, FailedLoginException {
		cR.createTest("offlineDepositMethods");
		offlineD.selectOfflineDepositOption();
		offlineD.selectSpecificOfflineDepositMethod(depositOptionName());
		offlineD.setDepositAmount(depositAmount());
		offlineD.setDepositoryName(depositoryName());
		offlineD.joinPromoOrNotRadioButton();
		offlineD.verifyActualReceivedAmountIfNoJoinPromo(depositAmount());
		offlineD.submitOfflineDepositRequest();
	}
//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "OfflineDepositMethod", priority = 5)
	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		cR.createTest("confirmOfflineDepositPaid");
		offlineD.confirmOfflineDepositPaid();
		takeSS.getPassScreenShot("confirmOfflineDepositPaid");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "confirmOfflineDepositPaid" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "OfflineDepositMethodCancel", priority = 6)
	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		cR.createTest("cancelOfflineDepositRequest");
		offlineD.cancelOfflineDepositRequest();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(groups = "VerifyOfflineDeposit", priority = 7)
	public void verifyOfflineDepositID() throws InterruptedException, FailedLoginException {
		cR.createTest("verifyOfflineDepositID");
		bDriver.getDriver().navigate().refresh();
		offlineD.hoverUserID(userIDFE());
		offlineD.selectWalletHistoryOptionFromDropdown();
		offlineD.walletHistorySelectDeposit();
		offlineD.verifyOfflineDepositID();
		takeSS.getPassScreenShot("verifyOfflineDepositID");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "verifyOfflineDepositID" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
	@Test(priority = 8, groups = { "BOLogin" })
	public void loginBOPage() throws InterruptedException, FailedLoginException {
		cR.createTest("loginBOPage");
		bDriver.getDriver().get(siteUrlBO());
		loginBOF.setUserID(userIDBO());
		loginBOF.setPassword(passwordAll());
		loginBOF.setCaptcha(captchaOtpAll());
		loginBOF.selectLoginButton();
		loginBOF.verifyLogIn(userIDBO());
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@Test(priority = 9, groups = { "BOOfflineDeposit", "BOLogin" })
	public void offlineDepositVerification() throws InterruptedException, FailedLoginException {
		cR.createTest("offlineDepositVerification");
		offlineDVBOF.selectOfflineDepositVerification(financeModule());
		offlineDVBOF.offlineDepositVerificationSubModule(financeSubModule());
		offlineDVBOF.filterUserAccount(userIDFE());
		offlineDVBOF.verifyDepositID();
		offlineDVBOF.rejectOfflineDepositAfterVerified();
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		rListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		cR.flushTest();
		Thread.sleep(1000);
		bDriver.stopDriver();
		Thread.sleep(1000);
	}
}