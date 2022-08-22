package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {

	private static BaseDriver baseDriver = new BaseDriver();

	public static BaseDriver getInstance() {
		return baseDriver;
	}
	
	private String driverPath = ".\\src\\test\\resources\\drivers\\";

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriverProperty(String driverType, String path) {
		System.setProperty(driverType, driverPath + path);
	}

	public void startDriver(String siteUrl) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(siteUrl);
	}

	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
	
	public void stopDriver() throws InterruptedException {
		driver.quit();
	}
}
