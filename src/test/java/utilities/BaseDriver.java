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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseDriver {

	private static BaseDriver bDriver = new BaseDriver();

	public static BaseDriver getInstance() {
		return bDriver;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private String driverPath = ".\\src\\test\\resources\\drivers\\";

	public void setDriverProperty(String driverType, String path) {
		System.setProperty(driverType, driverPath + path);
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

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

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void startDriver() throws InterruptedException, MalformedURLException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void getURL(String siteUrl) throws InterruptedException {
		driver.get(siteUrl);
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void stopDriver() throws InterruptedException {
		driver.quit();
	}
}
