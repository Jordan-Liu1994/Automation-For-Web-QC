package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.CreateReport;
import utilities.VariablesStorage;

public class LoginFE extends VariablesStorage {

	CreateReport cReport = new CreateReport();

	WebDriverWait wait;
	String fail;
	String skip;

	public void loginOptionButton() throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.id("header_login"));
		wait.until(ExpectedConditions.elementToBeClickable(loginOptionButton));

		if (loginOptionButton.isEnabled()) {
			String loginOptionButtonText = loginOptionButton.getText();
			loginOptionButton.click();
			cReport.getExtentTest().info("Clicked " + loginOptionButtonText);
		} else {
			fail = "loginOptionButton failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setUserID(String userID) throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement setUserID = bDriver.getDriver().findElement(By.id("username"));
		wait.until(ExpectedConditions.visibilityOf(setUserID));

		if (setUserID.isDisplayed()) {
			String setUserIDText = setUserID.getAttribute(attribute);
			setUserID.clear();
			setUserID.sendKeys(userID);
			cReport.getExtentTest().info(userID + keyIn + setUserIDText);
		} else {
			fail = "setUserID failed";
			cReport.getExtentTest().warning(fail);
		}
	}

	public void setPassword(String password) throws FailedLoginException {
		WebElement setPassword = bDriver.getDriver().findElement(By.id("password"));

		if (setPassword.isDisplayed()) {
			String setPasswordText = setPassword.getAttribute(attribute);
			setPassword.clear();
			setPassword.sendKeys(password);
			cReport.getExtentTest().info(password + keyIn + setPasswordText);
		} else {
			fail = "setPassword failed";
			cReport.getExtentTest().warning(fail);
		}
	}

//	DEPENDS ON REQUIREMENT
	public void setCaptcha(String captcha) throws FailedLoginException {
		try {
			WebElement setCaptcha = bDriver.getDriver().findElement(By.id("captcha_code"));

			if (setCaptcha.isDisplayed()) {
				String setCaptchaText = setCaptcha.getAttribute(attribute);
				setCaptcha.clear();
				setCaptcha.sendKeys(captcha);
				cReport.getExtentTest().info(captcha + keyIn + setCaptchaText);
			} else {
				fail = "setCaptcha failed";
				cReport.getExtentTest().warning(fail);
			}
		} catch (NoSuchElementException e) {
			skip = "setCaptcha skipped";
			cReport.getExtentTest().skip(skip);
		}
	}
	
//	DEPENDS ON REQUIREMENT
	public void setSliderCaptcha() throws FailedLoginException, InterruptedException {		
		try {
			Actions builder = new Actions(bDriver.getDriver());
			wait = new WebDriverWait(bDriver.getDriver(), 15);
			WebElement setSliderCaptcha = bDriver.getDriver().findElement(By.xpath("//div[@class='gt_slider_knob gt_show']"));
			wait.until(ExpectedConditions.elementToBeClickable(setSliderCaptcha));
			
			if (setSliderCaptcha.isDisplayed()) {
				Thread.sleep(1000);
				builder.moveToElement(setSliderCaptcha).clickAndHold().moveByOffset(100, 0).release().build().perform();
				cReport.getExtentTest().info("Dragged captcha slider to the right by 100 pixels");
				Thread.sleep(1000);
				builder.moveByOffset(500, 0).build().perform();
			}
		} catch (NoSuchElementException e) {
			skip = "setSliderCaptcha skipped";
			cReport.getExtentTest().skip(skip);
		}
	}

	public void selectLoginButton() throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectLoginButton = bDriver.getDriver().findElement(By.id("login_popup_btn"));
		wait.until(ExpectedConditions.visibilityOf(selectLoginButton));
		wait.until(ExpectedConditions.elementToBeClickable(selectLoginButton));

		if (selectLoginButton.isEnabled()) {
			String selectLoginButtonText = selectLoginButton.getText();
			selectLoginButton.click();
			cReport.getExtentTest().info("Clicked " + selectLoginButtonText);
		} else {
			fail = "selectLoginButton failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			cReport.getExtentTest().info("Account " + userID + " verified");
		} else {
			fail = "verifyLogIn failed";
			cReport.getExtentTest().warning(fail);
		}
	}
}