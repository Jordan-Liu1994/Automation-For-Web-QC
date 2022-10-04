package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreateReport {

	static ExtentSparkReporter sparkReport;
	static ExtentReports extentReport;
	static ExtentTest extentTest;

	static String userDirectory = System.getProperty("user.dir");
	static String reportFolderPath = userDirectory + ".\\src\\test\\resources\\reports\\";

	public void generateReport(String reportName, String platform, String browser, String javaVersion, String user) {
		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(reportFolderPath + reportName + ".html");
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Platform", platform);
		extentReport.setSystemInfo("Browser", browser);
		extentReport.setSystemInfo("Java version", javaVersion);
		extentReport.setSystemInfo("User", user);
	}

	public void createTest(String testName) {
		extentTest = extentReport.createTest(testName);
	}
	
	public ExtentTest getExtentTest() {
		return extentTest;
	}
	
	public void setExtentTestInfo(String infoText) {
		extentTest.info(infoText);
	}
	
	public void setExtentTestPass(String passText) {
		extentTest.pass(passText);
	}
	
	public void setExtentTestFail(String failText) {
		extentTest.fail(failText);
	}
	
	public void setExtentTestSkip(String skipText) {
		extentTest.skip(skipText);
	}
	
	public void flushReport() {
		extentReport.flush();
	}
}