package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Generate_Random_Numbers;
import utilities.Result_Listener;

public class Login_Option_Password {

	private static Login_Option_Password login_Option_Password = new Login_Option_Password();

	Base_Driver base_Driver = Base_Driver.get_Instance();
	Create_Report create_Report = Create_Report.getInstance();
	Result_Listener result_Listener = Result_Listener.getInstance();
	Generate_Random_Numbers generate_Random_Numbers = Generate_Random_Numbers.getInstance();

	String password = "test123";

	public static Login_Option_Password getInstance() {
		return login_Option_Password;
	}

	public void ClickAndSetLoginOptionPassword() throws FailedLoginException {
		WebElement login_Option_Password = base_Driver.getDriver().findElement(By.id("password"));
		String login_Option_Password_Text = login_Option_Password.getAttribute("placeholder");
		String login_Option_Password_Fail = "Password failed";
		
		if (login_Option_Password.isDisplayed()) {
			login_Option_Password.clear();
			login_Option_Password.sendKeys(password);
			create_Report.getExtentTest().info(password + " is entered in " + login_Option_Password_Text + " field");
			System.out.println(password);
		} else {
			create_Report.getExtentTest().fail(login_Option_Password_Fail);
			throw new FailedLoginException(login_Option_Password_Fail);
		}
	}

	public void ClickPasswordEyeIcon() throws InterruptedException, FailedLoginException {
		WebElement login_Option_Password_Eye_Icon_Closed = base_Driver.getDriver().findElement(By.xpath("(//div[@class='ico ico-eye_close'])[1]"));
		String eye_Icon = "Clicked on password eye icon";
		String eye_Icon_Fail = "Clicked on password eye icon FAILED";

		if (!login_Option_Password_Eye_Icon_Closed.isSelected()) {
			login_Option_Password_Eye_Icon_Closed.click();
			create_Report.getExtentTest().info(eye_Icon);
			Thread.sleep(500);
		} else {
			create_Report.getExtentTest().fail(eye_Icon_Fail);
			throw new FailedLoginException(eye_Icon_Fail);
		}

		WebElement login_Option_Password_Eye_Icon_Opened = base_Driver.getDriver().findElement(By.xpath("(//div[@class='ico ico-eye_open'])[1]"));
		if (login_Option_Password_Eye_Icon_Opened.isEnabled()) {
			login_Option_Password_Eye_Icon_Opened.click();
			create_Report.getExtentTest().info(eye_Icon);
		} else {
			create_Report.getExtentTest().fail(eye_Icon_Fail);
			throw new FailedLoginException(eye_Icon_Fail);
		}
	}
}