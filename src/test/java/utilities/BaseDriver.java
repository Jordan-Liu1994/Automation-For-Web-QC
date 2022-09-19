package utilities;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseDriver {

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

	public void doRefresh(String url) {
		driver.navigate().refresh();
	}

	public void getURL(String siteUrl) {
		driver.get(siteUrl);
	}

	public void closeBrowser() {
		driver.close();
	}

	public void stopDriver() {
		driver.quit();
	}
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