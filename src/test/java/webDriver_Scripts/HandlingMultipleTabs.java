package webDriver_Scripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import helperClasses.LoggerHelper;
import testBase.TestBase;

public class HandlingMultipleTabs extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(HandlingMultipleTabs.class);
		
		@Test
		public void handlingMultipleWindows() throws Exception {
		
			driver.get("http://only-testing-blog.blogspot.in/2014/01/textbox.html");
			
			driver.findElement(By.xpath("//b[contains(.,'Open New Page')]")).click();
			
			//Switch to window2(child window) and performing actions on it.
			switchToWindow(1);
			
			  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("My Name");
			  
			  driver.findElement(By.xpath("//input[@value='Bike']")).click();
			  
			  driver.findElement(By.xpath("//input[@value='Car']")).click();
			  
			  driver.findElement(By.xpath("//input[@value='Boat']")).click();
			  
			  driver.findElement(By.xpath("//input[@value='male']")).click();
			  
			  Thread.sleep(5000);
			  
			//Switch to window1(parent window) and performing actions on it.
			  switchToWindow(0);
			  
			  driver.findElement(By.xpath("//option[@id='country6']")).click();
			  driver.findElement(By.xpath("//input[@value='female']")).click();
			  driver.findElement(By.xpath("//input[@value='Show Me Alert']")).click();
			  driver.switchTo().alert().accept();
			  Thread.sleep(5000);
			  
			  //Once Again switch to window2(child window) and performing actions on it.
			  switchToWindow(1);
			  driver.findElement(By.xpath("//input[@name='fname']")).clear();
			  driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("Name Changed");
			  Thread.sleep(5000);
			  driver.close();
			  
			  
			  //Once Again switch to window1(parent window) and performing actions on it.
			  switchToWindow(0);
			  driver.findElement(By.xpath("//input[@value='male']")).click();
			  Thread.sleep(5000);
			  
			  log.info("Handling Multiple Tabs is finished...");	
		}	
		
		public void switchToWindow(int index) {
			
            Set<String> allTabs = driver.getWindowHandles();
			
			Iterator<String> itr = allTabs.iterator();
			
			ArrayList<String> ids = new ArrayList<String>();
			
			while(itr.hasNext()) {
				
				ids.add(itr.next());
			}	
			
			driver.switchTo().window(ids.get(index));
		}
}
