package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import utilities.Base_Driver;
import utilities.Create_Report;

public class Verify_Site {

	private static Verify_Site verifySite = new Verify_Site();

	public static Verify_Site getInstance() {
		return verifySite;
	}

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();
	private String verifySiteFail = "Verify site failed";
	public void VerifySite(String siteUrl) throws FailedLoginException {
		String verifySite = base_Driver.getDriver().getCurrentUrl();
		if (verifySite.contains(siteUrl)) {
			create_Report.getExtentTest().info(" Site url = " + verifySite);
			System.out.println(verifySite);
		} else {
			create_Report.getExtentTest().fail(verifySiteFail);
			throw new FailedLoginException(verifySiteFail);
		}
	}
}