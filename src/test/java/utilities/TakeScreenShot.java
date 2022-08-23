package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot {

	private static TakeScreenShot takeScreenShot = new TakeScreenShot();

	public static TakeScreenShot getInstance() {
		return takeScreenShot;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private String userDir = System.getProperty("user.dir");
	private String screenShotPath = userDir + ".\\src\\test\\resources\\screenshots\\";

	BaseDriver baseDriver = BaseDriver.getInstance();

	public void getTakeScreenShotPass(String fileName) {
		File screenShot = ((TakesScreenshot) baseDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
//			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + " " + timestamp() + "-passed.png"));
			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + "-passed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void getTakeScreenShotFail(String fileName) {
		File screenShot = ((TakesScreenshot) baseDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + "-failed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	private String dateFormat = "dd-MM-yy (HH.mm.ss)";

	public String timestamp() {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public String screenShotPathExtent() {
		String screenShotPathExtent = screenShotPath;
		return screenShotPath;
	}
}