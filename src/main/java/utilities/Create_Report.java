package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Create_Report extends Variables_Storage{

	private static Create_Report create_Report = new Create_Report();
	private static ExtentSparkReporter sparkReport;
	private static ExtentReports extentReport;
	private static ExtentTest extentTest;

	private String user_Dir = System.getProperty("user.dir");
	private String path_Of_Report = user_Dir + ".\\src\\main\\resources\\reports\\";

	public static Create_Report getInstance() {
		return create_Report;
	}

	public void generateReport(String name_Of_Report) {
		extentReport = new ExtentReports();
		sparkReport = new ExtentSparkReporter(path_Of_Report + name_Of_Report + "_Report.html");
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Platform", platformName());
		extentReport.setSystemInfo("Browser", browserName());
		extentReport.setSystemInfo("Java version", javaVersion());
		extentReport.setSystemInfo("User", userName());
	}
	
	public void createTest(String testName) {
		extentTest = extentReport.createTest(testName);
	}

	public void flushTest() {
		extentReport.flush();
	}

	public ExtentReports getExtentReport() {
		return extentReport;
	}

	public ExtentTest getExtentTest() {
		return extentTest;
	}
}