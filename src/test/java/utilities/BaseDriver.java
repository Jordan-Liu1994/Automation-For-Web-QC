package utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseDriver {

//	private static BaseDriver bDriver = new BaseDriver();
//
//	public static BaseDriver getInstance() {
//		return bDriver;
//	}

	static WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	private String driverFolderPath = ".\\src\\test\\resources\\drivers\\";

	public void setChromeDriverProperty(String driverType, String driverPath) {
		System.setProperty(driverType, driverFolderPath + driverPath);
	}
	
	public void setFirefoxDriverProperty(String driverType, String path) {
		System.setProperty(driverType, driverFolderPath + path);
	}

//	private URL url;
//
//	public void startDriverRemote() throws InterruptedException, MalformedURLException {
//		DesiredCapabilities capab = DesiredCapabilities.chrome();
//		capab.setBrowserName("chrome");
//		capab.setPlatform(Platform.WINDOWS);
//		url = new URL("http://localhost:4444/wd/hub");
//		driver = new RemoteWebDriver(url, capab);
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//	}

	public void startChromeDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	
	public void startFirefoxDriver() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	public void doRefresh() {
		driver.navigate().refresh();
	}
	
	public void getURL(String url) {
		driver.get(url);
	}

	public void closeBrowser() {
		driver.close();
	}

	public void stopDriver() {
		driver.quit();
	}
}
