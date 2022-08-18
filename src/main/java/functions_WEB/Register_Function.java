package functions_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.SkipException;

import utilities.Base_Driver;
import utilities.Create_Report;

public class Register_Function {

	private static Register_Function function = new Register_Function();

	public static Register_Function getInstance() {
		return function;
	}

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();

	public void ClickRegisterOptionButton() throws FailedLoginException {
		WebElement registerOptionButton = base_Driver.getDriver().findElement(By.id("header_register"));
		String registerOptionButton_Text = registerOptionButton.getText();
		String fail = "Login option button failed";

		if (registerOptionButton.isEnabled()) {
			registerOptionButton.click();
			create_Report.getExtentTest().info(registerOptionButton_Text + " is enabled & clicked");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetNewUserID(String userID) throws FailedLoginException {
		WebElement setNewUserID = base_Driver.getDriver().findElement(By.id("r_username"));
		String setNewUserID_Text = setNewUserID.getAttribute("placeholder");
		String fail = "New userID failed";

		if (setNewUserID.isDisplayed()) {
			setNewUserID.clear();
			setNewUserID.sendKeys(userID);
			create_Report.getExtentTest().info(userID + " is entered in " + setNewUserID_Text + " field");
			System.out.println(userID);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetNewPassword(String password) throws FailedLoginException {
		WebElement setNewPassword = base_Driver.getDriver().findElement(By.id("r_password"));
		String setNewPassword_Text = setNewPassword.getAttribute("placeholder");
		String fail = "Password failed";

		if (setNewPassword.isDisplayed()) {
			setNewPassword.clear();
			setNewPassword.sendKeys(password);
			create_Report.getExtentTest().info(password + " is entered in " + setNewPassword_Text + " field");
			System.out.println(password);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ClickPasswordEyeIcon() throws InterruptedException, FailedLoginException {
		WebElement clickPasswordEyeIcon = base_Driver.getDriver().findElement(By.xpath("//div[@class='toggle_password']//div[@class='ico ico-eye_close']"));
		String eye_Icon = "Clicked on password eye icon";
		String fail = "Clicked on password eye icon FAILED";

		if (!clickPasswordEyeIcon.isSelected()) {
			clickPasswordEyeIcon.click();
			create_Report.getExtentTest().info(eye_Icon);
			Thread.sleep(500);

			WebElement login_Option_Password_Eye_Icon_Opened = base_Driver.getDriver().findElement(By.xpath("//div[@class='ico ico-eye_open']"));
			if (login_Option_Password_Eye_Icon_Opened.isEnabled()) {
				login_Option_Password_Eye_Icon_Opened.click();
				create_Report.getExtentTest().info(eye_Icon + " again");
			}
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetCaptcha(String captcha) throws FailedLoginException {
		WebElement setCaptcha = base_Driver.getDriver().findElement(By.id("ipt_code3"));
		WebElement captchaRandom = base_Driver.getDriver().findElement(By.xpath("//div[@class='d-flex']//div[@class='captcha_hover']"));
		String setCaptcha_Text = setCaptcha.getAttribute("placeholder");
		String fail = "Captcha failed";

		if (captchaRandom.isDisplayed()) {
			captchaRandom.click();

			if (setCaptcha.isDisplayed()) {
				setCaptcha.clear();
				setCaptcha.sendKeys(captcha);
				create_Report.getExtentTest().info(captcha + " is entered in " + setCaptcha_Text + " field");
				System.out.println(captcha);
			} else {
				create_Report.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		}
	}

	public void SetReferralOptional(String referral) throws FailedLoginException {
		WebElement setReferralOptional = base_Driver.getDriver().findElement(By.id("referral_code"));
		String setReferralOptional_Text = setReferralOptional.getAttribute("placeholder");
		String fail = "Set Referral Optional failed";

		if (!setReferralOptional.isSelected()) {
			setReferralOptional.clear();
			setReferralOptional.sendKeys(referral);
			create_Report.getExtentTest().info(referral + " is entered in " + setReferralOptional_Text + " field");
			setReferralOptional.clear();
			create_Report.getExtentTest().info(referral + " is removed from " + setReferralOptional_Text + " field");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ClickRegisterButton(String userID) throws FailedLoginException {
		WebElement clickRegisterButton = base_Driver.getDriver().findElement(By.id("register_btn"));
		String clickRegisterButton_Text = clickRegisterButton.getText();
		String fail = "Register button failed";

		if (clickRegisterButton.isEnabled()) {
			clickRegisterButton.click();
			create_Report.getExtentTest().info(clickRegisterButton_Text + " is enabled & clicked");

			base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement userIDName = base_Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
			if (userIDName.isDisplayed()) {
				create_Report.getExtentTest().info("Register in success");
			}
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetDateOfBirth(String year, String day) throws FailedLoginException, InterruptedException {
		WebElement setDateOfBirth = base_Driver.getDriver().findElement(By.id("r_dob"));
		String clickRegisterButton_Text = setDateOfBirth.getAttribute("placeholder");
		String fail = "Set date of birth failed";

		if (setDateOfBirth.isDisplayed()) {
			setDateOfBirth.click();

			Thread.sleep(500);
			WebElement yearSelection = base_Driver.getDriver().findElement(By.xpath("//div[@class='drp-calendar left single']//select[@class='yearselect']"));
			Select selectYear = new Select(yearSelection);
			selectYear.selectByVisibleText(year);

			WebElement daySelection = base_Driver.getDriver().findElement(By.xpath("(//td[contains(text(),'" + day + "')])[1]"));
			daySelection.click();

			Thread.sleep(500);
			try {
				WebElement setDateOfBirth_Error = base_Driver.getDriver().findElement(By.xpath("//div[@class='error_msg error_dob']"));
				if (setDateOfBirth_Error.isDisplayed()) {
					create_Report.getExtentTest().info(clickRegisterButton_Text + " able to set");
				}
			} catch (NoSuchElementException e) {
				create_Report.getExtentTest().info("Set date of birth is skipping");
				throw new SkipException("Set date of birth is skipping");
			}
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}