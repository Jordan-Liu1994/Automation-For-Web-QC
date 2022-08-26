package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TakeScreenShot extends VariablesStorage {

	private static TakeScreenShot takeSS = new TakeScreenShot();

	public static TakeScreenShot getInstance() {
		return takeSS;
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	BaseDriver bDriver = BaseDriver.getInstance();

	private String userDir = System.getProperty("user.dir");
	private String pathOfSS = userDir + ".\\src\\test\\resources\\screenshots\\";
	private String screenShotPathExtent;

	public void getPassScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) bDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(pathOfSS + fileName + passSS()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public void getFailScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) bDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(pathOfSS + fileName + failSS()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	= = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = 

	public String screenShotPathExtent() {
		screenShotPathExtent = pathOfSS;
		return pathOfSS;
	}
}