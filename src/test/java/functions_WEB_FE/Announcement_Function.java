package functions_WEB_FE;

import java.util.concurrent.TimeUnit;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Announcement_Function {

	private static Announcement_Function function = new Announcement_Function();

	public static Announcement_Function getInstance() {
		return function;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	int number = 1;

	public void doNotShowAgainTodayRadioButton() throws FailedLoginException, InterruptedException {
		String skip = "Announcement Skipped";

		try {
			for (int number = 1; number <= 5; number++) {
				WebDriverWait wait = new WebDriverWait(baseDriver.getDriver(), 10);
				WebElement doNotShowAgainTodayRadioButton = baseDriver.getDriver().findElement(By.xpath("(//span[contains(text(),'今日不再显示')])[" + number + "]"));
				wait.until(ExpectedConditions.visibilityOf(doNotShowAgainTodayRadioButton));
				if (doNotShowAgainTodayRadioButton.isDisplayed()) {
					String doNotShowAgainTodayRadioButton_Text = doNotShowAgainTodayRadioButton.getText();
					doNotShowAgainTodayRadioButton.click();
					createReport.getExtentTest().info("Clicked " + doNotShowAgainTodayRadioButton_Text);
				}

				WebElement closeButton = baseDriver.getDriver().findElement(By.xpath("(//button[@aria-label='Close'])[" + number + "]"));
				if (closeButton.isDisplayed()) {
					closeButton.click();
					Thread.sleep(1000);
				}
			}
		} catch (NoSuchElementException e) {
			createReport.getExtentTest().info(skip);
			throw new SkipException(skip);
		}
	}
}
