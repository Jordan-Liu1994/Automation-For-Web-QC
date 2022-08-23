package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreateReport extends VariablesStorage{

	private static CreateReport createReport = new CreateReport();
	private static ExtentSparkReporter sparkReport;
	private static ExtentReports extentReport;
	private static ExtentTest extentTest;

	private String userDir = System.getProperty("user.dir");
	private String pathOfReport = userDir + ".\\src\\test\\resources\\reports\\";

	public static CreateReport getInstance() {
		return createReport;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void generateReport(String nameOfReport) {
		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(pathOfReport + nameOfReport + "-report.html");
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Platform", platformName());
		extentReport.setSystemInfo("Browser", browserName());
		extentReport.setSystemInfo("Java version", javaVersion());
		extentReport.setSystemInfo("User", userName());
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void createTest(String testName) {
		extentTest = extentReport.createTest(testName);
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void flushTest() {
		extentReport.flush();
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public ExtentReports getExtentReport() {
		return extentReport;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public ExtentTest getExtentTest() {
		return extentTest;
	}
}