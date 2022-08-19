package automations_WEB;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB.Verify_Site;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;
import utilities.Variables_Storage;

public class FE_Open_Site extends Variables_Storage {

	private static String nameOfReport = "FE_Open_Site";
	
	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();
	Result_Listener resultListener = Result_Listener.getInstance();
	Verify_Site verifySite = Verify_Site.getInstance();

	@BeforeClass
	public void startUp() {
		createReport.generateReport(nameOfReport);
		baseDriver.setDriverProperty(driverType(), driverPath());
	}

	@Test(priority = 0)
	public void openBrowserToSite() throws InterruptedException, FailedLoginException {
		createReport.createTest("openBrowserToSite");
		baseDriver.startDriver(siteUrlFE());
		verifySite.VerifySite(siteUrlFE());
	}

	@AfterMethod
	public void logCaseStatus(ITestResult result) {
		resultListener.caseLogging(result);
	}

	@AfterClass
	public void shutDown() throws InterruptedException {
		baseDriver.stopDriver();
		createReport.flushTest();
	}
}