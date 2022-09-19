package functions_WEB_FE;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CreateReport;
import utilities.VariablesStorage;

public class Announcement extends VariablesStorage {

	CreateReport cReport = new CreateReport();
	
	WebDriverWait wait;
	String skip;
	WebElement noShowAgainTodayButton;
	WebElement closeAnnouncementButton;

	public void closeAnnouncement() throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(bDriver.getDriver(), 15);
		try {
			for (int number = 0; number <= 10; number++) {
				wait = new WebDriverWait(bDriver.getDriver(), 15);
				noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='aip_" + number + "']//div//div//span"));
				Thread.sleep(250);
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
				if (noShowAgainTodayButton.isDisplayed()) {
					String buttonText = noShowAgainTodayButton.getText();
					noShowAgainTodayButton.click();
					cReport.getExtentTest().info("Clicked " + buttonText);
				}
				Thread.sleep(250);
				closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade web_announcement_image_popout show']//button[@aria-label='Close']"));
				wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
				wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
				if (closeAnnouncementButton.isDisplayed()) {
					closeAnnouncementButton.click();
					cReport.getExtentTest().info("Clicked closeAnnouncementButton");
				}
			}
		} catch (NoSuchElementException e) {
			skip = "Skipped";
			cReport.getExtentTest().skip(skip);
		}
	}

	public void closeAnnouncementOverview() throws FailedLoginException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(bDriver.getDriver(), 15);

		try {
			wait = new WebDriverWait(bDriver.getDriver(), 15);
			noShowAgainTodayButton = bDriver.getDriver().findElement(By.xpath("//label[@for='normal_announcement_radio']//div[@class='checkbox']//div//span"));
			Thread.sleep(250);
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(noShowAgainTodayButton));
			if (noShowAgainTodayButton.isDisplayed()) {
				String buttonText = noShowAgainTodayButton.getText();
				noShowAgainTodayButton.click();
				cReport.getExtentTest().info("Clicked " + buttonText);
			}
			Thread.sleep(250);
			closeAnnouncementButton = bDriver.getDriver().findElement(By.xpath("//div[@class='modal fade large wna_style show']//button[@aria-label='Close']"));
			wait.until(ExpectedConditions.visibilityOf(noShowAgainTodayButton));
			wait.until(ExpectedConditions.elementToBeClickable(closeAnnouncementButton));
			if (closeAnnouncementButton.isDisplayed()) {
				closeAnnouncementButton.click();
				cReport.getExtentTest().info("Clicked closeAnnouncementOverview");
			}
		} catch (NoSuchElementException e) {
			skip = "Skipped";
			cReport.getExtentTest().skip(skip);
		}
	}
}