/**
 * 
 */
package controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.ExceptionUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import utils.ConfigReader;
import utils.ExtentTestManager;
import utils.LogUtil;

/**
 * @Author Chandu
 * @Date 27-08-2022
 */
public class DriverFactory
{
	//Enable for parallel test and enable AfterTest method
	//public ThreadLocal<WindowsDriver<WindowsElement>> wd = new ThreadLocal<WindowsDriver<WindowsElement>>();
	
	//Enable for sequence tests and enable code in ITest Pass and Failed listner
	public  static ThreadLocal<WindowsDriver<WindowsElement>> wd = new ThreadLocal<WindowsDriver<WindowsElement>>();
	//public WindowsDriver<WindowsElement> wd_driver = null;
	
	public String PWD = System.getProperty("user.dir");
		/*
		@Parameters({ "Platform" , "App" })
		@BeforeMethod
		public void beforeMethod() throws Exception
		{		
			String platform=ConfigReader.getValue("Platform");
			String app=ConfigReader.getValue("Windows_App");
			LogUtil.infoLog(getClass(), "Platform: "+platform+"  Windows_App: "+app);
	
			setWinDriver(createDriver(platform,app));			
		}
		 */
		@BeforeMethod
		public void beforeMethod(ITestContext context) throws Exception
		{		
			String platform=ConfigReader.getValue("Platform");
			String app=ConfigReader.getValue("Windows_App");
			LogUtil.infoLog(getClass(), "Platform: "+platform+"  Windows_App: "+app);
	
			setWinDriver(createDriver(platform,app));	
			context.setAttribute("WebDriver", getWinDriver());
		}

	public void setWinDriver(WindowsDriver<WindowsElement> driver) 
	{
		wd.set(driver);
		
	}

	public WindowsDriver<WindowsElement> getWinDriver() 
	{
		return wd.get();
	}
	/*
	 * public void setWinDriver(WindowsDriver<WindowsElement> driver) {
	 * //wd.set(driver); wd_driver=driver; }
	 * 
	 * public WindowsDriver<WindowsElement> getWinDriver() { return wd_driver; }
	 */
	

	//@AfterMethod
	public void afterMethod(ITestResult iTestResult) 
	{
		String testName = iTestResult.getMethod().getConstructorOrMethod().getName();
		ExtentTestManager.getTest().setEndedTime(new Date());
		if(iTestResult.isSuccess()) {
		
    	//Extentreports log operation for passed tests.
    	 ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed : "+iTestResult.getMethod().getMethodName());
    	 LogUtil.infoLog(getClass(),"Test passed : "+iTestResult.getMethod().getMethodName());
		}
        else {
        	
       	 ExtentTestManager.getTest().setEndedTime(new Date());
       	 String  ErrorMsg=ExceptionUtils.getFullStackTrace(iTestResult.getThrowable());
       	 
           //Take base64Screenshot screenshot.
           String base64Screenshot = "data:image/png;base64,"+((TakesScreenshot)getWinDriver()). getScreenshotAs(OutputType.BASE64);
           ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot);
           //Extentreports log and screenshot operations for failed tests.
           ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed : "+iTestResult.getMethod().getMethodName(),ErrorMsg);
           ExtentTestManager.getTest().log(LogStatus.FAIL,"Test Failed : "+iTestResult.getMethod().getMethodName(),
        		   ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
           LogUtil.errorLog(getClass(), ErrorMsg,iTestResult.getThrowable());
       	
           
        }
        getWinDriver().close();
        
    }
  

	public WindowsDriver<WindowsElement> createDriver(String platform, String app) throws Exception 
	{
		WindowsDriver<WindowsElement> driver = null;

		DesiredCapabilities capabilities;
		switch(platform.toLowerCase())
		{
		case "windows 10" :
				capabilities = new DesiredCapabilities();
				//capabilities.setCapability("app","Microsoft.WindowsMaps_8wekyb3d8bbwe!App");
				capabilities.setCapability("app",app);
				
				capabilities.setCapability("platformName", "Windows");
				//capabilities.setCapability("platformVersion", "10");
				capabilities.setCapability("deviceName", "WindowsPC");
				//capabilities.setCapability("appArguments", "MyTestFile.txt");
				//capabilities.setCapability("appWorkingDir", "C:\\MyTestFolder\\");

			try {
				driver = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), capabilities);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
				
			break;
			
		case "windows 11":
			capabilities = new DesiredCapabilities();
			capabilities.setCapability("app","Microsoft.WindowsMaps_8wekyb3d8bbwe!App");

			capabilities.setCapability("platformName", "Windows");
			//capabilities.setCapability("platformVersion", "11");
			capabilities.setCapability("deviceName", "WindowsPC");

			try {
				driver = new WindowsDriver<WindowsElement>(new URL("http://127.0.0.1:4723"), capabilities);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			break;


		default:
			throw new Exception("Please Provide a Valid OS name");
		}
			driver.manage().window().maximize();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			LogUtil.infoLog(getClass(), "  Windows_App: "+app+" application launched succefully: ");
		return driver;		
	}
}
