package testBase;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import helperClasses.ExtentManager;
import helperClasses.LoggerHelper;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class TestBase {
	
	public WebDriver driver;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public static File reportDirectery;
	public ExtentReports extent;
	public ExtentTest test;
	
	@BeforeSuite
	public void beforeSuite() throws Exception{
		
		extent = ExtentManager.getInstance();
	}
	
	@BeforeTest     //BeforeClass
	public void beforeTest() throws Exception{
		
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
        driver.manage().window().maximize();
		
		reportDirectery = new File("./ScreenShots/");
		
		log.info("Initialize WebDriver: "+ driver.hashCode());
		
		log.info("Initialize Browser : Chrome ");
		
		test = extent.startTest(getClass().getSimpleName());
	}
	
	
	@BeforeMethod
	public void beforeMethod(Method method){
		
		test.log(LogStatus.INFO, method.getName()+"**************test started***************");
		
		log.info("**************"+method.getName()+"  Started***************");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
	    
		if(result.getStatus() == ITestResult.FAILURE){
			
			test.log(LogStatus.FAIL, result.getName() + " - Test Case is Failed");
			
			test.log(LogStatus.FAIL, result.getThrowable() + " - Test Case is Failed");
			 
			String screenshotPath1 = getFullPageScreenshot(driver, result.getName());
			
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath1));
	
			//test.addScreenCapture(screenshotPath1);
			
			log.info("capturing Failed Test screen...");
		}
		else if(result.getStatus() == ITestResult.SKIP){
			
			test.log(LogStatus.SKIP, result.getName() + " - Test Case Skipped"); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, result.getName()+" Test Case PASSED");
		}
		
		log.info("**************"+result.getName()+"  Finished ***************");
		
	}
	
	public static String getFullPageScreenshot(WebDriver driver, String screenshotName) throws Exception {
		
		if(driver == null){
			
			return null;
		}
		
		if(screenshotName==""){
			
			screenshotName = "blank";
		}
		
		Reporter.log("captureScreen method called");
		
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
        Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        
        String destination1 = System.getProperty("user.dir")+ "./ScreenShots/" + screenshotName + dateName+".png";
        
        ImageIO.write(screenshot.getImage(),"PNG",new File(destination1));
		
		return destination1;
	}
	
	@AfterTest  //AfterClass
	public void afterTest() throws Exception{
		
		if(driver!=null){
			
			driver.quit();
		}
		
		extent.flush();
	}
	
}
