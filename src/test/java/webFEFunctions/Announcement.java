package webFEFunctions;

import javax.security.auth.login.FailedLoginException;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CreateReport;
import utilities.Driver;
import utilities.VariablesStorage;

public class Announcement {

	Driver driver = new Driver();
	CreateReport createReport = new CreateReport();

	String skip = "Skipped";

	public void closeAnnouncement() throws InterruptedException {
		try {
			for (int number = 0; number <= 5; number++) {
				WebElement element = driver.getDriver().findElement(By.xpath("//label[@for='aip_" + number + "']//div//div//span"));
				Thread.sleep(250);
				driver.setWait(element);
				if (element.isDisplayed()) {
					String elementText = element.getText();
					element.click();
					createReport.getExtentTest().info(elementText);

					Thread.sleep(250);
					WebElement element2 = driver.getDriver().findElement(By.xpath("//div[@class='modal fade web_announcement_image_popout show']//button[@aria-label='Close']"));
					driver.setWait(element2);
					if (element2.isDisplayed()) {
						element2.click();
						createReport.getExtentTest().info("Announcement closed");
					}
				}
				break;
			}
		} catch (NoSuchElementException e) {
			createReport.getExtentTest().skip(skip);
		}
	}

	public void closeAnnouncementOverview() throws InterruptedException {
		try {
			WebElement element = driver.getDriver().findElement(By.xpath("//label[@for='normal_announcement_radio']//div[@class='checkbox']//div//span"));
			Thread.sleep(250);
			driver.setWait(element);
			if (element.isDisplayed()) {
				String elementText = element.getText();
				element.click();
				createReport.getExtentTest().info(elementText);

				Thread.sleep(250);
				WebElement element2 = driver.getDriver().findElement(By.xpath("//div[@class='modal fade large wna_style show']//button[@aria-label='Close']"));
				driver.setWait(element2);
				if (element2.isDisplayed()) {
					element2.click();
					createReport.getExtentTest().info("Announcement overview closed");
				}
			}
		} catch (NoSuchElementException e) {
			createReport.getExtentTest().skip(skip);
		}
	}
}