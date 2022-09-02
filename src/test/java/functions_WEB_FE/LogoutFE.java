package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;

public class LogoutFE {

	private static LogoutFE logoutF = new LogoutFE();

	public static LogoutFE getInstance() {
		return logoutF;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	
	WebDriverWait wait;

	public void selectLogoutButton(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		String fail = "selectLogoutButton failed";

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(bDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			cR.getExtentTest().info("Hovered over " + userID);
		} else {
			cR.getExtentTest().warning(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement logoutButton = bDriver.getDriver().findElement(By.xpath("//button[@class='btn btn_nav_logout']"));
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		String logoutButtonText = logoutButton.getText();

		Thread.sleep(250);
		if (logoutButton.isEnabled()) {
			logoutButton.click();
			cR.getExtentTest().info("Clicked " + logoutButtonText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
	
//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyLogout() throws FailedLoginException, InterruptedException {
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.id("header_login"));
		wait.until(ExpectedConditions.visibilityOf(loginOptionButton));
		String fail = "verifyLogout failed";

		if (loginOptionButton.isDisplayed()) {
			cR.getExtentTest().info("Logout success");
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}