package functions_WEB_FE;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;
import utilities.VariablesStorage;

public class Announcement {

	private static Announcement aF = new Announcement();

	public static Announcement getInstance() {
		return aF;
	}

//	= = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();
	CreateReport cR = CreateReport.getInstance();
	WebDriverWait wait;
	WebElement noShowAgainTodayButton;
	WebElement closeAnnouncementButton;

	public void closeAnnouncement() throws FailedLoginException, InterruptedException {
		String skip = "Skipped";
		WebDriverWait wait = new WebDriverWait(bDriver.getDriver(), 10);

		try {
			for (int number = 1; number <= 5; number++) {
				noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("(//span[contains(text(),'今日不再显示')])[" + number + "]"));
				wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
				if (noShowAgainTodayButton.isDisplayed()) {
					String button_Text = noShowAgainTodayButton.getText();
					noShowAgainTodayButton.click();
					cR.getExtentTest().info("Clicked " + button_Text);

					closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("(//button[@aria-label='Close'])[" + number + "]"));
					wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
					if (closeAnnouncementButton.isDisplayed()) {
						closeAnnouncementButton.click();
						cR.getExtentTest().info("Clicked closeAnnouncementButton");
					}
				}
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}
}
