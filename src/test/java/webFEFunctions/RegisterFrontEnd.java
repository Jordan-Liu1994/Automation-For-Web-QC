package webFEFunctions;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Driver;
import utilities.CreateReport;
import utilities.GenerateRandomNumbers;

public class RegisterFrontEnd {

	Driver driver = new Driver();
	CreateReport createReport = new CreateReport();
	GenerateRandomNumbers generateRandomNumbers = new GenerateRandomNumbers();

	static String attribute = "placeholder";
	static String space = " - ";
	static String password = "test123";
	static String captcha = "123456";
	static String username;

	public void selectRegisterOption() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("header_register"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			element.click();
			createReport.setExtentTestInfo(elementText);
		} else {
			createReport.setExtentTestFail(elementText);
			throw new FailedLoginException();
		}
	}

	public void setUsername() {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("r_username"));
		driver.setWait(element);
		String elementText = element.getAttribute(attribute);
		if (element.isDisplayed()) {
			element.clear();
			username = "qctester" + generateRandomNumbers.generateRandomNumbers();
			element.sendKeys(username);
			createReport.setExtentTestInfo(elementText + space + username);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void setPassword() {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("r_password"));
		String elementText = element.getAttribute(attribute);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(password);
			createReport.setExtentTestInfo(elementText + space + password);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void setCaptcha() {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("ipt_code3"));
		String elementText = element.getAttribute(attribute);
		if (element.isDisplayed()) {
			element.clear();
			element.sendKeys(captcha);
			createReport.setExtentTestInfo(elementText + space + captcha);
		} else {
			createReport.setExtentTestFail(elementText);
		}
	}

	public void selectRegister() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.id("register_btn"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			element.click();
			createReport.setExtentTestInfo(elementText);
		} else {
			createReport.setExtentTestFail(elementText);
			throw new FailedLoginException();
		}
	}

	public void getVerifyRegister() throws FailedLoginException {
		driver.setTimeOut();
		WebElement element = driver.getDriver().findElement(By.xpath("//div[@class='header_after_login_id']//a[contains(text(),'" + username + "')]"));
		String elementText = element.getText();
		if (element.isDisplayed()) {
			createReport.setExtentTestInfo(elementText + space + "register verified");
		} else {
			createReport.setExtentTestFail("register failed");
			throw new FailedLoginException();
		}
	}
}