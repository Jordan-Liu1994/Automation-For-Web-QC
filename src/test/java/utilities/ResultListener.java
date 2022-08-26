package utilities;

import org.testng.ITestResult;

public class ResultListener extends VariablesStorage {

	private static ResultListener resultListener = new ResultListener();

	public static ResultListener getInstance() {
		return resultListener;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	CreateReport cR = CreateReport.getInstance();
	TakeScreenShot takeSS = TakeScreenShot.getInstance();

	private String resultOfCaseStatus;

	public void caseLogging(ITestResult result) {
		resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			cR.getExtentTest().pass(step() + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			cR.getExtentTest().skip(step() + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			takeSS.getFailScreenShot(resultOfCaseStatus);
			cR.getExtentTest().fail(step() + resultOfCaseStatus + " is failed!");
			cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + resultOfCaseStatus + failSS(), resultOfCaseStatus);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public String resultOfCaseStatusExtent() {
		return resultOfCaseStatus;
	}
}
