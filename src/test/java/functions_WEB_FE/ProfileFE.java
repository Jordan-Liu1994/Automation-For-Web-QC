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

public class ProfileFE {

	private static ProfileFE profileF = new ProfileFE();

	public static ProfileFE getInstance() {
		return profileF;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();

	WebDriverWait wait;
	private static String attr = "placeholder";
	private static String keyIn = " keyed in ";

	public void hoverUserID(String userID) throws FailedLoginException, InterruptedException {
		String fail = "hoverUserID failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(bDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			Thread.sleep(500);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectProfile() throws FailedLoginException, InterruptedException {
		String fail = "selectProfile failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement selectProfile = bDriver.getDriver().findElement(By.xpath("//a[@class='header_bottom_after_popup_profile_name']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectProfile));
		wait.until(ExpectedConditions.visibilityOf(selectProfile));
		String selectProfileText = selectProfile.getText();

		Thread.sleep(250);
		if (selectProfile.isDisplayed()) {
			selectProfile.click();
			cR.getExtentTest().info("Clicked " + selectProfileText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectAddBankCard() throws FailedLoginException, InterruptedException {
		String fail = "selectAddBankCard failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement selectAddBankCard = bDriver.getDriver().findElement(By.xpath("//div[@data-cta='mybankcard']"));
		wait.until(ExpectedConditions.elementToBeClickable(selectAddBankCard));
		wait.until(ExpectedConditions.visibilityOf(selectAddBankCard));
		String selectAddBankCardText = selectAddBankCard.getText();

		if (selectAddBankCard.isDisplayed()) {
			selectAddBankCard.click();
			cR.getExtentTest().info("Clicked " + selectAddBankCardText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void bankCardOption() throws FailedLoginException, InterruptedException {
		String fail = "bankCardOption failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		WebElement optionBank = bDriver.getDriver().findElement(By.xpath("//*[@id='bankcard_container']/div[1]/div[1]"));
		String optionBankText = optionBank.getText();

		Thread.sleep(500);
		if (optionBank.isDisplayed()) {
			WebElement addBankCardButton = bDriver.getDriver().findElement(By.xpath("//button[@class='btn_major add_bankcard active']"));
			String addBankCardButtonText = addBankCardButton.getText();
			addBankCardButton.click();
			cR.getExtentTest().info("Clicked " + addBankCardButtonText + " in " + optionBankText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void bankOption(String realName) throws FailedLoginException, InterruptedException {
		String fail = "bankOption failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement bankOption = bDriver.getDriver().findElement(By.xpath("//div[@class='tab addBankCardBtn swiper-slide active swiper-slide-active']"));
		wait.until(ExpectedConditions.elementToBeClickable(bankOption));
		wait.until(ExpectedConditions.visibilityOf(bankOption));
		String bankOptionText = bankOption.getText();

		Thread.sleep(500);
		if (!bankOption.isSelected()) {
			bankOption.click();
			cR.getExtentTest().info("Clicked " + bankOptionText);
		} else {
			cR.getExtentTest().info(bankOptionText + " already selected");
		}

		WebElement bankOptionRealName = bDriver.getDriver().findElement(By.id("bank_account_name"));
		String bankOptionRealNameText = bankOptionRealName.getAttribute(attr);

		if (bankOptionRealName.isDisplayed()) {
			bankOptionRealName.clear();
			bankOptionRealName.sendKeys(realName);
			cR.getExtentTest().info(realName + keyIn + bankOptionRealNameText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 
}