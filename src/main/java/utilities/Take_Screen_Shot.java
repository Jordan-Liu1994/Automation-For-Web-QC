package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Take_Screen_Shot {

	private static Take_Screen_Shot take_Screen_Shot = new Take_Screen_Shot();

	public static Take_Screen_Shot getInstance() {
		return take_Screen_Shot;
	}

	private String user_Dir = System.getProperty("user.dir");
	private String screen_Shot_Path = user_Dir + ".\\src\\main\\resources\\screenshots\\";

	Base_Driver base_Driver = Base_Driver.get_Instance();

	public void getTakeScreenShot(String fileName) {
		File screenShot = ((TakesScreenshot) base_Driver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File(screen_Shot_Path + fileName + " " + timestamp() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String date_Format = "dd-MM-yy (HH.mm.ss)";

	public String timestamp() {
		return new SimpleDateFormat(date_Format).format(new Date());
	}

	public String screenShotPathExtent() {
		String screen_Shot_Path_Extent = screen_Shot_Path;
		return screen_Shot_Path;
	}
}