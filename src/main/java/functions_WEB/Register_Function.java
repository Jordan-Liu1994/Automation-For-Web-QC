package functions_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

	String userID = "qctester0101";

	public void SetUserID() throws FailedLoginException {
		WebElement setUserID = base_Driver.getDriver().findElement(By.id("username"));
		String setUserID_Text = setUserID.getAttribute("placeholder");
		String fail = "UserID failed";

		if (setUserID.isDisplayed()) {
			setUserID.clear();
			setUserID.sendKeys(userID);
			create_Report.getExtentTest().info(userID + " is entered in " + setUserID_Text + " field");
			System.out.println(userID);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	String password = "test123";

	public void SetPassword() throws FailedLoginException {
		WebElement setPassword = base_Driver.getDriver().findElement(By.id("password"));
		String setPassword_Text = setPassword.getAttribute("placeholder");
		String fail = "Password failed";

		if (setPassword.isDisplayed()) {
			setPassword.clear();
			setPassword.sendKeys(password);
			create_Report.getExtentTest().info(password + " is entered in " + setPassword_Text + " field");
			System.out.println(password);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ClickPasswordEyeIcon() throws InterruptedException, FailedLoginException {
		WebElement clickPasswordEyeIcon = base_Driver.getDriver().findElement(By.xpath("(//div[@class='ico ico-eye_close'])[1]"));
		String eye_Icon = "Clicked on password eye icon";
		String fail = "Clicked on password eye icon FAILED";

		if (!clickPasswordEyeIcon.isSelected()) {
			clickPasswordEyeIcon.click();
			create_Report.getExtentTest().info(eye_Icon);
			Thread.sleep(500);

			WebElement login_Option_Password_Eye_Icon_Opened = base_Driver.getDriver().findElement(By.xpath("(//div[@class='ico ico-eye_open'])[1]"));
			if (login_Option_Password_Eye_Icon_Opened.isEnabled()) {
				login_Option_Password_Eye_Icon_Opened.click();
				create_Report.getExtentTest().info(eye_Icon + " again");
			}
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	String captcha = "123456";

	public void SetCaptcha() throws FailedLoginException {
		WebElement setCaptcha = base_Driver.getDriver().findElement(By.id("captcha_code"));
		WebElement captchaRandom = base_Driver.getDriver().findElement(By.xpath("//div[@class='captcha_hover']"));
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

	public void ClickRememberMeButton() throws FailedLoginException {
		WebElement clickRememberMeButton = base_Driver.getDriver().findElement(By.xpath("(//div[contains(text(),'保持登录')])[1]"));
		String clickRememberMeButton_Text = clickRememberMeButton.getText();
		String fail = "Remember me button failed";

		if (!clickRememberMeButton.isSelected()) {
			clickRememberMeButton.click();
			create_Report.getExtentTest().info("Clicked " + clickRememberMeButton_Text);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ClickLoginButton() throws FailedLoginException {
		WebElement clickLoginButton = base_Driver.getDriver().findElement(By.id("login_popup_btn"));
		String clickLoginButton_Text = clickLoginButton.getText();
		String fail = "Login button failed";

		if (clickLoginButton.isEnabled()) {
			clickLoginButton.click();
			create_Report.getExtentTest().info(clickLoginButton_Text + " is enabled & clicked");

			base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			WebElement userIDName = base_Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
			if (userIDName.isDisplayed()) {
				create_Report.getExtentTest().info("Log in success");
			}
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}