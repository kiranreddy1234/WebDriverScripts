package webDriver_Scripts;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import helperClasses.LoggerHelper;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import testBase.TestBase;

public class ParticularElmScreenshot extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(ParticularElmScreenshot.class);
	
	   @Test
	   public void particularElementScreenshot() throws IOException {
		   
		   driver.get("https://anyxxx.com/");
		   
		   WebElement elm = driver.findElement(By.xpath("//div[3]//a[1]//span[1]//img[1]"));
		   
		   JavascriptExecutor js = ((JavascriptExecutor)driver);
		   
		   js.executeScript("arguments[0].scrollIntoView(true);", elm);
		   
		   Screenshot src = new AShot().takeScreenshot(driver,elm);
		   
		   ImageIO.write(src.getImage(), "PNG", new File("./FullPageScreenshot/ParticularElementScreenshot.png"));  
		   
		   log.info("Particular Element Screenshot is takened");
	   }
}
