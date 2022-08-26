package webAuthentication;

import java.net.MalformedURLException;

import javax.security.auth.login.FailedLoginException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import functions_WEB_FE.Announcement;
import utilities.BaseDriver;
import utilities.VariablesStorage;

public class Test_Purposes extends VariablesStorage {

	BaseDriver bDriver = BaseDriver.getInstance();
	Announcement aF = Announcement.getInstance();

	@BeforeClass
	public void start() throws MalformedURLException, InterruptedException {
		bDriver.setDriverProperty(driverType(), driverPath());
		bDriver.startDriver();
	}
	
	@Test
	public void test001() throws FailedLoginException, InterruptedException {
		bDriver.getDriver().get(siteUrlFE());
		aF.closeAnnouncement();
	}

	@AfterClass
	public void stop() throws InterruptedException {
		Thread.sleep(1500);
		bDriver.stopDriver();
	}
}
