package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;

public class LoginFE {

	private static LoginFE loginF = new LoginFE();

	public static LoginFE getInstance() {
		return loginF;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();

	WebDriverWait wait;
	private static String attr = "placeholder";
	private static String keyIn = " keyed in ";

	public void loginOptionButton() throws FailedLoginException {
		String fail = "loginOptionButton failed";

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.id("header_login"));
		wait.until(ExpectedConditions.elementToBeClickable(loginOptionButton));
		String loginOptionButtonText = loginOptionButton.getText();

		if (loginOptionButton.isEnabled()) {
			loginOptionButton.click();
			cR.getExtentTest().info("Clicked " + loginOptionButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setUserID(String userID) throws FailedLoginException {
		String fail = "setUserID failed";

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement setUserID = bDriver.getDriver().findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(setUserID));
		String setUserIDText = setUserID.getAttribute(attr);

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			cR.getExtentTest().info(userID + keyIn + setUserIDText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setPassword(String password) throws FailedLoginException {
		String fail = "setPassword failed";

		WebElement setPassword = bDriver.getDriver().findElement(By.id("password"));
		String setPasswordText = setPassword.getAttribute("placeholder");

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			cR.getExtentTest().info(password + keyIn + setPasswordText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setCaptcha(String captcha) throws FailedLoginException {
		String fail = "Captcha failed";

		WebElement setCaptcha = bDriver.getDriver().findElement(By.id("captcha_code"));
		String setCaptcha_Text = setCaptcha.getAttribute("placeholder");

		if (setCaptcha.isDisplayed()) {
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			cR.getExtentTest().info(captcha + keyIn + setCaptcha_Text);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectLoginButton() throws FailedLoginException {
		String fail = "selectLoginButton failed";

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement selectLoginButton = bDriver.getDriver().findElement(By.id("login_popup_btn"));
		wait.until(ExpectedConditions.elementToBeClickable(selectLoginButton));
		String selectLoginButtonText = selectLoginButton.getText();

		if (selectLoginButton.isEnabled()) {
			selectLoginButton.click();
			cR.getExtentTest().info("Clicked " + selectLoginButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		String fail = "verifyLogIn failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			cR.getExtentTest().info("Account " + userID + " verified");
		} else {
			cR.getExtentTest().warning(fail);
		}
	}
}