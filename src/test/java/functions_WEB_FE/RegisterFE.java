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
import utilities.GenerateRandomNumbers;
import utilities.VariablesStorage;

public class RegisterFE extends VariablesStorage {

	CreateReport cReport = new CreateReport();
	GenerateRandomNumbers gRNumbers = new GenerateRandomNumbers();

	WebDriverWait wait;
	String fail;
	String skip;

	String registerUserIDFE;

	public void registerOptionButton() throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement registerOptionButton = bDriver.getDriver().findElement(By.id("header_register"));
		wait.until(ExpectedConditions.elementToBeClickable(registerOptionButton));

		if (registerOptionButton.isEnabled()) {
			String registerOptionButtonText = registerOptionButton.getText();
			registerOptionButton.click();
			cReport.getExtentTest().info("Clicked " + registerOptionButtonText);
		} else {
			fail = "registerOptionButton failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setNewUserID() throws FailedLoginException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement setNewUserID = bDriver.getDriver().findElement(By.xpath("//input[@id='r_username']"));
		wait.until(ExpectedConditions.elementToBeClickable(setNewUserID));
		
		if (setNewUserID.isDisplayed()) {
			String setNewUserIDText = setNewUserID.getAttribute(attribute);
			registerUserIDFE = "qctester" + gRNumbers.generateRandomNumbers();
			setNewUserID.clear();
			setNewUserID.sendKeys(registerUserIDFE);
			cReport.getExtentTest().info(registerUserIDFE + keyIn + setNewUserIDText);
		} else {
			fail = "setNewUserID failed";
			cReport.getExtentTest().warning(fail);
		}
	}

	public void setNewPassword(String password) throws FailedLoginException {
		WebElement setNewPassword = bDriver.getDriver().findElement(By.id("r_password"));
	
		if (setNewPassword.isDisplayed()) {
			String setNewPasswordText = setNewPassword.getAttribute(attribute);
			setNewPassword.clear();
			setNewPassword.sendKeys(password);
			cReport.getExtentTest().info(password + keyIn + setNewPasswordText);
		} else {
			fail = "setNewPassword failed";
			cReport.getExtentTest().warning(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = bDriver.getDriver().findElement(By.id("ipt_code3"));

		if (setCaptcha.isDisplayed()) {
			String setCaptchaText = setCaptcha.getAttribute(attribute);
			setCaptcha.clear();
			setCaptcha.sendKeys(captcha);
			cReport.getExtentTest().info(captcha + keyIn + setCaptchaText);
		} else {
			fail = "setCaptcha failed";
			cReport.getExtentTest().warning(fail);
		}
	}

	public void setDOB(String year, String day) throws FailedLoginException, InterruptedException {
		Boolean dayListValue = false;

		try {
			WebElement setDateOfBirth = bDriver.getDriver().findElement(By.id("r_dob"));
			String setDateOfBirthText = setDateOfBirth.getAttribute(attribute);

			if (setDateOfBirth.isDisplayed()) {
				setDateOfBirth.click();
				cReport.getExtentTest().info("Clicked " + setDateOfBirthText);
			}
			wait = new WebDriverWait(bDriver.getDriver(), 15);
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
							cReport.getExtentTest().info("Clicked " + day);
							dayListValue = true;
						}
					}
				}
			}
			if (!dayListValue) {
				fail = "setDateOfBirth failed";
				cReport.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (StaleElementReferenceException e) {
			cReport.getExtentTest().warning("Stale element but skipped because succeed");
		} catch (NoSuchElementException e) {
			skip = "Skipped setDOB";
			cReport.getExtentTest().skip(skip);
		}
	}

	public void setPhoneNumber() throws FailedLoginException, InterruptedException {
		Boolean dayListValue = false;

		try {
			WebElement setPhoneNumber = bDriver.getDriver().findElement(By.id("r_mobile_number"));
			String setPhoneNumberText = setPhoneNumber.getAttribute(attribute);

			if (setPhoneNumber.isDisplayed()) {
				String registerPhoneNumberFE = "13515" + gRNumbers.generateRandomNumbers();
				setPhoneNumber.clear();
				setPhoneNumber.sendKeys(registerPhoneNumberFE);
				cReport.getExtentTest().info(registerPhoneNumberFE + keyIn + setPhoneNumberText);
			} else {
				fail = "setPhoneNumber failed";
				cReport.getExtentTest().fail(fail);
			}
		} catch (NoSuchElementException e) {
			skip = "Skipped setPhoneNumber";
			cReport.getExtentTest().skip(skip);
		}
	}

	public void selectRegisterButton() throws FailedLoginException {
		WebElement selectRegisterButton = bDriver.getDriver().findElement(By.id("register_btn"));

		if (selectRegisterButton.isEnabled()) {
			String selectRegisterButtonText = selectRegisterButton.getText();
			selectRegisterButton.click();
			cReport.getExtentTest().info("Clicked = " + selectRegisterButtonText);
			bDriver.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} else {
			fail = "selectRegisterButton failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyRegister() throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + registerUserIDFE + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));
		
		if (userIDName.isDisplayed()) {
			cReport.getExtentTest().info("Account " + registerUserIDFE + " verified");
		} else {
			fail = "verifyRegister failed";
			cReport.getExtentTest().warning(fail);
		}
	}
}