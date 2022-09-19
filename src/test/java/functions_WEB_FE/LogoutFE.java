package functions_WEB_FE;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CreateReport;
import utilities.VariablesStorage;

public class LogoutFE extends VariablesStorage {

	CreateReport cReport = new CreateReport();

	WebDriverWait wait;
	String fail;
	String skip;

	public void selectLogoutButton(String userID) throws FailedLoginException, InterruptedException {		
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(bDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			cReport.getExtentTest().info("Hovered over " + userID);
		} else {
			fail = "selectLogoutButton failed";
			cReport.getExtentTest().warning(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement logoutButton = bDriver.getDriver().findElement(By.xpath("//button[@class='btn btn_nav_logout']"));
		wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		Thread.sleep(250);
		if (logoutButton.isEnabled()) {
			String logoutButtonText = logoutButton.getText();
			logoutButton.click();
			cReport.getExtentTest().info("Clicked " + logoutButtonText);
		} else {
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void verifyLogout() throws FailedLoginException, InterruptedException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement loginOptionButton = bDriver.getDriver().findElement(By.id("header_login"));
		wait.until(ExpectedConditions.visibilityOf(loginOptionButton));
		
		if (loginOptionButton.isDisplayed()) {
			cReport.getExtentTest().info("Logout success");
		} else {
			fail = "verifyLogout failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}