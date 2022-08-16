package utilities;

import javax.security.auth.login.FailedLoginException;

import org.testng.ITestResult;
import org.testng.SkipException;

public class Result_Listener {

	private static Result_Listener result_Listener = new Result_Listener();

	public static Result_Listener getInstance() {
		return result_Listener;
	}

	private String resultOfCaseStatus;

	Create_Report create_Report = Create_Report.getInstance();
	Take_Screen_Shot take_Screen_Shot = Take_Screen_Shot.getInstance();

	public void caseLogging(ITestResult result) {
		resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			create_Report.getExtentTest().pass("Step = " + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			create_Report.getExtentTest().skip("Step = " + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			take_Screen_Shot.getTakeScreenShot(resultOfCaseStatus);
			create_Report.getExtentTest().fail("Step = " + resultOfCaseStatus + " is failed!");
			create_Report.getExtentTest().info(resultOfCaseStatus + " " + take_Screen_Shot.timestamp() + ".png");
			create_Report.getExtentTest().addScreenCaptureFromPath(take_Screen_Shot.screenShotPathExtent() + resultOfCaseStatus + " " + take_Screen_Shot.timestamp() + ".png", resultOfCaseStatus);
		}
	}

	public String resultOfCaseStatusExtent() {
		return resultOfCaseStatus;
	}
}
