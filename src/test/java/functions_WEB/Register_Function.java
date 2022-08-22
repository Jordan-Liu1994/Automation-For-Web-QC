package functions_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Register_Function {

	private static Register_Function function = new Register_Function();

	public static Register_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void selectRegisterOption() throws FailedLoginException {
		WebElement selectRegisterOption = baseDriver.getDriver().findElement(By.id("header_register"));
		String selectRegisterOption_Text = selectRegisterOption.getText();
		String fail = "Register option failed";

		if (selectRegisterOption.isEnabled()) {
			selectRegisterOption.click();
			createReport.getExtentTest().info("Clickec " + selectRegisterOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setNewUserID(String userID) throws FailedLoginException {
		WebElement setNewUserID = baseDriver.getDriver().findElement(By.id("r_username"));
		String setNewUserID_Text = setNewUserID.getAttribute("placeholder");
		String fail = "New userID failed";

		if (setNewUserID.isDisplayed()) {
			setNewUserID.clear();
			setNewUserID.sendKeys(userID);
			createReport.getExtentTest().info(userID + " is keyed in " + setNewUserID_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setNewPassword(String password) throws FailedLoginException {
		WebElement setNewPassword = baseDriver.getDriver().findElement(By.id("r_password"));
		String setNewPassword_Text = setNewPassword.getAttribute("placeholder");
		String fail = "Password failed";

		if (setNewPassword.isDisplayed()) {
			setNewPassword.clear();
			setNewPassword.sendKeys(password);
			createReport.getExtentTest().info(password + " is keyed in " + setNewPassword_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	private String eyeIconStatus;

	public void selectPasswordEyeIcon() throws InterruptedException, FailedLoginException {
		eyeIconStatus = "ico ico-eye_close";
		WebElement eyeIconClosed = baseDriver.getDriver().findElement(By.xpath("//div[@class='toggle_password']//div[@class='" + eyeIconStatus + "']"));
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
		WebElement eyeIconOpened = baseDriver.getDriver().findElement(By.xpath("//div[@class='" + eyeIconStatus + "']"));
		if (eyeIconOpened.isEnabled()) {
			eyeIconOpened.click();
			createReport.getExtentTest().info(eye_Icon);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = baseDriver.getDriver().findElement(By.id("ipt_code3"));
		WebElement captchaRandom = baseDriver.getDriver().findElement(By.xpath("//div[@class='d-flex']//div[@class='captcha_hover']"));
		String setCaptcha_Text = setCaptcha.getAttribute("placeholder");
		String fail = "Captcha failed";

		if (captchaRandom.isDisplayed()) {
			captchaRandom.click();

			if (setCaptcha.isDisplayed()) {
				setCaptcha.clear();
				setCaptcha.sendKeys(captcha);
				createReport.getExtentTest().info(captcha + " is keyed in " + setCaptcha_Text);
			} else {
				createReport.getExtentTest().fail(fail);
			}
		}
	}

	public void setReferralOptional(String referral) throws FailedLoginException {
		WebElement setReferralOptional = baseDriver.getDriver().findElement(By.id("referral_code"));
		String setReferralOptional_Text = setReferralOptional.getAttribute("placeholder");
		String fail = "Set Referral Optional failed";

		if (!setReferralOptional.isSelected()) {
			setReferralOptional.clear();
			setReferralOptional.sendKeys(referral);
			createReport.getExtentTest().info(referral + " is keyed in " + setReferralOptional_Text);
			setReferralOptional.clear();
			createReport.getExtentTest().info(referral + " is removed in " + setReferralOptional_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setDateOfBirth(String year, String day) throws FailedLoginException, InterruptedException {
		try {
			WebElement setDateOfBirth = baseDriver.getDriver().findElement(By.id("r_dob"));
			String clickRegisterButton_Text = setDateOfBirth.getAttribute("placeholder");
			String fail = "Set date of birth failed";
			if (setDateOfBirth.isDisplayed()) {
				setDateOfBirth.click();
				baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				WebElement yearSelection = baseDriver.getDriver().findElement(By.xpath("//div[@class='drp-calendar left single']//select[@class='yearselect']"));
				Select selectYear = new Select(yearSelection);
				selectYear.selectByVisibleText(year);

				WebElement daySelection = baseDriver.getDriver().findElement(By.xpath("(//td[contains(text(),'" + day + "')])[1]"));
				daySelection.click();
			} else {
				createReport.getExtentTest().fail(fail);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			throw new SkipException("Set date of birth not required");
		}
	}

	public void selectRegisterButton() throws FailedLoginException {
		WebElement selectRegisterButton = baseDriver.getDriver().findElement(By.id("register_btn"));
		String selectRegisterButton_Text = selectRegisterButton.getText();
		String fail = "Register button failed";

		if (selectRegisterButton.isEnabled()) {
			selectRegisterButton.click();
			createReport.getExtentTest().info("Clicked = " + selectRegisterButton_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyRegisterUserID(String userID) throws FailedLoginException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String fail = "Verify register " + userID + " failed";
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		if (userIDName.isDisplayed()) {
			createReport.getExtentTest().info("Register account " + userID + " success");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}