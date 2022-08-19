package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Take_Screen_Shot {

	private static Take_Screen_Shot takeScreenShot = new Take_Screen_Shot();

	public static Take_Screen_Shot getInstance() {
		return takeScreenShot;
	}

	private String userDir = System.getProperty("user.dir");
	private String screenShotPath = userDir + ".\\src\\main\\resources\\screenshots\\";

	Base_Driver baseDriver = Base_Driver.getInstance();

	public void getTakeScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) baseDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screenShotPath + fileName + " " + timestamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String dateFormat = "dd-MM-yy (HH.mm.ss)";

	public String timestamp() {
		return new SimpleDateFormat(dateFormat).format(new Date());
	}

	public String screenShotPathExtent() {
		String screenShotPathExtent = screenShotPath;
		return screenShotPath;
	}
}