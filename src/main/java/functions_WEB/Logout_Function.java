package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import utilities.Base_Driver;
import utilities.Create_Report;

public class Logout_Function {

	private static Logout_Function function = new Logout_Function();

	public static Logout_Function getInstance() {
		return function;
	}

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();

	public void ClickLogoutButton(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = base_Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		Actions builder = new Actions(base_Driver.getDriver());
		Action act = builder.moveToElement(userIDName).build();
		act.perform();
		Thread.sleep(500);
		
		WebElement logoutButton = base_Driver.getDriver().findElement(By.xpath("//button[contains(text(),'登出')]"));
		String logoutButton_Text = logoutButton.getText();
		String fail = "Logout button failed";

		if (logoutButton.isEnabled()) {
			logoutButton.click();
			create_Report.getExtentTest().info(logoutButton_Text + " is enabled & clicked");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}