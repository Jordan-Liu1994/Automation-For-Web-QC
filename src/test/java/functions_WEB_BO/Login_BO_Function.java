package functions_WEB_BO;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Login_BO_Function {

	private static Login_BO_Function function = new Login_BO_Function();

	public static Login_BO_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void setUserID(String userID) throws FailedLoginException {
		WebElement setUserID = baseDriver.getDriver().findElement(By.id("userEmail"));
		String setUserID_Text = setUserID.getAttribute("placeholder");
		String fail = "UserID failed";

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			createReport.getExtentTest().info(userID + " is keyed into " + setUserID_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setPassword(String password) throws FailedLoginException {
		WebElement setPassword = baseDriver.getDriver().findElement(By.id("userPassword"));
		String setPassword_Text = setPassword.getAttribute("placeholder");
		String fail = "Password failed";

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			createReport.getExtentTest().info(password + " is keyed into " + setPassword_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = baseDriver.getDriver().findElement(By.id("totp"));
		String setCaptcha_Text = setCaptcha.getAttribute("placeholder");
		String fail = "Captcha randomizer failed";
		String fail2 = "Captcha failed";

		if (setCaptcha.isDisplayed()) {
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			createReport.getExtentTest().info(captcha + " is keyed into " + setCaptcha_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
		}
	}

	public void selectLoginButton() throws FailedLoginException {
		WebElement selectLoginButton = baseDriver.getDriver().findElement(By.tagName("button"));
		String selectLoginButton_Text = selectLoginButton.getText();
		String fail = "Login failed";

		if (selectLoginButton.isEnabled()) {
			selectLoginButton.click();
			createReport.getExtentTest().info("Clicked " + selectLoginButton_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		Thread.sleep(1500);
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("//a[normalize-space()='" + userID + "']"));
		String fail = "Login to wrong account user failed";

		if (userIDName.isDisplayed()) {
			createReport.getExtentTest().info("Log in account " + userID + " verified");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}