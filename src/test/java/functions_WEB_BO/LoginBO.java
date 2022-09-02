package functions_WEB_BO;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;

public class LoginBO {

	private static LoginBO loginBOF = new LoginBO();

	public static LoginBO getInstance() {
		return loginBOF;
	}

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	
	WebDriverWait wait;
	private static String attr = "placeholder";
	private static String keyIn = " keyed in ";

	public void setUserID(String userID) throws FailedLoginException, InterruptedException {
		String fail = "setUserID failed";
		
		bDriver.getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		Thread.sleep(500);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement setUserID = bDriver.getDriver().findElement(By.id("userEmail"));
		wait.until(ExpectedConditions.visibilityOf(setUserID));
		String setUserIDText = setUserID.getAttribute("placeholder");

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			cR.getExtentTest().info(userID + keyIn + setUserIDText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void setPassword(String password) throws FailedLoginException {
		String fail = "setPassword failed";

		WebElement setPassword = bDriver.getDriver().findElement(By.id("userPassword"));
		String setPasswordText = setPassword.getAttribute(attr);

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			cR.getExtentTest().info(password + keyIn + setPasswordText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		String fail = "setCaptcha failed";

		WebElement setCaptcha = bDriver.getDriver().findElement(By.id("totp"));
		String setCaptchaText = setCaptcha.getAttribute(attr);

		if (setCaptcha.isDisplayed()) {
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			cR.getExtentTest().info(captcha + keyIn + setCaptchaText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void selectLoginButton() throws FailedLoginException {
		String fail = "selectLoginButton failed";

		WebElement selectLoginButton = bDriver.getDriver().findElement(By.tagName("button"));
		String selectLoginButtonText = selectLoginButton.getText();

		if (selectLoginButton.isEnabled()) {
			selectLoginButton.click();
			cR.getExtentTest().info("Clicked " + selectLoginButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		String fail = "verifyLogIn failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("//a[normalize-space()='" + userID + "']"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			cR.getExtentTest().info("Log in account " + userID + " verified");
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}