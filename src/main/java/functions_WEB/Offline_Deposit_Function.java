package functions_WEB;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import utilities.Base_Driver;
import utilities.Create_Report;

public class Offline_Deposit_Function {

	private static Offline_Deposit_Function function = new Offline_Deposit_Function();

	public static Offline_Deposit_Function getInstance() {
		return function;
	}

	Base_Driver baseDriver = Base_Driver.getInstance();
	Create_Report createReport = Create_Report.getInstance();

	public void hoverUserID(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = baseDriver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		String fail = "Hover over " + userID + " failed";

		if (userIDName.isDisplayed()) {
			Actions builder = new Actions(baseDriver.getDriver());
			Action act = builder.moveToElement(userIDName).build();
			act.perform();
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void selectDepositOption() throws FailedLoginException, InterruptedException {
		WebElement depositOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='header_bottom_after_popup_wallet_action']//div[1]//div[3]"));
		String depositOption_Text = depositOption.getText();
		String fail = "Deposit option failed";

		if (depositOption.isDisplayed()) {
			depositOption.click();
			Thread.sleep(1000);
			baseDriver.getDriver().navigate().refresh();
			createReport.getExtentTest().info("Clicked " + depositOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void depositOptionFromToolBar() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement depositOptionFromToolBar = baseDriver.getDriver().findElement(By.xpath("//div[@class='float_menu_label label_int'][contains(text(),'钱包')]"));
		Actions builder = new Actions(baseDriver.getDriver());
		Action act = builder.moveToElement(depositOptionFromToolBar).build();
		act.perform();

		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement depositOption = baseDriver.getDriver().findElement(By.xpath("//button[@class='btn btn_white btn_style'][contains(text(),'存款')]"));
		String depositOption_Text = depositOption.getText();
		String fail = "Deposit option toolbar failed";

		if (depositOption.isDisplayed()) {
			depositOption.click();
			createReport.getExtentTest().info("Clicked " + depositOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectOfflineDepositOption(String offlineDepositName) throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement offlineDepositOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='title'][contains(text(),'" + offlineDepositName + "')]"));
		String offlineDepositOption_Text = offlineDepositOption.getText();
		String fail = offlineDepositName + "Offline deposit option failed";

		if (offlineDepositOption.isDisplayed()) {
			offlineDepositOption.click();
			createReport.getExtentTest().info("Clicked " + offlineDepositOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void selectAnyDepositOption(String depositOption) throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement depositOptions = baseDriver.getDriver().findElement(By.id("channel_items"));
		Boolean depositOptionsValue = false;
		String fail = depositOption + " offline deposit option failed";

		List<WebElement> depositOptions_Tag = depositOptions.findElements(By.tagName("li"));
		for (WebElement depositOptions_Lists : depositOptions_Tag) {
			String depositOptions_Lists_Text = depositOptions_Lists.getText();
			System.out.println(depositOptions_Lists_Text);
			if (depositOptions_Lists_Text.equalsIgnoreCase(depositOption)) {
				depositOptions_Lists.click();
				createReport.getExtentTest().info("Clicked " + depositOption);
				depositOptionsValue = true;
			}
		}
		if (!depositOptionsValue) {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void setDepositAmount(int depositAmount) throws FailedLoginException, InterruptedException {
		WebElement setDepositAmount = baseDriver.getDriver().findElement(By.id("deposit_amount"));
		String setDepositAmount_Text = setDepositAmount.getAttribute("placeholder");
		String fail = "Set deposit amount failed";

		if (setDepositAmount.isDisplayed()) {
			setDepositAmount.clear();
			setDepositAmount.sendKeys(String.valueOf(depositAmount));
			createReport.getExtentTest().info(depositAmount + " is keyed in " + setDepositAmount_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void setDepositoryName(String depositoryName) throws FailedLoginException, InterruptedException {
		WebElement setDepositoryName = baseDriver.getDriver().findElement(By.id("real_name"));
		String setDepositAmount_Text = setDepositoryName.getAttribute("placeholder");
		String fail = "Set depository name failed";

		if (setDepositoryName.isDisplayed()) {
			setDepositoryName.clear();
			setDepositoryName.sendKeys(depositoryName);
			createReport.getExtentTest().info(depositoryName + " is keyed in " + setDepositAmount_Text);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void checkRememberDepositoryName() throws FailedLoginException, InterruptedException {
		WebElement rememberDepositoryName = baseDriver.getDriver().findElement(By.xpath("//div[contains(text(),'记住我的汇款人姓名')]"));
		String rememberDepositoryName_Text = rememberDepositoryName.getText();
		String fail = "Check remember depository name failed";

		if (!rememberDepositoryName.isSelected()) {
			rememberDepositoryName.click();
			createReport.getExtentTest().info(rememberDepositoryName_Text + " is selected");
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

	public void checkActualReceivedAmount(int depositAmountToCheck) throws FailedLoginException, InterruptedException {
		WebElement actualReceivedAmount = baseDriver.getDriver().findElement(By.id("actual_amount"));
		String actualReceivedAmount_Text = actualReceivedAmount.getText();
		String fail = "Actual received amount failed";

		double minDeduct = 0.01;
		double maxDeduct = 0.10;
		double finalDeductMax = depositAmountToCheck - maxDeduct;
		double finalDeductMin = depositAmountToCheck - minDeduct;

		if (actualReceivedAmount.isDisplayed()) {
			createReport.getExtentTest().info("Actual received amount is " + actualReceivedAmount_Text);
			System.out.println(actualReceivedAmount_Text);
			if (finalDeductMax <= Integer.valueOf(depositAmountToCheck)) {
				if (finalDeductMin <= Integer.valueOf(depositAmountToCheck)) {
					createReport.getExtentTest().info("Amount is between " + finalDeductMax + " - " + finalDeductMin);
				} else {
					createReport.getExtentTest().fail(fail);
					throw new FailedLoginException(fail);
				}
			}
		}
	}

	public void submitOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		WebElement submitOfflineDepositRequest = baseDriver.getDriver().findElement(By.id("payment"));
		String submitOfflineDepositRequest_Text = submitOfflineDepositRequest.getText();
		String fail = "Submit offline deposit request failed";

		if (submitOfflineDepositRequest.isEnabled()) {
			submitOfflineDepositRequest.click();
			createReport.getExtentTest().info("Clicked " + submitOfflineDepositRequest_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void confirmOfflineDepositPaid() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement confirmOfflineDepositPaid = baseDriver.getDriver().findElement(By.id("confirmPending"));
		String confirmOfflineDepositPaid_Text = confirmOfflineDepositPaid.getText();
		String fail = "Confirm offline deposit paid failed";

		if (confirmOfflineDepositPaid.isEnabled()) {
			confirmOfflineDepositPaid.click();
			createReport.getExtentTest().info("Clicked " + confirmOfflineDepositPaid_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void cancelOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		WebElement cancelOfflineDepositRequest = baseDriver.getDriver().findElement(By.xpath("(//a[@id='cancelPending'])[1]"));
		WebElement cancelOfflineDepositRequest = baseDriver.getDriver().findElement(By.id("cancelPending"));
		String cancelOfflineDepositRequest_Text = cancelOfflineDepositRequest.getText();
		String fail = "Cancel offline deposit request failed";

		if (cancelOfflineDepositRequest.isEnabled()) {
			cancelOfflineDepositRequest.click();
			createReport.getExtentTest().info("Clicked " + cancelOfflineDepositRequest_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement confirmCancelOfflineDepositRequest = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'确定')]"));
		String confirmCancelOfflineDepositRequest_Text = confirmCancelOfflineDepositRequest.getText();

		if (cancelOfflineDepositRequest.isEnabled()) {
			confirmCancelOfflineDepositRequest.click();
			createReport.getExtentTest().info("Clicked " + confirmCancelOfflineDepositRequest_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement cancelOfflineDepositRequestSuccess = baseDriver.getDriver().findElement(By.xpath("//div[@class='msg_show'][contains(text(),'取消成功')]"));
		String cancelOfflineDepositRequestSuccess_Text = cancelOfflineDepositRequestSuccess.getText();

		if (cancelOfflineDepositRequestSuccess.isDisplayed()) {
			createReport.getExtentTest().info(cancelOfflineDepositRequestSuccess_Text + " offline deposit is successful");
			baseDriver.getDriver().navigate().refresh();
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void VerifyActualOfflineDepositAmount() throws FailedLoginException, InterruptedException {
		WebElement confirmOfflineDepositPaid = baseDriver.getDriver().findElement(By.id("confirmPending"));
		String confirmOfflineDepositPaid_Text = confirmOfflineDepositPaid.getText();
		String fail = "Confirm offline deposit paid failed";

		if (confirmOfflineDepositPaid.isEnabled()) {
			confirmOfflineDepositPaid.click();
			createReport.getExtentTest().info(confirmOfflineDepositPaid_Text + " is clicked");
			baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}