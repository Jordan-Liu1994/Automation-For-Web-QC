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
		WebDriverWait wait = new WebDriverWait(bDriver.getDriver(), 15);

		try {
			for (int number = 0; number <= 10; number++) {
				noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='aip_" + number + "']//div//div//span"));
				Thread.sleep(250);
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
				if (noShowAgainTodayButton.isDisplayed()) {
					String buttonText = noShowAgainTodayButton.getText();
					noShowAgainTodayButton.click();
					cR.getExtentTest().info("Clicked " + buttonText);
				}
				Thread.sleep(250);
				closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade web_announcement_image_popout show']//button[@aria-label='Close']"));
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
				if (closeAnnouncementButton.isDisplayed()) {
					closeAnnouncementButton.click();
					cR.getExtentTest().info("Clicked closeAnnouncementButton");
				}
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}

//	= = = = = = = = = = = = = = = = = = = = 

	public void closeAnnouncementOverview() throws FailedLoginException, InterruptedException {
		String skip = "Skipped";
		WebDriverWait wait = new WebDriverWait(bDriver.getDriver(), 15);

		try {
			noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='normal_announcement_radio']//div[@class='checkbox']//div//span"));
			Thread.sleep(250);
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
			if (noShowAgainTodayButton.isDisplayed()) {
				String buttonText = noShowAgainTodayButton.getText();
				noShowAgainTodayButton.click();
				cR.getExtentTest().info("Clicked " + buttonText);
			}
			Thread.sleep(250);
			closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade large wna_style show']//button[@aria-label='Close']"));
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
			if (closeAnnouncementButton.isDisplayed()) {
				closeAnnouncementButton.click();
				cR.getExtentTest().info("Clicked closeAnnouncementOverview");
			}
		} catch (NoSuchElementException e) {
			cR.getExtentTest().skip(skip);
		}
	}
}
