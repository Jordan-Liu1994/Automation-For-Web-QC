package webFEFunctions;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.CreateReport;
import utilities.Driver;

public class LoginFrontEnd {

	Driver driver = new Driver();
	CreateReport createReport = new CreateReport();

	static String attribute = "placeholder";
	static String space = " - ";
	
	public void selectloginOption() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//div[@id='header_login']"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			element.click();
			createReport.setExtentTestInfo(elementText);
		} else {
			createReport.setExtentTestFail(elementText);
			throw new FailedLoginException();
		}
	}

	public void setUsername(String username) {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("username"));
		driver.setWait(element);
		String elementText = element.getAttribute(attribute);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(username);
			createReport.setExtentTestInfo(elementText + space + username);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void setPassword(String password) {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("password"));
		String elementText = element.getAttribute(attribute);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(password);
			createReport.setExtentTestInfo(elementText + space + password);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void selectLogin() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("login_popup_btn"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			element.click();
			createReport.setExtentTestInfo(elementText);
		} else {
			createReport.setExtentTestFail(elementText);
			throw new FailedLoginException();
		}
	}

	public void getVerifyLogin(String username) throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//div[@class='header_after_login_id']//a[contains(text(),'" + username + "')]"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			createReport.setExtentTestInfo(elementText + space + "login verified");
		} else {
			createReport.setExtentTestFail("login failed");
			throw new FailedLoginException();
		}
	}
}