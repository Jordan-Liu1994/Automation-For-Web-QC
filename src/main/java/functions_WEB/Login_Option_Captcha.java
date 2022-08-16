package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Generate_Random_Numbers;

public class Login_Option_Captcha {

	private static Login_Option_Captcha login_Option_Captcha = new Login_Option_Captcha();

	Base_Driver base_Driver = Base_Driver.get_Instance();
	Create_Report create_Report = Create_Report.getInstance();
	Generate_Random_Numbers generate_Random_Numbers = Generate_Random_Numbers.getInstance();

	String captcha = "123456";

	public static Login_Option_Captcha getInstance() {
		return login_Option_Captcha;
	}

	public void ClickAndSetLoginOptionCaptcha() throws FailedLoginException {
		WebElement login_Option_Captcha = base_Driver.getDriver().findElement(By.id("captcha_code"));
		String login_Option_Captcha_Text = login_Option_Captcha.getAttribute("placeholder");
		String captcha_Fail = "Captcha failed";
		
		if (login_Option_Captcha.isDisplayed()) {
			login_Option_Captcha.clear();
			login_Option_Captcha.sendKeys(captcha);
			create_Report.getExtentTest().info(captcha + " is entered in " + login_Option_Captcha_Text + " field");
			System.out.println(captcha);
		} else {
			create_Report.getExtentTest().fail(captcha_Fail);
			throw new FailedLoginException(captcha_Fail);
		}
	}
}