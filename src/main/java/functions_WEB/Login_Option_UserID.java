package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.Base_Driver;
import utilities.Create_Report;
import utilities.Generate_Random_Numbers;

public class Login_Option_UserID {

	private static Login_Option_UserID login_Option_UserID = new Login_Option_UserID();

	Base_Driver base_Driver = Base_Driver.get_Instance();
	Create_Report create_Report = Create_Report.getInstance();
	Generate_Random_Numbers generate_Random_Numbers = Generate_Random_Numbers.getInstance();

	String userID = "qctester0101";

	public static Login_Option_UserID getInstance() {
		return login_Option_UserID;
	}

	public void ClickAndSetLoginOptionUserID() throws FailedLoginException {
		WebElement login_Option_UserID = base_Driver.getDriver().findElement(By.id("username"));
		String login_Option_UserID_Text = login_Option_UserID.getAttribute("placeholder");
		String userID_Fail = "UserID failed";
		
		if (login_Option_UserID.isDisplayed()) {
			login_Option_UserID.clear();
			login_Option_UserID.sendKeys(userID);
			create_Report.getExtentTest().info(userID + " is entered in " + login_Option_UserID_Text + " field");
			System.out.println(userID);
		} else {
			create_Report.getExtentTest().fail(userID_Fail);
			throw new FailedLoginException(userID_Fail);
		}
	}
}