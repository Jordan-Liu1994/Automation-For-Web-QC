package utilities;

import org.testng.ITestResult;

public class Result_Listener {

	private static Result_Listener result_Listener = new Result_Listener();

	public static Result_Listener getInstance() {
		return result_Listener;
	}

	private String resultOfCaseStatus;

	Create_Report createReport = Create_Report.getInstance();
	Take_Screen_Shot takeScreenShot = Take_Screen_Shot.getInstance();

	public void caseLogging(ITestResult result) {
		resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			createReport.getExtentTest().pass("Step = " + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			createReport.getExtentTest().skip("Step = " + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenShot.getTakeScreenShot(resultOfCaseStatus);
			createReport.getExtentTest().fail("Step = " + resultOfCaseStatus + " is failed!");
			createReport.getExtentTest().info(resultOfCaseStatus + " " + takeScreenShot.timestamp() + ".png");
			createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + resultOfCaseStatus + " " + takeScreenShot.timestamp() + ".png", resultOfCaseStatus);
		}
	}

	public String resultOfCaseStatusExtent() {
		return resultOfCaseStatus;
	}
}
