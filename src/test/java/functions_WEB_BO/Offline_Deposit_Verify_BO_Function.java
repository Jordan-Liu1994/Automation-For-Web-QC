package functions_WEB_BO;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions_WEB_FE.Offline_Deposit_Function;
import utilities.BaseDriver;
import utilities.CreateReport;

public class Offline_Deposit_Verify_BO_Function {

	private static Offline_Deposit_Verify_BO_Function function = new Offline_Deposit_Verify_BO_Function();

	public static Offline_Deposit_Verify_BO_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	Offline_Deposit_Function offlineDepositFunction = Offline_Deposit_Function.getInstance();

	public void selectOfflineDepositVerification() throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement hoverFinanceManagementModule = baseDriver.getDriver().findElement(By.xpath("(//a[@class='new_tab'])[28]"));
		wait.until(ExpectedConditions.visibilityOf(hoverFinanceManagementModule));
		String hoverFinanceManagementModule_Text = hoverFinanceManagementModule.getText();
		String fail = "Select offline deposit verification failed";

		if (hoverFinanceManagementModule.isDisplayed()) {
			Actions builder = new Actions(baseDriver.getDriver());
			Action act = builder.moveToElement(hoverFinanceManagementModule).build();
			act.perform();
			createReport.getExtentTest().info("Hovered over " + hoverFinanceManagementModule_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void offlineDepositVerificationSubModule() throws FailedLoginException {
		WebElement offlineDepositVerificationSubModule = baseDriver.getDriver().findElement(By.xpath("//a[contains(text(),'公司入款审核')]"));
		String offlineDepositVerificationSubModule_Text = offlineDepositVerificationSubModule.getText();
		String fail = "Offline deposit verification submodule failed";

		if (offlineDepositVerificationSubModule.isDisplayed()) {
			offlineDepositVerificationSubModule.click();
			createReport.getExtentTest().info("Clicked " + offlineDepositVerificationSubModule_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void filterUserAccount(String userID) throws FailedLoginException, InterruptedException {
		WebElement filterUserAccount = baseDriver.getDriver().findElement(By.xpath("//input[@placeholder='帐号']"));
		String filterUserAccount_Text = filterUserAccount.getAttribute("placeholder");
		String fail = "Filter user account failed";

		if (filterUserAccount.isDisplayed()) {
			filterUserAccount.clear();
			filterUserAccount.sendKeys(userID);
			Thread.sleep(500);
			filterUserAccount.sendKeys(Keys.ENTER);
			createReport.getExtentTest().info(userID + " is keyed into " + filterUserAccount_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement searchButton = baseDriver.getDriver().findElement(By.id("search_credit_btn"));
		String searchButton_Text = searchButton.getText();
		String fail2 = "Search button failed";

		if (searchButton.isEnabled()) {
			searchButton.click();
			createReport.getExtentTest().info("Clicked " + searchButton_Text);
		} else {
			createReport.getExtentTest().fail(fail2);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyDepositID() throws FailedLoginException {
		WebElement verifyDepositID_Dropdown = baseDriver.getDriver().findElement(By.xpath("//tbody/tr[1]/td[14]/i[1]"));
		String fail = "Verify deposit ID failed";

		if (verifyDepositID_Dropdown.isDisplayed()) {
			verifyDepositID_Dropdown.click();
			createReport.getExtentTest().info("Clicked dropdown");
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement verifyDepositID = baseDriver.getDriver().findElement(By.xpath("//td[normalize-space()='" + offlineDepositFunction.storedDepositID() + "']"));
		if (verifyDepositID.isDisplayed()) {
			createReport.getExtentTest().info("Deposit ID = " + offlineDepositFunction.storedDepositID());
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void rejectOfflineDepositAfterVerified() throws FailedLoginException {
		WebElement rejectOfflineDepositAfterVerified_Lock = baseDriver.getDriver().findElement(By.xpath("//button[@title='解锁']"));
		String fail = "Reject offline deposit after verified failed";

		if (rejectOfflineDepositAfterVerified_Lock.isEnabled()) {
			rejectOfflineDepositAfterVerified_Lock.click();
			createReport.getExtentTest().info("Clicked unlocked offline deposit");
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement rejectOfflineDepositAfterVerified_Reject = baseDriver.getDriver().findElement(By.xpath("//button[@title='拒绝']"));
		if (rejectOfflineDepositAfterVerified_Reject.isEnabled()) {
			rejectOfflineDepositAfterVerified_Reject.click();
			createReport.getExtentTest().info("Clicked reject button");
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement rejectOfflineDepositAfterVerified_Confirm = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'是')]"));
		wait.until(ExpectedConditions.visibilityOf(rejectOfflineDepositAfterVerified_Confirm));
		if (rejectOfflineDepositAfterVerified_Confirm.isEnabled()) {
			rejectOfflineDepositAfterVerified_Confirm.click();
			createReport.getExtentTest().info("Clicked confirm reject button");
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement confirmMessage = baseDriver.getDriver().findElement(By.id("alert_title"));
		wait.until(ExpectedConditions.visibilityOf(confirmMessage));

		if (confirmMessage.isDisplayed()) {
			createReport.getExtentTest().info("Offline deposit confirmed to be rejected");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyLogIn(String userID) throws FailedLoginException, InterruptedException {
		Thread.sleep(1500);
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("//a[normalize-space()='" + userID + "']"));
		String fail = "Login to wrong account user failed";

		if (userIDName.isDisplayed()) {
			createReport.getExtentTest().info("Log in account " + userID + " verified");
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}