package utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot {

	Driver driver = new Driver();

	static String userDirectory = System.getProperty("user.dir");
	static String pathOfScreenShot = userDirectory + ".\\src\\test\\resources\\screenshots\\";

	public String takeSnapShot(String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) driver.getDriver());
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(pathOfScreenShot + fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
		return fileWithPath;
	}
	
	public String screenShotPathExtent() {
		return pathOfScreenShot;
	}
}