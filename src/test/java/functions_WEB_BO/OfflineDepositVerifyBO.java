package functions_WEB_BO;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import functions_WEB_FE.OfflineDepositFE;
import utilities.BaseDriver;
import utilities.CreateReport;

public class OfflineDepositVerifyBO {

	private static OfflineDepositVerifyBO offlineDV = new OfflineDepositVerifyBO();

	public static OfflineDepositVerifyBO getInstance() {
		return offlineDV;
	}

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	OfflineDepositFE offlineDF = OfflineDepositFE.getInstance();

	WebDriverWait wait;
	private static String attr = "placeholder";
	private static String keyIn = " keyed in ";

	public void selectOfflineDepositVerification(String module) throws FailedLoginException, InterruptedException {
		String fail = "selectOfflineDepositVerification failed";
		Boolean selectOfflineDepositVerificationValue = false;

		Thread.sleep(1000);
		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement listOfModules = bDriver.getDriver().findElement(By.xpath("//ul[@class='menu_list']"));
		wait.until(ExpectedConditions.visibilityOf(listOfModules));

		List<WebElement> listOfModulesTagNames = listOfModules.findElements(By.tagName("li"));
		for (WebElement listOfModulesLists : listOfModulesTagNames) {
			String listOfModulesListsText = listOfModulesLists.getText();
			bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if (listOfModulesListsText.equalsIgnoreCase(module)) {
				Actions builder = new Actions(bDriver.getDriver());
				Action act = builder.moveToElement(listOfModulesLists).build();
				act.perform();
				cR.getExtentTest().info("Hovered over " + module);
				selectOfflineDepositVerificationValue = true;
			}
		}
		if (!selectOfflineDepositVerificationValue) {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void offlineDepositVerificationSubModule(String subModule) throws FailedLoginException {
		String fail = "offlineDepositVerificationSubModule failed";
		Boolean offlineDepositVerificationSubModuleValue = false;

		bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement listOfSubModules = bDriver.getDriver().findElement(By.xpath("//div[@class='row menu_list_container']//li[4]//ul[1]"));
		wait.until(ExpectedConditions.visibilityOf(listOfSubModules));

		List<WebElement> listOfSubModulesTagNames = listOfSubModules.findElements(By.tagName("li"));
		for (WebElement listOfSubModulesLists : listOfSubModulesTagNames) {
			String listOfSubModulesText = listOfSubModulesLists.getText();
			bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if (listOfSubModulesText.equalsIgnoreCase(subModule)) {
				listOfSubModulesLists.click();
				cR.getExtentTest().info("Hovered over " + subModule);
				offlineDepositVerificationSubModuleValue = true;
			}
		}
		if (!offlineDepositVerificationSubModuleValue) {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void filterUserAccount(String userID) throws FailedLoginException, InterruptedException {
		String fail = "filterUserAccount failed";

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement filterUserAccount = bDriver.getDriver().findElement(By.xpath("//*[@id=\"filter_form\"]/div[1]/label/span/span[1]/span/ul/li/input"));
		wait.until(ExpectedConditions.visibilityOf(filterUserAccount));
		String filterUserAccountText = filterUserAccount.getAttribute(attr);

		if (filterUserAccount.isDisplayed()) {
			filterUserAccount.clear();
			filterUserAccount.sendKeys(userID);
			Thread.sleep(1000);
			filterUserAccount.sendKeys(Keys.ENTER);
			cR.getExtentTest().info(userID + keyIn + filterUserAccountText);
		} else {
			cR.getExtentTest().fail(fail);
		}

		Thread.sleep(500);
		WebElement searchButton = bDriver.getDriver().findElement(By.id("search_credit_btn"));
		String searchButtonText = searchButton.getText();

		if (searchButton.isEnabled()) {
			searchButton.click();
			cR.getExtentTest().info("Clicked " + searchButtonText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void verifyDepositID() throws FailedLoginException, InterruptedException {
		String fail = "verifyDepositID failed";
		
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement verifyDepositIDx = bDriver.getDriver().findElement(By.xpath("//td[normalize-space()='" + offlineDF.offlineDepositRecordID() + "']"));
		wait.until(ExpectedConditions.visibilityOf(verifyDepositIDx));
		if (verifyDepositIDx.isDisplayed()) {
			String verifyDepositIDxText = verifyDepositIDx.getText();
			cR.getExtentTest().info("Verified " + verifyDepositIDxText);
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void rejectOfflineDepositAfterVerified() throws FailedLoginException, InterruptedException {
		String fail = "rejectOfflineDepositAfterVerified failed";

		Thread.sleep(1000);
		WebElement rejectOfflineDepositAfterVerifiedLock = bDriver.getDriver().findElement(By.xpath("//button[@data-toggle='tooltip']"));
		if (rejectOfflineDepositAfterVerifiedLock.isEnabled()) {
			rejectOfflineDepositAfterVerifiedLock.click();
			cR.getExtentTest().info("Clicked unlocked offline deposit");
		} else {
			cR.getExtentTest().fail(fail);
		}

		wait = new WebDriverWait(bDriver.getDriver(), 30);
		WebElement rejectOfflineDepositAfterVerifiedReject = bDriver.getDriver().findElement(By.xpath("//button[@title='拒绝']"));
		wait.until(ExpectedConditions.visibilityOf(rejectOfflineDepositAfterVerifiedReject));
		wait.until(ExpectedConditions.elementToBeClickable(rejectOfflineDepositAfterVerifiedReject));
		if (rejectOfflineDepositAfterVerifiedReject.isEnabled()) {
			rejectOfflineDepositAfterVerifiedReject.click();
			cR.getExtentTest().info("Clicked reject button");
		} else {
			cR.getExtentTest().fail(fail);
		}

		WebElement rejectOfflineDepositAfterVerifiedConfirmReject = bDriver.getDriver().findElement(By.xpath("//body/div/div/div/div/div/div/button[1]"));
		wait.until(ExpectedConditions.visibilityOf(rejectOfflineDepositAfterVerifiedConfirmReject));
		wait.until(ExpectedConditions.elementToBeClickable(rejectOfflineDepositAfterVerifiedConfirmReject));
		if (rejectOfflineDepositAfterVerifiedConfirmReject.isEnabled()) {
			rejectOfflineDepositAfterVerifiedConfirmReject.click();
			cR.getExtentTest().info("Clicked confirm reject button");
		} else {
			cR.getExtentTest().fail(fail);
		}

		WebElement confirmMessage = bDriver.getDriver().findElement(By.id("alert_title"));
		wait.until(ExpectedConditions.visibilityOf(confirmMessage));
		if (confirmMessage.isDisplayed()) {
			cR.getExtentTest().info("Offline deposit confirmed to be rejected");
			Thread.sleep(1500);
		} else {
			cR.getExtentTest().fail(fail);
			throw new FailedLoginException(fail);
		}
	}
}