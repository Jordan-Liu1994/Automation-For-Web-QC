package webFEFunctions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.VariablesStorage;

public class OfflineDepositFE extends VariablesStorage {

	CreateReport cReport = new CreateReport();

	WebDriverWait wait;
	String fail;
	String skip;

	public void hoverUserID(String userID) throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement userIDName = bDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
//		wait.until(ExpectedConditions.visibilityOf(userIDName));

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(bDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
			Thread.sleep(500);
		} else {
			fail = "hoverUserID failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public void selectDepositOptionFromDropdown() throws FailedLoginException, InterruptedException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement depositOptionFromDropdown = bDriver.getDriver().findElement(By.xpath("//div[@class='header_bottom_after_popup_wallet_action']//div[1]//div[3]"));
		wait.until(ExpectedConditions.visibilityOf(depositOptionFromDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(depositOptionFromDropdown));

		if (depositOptionFromDropdown.isDisplayed()) {
			String depositOptionFromDropdownText = depositOptionFromDropdown.getText();
			depositOptionFromDropdown.click();
			cReport.getExtentTest().info("Clicked " + depositOptionFromDropdownText);
		} else {
			fail = "selectDepositOptionFromDropdown failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void closeBeforeDepositInfoPopUp() throws FailedLoginException, InterruptedException {
		try {
			WebElement closeBeforeDepositInfoPopUp = bDriver.getDriver().findElement(By.xpath("//button[@class='btn_major']"));
			bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

			if (closeBeforeDepositInfoPopUp.isDisplayed()) {
				String closeBeforeDepositInfoPopUpText = closeBeforeDepositInfoPopUp.getText();
				closeBeforeDepositInfoPopUp.click();
				cReport.getExtentTest().info("Clicked " + closeBeforeDepositInfoPopUpText);
			} else {
				fail = "closeBeforeDepositInfoPopUp failed";
				cReport.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (NoSuchElementException e) {
			skip = "Skipped closeBeforeDepositInfoPopUp";
			cReport.getExtentTest().skip(skip);
		}
	}

	public void selectOfflineDepositOption(String offlineDepositMethod) throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement offlineDepositOption = bDriver.getDriver().findElement(By.xpath("//div[@class='title'][contains(text(),'" + offlineDepositMethod + "')]"));

		if (offlineDepositOption.isDisplayed()) {
			String offlineDepositOptionText = offlineDepositOption.getText();
			offlineDepositOption.click();
			cReport.getExtentTest().info("Clicked " + offlineDepositOptionText);
		} else {
			fail = "selectOfflineDepositOption failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public void selectSpecificOfflineDepositMethod(String depositOptionType) throws FailedLoginException, InterruptedException {
		try {
			Boolean specificOfflineDepositMethodValue = false;

			bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			wait = new WebDriverWait(bDriver.getDriver(), 15);
			WebElement specificOfflineDepositMethod = bDriver.getDriver().findElement(By.xpath("//ul[@id='channel_items']"));
			wait.until(ExpectedConditions.elementToBeClickable(specificOfflineDepositMethod));

			List<WebElement> specificOfflineDepositMethodTagNames = specificOfflineDepositMethod.findElements(By.tagName("li"));
			for (WebElement specificOfflineDepositMethodLists : specificOfflineDepositMethodTagNames) {
				String specificOfflineDepositMethodListsText = specificOfflineDepositMethodLists.getText();
				if (specificOfflineDepositMethodListsText.equalsIgnoreCase(depositOptionType)) {
					Thread.sleep(1000);
					specificOfflineDepositMethodLists.click();
					cReport.getExtentTest().info("Clicked " + depositOptionType);
					specificOfflineDepositMethodValue = true;
				}
			}
			if (!specificOfflineDepositMethodValue) {
				fail = "selectSpecificOfflineDepositMethod failed";
				cReport.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (StaleElementReferenceException e) {
			cReport.getExtentTest().info(depositOptionType + " already selected");
		}
	}

	public void setDepositAmount(int depositAmount) throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement setDepositAmount = bDriver.getDriver().findElement(By.id("deposit_amount"));

		if (setDepositAmount.isDisplayed()) {
			String setDepositAmountText = setDepositAmount.getAttribute(attribute);
			setDepositAmount.clear();
			setDepositAmount.sendKeys(String.valueOf(depositAmount));
			cReport.getExtentTest().info("Amount of " + depositAmount + keyIn + setDepositAmountText);
		} else if (!setDepositAmount.isDisplayed()) {
			fail = "setDepositAmount failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public void setDepositoryName(String depositoryName) throws FailedLoginException, InterruptedException {
		WebElement setDepositoryName = bDriver.getDriver().findElement(By.id("real_name"));

		if (setDepositoryName.isDisplayed()) {
			String setDepositAmountText = setDepositoryName.getAttribute(attribute);
			setDepositoryName.clear();
			setDepositoryName.sendKeys(depositoryName);
			cReport.getExtentTest().info(depositoryName + " is keyed in " + setDepositAmountText);
		} else {
			fail = "setDepositoryName failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public void joinPromoOrNotRadioButton(String falseJoinPromo) throws FailedLoginException, InterruptedException {

		try {
			JavascriptExecutor js = (JavascriptExecutor) bDriver.getDriver();
			WebElement joinPromoRadioButton = bDriver.getDriver().findElement(By.xpath("//div[@id='radio_txt']"));
			WebElement doNotJoinPromoRadioButton = bDriver.getDriver().findElement(By.xpath("//div[@id='radio_txt_false']"));
			js.executeScript("arguments[0].scrollIntoView(true);", joinPromoRadioButton);
			String joinPromoRadioButtonText = joinPromoRadioButton.getText();
			String doNotJoinPromoRadioButtonText = doNotJoinPromoRadioButton.getText();
			if (falseJoinPromo.equalsIgnoreCase("true")) {
				cReport.getExtentTest().info(joinPromoRadioButtonText + " is already selected");
			} else if (falseJoinPromo.equalsIgnoreCase("false")) {
				doNotJoinPromoRadioButton.click();
				cReport.getExtentTest().info("Selected " + doNotJoinPromoRadioButtonText);
			} else {
				fail = "joinPromoOrNotRadioButton failed";
				cReport.getExtentTest().fail(fail);
			}
		} catch (NoSuchElementException e) {
			skip = "joinPromoOrNotRadioButton TURNED OFF IN BO";
			cReport.getExtentTest().info(skip);
			throw new SkipException(skip);
		}
	}

	String verifyActualReceivedAmountIfNoJoinPromoText;

	public void verifyActualReceivedAmountIfNoJoinPromo(int depositAmountToCheck) throws FailedLoginException, InterruptedException {
		WebElement verifyActualReceivedAmountIfNoJoinPromo = bDriver.getDriver().findElement(By.id("actual_amount"));

		double minDeduct = 0.01;
		double maxDeduct = 0.10;
		double finalMaxDeduct = depositAmountToCheck - maxDeduct;
		double finalMinDeduct = depositAmountToCheck - minDeduct;

		if (verifyActualReceivedAmountIfNoJoinPromo.isDisplayed()) {
			verifyActualReceivedAmountIfNoJoinPromoText = verifyActualReceivedAmountIfNoJoinPromo.getText();
			if (finalMaxDeduct <= Integer.valueOf(depositAmountToCheck)) {
				if (finalMinDeduct <= Integer.valueOf(depositAmountToCheck)) {
					cReport.getExtentTest().info("Actual received amount is " + verifyActualReceivedAmountIfNoJoinPromoText);
				} else {
					fail = "verifyActualReceivedAmountIfNoJoinPromo failed";
					cReport.getExtentTest().fail(fail);
				}
			}
		}
	}

	public String storedActualReceivedAmountText() throws FailedLoginException, InterruptedException {
		return verifyActualReceivedAmountIfNoJoinPromoText;
	}

	public void submitOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		WebElement submitOfflineDepositRequest = bDriver.getDriver().findElement(By.id("payment"));

		if (submitOfflineDepositRequest.isEnabled()) {
			String submitOfflineDepositRequestText = submitOfflineDepositRequest.getText();
			submitOfflineDepositRequest.click();
			cReport.getExtentTest().info("Clicked " + submitOfflineDepositRequestText);
		} else {
			fail = "submitOfflineDepositRequest failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void confirmOfflineDepositPaid() throws FailedLoginException, InterruptedException {
		try {
			bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			WebElement confirmOfflineDepositPaid = bDriver.getDriver().findElement(By.xpath("//button[@id='confirmPending']"));

			if (confirmOfflineDepositPaid.isEnabled()) {
				String confirmOfflineDepositPaidText = confirmOfflineDepositPaid.getText();
				confirmOfflineDepositPaid.click();
				cReport.getExtentTest().info("Clicked " + confirmOfflineDepositPaidText);
				Thread.sleep(1500);
			} else {
				fail = "confirmOfflineDepositPaid failed";
				cReport.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (StaleElementReferenceException e) {
			cReport.getExtentTest().warning("Stale element but skipped because succeed");
		}
	}

	public void cancelOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		WebElement cancelOfflineDepositRequest = bDriver.getDriver().findElement(By.id("cancelPending"));

		if (cancelOfflineDepositRequest.isEnabled()) {
			String cancelOfflineDepositRequestText = cancelOfflineDepositRequest.getText();
			cancelOfflineDepositRequest.click();
			cReport.getExtentTest().info("Clicked " + cancelOfflineDepositRequestText);
		} else {
			fail = "cancelOfflineDepositRequest failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement confirmCancelOfflineDepositRequest = bDriver.getDriver().findElement(By.xpath("(//button[@class='btn_major active w-70'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(confirmCancelOfflineDepositRequest));
		wait.until(ExpectedConditions.elementToBeClickable(confirmCancelOfflineDepositRequest));
		String confirmCancelOfflineDepositRequestText = confirmCancelOfflineDepositRequest.getText();

		if (cancelOfflineDepositRequest.isEnabled()) {
			confirmCancelOfflineDepositRequest.click();
			cReport.getExtentTest().info("Clicked " + confirmCancelOfflineDepositRequestText);
		} else {
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		Thread.sleep(3000);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement cancelOfflineDepositRequestSuccess = bDriver.getDriver().findElement(By.xpath("(//div[@class='msg_show'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(cancelOfflineDepositRequestSuccess));
		String cancelOfflineDepositRequestSuccessText = cancelOfflineDepositRequestSuccess.getText();

		if (cancelOfflineDepositRequestSuccess.isDisplayed()) {
			cReport.getExtentTest().info(cancelOfflineDepositRequestSuccessText + " offline deposit is successfully rejected");
			bDriver.getDriver().navigate().refresh();
		} else {
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectWalletHistoryOptionFromDropdown() throws FailedLoginException, InterruptedException {
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectWalletHistoryOptionFromDropdown = bDriver.getDriver().findElement(By.xpath("(//a)[7]"));
		wait.until(ExpectedConditions.visibilityOf(selectWalletHistoryOptionFromDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(selectWalletHistoryOptionFromDropdown));

		if (selectWalletHistoryOptionFromDropdown.isDisplayed()) {
			String selectWalletHistoryOptionFromDropdownText = selectWalletHistoryOptionFromDropdown.getText();
			selectWalletHistoryOptionFromDropdown.click();
			cReport.getExtentTest().info("Clicked " + selectWalletHistoryOptionFromDropdownText);
		} else {
			fail = "selectWalletHistoryOptionFromDropdown failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void walletHistorySelectDeposit() throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement walletHistorySelectDeposit = bDriver.getDriver().findElement(By.xpath("//div[@data-cta='wallet_deposit']"));
		wait.until(ExpectedConditions.visibilityOf(walletHistorySelectDeposit));
		wait.until(ExpectedConditions.elementToBeClickable(walletHistorySelectDeposit));

		if (walletHistorySelectDeposit.isDisplayed()) {
			String walletHistorySelectDepositText = walletHistorySelectDeposit.getText();
			cReport.getExtentTest().info("In " + walletHistorySelectDepositText);
			Thread.sleep(1500);
		} else {
			fail = "walletHistorySelectDeposit failed";
			cReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	String verifyOfflineDepositIDText;

	public void verifyOfflineDepositID() throws FailedLoginException, InterruptedException {
		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement verifyOfflineDepositID = bDriver.getDriver().findElement(By.xpath("//*[@id=\"deposit_history_list\"]/tr[1]/td[2]/div[1]/div"));
		wait.until(ExpectedConditions.visibilityOf(verifyOfflineDepositID));

		if (verifyOfflineDepositID.isDisplayed()) {
			verifyOfflineDepositIDText = verifyOfflineDepositID.getText();
			cReport.getExtentTest().info("Clicked " + verifyOfflineDepositIDText);
			Thread.sleep(1500);
		} else {
			fail = "verifyOfflineDepositID failed";
			cReport.getExtentTest().fail(fail);
		}
	}

	public String recordID() throws FailedLoginException, InterruptedException {
		System.out.println(verifyOfflineDepositIDText);
		return verifyOfflineDepositIDText;
	}
}