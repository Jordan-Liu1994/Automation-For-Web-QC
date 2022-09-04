package webProfile;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement;
import functions_WEB_FE.LanguageSelectionFE;
import functions_WEB_FE.LoginFE;
import functions_WEB_FE.LogoutFE;
import functions_WEB_FE.OfflineDepositFE;
import functions_WEB_FE.ProfileFE;
import functions_WEB_FE.RegisterFE;
import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.ResultListener;
import utilities.TakeScreenShot;
import utilities.VariablesStorage;

public class FrontEndAddBankCard extends VariablesStorage {

	private static String nameOfReport = "FrontEndAddBankCard";

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	ResultListener rListener = ResultListener.getInstance();
	TakeScreenShot takeSS = TakeScreenShot.getInstance();

	Announcement aF = Announcement.getInstance();
	LanguageSelectionFE languageSF = LanguageSelectionFE.getInstance();
	LoginFE loginF = LoginFE.getInstance();
	ProfileFE profileF = ProfileFE.getInstance();
	
//	= = = = = = = = = = = = = = = = = = = = 

	@BeforeClass(groups = "Authentication")
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
//		depends on REQUIREMENT --->
//		languageSF.languageSelectionButton();
//		languageSF.selectSpecificLanguage(languageSelection());
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
		loginF.verifyLogIn(userIDFE());
		takeSS.getPassScreenShot("login");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "login" + "-passed.png");
	}

	@Test(groups = { "profilePage", "profilePage" }, priority = 3)
	public void profilePage() throws InterruptedException, FailedLoginException {
		cR.createTest("profilePage");
		profileF.hoverUserID(userIDFE());
		profileF.selectProfile();
		profileF.selectAddBankCard();
		profileF.bankCardOption();
		profileF.bankOption(depositoryName());
		takeSS.getPassScreenShot("profilePage");
		cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + "profilePage" + "-passed.png");
	}

//	= = = = = = = = = = = = = = = = = = = = 

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		rListener.caseLogging(result);
	}

	@AfterClass
	public void endReport() throws InterruptedException {
		cR.flushTest();
		Thread.sleep(1000);
		bDriver.stopDriver();
		Thread.sleep(1000);
	}
}