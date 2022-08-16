package testings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base_Driver {

	private WebDriver driver;
	private static Base_Driver base_driver = new Base_Driver();
	
	private static String driverPath = ".\\src\\main\\resources\\drivers\\";
	
	public static void main(String[] args) throws InterruptedException {
		base_driver.setDriverProperty("webdriver.chrome.driver", "chromedriver.exe");
		base_driver.startDriver("https://www.youtube.com");
		base_driver.stopDriver();
	}
	
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
	
	public void stopDriver() throws InterruptedException {
		Thread.sleep(1500);
		driver.quit();
	}
}