package webOfflineDeposit;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
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

	private static String reportName = "FEOfflineDeposit";

	CreateReport cReport = new CreateReport();
	ResultListener rListener = new ResultListener();
	TakeScreenShot takeSS = new TakeScreenShot();

	Announcement announcementF = new Announcement();
	LoginFE loginF = new LoginFE();
	OfflineDepositFE offlineDF = new OfflineDepositFE();
	LoginBO loginB = new LoginBO();
	OfflineDepositVerifyBO oDVBOF = new OfflineDepositVerifyBO();

	@BeforeClass
	@Parameters({ "platform", "browser", "javaVersion", "user" })
	public void startReportAndSetProperty(String platform, String browser, String javaVersion, String user) throws InterruptedException, MalformedURLException {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
//		depends on REQUIREMENT --->
//		bDriver.setFirefoxDriverProperty(firefoxDriverType(), firefoxDriverPath());
//		bDriver.startFirefoxDriver();
		cReport.generateReport(reportName, platform, browser, javaVersion, user);
	}

	@Test(priority = 0)
	@Parameters({ "url" })
	public void toSite(String url) throws InterruptedException, FailedLoginException {
		cReport.createTest("toSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1)
	public void closeAnnouncement() throws InterruptedException, FailedLoginException {
		cReport.createTest("closeAnnouncement");
		announcementF.closeAnnouncement();
		announcementF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("login");
		loginF.loginOptionButton();
		loginF.setUserID(userIDFE);
		loginF.setPassword(passwordFE);
		loginF.setCaptcha(captchaFE);
		loginF.selectLoginButton();
		loginF.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = "deposit")
	@Parameters({ "userIDFE" })
	public void toDepositPage(String userIDFE) throws InterruptedException, FailedLoginException {
		cReport.createTest("toDepositPage");
		offlineDF.hoverUserID(userIDFE);
		offlineDF.selectDepositOptionFromDropdown();
		offlineDF.closeBeforeDepositInfoPopUp();
	}

	@Test(priority = 4, dependsOnMethods = { "toDepositPage" })
	@Parameters({ "offlineDepositMethod", "depositOptionType", "depositAmount", "depositoryName", "falseJoinPromo" })
	public void offlineDepositMethods(String offlineDepositMethod, String depositOptionType, int depositAmount, String depositoryName, String falseJoinPromo) throws InterruptedException, FailedLoginException {
		cReport.createTest("offlineDepositMethods");
		offlineDF.selectOfflineDepositOption(offlineDepositMethod);
		offlineDF.selectSpecificOfflineDepositMethod(depositOptionType);
		offlineDF.setDepositAmount(depositAmount);
		offlineDF.setDepositoryName(depositoryName);
		offlineDF.joinPromoOrNotRadioButton(falseJoinPromo);
		offlineDF.verifyActualReceivedAmountIfNoJoinPromo(depositAmount);
//		oDFE.submitOfflineDepositRequest();
	}

//	@Test(priority = 5, groups = { "offlineDepositMethods", "confirmOfflineDepositPaid" })
//	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
//		cReport.createTest("confirmOfflineDepositPaid");
//		oDFE.confirmOfflineDepositPaid();
//	}
//
//	@Test(priority = 6, groups = "cancelOfflineDepositRequest")
//	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
//		cReport.createTest("cancelOfflineDepositRequest");
//		oDFE.cancelOfflineDepositRequest();
//	}
//
//	@Test(priority = 7, groups = "verifyOfflineDepositID")
//	@Parameters({ "userIDFE" })
//	public void verifyOfflineDepositID(String userIDFE) throws InterruptedException, FailedLoginException {
//		cReport.createTest("verifyOfflineDepositID");
//		bDriver.getDriver().navigate().refresh();
//		oDFE.hoverUserID(userIDFE);
//		oDFE.selectWalletHistoryOptionFromDropdown();
//		oDFE.walletHistorySelectDeposit();
//		oDFE.verifyOfflineDepositID();
//	}
//
//	@Test(priority = 8, groups = { "loginBO" })
//	@Parameters({ "BOUrl", "BOUserID" })
//	public void loginBOPage(String BOUrl, String BOUserID) throws InterruptedException, FailedLoginException {
//		cReport.createTest("loginBOPage");
//		bDriver.getDriver().get(BOUrl);
//		loginB.setUserID(BOUserID);
//		loginB.setPassword(passwordFE);
//		loginB.setCaptcha(captchaFE);
//		loginB.selectLoginButton();
//		loginB.verifyLogIn(BOUserID);
//	}
//
//	@Test(priority = 9, groups = { "offlineDepositVerification" })
//	@Parameters({ "userIDFE" })
//	public void offlineDepositVerification(String userIDFE) throws InterruptedException, FailedLoginException {
//		cReport.createTest("offlineDepositVerification");
//		oDVBOF.selectOfflineDepositVerification(financeModule);
//		oDVBOF.offlineDepositVerificationSubModule(financeSubModule);
//		oDVBOF.filterUserAccount(userIDFE);
//		oDVBOF.verifyDepositID(oDFE.recordID());
//	}
//	
//	@Test(priority = 10, groups = { "rejectOfflineDepositAfterVerification" })
//	public void rejectOfflineDepositAfterVerification(String userIDFE) throws InterruptedException, FailedLoginException {
//		cReport.createTest("rejectOfflineDepositAfterVerification");
//		oDVBOF.rejectOfflineDepositAfterVerified(oDFE.recordID());
//	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) throws Exception {
		rListener.logCaseStatus(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		Thread.sleep(1000);
		cReport.flushTest();
		bDriver.stopDriver();
	}
}