package helperClasses;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		
		if(extent == null){
			
			String location = System.getProperty("user.dir") +"./Reports/STMExtentReport.html";
			
			return createInstance(location);
		}
		else{
			
			return extent;
		}
	}
	
	public static ExtentReports createInstance(String fileName){
		
		extent = new ExtentReports (fileName, true);
		
		extent
                .addSystemInfo("Host Name", "Local Host")
                
                .addSystemInfo("Environment", "QA")
                
                .addSystemInfo("User Name", "Kiran Reddy");
                
                //extent.loadConfig(new File(System.getProperty("user.dir")+"./extent-config.xml"));
		
		return extent;
	}
}
