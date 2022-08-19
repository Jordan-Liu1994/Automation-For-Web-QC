package functions_WEB;

import java.util.concurrent.TimeUnit;

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

	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();

	public void selectLogoutButton(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		String fail = "Hover " + userID + " failed";
		String fail2 = "Logout failed";

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(baseDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			createReport.getExtentTest().info("Hovered over " + userID);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement logoutButton = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'登出')]"));
		String logoutButton_Text = logoutButton.getText();

		if (logoutButton.isEnabled()) {
			logoutButton.click();
			createReport.getExtentTest().info("Clicked " + logoutButton_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
			throw new FailedLoginException(fail2);
		}
	}
}