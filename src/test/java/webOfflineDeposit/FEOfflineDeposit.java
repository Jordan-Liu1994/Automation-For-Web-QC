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

	private static String nameOfReport = "FEOfflineDeposit";

	CreateReport cR = new CreateReport();
	ResultListener rL = new ResultListener();
	TakeScreenShot takeSS = new TakeScreenShot();

	Announcement aF = new Announcement();
	LoginFE lFE = new LoginFE();
	OfflineDepositFE oDFE = new OfflineDepositFE();

	LoginBO lBO = new LoginBO();
	OfflineDepositVerifyBO oDVBOF = new OfflineDepositVerifyBO();

	@BeforeClass(groups = "Start")
	@Parameters({ "platformName", "browserName", "javaVersion", "automationAuthor" })
	public void setReport(String platformName, String browserName, String javaVersion, String automationAuthor) throws InterruptedException, MalformedURLException {
		bDriver.setChromeDriverProperty(driverTypeChrome, driverPathChrome);
		bDriver.startChromeDriver();
		cR.generateReport(nameOfReport, platformName, browserName, javaVersion, automationAuthor);
	}

	@Test(priority = 0, groups = "goToSite")
	@Parameters({ "url" })
	public void goToSite(String url) {
		cR.createTest("goToSite");
		bDriver.getURL(url);
	}

	@Test(priority = 1, groups = "announcement")
	public void announcement() throws InterruptedException, FailedLoginException {
		cR.createTest("announcement");
		aF.closeAnnouncement();
		aF.closeAnnouncementOverview();
	}

	@Test(priority = 2, groups = { "login" })
	@Parameters({ "userIDFE" })
	public void login(String userIDFE) throws FailedLoginException, InterruptedException {
		cR.createTest("login");
		lFE.loginOptionButton();
		lFE.setUserID(userIDFE);
		lFE.setPassword(passwordFE);
		lFE.setCaptcha(captchaFE);
		lFE.selectLoginButton();
		lFE.verifyLogIn(userIDFE);
	}

	@Test(priority = 3, groups = "toDepositPage")
	@Parameters({ "userIDFE" })
	public void toDepositPage(String userIDFE) throws InterruptedException, FailedLoginException {
		cR.createTest("toDepositPage");
		oDFE.hoverUserID(userIDFE);
		oDFE.selectDepositOptionFromDropdown();
//		depends on CLIENT
		oDFE.closeBeforeDepositInfoPopUp();
	}

	@Test(priority = 4, groups = "offlineDepositMethods")
	@Parameters({ "depositOptionName", "depositAmount", "depositoryName", "falseJoinPromo" })
	public void offlineDepositMethods(String depositOptionName, int depositAmount, String depositoryName, String falseJoinPromo) throws InterruptedException, FailedLoginException {
		cR.createTest("offlineDepositMethods");
		oDFE.selectOfflineDepositOption();
		oDFE.selectSpecificOfflineDepositMethod(depositOptionName);
		oDFE.setDepositAmount(depositAmount);
		oDFE.setDepositoryName(depositoryName);
		oDFE.joinPromoOrNotRadioButton(falseJoinPromo);
		oDFE.verifyActualReceivedAmountIfNoJoinPromo(depositAmount);
		oDFE.submitOfflineDepositRequest();
	}

	@Test(priority = 5, groups = { "offlineDepositMethods", "confirmOfflineDepositPaid" })
	public void confirmOfflineDepositPaid() throws InterruptedException, FailedLoginException {
		cR.createTest("confirmOfflineDepositPaid");
		oDFE.confirmOfflineDepositPaid();
	}

	@Test(priority = 6, groups = "cancelOfflineDepositRequest")
	public void cancelOfflineDepositRequest() throws InterruptedException, FailedLoginException {
		cR.createTest("cancelOfflineDepositRequest");
		oDFE.cancelOfflineDepositRequest();
	}

	@Test(priority = 7, groups = "verifyOfflineDepositID")
	@Parameters({ "userIDFE" })
	public void verifyOfflineDepositID(String userIDFE) throws InterruptedException, FailedLoginException {
		cR.createTest("verifyOfflineDepositID");
		bDriver.getDriver().navigate().refresh();
		oDFE.hoverUserID(userIDFE);
		oDFE.selectWalletHistoryOptionFromDropdown();
		oDFE.walletHistorySelectDeposit();
		oDFE.verifyOfflineDepositID();
	}

	@Test(priority = 8, groups = { "loginBOPage" })
	@Parameters({ "BOUrl", "BOUserID" })
	public void loginBOPage(String BOUrl, String BOUserID) throws InterruptedException, FailedLoginException {
		cR.createTest("loginBOPage");
		bDriver.getDriver().get(BOUrl);
		lBO.setUserID(BOUserID);
		lBO.setPassword(passwordFE);
		lBO.setCaptcha(captchaFE);
		lBO.selectLoginButton();
		lBO.verifyLogIn(BOUserID);
	}

	@Test(priority = 9, groups = { "offlineDepositVerification" })
	@Parameters({ "userIDFE" })
	public void offlineDepositVerification(String userIDFE) throws InterruptedException, FailedLoginException {
		cR.createTest("offlineDepositVerification");
		oDVBOF.selectOfflineDepositVerification(financeModule);
		oDVBOF.offlineDepositVerificationSubModule(financeSubModule);
		oDVBOF.filterUserAccount(userIDFE);
		oDVBOF.verifyDepositID(oDFE.recordID());
	}
	
	@Test(priority = 10, groups = { "rejectOfflineDepositAfterVerification" })
	public void rejectOfflineDepositAfterVerification(String userIDFE) throws InterruptedException, FailedLoginException {
		cR.createTest("rejectOfflineDepositAfterVerification");
		oDVBOF.rejectOfflineDepositAfterVerified(oDFE.recordID());
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		rL.logCaseStatus(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		Thread.sleep(1000);
		cR.flushTest();
		bDriver.stopDriver();
	}
}