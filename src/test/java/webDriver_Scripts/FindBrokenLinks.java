package webDriver_Scripts;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import helperClasses.LoggerHelper;
import testBase.TestBase;

public class FindBrokenLinks extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(FindBrokenLinks.class);
	
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

}
