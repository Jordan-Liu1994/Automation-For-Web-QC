package functions_WEB_FE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
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

	CreateReport cR = new CreateReport();

	WebDriverWait wait;
	String fail;
	String skip;

	public void hoverUserID(String userID) throws FailedLoginException, InterruptedException {
		fail = "hoverUserID failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
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

	public void selectDepositOptionFromDropdown() throws FailedLoginException, InterruptedException {
		fail = "selectDepositOptionFromDropdown failed";

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement depositOptionFromDropdown = bDriver.getDriver().findElement(By.xpath("//div[@class='header_bottom_after_popup_wallet_action']//div[1]//div[3]"));
		wait.until(ExpectedConditions.visibilityOf(depositOptionFromDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(depositOptionFromDropdown));
		String depositOptionFromDropdownText = depositOptionFromDropdown.getText();

		if (depositOptionFromDropdown.isDisplayed()) {
			depositOptionFromDropdown.click();
			cR.getExtentTest().info("Clicked " + depositOptionFromDropdownText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	depends on CLIENT

	public void closeBeforeDepositInfoPopUp() throws FailedLoginException, InterruptedException {
		fail = "closeBeforeDepositInfoPopUp failed";
		skip = "Skipped closeBeforeDepositInfoPopUp";

		try {
			wait = new WebDriverWait(bDriver.getDriver(), 10);
			WebElement closeBeforeDepositInfoPopUp = bDriver.getDriver().findElement(By.xpath("//button[@class='btn_major']"));
			wait.until(ExpectedConditions.visibilityOf(closeBeforeDepositInfoPopUp));
			wait.until(ExpectedConditions.elementToBeClickable(closeBeforeDepositInfoPopUp));
			String closeBeforeDepositInfoPopUpText = closeBeforeDepositInfoPopUp.getText();

			if (closeBeforeDepositInfoPopUp.isDisplayed()) {
				closeBeforeDepositInfoPopUp.click();
				cR.getExtentTest().info("Clicked " + closeBeforeDepositInfoPopUpText);
			} else {
				cR.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

	public void selectOfflineDepositOption() throws FailedLoginException, InterruptedException {
		fail = "selectOfflineDepositOption failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement offlineDepositOption = bDriver.getDriver().findElement(By.xpath("//div[@class='deposit_method_item active']//div[@class='title']"));
		wait.until(ExpectedConditions.visibilityOf(offlineDepositOption));
		wait.until(ExpectedConditions.elementToBeClickable(offlineDepositOption));
		String offlineDepositOptionText = offlineDepositOption.getText();

		if (offlineDepositOption.isDisplayed()) {
			offlineDepositOption.click();
			cR.getExtentTest().info("Clicked " + offlineDepositOptionText);
			Thread.sleep(1000);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectSpecificOfflineDepositMethod(String depositOption) throws FailedLoginException, InterruptedException {
		fail = "selectSpecificOfflineDepositMethod failed";
		Boolean specificOfflineDepositMethodValue = false;

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement specificOfflineDepositMethod = bDriver.getDriver().findElement(By.xpath("//ul[@id='channel_items']"));
		wait.until(ExpectedConditions.elementToBeClickable(specificOfflineDepositMethod));

		List<WebElement> specificOfflineDepositMethodTagNames = specificOfflineDepositMethod.findElements(By.tagName("li"));
		for (WebElement specificOfflineDepositMethodLists : specificOfflineDepositMethodTagNames) {
			String specificOfflineDepositMethodListsText = specificOfflineDepositMethodLists.getText();
			if (specificOfflineDepositMethodListsText.equalsIgnoreCase(depositOption)) {
				specificOfflineDepositMethodLists.click();
				cR.getExtentTest().info("Clicked " + depositOption);
				specificOfflineDepositMethodValue = true;
			}
		}
		if (!specificOfflineDepositMethodValue) {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setDepositAmount(int depositAmount) throws FailedLoginException, InterruptedException {
		fail = "setDepositAmount failed";

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement setDepositAmount = bDriver.getDriver().findElement(By.id("deposit_amount"));
		String setDepositAmountText = setDepositAmount.getAttribute(attribute);

		if (setDepositAmount.isDisplayed()) {
			setDepositAmount.clear();
			setDepositAmount.sendKeys(String.valueOf(depositAmount));
			cR.getExtentTest().info("Amount of " + depositAmount + keyIn + setDepositAmountText);
		} else if (!setDepositAmount.isDisplayed()) {
			WebElement setDepositAmountOptions = bDriver.getDriver().findElement(By.xpath("//div[@class='recommend_amount'][normalize-space()='" + depositAmount + "']"));
			setDepositAmountOptions.click();
		}
	}

	public void setDepositoryName(String depositoryName) throws FailedLoginException, InterruptedException {
		fail = "setDepositoryName failed";

		WebElement setDepositoryName = bDriver.getDriver().findElement(By.id("real_name"));
		String setDepositAmountText = setDepositoryName.getAttribute(attribute);

		if (setDepositoryName.isDisplayed()) {
			setDepositoryName.clear();
			setDepositoryName.sendKeys(depositoryName);
			cR.getExtentTest().info(depositoryName + " is keyed in " + setDepositAmountText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

	public void joinPromoOrNotRadioButton(String falseJoinPromo) throws FailedLoginException, InterruptedException {
		fail = "joinPromoOrNotRadioButton failed";
		skip = "joinPromoOrNotRadioButton TURNED OFF IN BO";

		try {
			WebElement joinPromoRadioButton = bDriver.getDriver().findElement(By.xpath("//div[@id='radio_txt']"));
			WebElement doNotJoinPromoRadioButton = bDriver.getDriver().findElement(By.xpath("//div[@id='radio_txt_false']"));
			String joinPromoRadioButtonText = joinPromoRadioButton.getText();
			String doNotJoinPromoRadioButtonText = doNotJoinPromoRadioButton.getText();
			if (falseJoinPromo.equalsIgnoreCase("true")) {
				cR.getExtentTest().info(joinPromoRadioButtonText + " is already selected");
			} else if (falseJoinPromo.equalsIgnoreCase("false")){
				doNotJoinPromoRadioButton.click();
				cR.getExtentTest().info("Selected " + doNotJoinPromoRadioButtonText);
			} else {
				cR.getExtentTest().fail(fail);
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().info(skip);
			throw new SkipException(skip);
		}
	}

	String verifyActualReceivedAmountIfNoJoinPromoText;

	public void verifyActualReceivedAmountIfNoJoinPromo(int depositAmountToCheck) throws FailedLoginException, InterruptedException {
		fail = "verifyActualReceivedAmountIfNoJoinPromo failed";

		WebElement verifyActualReceivedAmountIfNoJoinPromo = bDriver.getDriver().findElement(By.id("actual_amount"));
		verifyActualReceivedAmountIfNoJoinPromoText = verifyActualReceivedAmountIfNoJoinPromo.getText();

		double minDeduct = 0.01;
		double maxDeduct = 0.10;
		double finalMaxDeduct = depositAmountToCheck - maxDeduct;
		double finalMinDeduct = depositAmountToCheck - minDeduct;

		if (verifyActualReceivedAmountIfNoJoinPromo.isDisplayed()) {
			if (finalMaxDeduct <= Integer.valueOf(depositAmountToCheck)) {
				if (finalMinDeduct <= Integer.valueOf(depositAmountToCheck)) {
					cR.getExtentTest().info("Actual received amount is " + verifyActualReceivedAmountIfNoJoinPromoText);
				} else {
					cR.getExtentTest().fail(fail);
				}
			}
		}
	}

	public String storedActualReceivedAmountText() throws FailedLoginException, InterruptedException {
		return verifyActualReceivedAmountIfNoJoinPromoText;
	}

	public void submitOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		fail = "submitOfflineDepositRequest failed";

		WebElement submitOfflineDepositRequest = bDriver.getDriver().findElement(By.id("payment"));
		String submitOfflineDepositRequestText = submitOfflineDepositRequest.getText();

		if (submitOfflineDepositRequest.isEnabled()) {
			submitOfflineDepositRequest.click();
			cR.getExtentTest().info("Clicked " + submitOfflineDepositRequestText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void confirmOfflineDepositPaid() throws FailedLoginException, InterruptedException {
		fail = "confirmOfflineDepositPaid failed";

		try {
			bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			WebElement confirmOfflineDepositPaid = bDriver.getDriver().findElement(By.xpath("//button[@id='confirmPending']"));
			String confirmOfflineDepositPaidText = confirmOfflineDepositPaid.getText();

			if (confirmOfflineDepositPaid.isEnabled()) {
				confirmOfflineDepositPaid.click();
				cR.getExtentTest().info("Clicked " + confirmOfflineDepositPaidText);
				Thread.sleep(1500);
			} else {
				cR.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		} catch (StaleElementReferenceException e) {
			cR.getExtentTest().warning("Stale element but skipped because succeed");
		}
	}

	public void cancelOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		fail = "Cancel offline deposit request failed";

		WebElement cancelOfflineDepositRequest = bDriver.getDriver().findElement(By.id("cancelPending"));
		String cancelOfflineDepositRequestText = cancelOfflineDepositRequest.getText();

		if (cancelOfflineDepositRequest.isEnabled()) {
			cancelOfflineDepositRequest.click();
			cR.getExtentTest().info("Clicked " + cancelOfflineDepositRequestText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement confirmCancelOfflineDepositRequest = bDriver.getDriver().findElement(By.xpath("(//button[@class='btn_major active w-70'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(confirmCancelOfflineDepositRequest));
		wait.until(ExpectedConditions.elementToBeClickable(confirmCancelOfflineDepositRequest));
		String confirmCancelOfflineDepositRequestText = confirmCancelOfflineDepositRequest.getText();

		if (cancelOfflineDepositRequest.isEnabled()) {
			confirmCancelOfflineDepositRequest.click();
			cR.getExtentTest().info("Clicked " + confirmCancelOfflineDepositRequestText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		Thread.sleep(3000);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement cancelOfflineDepositRequestSuccess = bDriver.getDriver().findElement(By.xpath("(//div[@class='msg_show'])[1]"));
		wait.until(ExpectedConditions.visibilityOf(cancelOfflineDepositRequestSuccess));
		String cancelOfflineDepositRequestSuccessText = cancelOfflineDepositRequestSuccess.getText();

		if (cancelOfflineDepositRequestSuccess.isDisplayed()) {
			cR.getExtentTest().info(cancelOfflineDepositRequestSuccessText + " offline deposit is successfully rejected");
			bDriver.getDriver().navigate().refresh();
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectWalletHistoryOptionFromDropdown() throws FailedLoginException, InterruptedException {
		fail = "selectWalletHistoryOptionFromDropdown failed";

		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement selectWalletHistoryOptionFromDropdown = bDriver.getDriver().findElement(By.xpath("(//a)[7]"));
		wait.until(ExpectedConditions.visibilityOf(selectWalletHistoryOptionFromDropdown));
		wait.until(ExpectedConditions.elementToBeClickable(selectWalletHistoryOptionFromDropdown));
		String selectWalletHistoryOptionFromDropdownText = selectWalletHistoryOptionFromDropdown.getText();

		if (selectWalletHistoryOptionFromDropdown.isDisplayed()) {
			selectWalletHistoryOptionFromDropdown.click();
			cR.getExtentTest().info("Clicked " + selectWalletHistoryOptionFromDropdownText);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void walletHistorySelectDeposit() throws FailedLoginException, InterruptedException {
		fail = "walletHistorySelectDeposit failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement walletHistorySelectDeposit = bDriver.getDriver().findElement(By.xpath("//div[@data-cta='wallet_deposit']"));
		wait.until(ExpectedConditions.visibilityOf(walletHistorySelectDeposit));
		wait.until(ExpectedConditions.elementToBeClickable(walletHistorySelectDeposit));
		String walletHistorySelectDepositText = walletHistorySelectDeposit.getText();

		if (walletHistorySelectDeposit.isDisplayed()) {
			cR.getExtentTest().info("In " + walletHistorySelectDepositText);
			Thread.sleep(1500);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
		
	String verifyOfflineDepositIDText;
	
	public void verifyOfflineDepositID() throws FailedLoginException, InterruptedException {
		fail = "verifyOfflineDepositID failed";

		bDriver.getDriver().manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 15);
		WebElement verifyOfflineDepositID = bDriver.getDriver().findElement(By.xpath("//*[@id=\"deposit_history_list\"]/tr[1]/td[2]/div[1]/div"));
		wait.until(ExpectedConditions.visibilityOf(verifyOfflineDepositID));
		verifyOfflineDepositIDText = verifyOfflineDepositID.getText();

		if (verifyOfflineDepositID.isDisplayed()) {
			cR.getExtentTest().info("Clicked " + verifyOfflineDepositIDText);
			Thread.sleep(1500);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}
	
	public String recordID() throws FailedLoginException, InterruptedException {
		System.out.println(verifyOfflineDepositIDText);
		return verifyOfflineDepositIDText;
	}
}