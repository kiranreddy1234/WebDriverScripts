package testCases;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import helperClasses.LoggerHelper;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import testBase.TestBase;

public class AllTestCases extends TestBase {
	 
	private final Logger log = LoggerHelper.getLogger(AllTestCases.class);
	
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
	
	@Test
	public void dropdownMethod() {
		
		driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
		
		WebElement elm = driver.findElement(By.xpath("//select[@id='select-de']"));
		
		Select dropDown = new Select(elm);
		
		List<WebElement> allValues = dropDown.getOptions();
		
		System.out.println("Size of the dropDown : " + allValues.size());
		
		for(WebElement val : allValues) {
			
			String str = val.getText();
			
			System.out.println(str);
		}
	
		log.info("Get all dropDown Values is finished... ");
	}
	
	@Test
	public void brokenLinkMethod() {
		
		driver.get("https://anyxxx.com/");
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		System.out.println("Total available links are :"+links.size());
		
		for(WebElement elm : links) {
			
			String url = elm.getAttribute("href");
			
			verifyLinkActive(url);
			
			System.out.println(url);
		}
		
		log.info("Broken links are there in a webpage ");
	}
	
	public static void verifyLinkActive(String linkUrl) {
		
		try {
			
			URL url = new URL(linkUrl);
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			connection.setConnectTimeout(3000);
			
			connection.connect();
			
			if(connection.getResponseCode() == 200) {
				
				System.out.println(linkUrl+"_"+connection.getResponseMessage());
			}
			
			if(connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				
				System.out.println(linkUrl+"_"+connection.getResponseMessage()+"_"+ HttpURLConnection.HTTP_NOT_FOUND);
			}
		}
		catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
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
