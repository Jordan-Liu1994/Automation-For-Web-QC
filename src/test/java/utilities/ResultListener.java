package utilities;

import org.testng.ITestResult;

public class ResultListener {

	private static ResultListener resultListener = new ResultListener();

	public static ResultListener getInstance() {
		return resultListener;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private String resultOfCaseStatus;

	CreateReport createReport = CreateReport.getInstance();
	TakeScreenShot takeScreenShot = TakeScreenShot.getInstance();

	public void caseLogging(ITestResult result) {
		resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			createReport.getExtentTest().pass("Step = " + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			createReport.getExtentTest().skip("Step = " + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			takeScreenShot.getTakeScreenShotFail(resultOfCaseStatus);
			createReport.getExtentTest().fail("Step = " + resultOfCaseStatus + " is failed!");
			createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + resultOfCaseStatus + " " + takeScreenShot.timestamp() + "-failed.png", resultOfCaseStatus);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public String resultOfCaseStatusExtent() {
		return resultOfCaseStatus;
	}
}
