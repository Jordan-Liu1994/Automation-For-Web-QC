package automations_WEB;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Result_Listener;

public class WEB_Open_Site {

	static String site_Url = "https://wl003.the777888.com/";
	static String driver_Type = "webdriver.chrome.driver";
	static String driver_Path = "chromedriver.exe";
	static String name_Of_Report = "WEB_Open_Site";
	static String browser_Name = "Chrome";

	Base_Driver base_Driver = Base_Driver.get_Instance();
	Create_Report create_Report = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	
	@BeforeClass
	public void StartUp() {
		create_Report.generateReport(name_Of_Report, browser_Name);
		base_Driver.setDriverProperty(driver_Type, driver_Path);
	}
	
	String current_Url;
	String current_WindowHandle;
	
	@Test(priority = 0)
	public void OpenBrowserToSite() throws InterruptedException {
		create_Report.createTest("openBrowserToSite");
		base_Driver.startDriver(site_Url);
	}
	
	@AfterMethod
	public void Log_Case_Status(ITestResult result) {
		result_Listener.caseLogging(result);
	}
	
	@AfterClass
	public void ShutDown() throws InterruptedException {
		base_Driver.stopDriver();
		create_Report.flushTest();
	}
}