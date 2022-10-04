package webFEFunctions;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.CreateReport;
import utilities.Driver;

public class LogoutFrontEnd {

	Driver driver = new Driver();
	CreateReport createReport = new CreateReport();

	static String space = " - ";

	public void setHoverUsername(String username) {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//div[@class='header_after_login_id']//a[contains(text(),'" + username + "')]"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			driver.setActionMoveTo(element);
			createReport.setExtentTestInfo("Hover" + space + elementText);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void selectLogout() {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//button[@class='btn btn_nav_logout']"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			element.click();
			createReport.setExtentTestInfo(elementText);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void getVerifyLogout() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//div[@id='header_login']"));
		if (element.isDisplayed()) {
			createReport.setExtentTestInfo("logout verified");
		} else {
			createReport.setExtentTestFail("logout failed");
			throw new FailedLoginException();
		}
	}
}