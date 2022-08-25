package functions_WEB_FE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Withdraw_AddBankCard_Function {

	private static Withdraw_AddBankCard_Function function = new Withdraw_AddBankCard_Function();

	public static Withdraw_AddBankCard_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	public void addBankCardFromWithdrawPage() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement addBankCardFromWithdrawPage = baseDriver.getDriver().findElement(By.xpath("//div[@class='btn_txt add_bankcard']"));
		String addBankCardFromWithdrawPage_Text = addBankCardFromWithdrawPage.getText();
		String fail = "Add bank card from withdraw page failed";

		if (addBankCardFromWithdrawPage.isDisplayed()) {
			addBankCardFromWithdrawPage.click();
			createReport.getExtentTest().info("Clicked " + addBankCardFromWithdrawPage_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectAddBankCardOption() throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement addBankCardOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='tab addBankCardBtn swiper-slide active swiper-slide-active']"));
		String addBankCardOption_Text = addBankCardOption.getText();
		String fail = "Add bank card option failed";

		if (addBankCardOption.isDisplayed()) {
			addBankCardOption.click();
			createReport.getExtentTest().info("Clicked " + addBankCardOption_Text);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void fillInBankCardDetails(String addName, String phoneNumber) throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 5);
		WebElement addRealName = baseDriver.getDriver().findElement(By.id("bank_account_name"));
		wait.until(ExpectedConditions.visibilityOf(addRealName));
		String addRealName_Text = addRealName.getAttribute("placeholder");
		String skip = "Name already binded so skipped this step";
		String fail = "Add real name failed";

		if (addRealName.isEnabled()) {
			addRealName.sendKeys(addName);
			createReport.getExtentTest().info(addName + " keyed in " + addRealName_Text);
		} else if (!addRealName.isEnabled()) {
			createReport.getExtentTest().skip(skip);
			throw new SkipException(skip);
		}

		WebElement addMobile = baseDriver.getDriver().findElement(By.id("bank_mobile_number"));
		String addMobile_Text = addMobile.getAttribute("placeholder");

		if (addMobile.isEnabled()) {
			addMobile.sendKeys(phoneNumber);
			createReport.getExtentTest().info(phoneNumber + " keyed in " + addMobile_Text);
		} else if (!addMobile.isEnabled()) {
			createReport.getExtentTest().skip(skip);
			throw new SkipException(skip);
		}

		Thread.sleep(2000);
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void generateOTPButton(String phoneOtp) throws FailedLoginException, InterruptedException {
		WebElement selectGenerateOTPButton = baseDriver.getDriver().findElement(By.id("bank_otp_button"));
		String selectGenerateOTPButton_Text = selectGenerateOTPButton.getText();
		String fail = "generateOTPButton failed";

		if (selectGenerateOTPButton.isDisplayed()) {
			selectGenerateOTPButton.click();
			createReport.getExtentTest().info("Clicked " + selectGenerateOTPButton_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}

		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement setPhoneOtp = baseDriver.getDriver().findElement(By.id("bank_otp_code"));
		wait.until(ExpectedConditions.elementToBeClickable(setPhoneOtp));
		String setPhoneOtp_Text = setPhoneOtp.getAttribute("placeholder");

		if (setPhoneOtp.isDisplayed()) {
			setPhoneOtp.clear();
			setPhoneOtp.sendKeys(phoneOtp);
			setPhoneOtp.sendKeys(Keys.ENTER);
			createReport.getExtentTest().info(phoneOtp + " keyed in " + setPhoneOtp_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void withdrawBankVendorOption() throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement selectBankVendorOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='textbox_content bpc_selection_area openBankSelector']//img"));
		wait.until(ExpectedConditions.elementToBeClickable(selectBankVendorOption));
		String fail = "withdrawBankVendorOption failed";

		if (selectBankVendorOption.isDisplayed()) {
			selectBankVendorOption.click();
			createReport.getExtentTest().info("Clicked add bank vendor button");
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
	
//	= = = = = = = = = = = = = = = = = = = = 

	public void filterBankVendorOption(String bankVendorOption) throws FailedLoginException, InterruptedException {
		baseDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		WebElement filterBankVendorOption = baseDriver.getDriver().findElement(By.xpath("//div[@class='bank_alpha_container']"));
		Boolean filterBankVendorOptionValue = false;
		String fail = "withdrawBankVendorOption failed";
		
		List<WebElement> filterBankVendorOption_Tag = filterBankVendorOption.findElements(By.className("bank_alpha_btn"));
		for (WebElement filterBankVendorOption_Lists : filterBankVendorOption_Tag) {
			String filterBankVendorOption_Text = filterBankVendorOption_Lists.getText();
			System.out.println(filterBankVendorOption_Text);
			if (filterBankVendorOption_Text.equalsIgnoreCase(bankVendorOption)) {
				filterBankVendorOption_Lists.click();
				createReport.getExtentTest().info("Clicked " + bankVendorOption);
				filterBankVendorOptionValue = true;
			}
		}
		if (!filterBankVendorOptionValue) {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
		WebElement BankVendorOption_X = baseDriver.getDriver().findElement(By.xpath("//div[contains(text(),'厦门国际银行')]"));
		wait.until(ExpectedConditions.elementToBeClickable(BankVendorOption_X));
		if (BankVendorOption_X.isDisplayed()) {
			BankVendorOption_X.click();
			createReport.getExtentTest().info("Clicked 厦门国际银行");
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebElement selectProvinceOption = baseDriver.getDriver().findElement(By.id("input_province_name"));
		String selectProvinceOption_Text = selectProvinceOption.getAttribute("placeholder");
		
		if (selectProvinceOption.isDisplayed()) {
			selectProvinceOption.click();
			createReport.getExtentTest().info("Clicked " + selectProvinceOption_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebElement selectProvinceOption_1st = baseDriver.getDriver().findElement(By.xpath("//div[contains(text(),'河北省')]"));
		String selectProvinceOption_1st_Text = selectProvinceOption_1st.getText();
		if (selectProvinceOption_1st.isDisplayed()) {
			selectProvinceOption_1st.click();
			createReport.getExtentTest().info("Clicked " + selectProvinceOption_1st_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebElement selectCityOption_1st = baseDriver.getDriver().findElement(By.xpath("//div[@data-city='安国市']"));
		String selectCityOption_1st_Text = selectCityOption_1st.getText();
		if (selectCityOption_1st.isDisplayed()) {
			selectCityOption_1st.click();
			createReport.getExtentTest().info("Clicked " + selectCityOption_1st_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebElement selectCityProvinceOptionConfirm = baseDriver.getDriver().findElement(By.xpath("//div[contains(text(),'确定')]"));
		String selectCityProvinceOptionConfirm_Text = selectCityProvinceOptionConfirm.getText();
		if (selectCityProvinceOptionConfirm.isDisplayed()) {
			selectCityProvinceOptionConfirm.click();
			createReport.getExtentTest().info("Clicked " + selectCityProvinceOptionConfirm_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
		WebElement confirmBankSelection = baseDriver.getDriver().findElement(By.xpath("//button[contains(text(),'确定')]"));
		String confirmBankSelection_Text = confirmBankSelection.getText();
		if (confirmBankSelection.isDisplayed()) {
			confirmBankSelection.click();
			createReport.getExtentTest().info("Clicked " + confirmBankSelection_Text);
			Thread.sleep(1000);
		} else {
			createReport.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
		
//		WebElement selectProvinceOption_Container = baseDriver.getDriver().findElement(By.xpath("//div[@class='province_list']"));
//		Boolean selectProvinceOptionValue = false;
//		
//		List<WebElement> selectProvinceOption_Container_Tag = selectProvinceOption_Container.findElements(By.className("name_item province_name_item"));
//		for (WebElement selectProvinceOption_Container_Lists : selectProvinceOption_Container_Tag) {
//			String selectProvinceOption_Container_Text = selectProvinceOption_Container_Lists.getText();
//			System.out.println(selectProvinceOption_Container_Text);
//			if (selectProvinceOption_Container_Text.equalsIgnoreCase("山西省")) {
//				selectProvinceOption_Container_Lists.click();
//				createReport.getExtentTest().info("Clicked 山西省");
//				filterBankVendorOptionValue = true;
//			}
//		}
//		if (!selectProvinceOptionValue) {
//			createReport.getExtentTest().fail(fail);
//			throw new FailedLoginException(fail);
//		}
	}
}