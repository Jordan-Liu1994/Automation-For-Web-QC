package functions_WEB;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import utilities.BaseDriver;
import utilities.CreateReport;

public class Announcement_Function {

	private static Announcement_Function function = new Announcement_Function();

	public static Announcement_Function getInstance() {
		return function;
	}

	BaseDriver baseDriver = BaseDriver.getInstance();
	CreateReport createReport = CreateReport.getInstance();

	int number = 1;

	public void selectDoNotShowAgainTodayCheckBox() throws FailedLoginException, InterruptedException {
		String skip = "Announcement Skipped";

		while (number <= 5) {
			try {
				WebElement selectDoNotShowAgainTodayCheckBox = baseDriver.getDriver().findElement(By.xpath("(//span[contains(text(),'今日不再显示')])[" + number + "]"));
				if (selectDoNotShowAgainTodayCheckBox.isDisplayed()) {
					String selectDoNotShowAgainTodayCheckBox_Text = selectDoNotShowAgainTodayCheckBox.getText();
					selectDoNotShowAgainTodayCheckBox.click();
					createReport.getExtentTest().info("Clicked " + selectDoNotShowAgainTodayCheckBox_Text);
				}

				WebElement closeButton = baseDriver.getDriver().findElement(By.xpath("(//button[@aria-label='Close'])[" + number + "]"));
				if (closeButton.isDisplayed()) {
					closeButton.click();
					Thread.sleep(1000);
					number++;
				}
				
			} catch (NoSuchElementException e) {
				createReport.getExtentTest().info(skip);
				throw new SkipException(skip);
			}
		}
	}
}
