package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base_Driver {

	private static Base_Driver base_Driver = new Base_Driver();

	public static Base_Driver getInstance() {
		return base_Driver;
	}
	
	private String driverPath = ".\\src\\main\\resources\\drivers\\";

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriverProperty(String driver_Type, String path) {
		System.setProperty(driver_Type, driverPath + path);
	}

	public void startDriver(String site_Url) throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(site_Url);
	}

	public void stopDriver() throws InterruptedException {
		driver.quit();
	}
}
