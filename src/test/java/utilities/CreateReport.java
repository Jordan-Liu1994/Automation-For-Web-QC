package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CreateReport extends VariablesStorage {

	private static CreateReport cR = new CreateReport();
	private static ExtentSparkReporter sR;
	private static ExtentReports eR;
	private static ExtentTest eT;

	private String userDir = System.getProperty("user.dir");
	private String pathOfReport = userDir + ".\\src\\test\\resources\\reports\\";

	public static CreateReport getInstance() {
		return cR;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void generateReport(String nameOfReport) {
		eR = new ExtentReports();
		sR = new ExtentSparkReporter(pathOfReport + nameOfReport + "-report.html");
		eR.attachReporter(sR);
		eR.setSystemInfo("Platform", platformName());
		eR.setSystemInfo("Browser", browserName());
		eR.setSystemInfo("Java version", javaVersion());
		eR.setSystemInfo("User", automationAuthor());
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void createTest(String testName) {
		eT = eR.createTest(testName);
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void flushTest() {
		eR.flush();
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public ExtentReports getExtentReport() {
		return eR;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public ExtentTest getExtentTest() {
		return eT;
	}
}