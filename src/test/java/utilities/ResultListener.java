package utilities;

import org.testng.ITestResult;

public class ResultListener {

	TakeScreenShot takeScreenShot = new TakeScreenShot();
	CreateReport createReport = new CreateReport();
	
	public void logStatus(ITestResult result) throws Exception {
		String logStatus = result.getName();
		if (result.getStatus() == ITestResult.SUCCESS) {
			createReport.setExtentTestPass(logStatus + " - Passed");
		}
		if (result.getStatus() == ITestResult.SKIP) {
			createReport.setExtentTestSkip(logStatus + " - Skipped");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			createReport.setExtentTestFail(logStatus + " - Failed");
			try {
				String screenShotOfFailedResult = takeScreenShot.takeSnapShot(logStatus + ".png");
				createReport.getExtentTest().addScreenCaptureFromPath(takeScreenShot.screenShotPathExtent() + screenShotOfFailedResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
