package functions_WEB;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Offline_Deposit_Verify_BO_Function {

	private static Offline_Deposit_Verify_BO_Function function = new Offline_Deposit_Verify_BO_Function();

	public static Offline_Deposit_Verify_BO_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();
	Offline_Deposit_Function function2 = Offline_Deposit_Function.getInstance();

	public void selectOfflineDepositVerification() throws FailedLoginException, InterruptedException {
		WebElement hoverFinanceManagementModule = baseDriver.getDriver().findElement(By.xpath("//body/div/div/div/nav/div/div[1]/ul[1]/li[4]/a[1]"));
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

	public void verifyDepositID() throws FailedLoginException {
		WebElement verifyDepositID_Dropdown = baseDriver.getDriver().findElement(By.xpath("(//i[@class='fa fa-chevron-down'])[1]"));
		String fail = "Verify deposit ID failed";

		if (verifyDepositID_Dropdown.isDisplayed()) {
			verifyDepositID_Dropdown.click();
			createReport.getExtentTest().info("Clicked dropdown");
		} else {
			createReport.getExtentTest().fail(fail);
		}
		System.out.println(function2.dataToCompare().getText());
		WebElement verifyDepositID = baseDriver.getDriver().findElement(By.xpath("//td[normalize-space()='"+ function2.dataToCompare().getText() +"']"));
		if (verifyDepositID.isDisplayed()) {
			createReport.getExtentTest().info("Deposit ID = " + function2.dataToCompare());
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

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