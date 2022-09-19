package functions_WEB_FE;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseDriver;
import utilities.CreateReport;

public class LanguageSelectionFE {

	private static LanguageSelectionFE languageSF = new LanguageSelectionFE();

	public static LanguageSelectionFE getInstance() {
		return languageSF;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();

	WebDriverWait wait;

	public void languageSelectionButton() throws FailedLoginException {
		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement languageSelectionButton = bDriver.getDriver().findElement(By.xpath("//span[@role='presentation']"));
		wait.until(ExpectedConditions.elementToBeClickable(languageSelectionButton));
		String fail = "languageSelectionButton failed";

		if (languageSelectionButton.isDisplayed()) {
			languageSelectionButton.click();
			cR.getExtentTest().info("Clicked languageSelectionButton");
		} else {
			cR.getExtentTest().fail(fail);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void selectSpecificLanguage(String language) throws FailedLoginException {
		String fail = "selectSpecificLanguage failed";
		Boolean selectSpecificLanguageValue = false;

		wait = new WebDriverWait(bDriver.getDriver(), 10);
		WebElement selectSpecificLanguage = bDriver.getDriver().findElement(By.xpath("//ul[@role='listbox']"));
		wait.until(ExpectedConditions.visibilityOf(selectSpecificLanguage));
		wait.until(ExpectedConditions.elementToBeClickable(selectSpecificLanguage));

		List<WebElement> selectSpecificLanguageTagNames = selectSpecificLanguage.findElements(By.tagName("li"));
		for (WebElement selectSpecificLanguageLists : selectSpecificLanguageTagNames) {
			String selectSpecificLanguageListsText = selectSpecificLanguageLists.getText();
			System.out.println(selectSpecificLanguageListsText);
			bDriver.getDriver().manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			if (selectSpecificLanguageListsText.equalsIgnoreCase(language)) {
				selectSpecificLanguageLists.click();
				cR.getExtentTest().info("Clicked " + language);
				selectSpecificLanguageValue = true;
			}
		}
		if (!selectSpecificLanguageValue) {
			cR.getExtentTest().fail(fail);
		}
	}
}