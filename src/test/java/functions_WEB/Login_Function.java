package functions_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Login_Function {

	private static Login_Function function = new Login_Function();

	public static Login_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void selectLoginOption() throws FailedLoginException {
		WebElement selectLoginOption = baseDriver.getDriver().findElement(By.id("header_login"));
		String selectLoginOption_Text = selectLoginOption.getText();
		String fail = "Login option failed";

		if (selectLoginOption.isEnabled()) {
			selectLoginOption.click();
			createReport.getExtentTest().info("Clicked " + selectLoginOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setUserID(String userID) throws FailedLoginException {
		WebElement setUserID = baseDriver.getDriver().findElement(By.id("username"));
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
		WebElement setPassword = baseDriver.getDriver().findElement(By.id("password"));
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

	private String eyeIconStatus;

	public void clickPasswordEyeIcon() throws InterruptedException, FailedLoginException {
		eyeIconStatus = "ico ico-eye_close";
		WebElement eyeIconClosed = baseDriver.getDriver().findElement(By.xpath("(//div[@class='" + eyeIconStatus + "'])[1]"));
		String eye_Icon = "Clicked eye icon";
		String fail = "Eye icon failed";

		if (!eyeIconClosed.isSelected()) {
			eyeIconClosed.click();
			createReport.getExtentTest().info(eye_Icon);
			eyeIconStatus = "ico ico-eye_open";
			Thread.sleep(250);
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement eyeIconOpened = baseDriver.getDriver().findElement(By.xpath("(//div[@class='" + eyeIconStatus + "'])[1]"));
		if (eyeIconOpened.isEnabled()) {
			eyeIconOpened.click();
			createReport.getExtentTest().info(eye_Icon);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = baseDriver.getDriver().findElement(By.id("captcha_code"));
		WebElement captchaRandomizer = baseDriver.getDriver().findElement(By.xpath("//div[@class='captcha_hover']"));
		String setCaptcha_Text = setCaptcha.getAttribute("placeholder");
		String fail = "Captcha randomizer failed";
		String fail2 = "Captcha failed";

		if (captchaRandomizer.isDisplayed()) {
			captchaRandomizer.click();
		} else {
			createReport.getExtentTest().fail(fail);
		}

		if (setCaptcha.isDisplayed()) {
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			createReport.getExtentTest().info(captcha + " is keyed into " + setCaptcha_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
		}
	}

	public void selectRememberMeButton() throws FailedLoginException {
		WebElement selectRememberMeButton = baseDriver.getDriver().findElement(By.xpath("//div[contains(text(),'保持登录')]"));
		String selectRememberMeButton_Text = selectRememberMeButton.getText();
		String fail = "Remember me failed";

		if (!selectRememberMeButton.isSelected()) {
			selectRememberMeButton.click();
			createReport.getExtentTest().info("Clicked " + selectRememberMeButton_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void selectLoginButton() throws FailedLoginException {
		WebElement selectLoginButton = baseDriver.getDriver().findElement(By.id("login_popup_btn"));
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
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		String fail = "Login to wrong account user failed";

		if (userIDName.isDisplayed()) {
			createReport.getExtentTest().info("Log in account " + userID + " verified");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}