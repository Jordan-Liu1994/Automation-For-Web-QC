package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Deposit_WalletHistory_Function {

	private static Deposit_WalletHistory_Function function = new Deposit_WalletHistory_Function();

	public static Deposit_WalletHistory_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void selectWalletHistoryButton(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		String fail = "Hover " + userID + " failed";
		String fail2 = "Select wallet history button failed";

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
		WebElement walletHistory = baseDriver.getDriver().findElement(By.xpath("//a[contains(text(),'钱包记录')]"));
		String walletHistory_Text = walletHistory.getText();

		if (walletHistory.isEnabled()) {
			walletHistory.click();
			createReport.getExtentTest().info("Clicked " + walletHistory_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
			throw new FailedLoginException(fail2);
		}
	}
	
//	= = = = = = = = = = = = = = = = = = = = 
	
	public void selectFundsDetails() throws FailedLoginException, InterruptedException {
		WebElement fundsDetails = baseDriver.getDriver().findElement(By.id("wallet_history_container_tab"));
		String fundsDetails_Text = fundsDetails.getText();
		String fail = "Hover over wallet icon failed";
		String fail2 = "Logout failed";

		if (fundsDetails.isDisplayed()) {
			fundsDetails.click();
			createReport.getExtentTest().info("Clicked " + fundsDetails_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}

		Thread.sleep(1000);
		WebElement depositHistoryOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='active'][contains(text(),'存款')]"));
		String depositHistoryOption_Text = depositHistoryOption.getText();

		if (depositHistoryOption.isDisplayed()) {
			depositHistoryOption.click();
			createReport.getExtentTest().info("Clicked " + depositHistoryOption_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
			throw new FailedLoginException(fail2);
		}
		
		JavascriptExecutor js = (JavascriptExecutor) baseDriver.getDriver();
		js.executeScript("window.scrollBy(0,150)", "");
	}
}