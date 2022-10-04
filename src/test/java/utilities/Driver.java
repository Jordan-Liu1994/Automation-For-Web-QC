package utilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Driver {

	static String driverFolderPath = ".\\src\\test\\resources\\drivers\\";

	static WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

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

	public void setTimeOut() {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	public void setWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void setActionMoveTo(WebElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
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