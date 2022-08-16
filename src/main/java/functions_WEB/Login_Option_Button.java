package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Base_Driver;
import utilities.Create_Report;

public class Login_Option_Button {

	private static Login_Option_Button login_Option_Button = new Login_Option_Button();

	Base_Driver base_Driver = Base_Driver.get_Instance();
	Create_Report create_Report = Create_Report.getInstance();

	public static Login_Option_Button getInstance() {
		return login_Option_Button;
	}

	public void ClickLoginOptionButton() throws FailedLoginException {
		WebElement login_Option_Button = base_Driver.getDriver().findElement(By.id("header_login"));
		String login_Option_Button_Text = login_Option_Button.getText();
		String login_Option_Button_Fail = "Login option button failed";
		
		if (login_Option_Button.isEnabled()) {
			login_Option_Button.click();
			create_Report.getExtentTest().info(login_Option_Button_Text + " is enabled & clicked");
		} else {
			create_Report.getExtentTest().fail(login_Option_Button_Fail);
			throw new FailedLoginException(login_Option_Button_Fail);
		}
	}
}