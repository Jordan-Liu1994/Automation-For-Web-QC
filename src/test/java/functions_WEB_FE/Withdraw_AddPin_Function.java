package functions_WEB_FE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Withdraw_AddPin_Function {

	private static Withdraw_AddPin_Function function = new Withdraw_AddPin_Function();

	public static Withdraw_AddPin_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void addPinFromWithdrawPage() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement addPinFromWithdrawPage = baseDriver.getDriver().findElement(By.xpath("//a[@class='add_withdrawal_pin set_withdrawal_pin']//div[1]"));
		String addPinFromWithdrawPage_Text = addPinFromWithdrawPage.getText();
		String fail = "Add pin from withdraw page failed";

		if (addPinFromWithdrawPage.isDisplayed()) {
			addPinFromWithdrawPage.click();
			createReport.getExtentTest().info("Clicked " + addPinFromWithdrawPage_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void addPin(String pinNumber) throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement addPin = baseDriver.getDriver().findElement(By.id("new_pin"));
		wait.until(ExpectedConditions.visibilityOf(addPin));
		String addPin_Text = addPin.getAttribute("placeholder");
		String fail = "Add pin failed";

		if (addPin.isDisplayed()) {
			addPin.sendKeys(pinNumber);
			createReport.getExtentTest().info(pinNumber + " keyed in " + addPin_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}

		WebElement addPin_Same = baseDriver.getDriver().findElement(By.id("re_pin"));
		String addPin_Same_Text = addPin_Same.getAttribute("placeholder");

		if (addPin_Same.isDisplayed()) {
			addPin_Same.sendKeys(pinNumber);
			createReport.getExtentTest().info(pinNumber + " keyed in " + addPin_Same_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}

		Thread.sleep(1000);
		WebElement addPin_Save = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'保存')]"));
		String addPin_Save_Text = addPin_Save.getText();

		if (addPin_Save.isEnabled()) {
			addPin_Save.click();
			createReport.getExtentTest().info("Clicked " + addPin_Save_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}
}