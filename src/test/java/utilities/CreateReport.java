package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreateReport {

	private static ExtentSparkReporter spReport;
	private static ExtentReports exReport;
	private static ExtentTest exTest;

	private String userDir = System.getProperty("user.dir");
	private String reportFolderPath = userDir + ".\\src\\test\\resources\\reports\\";

	public void generateReport(String reportName, String platform, String browser, String javaVersion, String user) {
		exReport = new ExtentReports();
		spReport = new ExtentSparkReporter(reportFolderPath + reportName + ".html");
		exReport.attachReporter(spReport);
		exReport.setSystemInfo("Platform", platform);
		exReport.setSystemInfo("Browser", browser);
		exReport.setSystemInfo("Java version", javaVersion);
		exReport.setSystemInfo("User", user);
	}

	public void createTest(String testName) {
		exTest = exReport.createTest(testName);
	}

	public void flushTest() {
		exReport.flush();
	}

	public ExtentReports getExtentReport() {
		return exReport;
	}

	public ExtentTest getExtentTest() {
		return exTest;
	}
}