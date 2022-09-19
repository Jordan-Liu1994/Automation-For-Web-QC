package utilities;

import org.testng.ITestResult;

public class ResultListener extends VariablesStorage {

	TakeScreenShot takeSS = new TakeScreenShot();
	CreateReport cReport = new CreateReport();
	String res;
	
	public void logCaseStatus(ITestResult result) throws Exception {
		String resultOfCaseStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			cReport.getExtentTest().pass("Step = " + resultOfCaseStatus + " is passed!");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			cReport.getExtentTest().skip("Step = " + resultOfCaseStatus + " is skipped!");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			cReport.getExtentTest().fail("Step = " + resultOfCaseStatus + " is failed!");
			res = resultOfCaseStatus + " fail.png";
			takeSS.takeSnapShot(res);
			cReport.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + res, resultOfCaseStatus);
		}
	}
	
//	private static ResultListener resultListener = new ResultListener();
//
//	public static ResultListener getInstance() {
//		return resultListener;
//	}
//
////	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
//
//	CreateReport cR = CreateReport.getInstance();
//	TakeScreenShot takeSS = TakeScreenShot.getInstance();
//
//	private String resultOfCaseStatus;
//
//	public void caseLogging(ITestResult result) {
//		resultOfCaseStatus = result.getName();
//		if (result.getStatus() == ITestResult.SUCCESS) {
//			cR.getExtentTest().pass(step() + resultOfCaseStatus + " is passed!");
//		}
//		if (result.getStatus() == ITestResult.SKIP) {
//			cR.getExtentTest().skip(step() + resultOfCaseStatus + " is skipped!");
//		} else if (result.getStatus() == ITestResult.FAILURE) {
//			takeSS.getFailScreenShot(resultOfCaseStatus);
//			cR.getExtentTest().fail(step() + resultOfCaseStatus + " is failed!");
//			cR.getExtentTest().addScreenCaptureFromPath(takeSS.screenShotPathExtent() + resultOfCaseStatus + failSS(), resultOfCaseStatus);
//		}
//	}
//
////	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
//
//	public String resultOfCaseStatusExtent() {
//		return resultOfCaseStatus;
//	}
}
