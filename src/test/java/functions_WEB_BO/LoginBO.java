package functions_WEB_BO;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CreateReport;
import utilities.VariablesStorage;

public class LoginBO extends VariablesStorage {

	CreateReport cReport = new CreateReport();
	
	WebDriverWait wait;
	String fail;
	
	public void setUserID(String userID) throws FailedLoginException, InterruptedException {
		
		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		Thread.sleep(500);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement setUserID = bDriver.getDriver().findElement(By.id("userEmail"));
		wait.until(ExpectedConditions.visibilityOf(setUserID));
		String setUserIDText = setUserID.getAttribute(attribute);

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			cReport.getExtentTest().info(userID + keyIn + setUserIDText);
		} else {
			fail = "setUserID failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public void setPassword(String password) throws FailedLoginException {
		fail = "setPassword failed";

		WebElement setPassword = bDriver.getDriver().findElement(By.id("userPassword"));
		String setPasswordText = setPassword.getAttribute(attribute);

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			cReport.getExtentTest().info(password + keyIn + setPasswordText);
		} else {
			cReport.getExtentTest().fail(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		fail = "setCaptcha failed";

		WebElement setCaptcha = bDriver.getDriver().findElement(By.id("totp"));
		String setCaptchaText = setCaptcha.getAttribute(attribute);

		if (setCaptcha.isDisplayed()) {
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			cReport.getExtentTest().info(captcha + keyIn + setCaptchaText);
		} else {
			cReport.getExtentTest().fail(fail);
		}
	}

	public void selectLoginButton() throws FailedLoginException {
		fail = "selectLoginButton failed";

		WebElement selectLoginButton = bDriver.getDriver().findElement(By.tagName("button"));
		String selectLoginButtonText = selectLoginButton.getText();

		if (selectLoginButton.isEnabled()) {
			selectLoginButton.click();
			cReport.getExtentTest().info("Clicked " + selectLoginButtonText);
		} else {
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		fail = "verifyLogIn failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("//a[normalize-space()='" + userID + "']"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			cReport.getExtentTest().info("Log in account " + userID + " verified");
		} else {
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}