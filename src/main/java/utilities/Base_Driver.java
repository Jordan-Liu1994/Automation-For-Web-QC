package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base_Driver {

	private static Base_Driver baseDriver = new Base_Driver();

	public static Base_Driver getInstance() {
		return baseDriver;
	}
	
	private String driverPath = ".\\src\\main\\resources\\drivers\\";

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
