package functions_WEB_FE;

import javax.security.auth.login.FailedLoginException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Verify_Site {

	private static Verify_Site verifySite = new Verify_Site();

	public static Verify_Site getInstance() {
		return verifySite;
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
	BaseDriver base_Driver = BaseDriver.getInstance();
	CreateReport create_Report = CreateReport.getInstance();
	
	private String fail = "Verify site failed";
	
	public void verifySite(String siteUrl) throws FailedLoginException {
		String verifySite = base_Driver.getDriver().getCurrentUrl();
		if (verifySite.contains(siteUrl)) {
			create_Report.getExtentTest().info(" Site = " + verifySite);
			System.out.println(verifySite);
		} else {
			create_Report.getExtentTest().fail(fail);
		}
	}
}