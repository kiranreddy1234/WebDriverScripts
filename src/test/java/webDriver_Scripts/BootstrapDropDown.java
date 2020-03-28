package webDriver_Scripts;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import helperClasses.LoggerHelper;
import testBase.TestBase;

public class BootstrapDropDown extends TestBase{

	private final Logger log = LoggerHelper.getLogger(BootstrapDropDown.class);
	
	@Test
	public void bootstrapDropdownMethod() {
		
		driver.get("https://www.w3schools.com/bootstrap/bootstrap_dropdowns.asp");
		
		driver.findElement(By.xpath("//button[@id='menu1']")).click();
		
		List<WebElement> val = driver.findElements(By.xpath("//div[@class='dropdown open']//ul[@class='dropdown-menu test']/li"));
		
		System.out.println("Size of the dropDown :" +val.size());
		
		for(WebElement elm : val) {
			
			String text = elm.getText();
			
			System.out.println(text);
		}
		
		log.info("Bootstrap Dropdown Method is finished...");
	}

}
