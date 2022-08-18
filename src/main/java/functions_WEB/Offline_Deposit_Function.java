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

	Base_Driver base_Driver = Base_Driver.getInstance();
	Create_Report create_Report = Create_Report.getInstance();

	public void ClickDepositOption(String userID) throws FailedLoginException, InterruptedException {
		WebElement userIDName = base_Driver.getDriver().findElement(By.xpath("(//a[contains(text(),'" + userID + "')])[1]"));
		Actions builder = new Actions(base_Driver.getDriver());
		Action act = builder.moveToElement(userIDName).build();
		act.perform();
		Thread.sleep(500);

		WebElement depositOption = base_Driver.getDriver().findElement(By.xpath("//div[@class='header_bottom_after_popup_wallet_action']//div[1]//div[3]"));
		String depositOption_Text = depositOption.getText();
		String fail = "Deposit option failed";

		if (depositOption.isDisplayed()) {
			depositOption.click();
			Thread.sleep(1000);
			base_Driver.getDriver().navigate().refresh();
			create_Report.getExtentTest().info("Clicked " + depositOption_Text);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ClickDepositOptionFromToolBar() throws FailedLoginException, InterruptedException {
		base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement depositOptionFromToolBar = base_Driver.getDriver().findElement(By.xpath("//div[@class='float_menu_label label_int'][contains(text(),'钱包')]"));
		Actions builder = new Actions(base_Driver.getDriver());
		Action act = builder.moveToElement(depositOptionFromToolBar).build();
		act.perform();
		Thread.sleep(500);

		WebElement depositOption = base_Driver.getDriver().findElement(By.xpath("//button[@class='btn btn_white btn_style'][contains(text(),'存款')]"));
		String depositOption_Text = depositOption.getText();
		String fail = "Deposit option toolbar failed";

		if (depositOption.isDisplayed()) {
			depositOption.click();
			create_Report.getExtentTest().info("Clicked " + depositOption_Text);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SelectOfflineDepositOption(String offlineDepositName) throws FailedLoginException, InterruptedException {
		base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement offlineDepositOption = base_Driver.getDriver().findElement(By.xpath("//div[@class='title'][contains(text(),'" + offlineDepositName + "')]"));
		String offlineDepositOption_Text = offlineDepositOption.getText();
		String fail = "Offline deposit option failed";

		if (offlineDepositOption.isDisplayed()) {
			offlineDepositOption.click();
			create_Report.getExtentTest().info("Clicked " + offlineDepositOption_Text);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SelectAnyDepositOption(String DpstOption) throws FailedLoginException, InterruptedException {
		WebElement depositOptions = base_Driver.getDriver().findElement(By.id("channel_items"));
		Boolean depositOptionsValue = false;
		String fail = DpstOption + " offline deposit option failed";

		List<WebElement> depositOptions_Tag = depositOptions.findElements(By.tagName("li"));
		for (WebElement depositOptions_Lists : depositOptions_Tag) {
			String depositOptions_Lists_Text = depositOptions_Lists.getText();
			System.out.println(depositOptions_Lists_Text);
			if (depositOptions_Lists_Text.equalsIgnoreCase(DpstOption)) {
				depositOptions_Lists.click();
				create_Report.getExtentTest().info("Clicked " + DpstOption);
				depositOptionsValue = true;
			}
		}
		if (!depositOptionsValue) {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetDepositAmount(int depositAmount) throws FailedLoginException, InterruptedException {
		WebElement setDepositAmount = base_Driver.getDriver().findElement(By.id("deposit_amount"));
		String setDepositAmount_Text = setDepositAmount.getAttribute("placeholder");
		String fail = "Set deposit amount failed";

		if (setDepositAmount.isEnabled()) {
			setDepositAmount.clear();
			setDepositAmount.sendKeys(String.valueOf(depositAmount));
			create_Report.getExtentTest().info(depositAmount + " is entered in " + setDepositAmount_Text + " field");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void SetDepositoryName(String depositoryName) throws FailedLoginException, InterruptedException {
		WebElement setDepositoryName = base_Driver.getDriver().findElement(By.id("real_name"));
		String setDepositAmount_Text = setDepositoryName.getAttribute("placeholder");
		String fail = "Set depository name failed";

		if (setDepositoryName.isEnabled()) {
			setDepositoryName.clear();
			setDepositoryName.sendKeys(depositoryName);
			create_Report.getExtentTest().info(depositoryName + " is entered in " + setDepositAmount_Text + " field");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void CheckRememberDepositoryName() throws FailedLoginException, InterruptedException {
		WebElement rememberDepositoryName = base_Driver.getDriver().findElement(By.xpath("//div[contains(text(),'记住我的汇款人姓名')]"));
		String rememberDepositoryName_Text = rememberDepositoryName.getText();
		String fail = "Check remember depository name failed";

		if (!rememberDepositoryName.isSelected()) {
			rememberDepositoryName.click();
			create_Report.getExtentTest().info(rememberDepositoryName_Text + " is selected");
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void CheckActualReceivedAmount(int depositAmountToCheck) throws FailedLoginException, InterruptedException {
		WebElement actualReceivedAmount = base_Driver.getDriver().findElement(By.id("actual_amount"));
		String actualReceivedAmount_Text = actualReceivedAmount.getText();
		String fail = "Actual received amount failed";

		double minDeduct = 0.01;
		double maxDeduct = 0.10;
		double finalDeductMax = depositAmountToCheck - maxDeduct;
		double finalDeductMin = depositAmountToCheck - minDeduct;

		if (actualReceivedAmount.isDisplayed()) {
			create_Report.getExtentTest().info("Actual received amount is " + actualReceivedAmount_Text);
			System.out.println(actualReceivedAmount_Text);
			if (finalDeductMax <= Integer.valueOf(depositAmountToCheck)) {
				if (finalDeductMin <= Integer.valueOf(depositAmountToCheck)) {
					create_Report.getExtentTest().info("Amount is between " + finalDeductMax + " - " + finalDeductMin);
				} else {
					create_Report.getExtentTest().fail(fail);
					throw new FailedLoginException(fail);
				}
			}
		}
	}

	public void SubmitOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		WebElement submitOfflineDepositRequest = base_Driver.getDriver().findElement(By.id("payment"));
		String submitOfflineDepositRequest_Text = submitOfflineDepositRequest.getText();
		String fail = "Submit offline deposit request failed";

		if (submitOfflineDepositRequest.isEnabled()) {
			submitOfflineDepositRequest.click();
			create_Report.getExtentTest().info(submitOfflineDepositRequest_Text + " is enabled & clicked");
			base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void ConfirmOfflineDepositPaid() throws FailedLoginException, InterruptedException {
		WebElement confirmOfflineDepositPaid = base_Driver.getDriver().findElement(By.id("confirmPending"));
		String confirmOfflineDepositPaid_Text = confirmOfflineDepositPaid.getText();
		String fail = "Confirm offline deposit paid failed";

		if (confirmOfflineDepositPaid.isEnabled()) {
			confirmOfflineDepositPaid.click();
			create_Report.getExtentTest().info(confirmOfflineDepositPaid_Text + " is clicked");
			base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		} else {
			create_Report.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

	public void CancelOfflineDepositRequest() throws FailedLoginException, InterruptedException {
		WebElement cancelOfflineDepositRequest = base_Driver.getDriver().findElement(By.xpath("(//a[@id='cancelPending'])[1]"));
		String cancelOfflineDepositRequest_Text = cancelOfflineDepositRequest.getText();
		String fail = "Submit offline deposit request failed";

		base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (cancelOfflineDepositRequest.isEnabled()) {
			cancelOfflineDepositRequest.click();
			create_Report.getExtentTest().info(cancelOfflineDepositRequest_Text + " is clicked");

			Thread.sleep(500);
			WebElement confirmCancelOfflineDepositRequest = base_Driver.getDriver().findElement(By.xpath("//button[contains(text(),'确定')]"));
			String confirmCancelOfflineDepositRequest_Text = confirmCancelOfflineDepositRequest.getText();

			if (cancelOfflineDepositRequest.isDisplayed()) {
				confirmCancelOfflineDepositRequest.click();
				create_Report.getExtentTest().info(confirmCancelOfflineDepositRequest_Text + " is clicked & request is cancelled");

				Thread.sleep(500);
				WebElement cancelOfflineDepositRequestSuccess = base_Driver.getDriver().findElement(By.xpath("//div[@class='msg_show'][contains(text(),'取消成功')]"));
				String cancelOfflineDepositRequestSuccess_Text = cancelOfflineDepositRequestSuccess.getText();
				if (cancelOfflineDepositRequestSuccess.isDisplayed()) {
					create_Report.getExtentTest().info(cancelOfflineDepositRequestSuccess_Text + " is clicked & cancel offline deposit request is successful");
					base_Driver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
				}
			} else {
				create_Report.getExtentTest().fail(fail);
				throw new FailedLoginException(fail);
			}
		}
	}
}