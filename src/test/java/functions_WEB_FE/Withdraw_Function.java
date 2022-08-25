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

public class Withdraw_Function {

	private static Withdraw_Function function = new Withdraw_Function();

	public static Withdraw_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void withdrawOptionFromToolBar() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement withdrawOptionFromToolBar = baseDriver.getDriver().findElement(By.xpath("//div[@class='float_menu_label label_int'][contains(text(),'钱包')]"));
		Actions builder = new Actions(baseDriver.getDriver());
		Action act = builder.moveToElement(withdrawOptionFromToolBar).build();
		act.perform();

		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement withdrawOption = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'取款')]"));
		String withdrawOption_Text = withdrawOption.getText();
		String fail = "Deposit option toolbar failed";

		if (withdrawOption.isDisplayed()) {
			withdrawOption.click();
			createReport.getExtentTest().info("Clicked " + withdrawOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectWithdrawOption() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement withdrawOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='tab active addBankCardBtn swiper-slide swiper-slide-active']"));
		String withdrawOption_Text = withdrawOption.getText();
		String fail = withdrawOption + "Offline deposit option failed";

		if (withdrawOption.isDisplayed()) {
			withdrawOption.click();
			createReport.getExtentTest().info("Clicked " + withdrawOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void fillInWithdrawAmount(String withdrawAmount) throws FailedLoginException, InterruptedException {
		WebElement withdrawAmt = baseDriver.getDriver().findElement(By.id("amount"));
		String withdrawAmt_Text = withdrawAmt.getAttribute("placeholder");
		String fail = withdrawAmount + "Fill in withdraw amount failed";

		if (withdrawAmt.isDisplayed()) {
			withdrawAmt.clear();
			withdrawAmt.sendKeys(withdrawAmount);
			createReport.getExtentTest().info(withdrawAmount + " keyed in " + withdrawAmt_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 
	
	public void fillInPinNumber(String pinNumber) throws FailedLoginException, InterruptedException {
		WebElement pinNum = baseDriver.getDriver().findElement(By.id("withdrawal_pin"));
		String pinNum_Text = pinNum.getAttribute("placeholder");
		String fail = pinNumber + " Fill in pin number failed";

		if (pinNum.isDisplayed()) {
			pinNum.clear();
			pinNum.sendKeys(pinNumber);
			createReport.getExtentTest().info(pinNumber + " keyed in " + pinNum_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}
}