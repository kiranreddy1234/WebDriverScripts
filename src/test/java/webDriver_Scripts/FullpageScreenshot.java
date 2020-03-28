package webDriver_Scripts;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import helperClasses.LoggerHelper;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import testBase.TestBase;

public class FullpageScreenshot extends TestBase{
	   
	   private final Logger log = LoggerHelper.getLogger(FullpageScreenshot.class);
	
	   @Test
	   public void fullPageScreenshot() throws IOException {
		   
		   driver.get("https://anyxxx.com/");
		   
		  //Screenshot src = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000).getScreenshot(driver));
		  
		  AShot src = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000));
		  
		  Screenshot img = src.takeScreenshot(driver);
		  
		  ImageIO.write(img.getImage(), "PNG", new File("./FullPageScreenshot/Screenshot.png"));
		  
		  log.info("Fullpage Screenshot is takened");
	   }
}
