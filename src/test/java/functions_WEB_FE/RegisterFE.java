package functions_WEB_FE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class RegisterFE {

	private static RegisterFE function = new RegisterFE();

	public static RegisterFE getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();

	WebDriverWait wait;
	private static String attr = "placeholder";
	private static String keyIn = " keyed in ";
	private static String keyOut = " removed from ";

	public void registerOptionButton() throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 5);
		WebElement registerOptionButton = bDriver.getDriver().findElement(By.id("header_register"));
		wait.until(ExpectedConditions.elementToBeClickable(registerOptionButton));
		String registerOptionButtonText = registerOptionButton.getText();
		String fail = "registerOptionButton failed";

		if (registerOptionButton.isEnabled()) {
			registerOptionButton.click();
			cR.getExtentTest().info("Clicked " + registerOptionButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setNewUserID(String userID) throws FailedLoginException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement setNewUserID = bDriver.getDriver().findElement(By.xpath("//input[@id='r_username']"));
		wait.until(ExpectedConditions.elementToBeClickable(setNewUserID));
		String setNewUserIDText = setNewUserID.getAttribute(attr);
		String fail = "setNewUserID failed";

		if (setNewUserID.isDisplayed()) {
			setNewUserID.clear();
			setNewUserID.sendKeys(userID);
			cR.getExtentTest().info(userID + keyIn + setNewUserIDText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setNewPassword(String password) throws FailedLoginException {
		WebElement setNewPassword = bDriver.getDriver().findElement(By.id("r_password"));
		String setNewPasswordText = setNewPassword.getAttribute(attr);
		String fail = "setNewPassword failed";

		if (setNewPassword.isDisplayed()) {
			setNewPassword.clear();
			setNewPassword.sendKeys(password);
			cR.getExtentTest().info(password + keyIn + setNewPasswordText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void setCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = bDriver.getDriver().findElement(By.id("ipt_code3"));
		String setCaptchaText = setCaptcha.getAttribute(attr);
		String fail = "setCaptcha failed";

		if (setCaptcha.isDisplayed()) {
			setCaptcha.click();
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			cR.getExtentTest().info(captcha + keyIn + setCaptchaText);
		} else {
			cR.getExtentTest().warning(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

//	depends on CLIENT

	public void setDOB(String year, String day) throws FailedLoginException, InterruptedException {
		String fail = "setDateOfBirth failed";
		String skip = "Skipped setDateOfBirth";
		Boolean dayListValue = false;

		try {
			WebElement setDateOfBirth = bDriver.getDriver().findElement(By.id("r_dob"));
			String setDateOfBirthText = setDateOfBirth.getAttribute(attr);

			if (setDateOfBirth.isDisplayed()) {
				setDateOfBirth.click();
				cR.getExtentTest().info("Clicked " + setDateOfBirthText);
			}
			wait = new WebDriverWait(bDriver.getDriver(), 10);
			WebElement yearSelect = bDriver.getDriver().findElement(By.xpath("(//select[@class='yearselect'])[1]"));
			wait.until(ExpectedConditions.elementToBeClickable(yearSelect));
			if (yearSelect.isDisplayed()) {
				Select sYear = new Select(yearSelect);
				sYear.selectByVisibleText(year);

				WebElement dayList = bDriver.getDriver().findElement(By.xpath("(//tbody)[1]"));
				List<WebElement> dayListRow = dayList.findElements(By.tagName("tr"));
				List<WebElement> dayListColumn;
				for (WebElement Rows : dayListRow) {
					dayListColumn = bDriver.getDriver().findElements(By.tagName("td"));
					for (WebElement dayColumns : dayListColumn) {
						String dayListText = dayColumns.getText();
						if (dayListText.equals(day)) {
							dayColumns.click();
							cR.getExtentTest().info("Clicked " + day);
							dayListValue = true;
						}
					}
				}
			}
			if (!dayListValue) {
				cR.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (StaleElementReferenceException e) {
			cR.getExtentTest().warning("Stale element but skipped because succeed");
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

//	depends on CLIENT

	public void setPhoneNumber(String phoneNumber) throws FailedLoginException, InterruptedException {
		String fail = "setPhoneNumber failed";
		String skip = "Skipped setPhoneNumber";
		Boolean dayListValue = false;

		try {
			WebElement setPhoneNumber = bDriver.getDriver().findElement(By.id("r_mobile_number"));
			String setPhoneNumberText = setPhoneNumber.getAttribute(attr);

			if (setPhoneNumber.isDisplayed()) {
				setPhoneNumber.clear();
				setPhoneNumber.sendKeys(phoneNumber);
				cR.getExtentTest().info(phoneNumber + keyIn + setPhoneNumberText);
			} else {
				cR.getExtentTest().fail(fail);
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectRegisterButton() throws FailedLoginException {
		WebElement selectRegisterButton = bDriver.getDriver().findElement(By.id("register_btn"));
		String selectRegisterButtonText = selectRegisterButton.getText();
		String fail = "selectRegisterButton failed";

		if (selectRegisterButton.isEnabled()) {
			selectRegisterButton.click();
			cR.getExtentTest().info("Clicked = " + selectRegisterButtonText);
			bDriver.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyRegister(String userID) throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));
		String fail = "verifyRegister failed";

		if (userIDName.isDisplayed()) {
			cR.getExtentTest().info("Account " + userID + " verified");
		} else {
			cR.getExtentTest().warning(fail);
		}
	}
}