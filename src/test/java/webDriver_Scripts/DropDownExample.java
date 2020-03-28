package webDriver_Scripts;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import helperClasses.LoggerHelper;
import testBase.TestBase;

public class DropDownExample extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(DropDownExample.class);	
		
	@Test
	public void dropdownMethod1() {
		
		driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
		
		WebElement elm = driver.findElement(By.xpath("//select[@id='select-demo']"));
		
		Select dropDown = new Select(elm);
		
		List<WebElement> allValues = dropDown.getOptions();
		
		System.out.println("Size of the dropDown : " + allValues.size());
		
		for(WebElement val : allValues) {
			
			String str = val.getText();
			
			System.out.println(str);
		}
	
		log.info("Get all dropDown Values is finished... ");
	}
}
