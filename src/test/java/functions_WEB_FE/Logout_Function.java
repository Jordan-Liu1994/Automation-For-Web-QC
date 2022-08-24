package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Logout_Function {

	private static Logout_Function function = new Logout_Function();

	public static Logout_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

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

		Thread.sleep(1000);
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
	
//	= = = = = = = = = = = = = = = = = = = = 
	
	public void selectLogoutButtonAfterRegister() throws FailedLoginException, InterruptedException {
		WebElement walletIcon = baseDriver.getDriver().findElement(By.xpath("(//img)[4]"));
		String fail = "Hover over wallet icon failed";
		String fail2 = "Logout failed";

		if (walletIcon.isDisplayed()) {
			Actions builder = new Actions(baseDriver.getDriver());
			Action act = builder.moveToElement(walletIcon).build();
			act.perform();
			createReport.getExtentTest().info("Hovered over wallet icon");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		Thread.sleep(1000);
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