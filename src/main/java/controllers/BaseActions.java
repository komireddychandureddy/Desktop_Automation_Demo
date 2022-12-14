/**
 * 
 */
package controllers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Stack;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

import java.time.Duration;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.ConfigReader;
import utils.ExtentReportsUtil;
import utils.LogUtil;

/**
 * @Author Chandu
 * @Date 15-Aug-2022
 */
public class BaseActions
{
	//public String RESULT_FOLDER_NAME = USERDIR + "\\ExecutionReports\\ExecutionReports";
	//public Duration DEFAULT_WAIT_SECONDS = Duration.ofSeconds(45);
	public String USERDIR =System.getProperty("user.dir");
	public int DEFAULT_WAIT_SECONDS = 45;
	public Robot re;
	public Select se;
	public Actions ac;
	public Alert al;
	public WindowsDriver<WindowsElement> win_driver;
	public String screenshot_path;
	
	public void logStep(String logStep) {
		LogUtil.infoLog(this.getClass(), logStep);
		ExtentReportsUtil.getLogger().info(logStep);
		ExtentReportsUtil.stepInfo(logStep);
	}
	
		
	public void logStepFail(String logStep) {
				   
		LogUtil.infoLog(this.getClass(), "Failed :"+logStep);
		ExtentReportsUtil.stepFail(logStep);
		ExtentReportsUtil.getLogger().error(logStep);
	}
	
	public void logStepPass(String logStep) {
		LogUtil.infoLog(this.getClass(),"Passed: "+ logStep);
		ExtentReportsUtil.stepPass(logStep);
		ExtentReportsUtil.getLogger().info(logStep);
	}
	
	/* To Press ENTER Key using Robot */
	public void hitEnter() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}


	/* To Press BACKSPACE Key using Robot */
	public void hitBackspace() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_BACK_SPACE);
		re.keyRelease(KeyEvent.VK_BACK_SPACE);
	}


	/* To Press DELETE Key using Robot */
	public void hitDelete()
	{
		try {
			re = new Robot();
		} catch (AWTException e) {
		
		}
		re.keyPress(KeyEvent.VK_DELETE);
		re.keyRelease(KeyEvent.VK_DELETE);
	}


	/* To Select all the Text/Web Elements using ROBOT */
	public void selectAll() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_A);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_A);
	}


	/* To Copy all the Selected Text/Web Elements using ROBOT */
	public void copyAll() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_C);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_C);
	}


	/* To Paste all the Selected Text/Web Elements using ROBOT */
	public void pasteAll() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyRelease(KeyEvent.VK_V);
	}


	/* To Capture Screenshot(Replaces if already exists) */
	/*
	 * Also, Make sure that the automation in running in the foreground to
	 * capture the Image of the Browser. Else, It'll capture the open Window
	 */
	public void robotScreenCapture(String robotImageName) throws Exception 
	{
		re = new Robot();
		Rectangle area = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		BufferedImage bufferedImage = re.createScreenCapture(area);
		// Save as PNG
		File file = new File(robotImageName);
		if (file.exists()) {
			file.delete();
		}
		ImageIO.write(bufferedImage, "png", file);
	}


	/* To ZoomOut */
	public void robotZoomOut() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_SUBTRACT);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}


	/* To ZoomIn */
	public void robotZoomIn() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_ADD);
		re.keyRelease(KeyEvent.VK_CONTROL);
	}


	/* To ScrollUp using ROBOT */
	public void robotScrollUp() throws Exception 
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_UP);
		re.keyRelease(KeyEvent.VK_PAGE_UP);
	}


	/* To ScrollDown using ROBOT */
	public void robotScrollDown() throws Exception
	{
		re = new Robot();
		re.keyPress(KeyEvent.VK_PAGE_DOWN);
		re.keyRelease(KeyEvent.VK_PAGE_DOWN);
	}


	/* To ScrollUp using JavaScript Executor */
	public void scrollUp() throws Exception 
	{
		((JavascriptExecutor) win_driver).executeScript("scroll(0, -100);");
	}


	/* To ScrollDown using JavaScript Executor */
	public void scrollDown() throws Exception 
	{
		((JavascriptExecutor) win_driver).executeScript("scroll(0, 100);");
	}


	/* To Move cursor to the Desired Location */
	public void moveCursor(int X_Position, int Y_Position) throws Exception 
	{
		re.mouseMove(X_Position, Y_Position);
	}


	/* To Accept the Alert Dialog Message */
	public void alertAccept() throws Exception
	{
		al = win_driver.switchTo().alert();
		al.accept();
	}


	/* To Dismiss the Alert Dialog Message */
	public void alertDismiss() throws Exception 
	{
		al = win_driver.switchTo().alert();
		al.dismiss();
	}


	/* To Get the Alert Messages */
	public String getAlertText() throws Exception 
	{
		al = win_driver.switchTo().alert();
		String text = al.getText();
		Thread.sleep(2000);
		alertAccept();
		return text;
	}


	/* To Upload a File using JAVA AWT ROBOT */
	public void fileUpload(String FileToUpload) throws Exception 
	{
		Thread.sleep(5000);
		StringSelection filetocopy = new StringSelection(FileToUpload);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filetocopy, null);
		Thread.sleep(1000);
		re = new Robot();
		re.keyPress(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_V);
		re.keyRelease(KeyEvent.VK_CONTROL);
		re.keyPress(KeyEvent.VK_ENTER);
		re.keyRelease(KeyEvent.VK_ENTER);
	}


	/* To Perform a WebAction of Mouse Over */
	public void mousehover(WebElement element) 
	{
		ac = new Actions(win_driver);
		ac.moveToElement(element).build().perform();
	}


	/* To Perform Select Option by Visible Text */
	public void selectByVisibleText(WebElement element, String value)
	{
		se = new Select(element);
		se.selectByVisibleText(value);
	}


	/* To Perform Select Option by Index */
	public void selectByIndex(WebElement element, int value)
	{
		se = new Select(element);
		se.selectByIndex(value);
	}


	/* To Perform Select Option by Value */
	public void selectByValue(WebElement element, String value) 
	{
		se = new Select(element);
		se.selectByValue(value);
	}
	/* To Perform Select Option by Value */
	public WebElement getWebElement(By locator) 
	{
		return win_driver.findElement(locator);
	}

	

	/* To click a certain Web Element */
	public void click(WebElement element,String message) 
	{
		if (element == null) {
			//	LogUtil.infoLog(KeywordUtil.class, message+"  --Fail");
				logStepFail(message+"  --Fail");
				//return false;
			} else {
				//pause(1000);			
				element.click();			
				pause(3000);
				//	LogUtil.infoLog(KeywordUtil.class, message+"  --Pass");
					logStep(message +"  --Pass");
				//return true;
			}
	}

	/* To click a certain Web Element */
	public void click(By locator,String message) 
	{
		WebElement element=getWebElement(locator);
		if (element == null) {
			//	LogUtil.infoLog(KeywordUtil.class, message+"  --Fail");
				logStepFail(message+"  --Fail");
				//return false;
			} else {
				//highLightElement(locator);
				//pause(1000);			
				element.click();			
				pause(2000);
				//	LogUtil.infoLog(KeywordUtil.class, message+"  --Pass");
					logStep(message +"  --Pass");
				//return true;
			}
	}
	
	
	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public void inputText(By locator, String data, String logStep) {
		
		WebElement elm = getWebElement(locator);
		
		if (elm == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(locator);
			elm.clear();
			elm.sendKeys(data);
			logStep(logStep +"  --Pass");
	
		}
	}
	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public void inputText(By locator, Keys key, String logStep) {
		
		WebElement elm = getWebElement(locator);
		
		if (elm == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(locator);
			elm.clear();
			elm.sendKeys(key);
			logStep(logStep +"  --Pass");
	
		}
	}
	/**
	 * @param locator
	 * @param data
	 * @return
	 */
	public void inputText(WebElement elm, String data, String logStep) {
		
		
		if (elm == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			elm.clear();
			elm.sendKeys(data);
			logStep(logStep +"  --Pass");
	
		}
	}

	
	public void selectByVisibleText(By locator, String data, String logStep)
	{
		
		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(locator);
			se = new Select(element);
			se.selectByVisibleText(data);
			logStep(logStep +"  --Pass");
	
		}
		
	}
	public void selectByVisibleText(WebElement element, String data, String logStep)
	{
		
		if (element == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(element);
			se = new Select(element);
			se.selectByVisibleText(data);
			delay(2000);
			logStep(logStep +"  --Pass");
	
		}
		
	}
	
	public void selectByValue(By locator, String data, String logStep)
	{
		
		WebElement element = getWebElement(locator);
		if (element == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(locator);
			se = new Select(element);
			se.selectByValue(data);
			logStep(logStep +"  --Pass");
	
		}
		
	}
	public void selectByValue(WebElement element, String data, String logStep)
	{
		
		if (element == null) {
			logStepFail(logStep+"  --Fail");		
		} else {
			//highLightElement(element);
			se = new Select(element);
			se.selectByValue(data);
			logStep(logStep +"  --Pass");
	
		}
		
	}
	
	/**
	 * @param locator
	 * @return
	 *//*
	public boolean isWebElementVisible(By locator) {
		try{
			
			WebElement elm = getWebElement(locator);
			return elm.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}

	}*/
	/**
	 * @param locator
	 * @return
	 */
	public boolean isWebElementVisible(By locator) {
		try{
			LogUtil.infoLog(BaseActions.class, "Check Element visible: " +locator.toString());
			waitForElement(locator);
			WebElement elm = waitForVisible(locator);
			return elm.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}

	}
	public  boolean isWebElementVisible(By locator,int timeOut) {
		try{
			LogUtil.infoLog(BaseActions.class,"Check Element visible: " +locator.toString());
			waitForElement(locator,timeOut);
			WebElement elm = waitForVisible(locator);
			return elm.isDisplayed();
		}
		catch (Exception e) {
			return false;
		}

	}
	public boolean isWebElementVisible(By locator, String logstep) {
		try{
			LogUtil.infoLog(BaseActions.class,"Check Element visible: " +locator.toString());
			waitForElement(locator);
			WebElement elm = waitForVisible(locator);
			//LogUtil.infoLog(KeywordUtil.class, logstep+" --Pass");
			logStep(logstep+" --Pass");
			return elm.isDisplayed();
		}
		catch (Exception e) {
			//LogUtil.infoLog(KeywordUtil.class, logstep+" --Fail");
			logStepFail(logstep+"  --Fail");
			return false;
		}

	}
	public boolean isWebElementVisible(WebElement element, String logstep) {
		try{
			LogUtil.infoLog(BaseActions.class,"Check Element visible: " +element.toString());
			WebElement elm = waitForVisible(element);
			//LogUtil.infoLog(KeywordUtil.class, logstep+" --Pass");
			logStep(logstep+" --Pass");
			return elm.isDisplayed();
		}
		catch (Exception e) {
			//LogUtil.infoLog(KeywordUtil.class, logstep+" --Fail");
			logStepFail(logstep+"  --Fail");
			return false;
		}

	}
	
	public boolean isWebElementNotPresent1(By locator,String logstep) {
		boolean status=win_driver.findElement(locator).isDisplayed();
		if (!status) {
			
			logStepPass(logstep+"  --Pass");
		//	LogUtil.infoLog(KeywordUtil.class, logstep+"  --Pass");
			return true;
		}else{
			logStepFail(logstep+"  --Fail");
		//LogUtil.infoLog(KeywordUtil.class, logstep+"  --Fail");
		return false;
		}
	}
	
		/**
		 * @param a
		 * @throws InterruptedException
		 */
		public static void pause(long a)  {
			try {
				Thread.sleep(a);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		}
		/*
		 * public void highLightElement(By locator) { WebElement
		 * element=win_driver.findElement(locator); JavascriptExecutor
		 * js=(JavascriptExecutor)win_driver;
		 * 
		 * js.
		 * executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');"
		 * , element);
		 * 
		 * try { Thread.sleep(500); } catch (InterruptedException e) {
		 * 
		 * System.out.println(e.getMessage()); }
		 * 
		 * js.executeScript("arguments[0].setAttribute('style','');", element);
		 * //border: solid 2px white
		 * 
		 * }
		 */
		
		public void highLightElement( By locator) {
			WebElement element=win_driver.findElement(locator);
		    for (int i = 0; i < 2; i++) {
		        JavascriptExecutor js = (JavascriptExecutor) win_driver;
		        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		                element, "color: yellow; border: 2px solid yellow;");
		        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		                element, "");
		    }
		}
		public void highLightElement( WebElement element) {
			//WebElement element=win_driver.findElement(locator);
		    for (int i = 0; i < 2; i++) {
		        JavascriptExecutor js = (JavascriptExecutor) win_driver;
		        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		                element, "color: yellow; border: 2px solid yellow;");
		        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
		                element, "");
		    }
		}
		
		public void highLightElement1(By locator)
		{
			WebElement element=win_driver.findElement(locator);
		JavascriptExecutor js=(JavascriptExecutor)win_driver; 

		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);

		try 
		{
		Thread.sleep(500);
		} 
		catch (InterruptedException e) {

		System.out.println(e.getMessage());
		} 

		js.executeScript("arguments[0].setAttribute('style','');", element); 
		//border: solid 2px white

		}
			
		public boolean  waitUntilElement(By type, int timeOut) {
			int time=0;
			boolean status=false;
			while(time<timeOut)
			{
				status=win_driver.findElements(type).size()>0;
				if(status) {
					status =true;
					//highLightElement(type);
					break;
				}
				time=time+1;
				pause(1000);
			}
			return status;
		}

		//Get Tag name and locator value of Element
		public static String getElementInfo(By locator) throws Exception{
			return " Locator: "+locator.toString();
		}

		public static String getElementInfo(WebElement element) throws Exception{
			String webElementInfo="";
			webElementInfo=webElementInfo +"Tag Name: "+element.getTagName() +", Locator: ["+element.toString().substring(element.toString().indexOf("->")+2);
			return webElementInfo;

		}

		public WebElement waitForClickable(By locator) {
			WebDriverWait wait = new WebDriverWait(win_driver, DEFAULT_WAIT_SECONDS);
			wait.ignoring(ElementNotInteractableException.class);
			wait.ignoring(WebDriverException.class);

			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		}

		/**
		 * @param locator
		 * @return
		 */
		public WebElement waitForPresent(By locator) {
			WebDriverWait wait = new WebDriverWait(win_driver, DEFAULT_WAIT_SECONDS);
			wait.ignoring(ElementNotInteractableException.class);
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		/**
		 * @param locator
		 * @return
		 */
		public WebElement waitForVisible(By locator) {
			WebDriverWait wait = new WebDriverWait(win_driver, DEFAULT_WAIT_SECONDS);
			wait.ignoring(ElementNotInteractableException.class);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		/**
		 * @param locator
		 * @return
		 */
		public WebElement waitForVisible(WebElement element) {
			WebDriverWait wait = new WebDriverWait(win_driver, DEFAULT_WAIT_SECONDS);
			wait.ignoring(ElementNotInteractableException.class);
			return wait.until(ExpectedConditions.visibilityOf(element));
		}



		/**
		 * @param time
		 * @throws InterruptedException
		 */
		public static void delay(long time) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		/**
		 * @param locator
		 * @return
		 */
		public boolean verifyCurrentDateInput(By locator) {
			boolean flag = false;
			WebElement element = waitForVisible(locator);
			String actual = element.getAttribute("VALUE").trim();
			DateFormat dtFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			dtFormat.setTimeZone(TimeZone.getTimeZone("US/Central"));
			String expected = dtFormat.format(date).trim();
			if (actual.trim().contains(expected)) {
				flag = true;

			}
			return flag;
		}

		/**
		 * @param locator
		 * @param data
		 * @return
		 * @throws InterruptedException
		 */
		public boolean uploadFilesUsingSendKeys(By locator, String data) throws InterruptedException {
			WebElement element = waitForVisible(locator);
			element.clear();
			element.sendKeys(System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFiles\\" + data);
			return true;
		}

		/**
		 * @param data
		 * @param page
		 * @param fileName
		 * @return
		 * @throws IOException
		 */
		public boolean verifyPDFData(String data, int page, String fileName) throws IOException {
			FileInputStream fis = null;
			String dwnFile = null;
			try {

				String dirName = USERDIR + "\\src\\test\\resources\\downloadFile\\";
				File dir = new File(dirName);
				File[] path1 = dir.listFiles();

				for (int k = 0; k < path1.length; k++) {
					dwnFile = path1[k].toString();
					if (dwnFile.contains(fileName)) {
						break;
					}

					continue;
				}
				File file = new File(dwnFile);
				fis = new FileInputStream(file.getAbsolutePath());
				PdfReader text = new PdfReader(fis);
				String expected = PdfTextExtractor.getTextFromPage(text, page);

				String[] b = data.split(",");
				fis.close();
				for (int i = 0; i < b.length; i++) {
					if (expected.contains(b[i]))
						continue;
				}
			} catch (Exception e) {
				LogUtil.errorLog(BaseActions.class, e.getMessage(), e);
			}
			return true;
		}

		/**
		 * @return
		 */
		public boolean delDirectory() {
			File delDestination = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\downloadFile");
			if (delDestination.exists()) {
				File[] files = delDestination.listFiles();
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						delDirectory();
					} else {
						files[i].delete();
					}
				}
			}
			return delDestination.delete();
		}

		public void hoverElement(By locator) {
			LogUtil.infoLog(this.getClass(), "Hover Element: "+locator.toString());

			WebElement element = waitForClickable(locator);
			Point p =element.getLocation();
			Actions builder = new Actions(win_driver);
			builder.moveToElement(element, p.getX(), p.getY()).build().perform();
			pause(1000);

		}
		public void hoverbyElement(By locator) {
			LogUtil.infoLog(this.getClass(),"Hover Element: "+locator.toString());
			WebElement element = waitForClickable(locator);		
			Actions builder = new Actions(win_driver);
			builder.moveToElement(element).build().perform();
			pause(1000);

		}
		
		public boolean doubleClick(By locator) {
			boolean result=false;
			try {
				
				LogUtil.infoLog(this.getClass(), "Double click: "+locator.toString());
				WebElement element = win_driver.findElement(locator);
				Actions action = new Actions(win_driver).doubleClick(element);
				action.build().perform();
				result=true;

			} catch (StaleElementReferenceException e) {
				LogUtil.infoLog("DoubleClick",locator.toString()+" - Element is not attached to the page document "
						+ e.getStackTrace());
				result=false;
			} catch (NoSuchElementException e) {
				LogUtil.infoLog("DoubleClick",locator.toString()+" - Element is not attached to the page document "
						+ e.getStackTrace());
				result=false;			
			} catch (Exception e) {
				LogUtil.infoLog("DoubleClick",locator.toString()+" - Element is not attached to the page document "
						+ e.getStackTrace());
				result=false;
			}
			return result;
		}
		
		public boolean switchToFrame(String frameName)
		{

			try
			{
				win_driver.switchTo().frame(frameName);
				return true;
			}
			catch(Exception e)
			{
				LogUtil.infoLog("switchToFrame",frameName+" TO FRAME FAILED"
						+ e.getStackTrace());
				return false;
			}
		}
		public boolean switchToFrame(By locator)
		{

			try
			{
				win_driver.switchTo().frame(win_driver.findElement(locator));
				return true;
			}
			catch(Exception e)
			{
				LogUtil.infoLog("switchToFrame"," TO FRAME FAILED"
						+ e.getStackTrace());
				return false;
			}
		}
		public boolean switchToFrame(int frameName)
		{
			try
			{
				win_driver.switchTo().frame(frameName);
				return true;
			}
			catch(Exception e)
			{
				LogUtil.infoLog("switchToFrame",frameName+" TO FRAME FAILED"
						+ e.getStackTrace());
				return false;
			}
		}
		public boolean switchTodefaultContent()
		{
			try
			{
				win_driver.switchTo().defaultContent();
				
				return true;
			}
			catch(Exception e)
			{
				LogUtil.infoLog("switchToFrame"," default TO FRAME FAILED"					+ e.getStackTrace());
				return false;
			}
		}
		
		public boolean clickAndHold(By locator1, By locator2  ) {
			boolean result=false;
			try {
				//KeywordUtil.lastAction="Double click: "+locator.toString();
			//	LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
				WebElement element1 = win_driver.findElement(locator1);
				WebElement element2 = win_driver.findElement(locator2);
				Actions action = new Actions(win_driver);
				action.clickAndHold(element1).moveToElement(element2).release().build().perform();
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		public boolean rightClick(By locator1) {
			boolean result=false;
			try {
				//KeywordUtil.lastAction="Double click: "+locator.toString();
			//	LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
				WebElement element1 = win_driver.findElement(locator1);
				Actions action = new Actions(win_driver);
				action.contextClick(element1);
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		public boolean rightClickJS(By locator1) {
			boolean result=false;
			try {
				WebElement element = win_driver.findElement(locator1);
				JavascriptExecutor js = (JavascriptExecutor) win_driver;

			String javaScript = "var evt = document.createEvent('MouseEvents');"
			                + "var RIGHT_CLICK_BUTTON_CODE = 2;"
			                + "evt.initMouseEvent('contextmenu', true, true, window, 1, 0, 0, 0, 0, false, false, false, false, RIGHT_CLICK_BUTTON_CODE, null);"
			                + "arguments[0].dispatchEvent(evt)";

			js.executeScript(javaScript, element);
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		public boolean rightClick1(By locator1) {
			boolean result=false;
			try {
				WebElement element = win_driver.findElement(locator1);
				Actions oAction=new Actions(win_driver);
		        oAction.moveToElement(element);
		        oAction.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		
		public boolean clickAndHold2(By locator1, By locator2  ) {
			boolean result=false;
			try {
				//KeywordUtil.lastAction="Double click: "+locator.toString();
			//	LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
				WebElement element1 = win_driver.findElement(locator1);
				WebElement element2 = win_driver.findElement(locator2);
				Actions action = new Actions(win_driver);
				action.clickAndHold(element1).moveToElement(element2).release().build().perform();
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		public boolean dragAndDrop(By locator1, By locator2  ) {
			boolean result=false;
			try {
				//KeywordUtil.lastAction="Double click: "+locator.toString();
			//	LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
				WebElement element1 = win_driver.findElement(locator1);
				WebElement element2 = win_driver.findElement(locator2);
				Actions action = new Actions(win_driver);
				action.dragAndDrop(element1, element2).build().perform();
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		public boolean dragAndDropBy(By locator1, By locator2  ) {
			boolean result=false;
			try {
				//KeywordUtil.lastAction="Double click: "+locator.toString();
			//	LogUtil.infoLog(KeywordUtil.class, KeywordUtil.lastAction);
				WebElement element1 = win_driver.findElement(locator1);
				WebElement element2 = win_driver.findElement(locator2);
				Point classname = element2.getLocation();
		        int xcordi = classname.getX();
		        System.out.println("Element's Position from left side"+xcordi +" pixels.");
		        int ycordi = classname.getY();
		        System.out.println("Element's Position from top"+ycordi +" pixels.");
		        
				Actions action = new Actions(win_driver);
				action.dragAndDropBy(element1,xcordi,ycordi).build().perform();
				result=true;
			} catch (Exception e) {
				result=false;	
			}
			return result;
		}
		
		public int getCoordinates(By locator) throws Exception {
			  //Locate element for which you wants to retrieve x y coordinates.
			       WebElement Image = win_driver.findElement(locator);
			       //Used points class to get x and y coordinates of element.
			        Point classname = Image.getLocation();
			        int xcordi = classname.getX();
			        System.out.println("Element's Position from left side"+xcordi +" pixels.");
			        int ycordi = classname.getY();
			        System.out.println("Element's Position from top"+ycordi +" pixels.");
			        
				 return xcordi+ycordi;
			 
			}
		
		public int getElementSize(By locator) throws Exception {
			  //Locate element for which you wants to get height and width.
			        WebElement Image = win_driver.findElement(locator);

			        //Get width of element.
			        int ImageWidth = Image.getSize().getWidth();
			        System.out.println("Image width Is "+ImageWidth+" pixels");

			        //Get height of element.
			        int ImageHeight = Image.getSize().getHeight();        
			        System.out.println("Image height Is "+ImageHeight+" pixels");
			 return ImageWidth+ImageHeight;
			}
		
		public int getElementXSize(By locator) throws Exception {
			  //Locate element for which you wants to get height and width.
			        WebElement Image = win_driver.findElement(locator);

			        //Get width of element.
			        int ImageWidth = Image.getSize().getWidth();
			        System.out.println("Image width Is "+ImageWidth+" pixels");

			    
			 return ImageWidth;
			}
		public int getElementYSize(By locator) throws Exception {
			  //Locate element for which you wants to get height and width.
			        WebElement Image = win_driver.findElement(locator);

			       		        //Get height of element.
			        int ImageHeight = Image.getSize().getHeight();        
			        System.out.println("Image height Is "+ImageHeight+" pixels");
			 return ImageHeight;
			}
		
		
		
	
		public  String getRandomString(String data) {
	        // String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	         String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";
	         
	         StringBuilder salt = new StringBuilder();
	         Random rnd = new Random();
	         while (salt.length() < 10) { // length of the random string.
	             int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	             salt.append(SALTCHARS.charAt(index));
	         }
	         String saltStr = salt.toString();
	         return data+saltStr;

	     }
		
		public  int  getRandomNumber(int min, int max ) {
			Random rand = new Random();
			return  rand.nextInt(max) +min;	
		}
		
		
		public String todayDate() {
			Calendar cal = Calendar.getInstance();
		    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
		    String date = format1.format(cal.getTime());
		   return date;
			
			}
		public String getTodayDate() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			String todatdate=sdf.format(date);
			return todatdate;			
			}
		
		public String getTodayDate(String dateformat) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
			String todatdate=sdf.format(date);
			return todatdate;			
			}
		
		public String getYearDate(String dateformat) {	
			Calendar c = Calendar.getInstance();
					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.YEAR, 1); // Adds 365 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			 String date = format1.format(c.getTime());
			   return date;		
			}
	public String getTomorrowDate(String dateformat) {
			
			Calendar c = Calendar.getInstance();
					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.DATE, 1); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			 String date = format1.format(c.getTime());
			   return date;		
			}
	public String getweekDate(String dateformat) {
		
		Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Now use today date.
				c.add(Calendar.DATE, 7); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		 String date = format1.format(c.getTime());
		   return date;		
		}
	public String getFutureDateTime(String dateformat, int days) {
		
		Calendar c = Calendar.getInstance();
				c.setTime(new Date()); // Now use today date.
				c.add(Calendar.DATE, days); // Adds 7 days
		SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
		 String date = format1.format(c.getTime());
		   return date;		
		}

		public String getTodayDateTime() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");  
			LocalDateTime now = LocalDateTime.now();  
			return dtf.format(now);		
		}
		
		public String getTodayDateOnly() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
		    LocalDate today = LocalDate.now();
			return dtf.format(today);		
		}
		public String getFutureDate(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDate today = LocalDate.now();
		    LocalDate future = today.plus(value, ChronoUnit.DAYS);
			return dtf.format(future);		
		}
		public String getPastDate(int value) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
			LocalDate today = LocalDate.now();
			LocalDate past = today.minusDays(value); 
			return dtf.format(past);		
		}
	 
		public String getWeekDate(String dateformat) {
			
			Calendar c = Calendar.getInstance();
					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.DATE, 7); // Adds 7 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			 String date = format1.format(c.getTime());
			   return date;		
			}
		public String getTwoWeekDate(String dateformat) {
			
			Calendar c = Calendar.getInstance();
					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.DATE, 14); // Adds 4 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			 String date = format1.format(c.getTime());
			   return date;		
			}
	public String getTenDaysDate(String dateformat) {
			
			Calendar c = Calendar.getInstance();
					c.setTime(new Date()); // Now use today date.
					c.add(Calendar.DATE, 10); // Adds 4 days
			SimpleDateFormat format1 = new SimpleDateFormat(dateformat);
			String date = format1.format(c.getTime());
			   return date;		
			}
		public String getTime(String dateformat) {
			DateFormat dateFormat = new SimpleDateFormat(dateformat);
			Date date = new Date();
			return dateFormat.format(date);		
			}
		public String getCurrentTime() {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			Date date = new Date();
			return dateFormat.format(date);		
			}
		public String getCurrentMonth() {	
			Calendar cal = Calendar.getInstance();	
			String currentMonth =new SimpleDateFormat("MMM").format(cal.getTime());
			//System.out.println("currentMonth------------------"+currentMonth);
			return currentMonth;	
			}	public String getCurrentYear() {
			Calendar cal = Calendar.getInstance();	
			String currentyear =new SimpleDateFormat("YYYY").format(cal.getTime());
			//System.out.println("currentyear"+currentyear);
			return currentyear;	
			}
			public String getDate() {
			Calendar cal = Calendar.getInstance();	
			String curerntdate =new SimpleDateFormat("dd").format(cal.getTime());
			//System.out.println(curerntdate);
			return curerntdate;
			}
		public  String getRandomEmail(String data) {
	        // String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	         String SALTCHARS = "abcdefghijklmnopqrstuvwxyz";      
	         StringBuilder salt = new StringBuilder();
	         Random rnd = new Random();
	         while (salt.length() < 10) { // length of the random string.
	             int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	             salt.append(SALTCHARS.charAt(index));
	         }
	         String saltStr = salt.toString();
	         return data+saltStr+getRandomNumber(100,99999)+"@malinator.com";

	     }
		
		public boolean waitForElement(By locator, int maxtime) {
	        int time=0;
	         boolean status=false;
	         while (time < maxtime) {       	 
	        	 status=win_driver.findElements(locator) .size()>0;
	        	 if(status) {
	        		 status=true;   
	        		 break;
	        	 }
	        	 time=time+2;
	        	 pause(2000);    
	         }        
	         return status;
	     }
		public boolean waitForElement(By locator) {
	        int time=0;
	         boolean status=false;
	         while (time < 20) {       	 
	        	 status=win_driver.findElements(locator) .size()>0;
	        	 if(status) {
	        		 status=true;
	        		 //highLightElement(locator);
	        		 break;
	        	 }
	        	 time=time+1;
	        	 pause(1000);    
	         }        
	         return status;
	     }
		
		
		/*public static void alertAccept() {
			driver.switchTo().alert().accept();
			LogUtil.infoLog(KeywordUtil.class, "Alert accepted");
			
		}
		public static void alertDismiss() {
			driver.switchTo().alert().dismiss();
			LogUtil.infoLog(KeywordUtil.class, "Alert dismissed");
		}*/
		public  String alertText() {
			String text= win_driver.switchTo().alert().getText();
			LogUtil.infoLog(this.getClass(), text);
			return text;
		}
		
		
		
		
		public static boolean deleteFile(String path) {
			try{
	    		
	    		File file = new File(path);
	        	
	    		if(file.exists()){
	    			file.delete();
	    			System.out.println(file.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
	    	   
	    	}catch(Exception e){
	    		
	    		e.printStackTrace();
	    		
	    	}
			return true;
		}
		
		
		public  void scrollTo(By locator) {
			
	        ((JavascriptExecutor) win_driver).executeScript(
	                "arguments[0].scrollIntoView();", win_driver.findElement(locator));
	    }
		public  void scrollToBottom() {
	        ((JavascriptExecutor) win_driver)
	                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    }
		
		public void scrollingByCoordinatesofAPage(int x, int y) {
			((JavascriptExecutor) win_driver).executeScript("window.scrollBy("+x+","+y+")", "");
		}
		
		public void scrollingByCoordinatesofAPage1(int x, int y) {
			((JavascriptExecutor) win_driver).executeScript("scroll("+x+","+y+");");
		}
	/* To click a certain Web Element using DOM/ JavaScript Executor */
	public void JSclick(WebElement element) 
	{
		((JavascriptExecutor) win_driver).executeScript("return arguments[0].click();", element);
	}


	/* To Type at the specified location */
	public void sendKeys(WebElement element, String value) 
	{
		element.sendKeys(value);
	}


	/* To Clear the content in the input location */
	public void clear(WebElement element) 
	{
		element.clear();
	}


	/* To Drag and Drop from Source Locator to Destination Locator */
	public void dragandDrop(WebElement Source, WebElement Destination)
	{
		ac = new Actions(win_driver);
		ac.dragAndDrop(Source, Destination);
	}


	/*To Drag from the given WebElement Location and Drop at the given WebElement location */
	public void dragandDropTo(WebElement Source, int XOffset, int YOffset) throws Exception 
	{
		ac = new Actions(win_driver);
		ac.dragAndDropBy(Source, XOffset, YOffset);
	}


	/*To Open a Page in New Tab */
	public void rightClick(WebElement element) 
	{
		ac = new Actions(win_driver);
		ac.contextClick(element);
		ac.build().perform();	
	}


	/*To Close Current Tab */
	public void closeCurrentTab() 
	{
		win_driver.close();		
	}


	/*To Perform Click and Hold Action */
	public void clickAndHold(WebElement element)
	{
		ac = new Actions(win_driver);
		ac.clickAndHold(element);
		ac.build().perform();
	}


	/*To Perform Click and Hold Action */
	public void doubleClick(WebElement element)
	{
		ac = new Actions(win_driver);
		ac.doubleClick(element);
		ac.build().perform();
	}


	/*To Switch To Frame By Index */
	public void switchToFrameByIndex(int index) throws Exception
	{
		win_driver.switchTo().frame(index);
	}


	/*To Switch To Frame By Frame Name */
	public void switchToFrameByFrameName(String frameName) throws Exception
	{
		win_driver.switchTo().frame(frameName);
	}


	/*To Switch To Frame By Web Element */
	public void switchToFrameByWebElement(WebElement element) throws Exception
	{
		win_driver.switchTo().frame(element);
	}


	/*To Switch out of a Frame */
	public void switchOutOfFrame() throws Exception
	{
		win_driver.switchTo().defaultContent();
	}


	/*To Get Tooltip Text */
	public String getTooltipText(WebElement element)
	{
		String tooltipText = element.getAttribute("title").trim();
		return tooltipText;
	}


	/*To Close all Tabs/Windows except the First Tab */
	public void closeAllTabsExceptFirst() 
	{
		ArrayList<String> tabs = new ArrayList<String> (win_driver.getWindowHandles());
		for(int i=1;i<tabs.size();i++)
		{	
			win_driver.switchTo().window(tabs.get(i));
			win_driver.close();
		}
		win_driver.switchTo().window(tabs.get(0));
	}
	
	
	/*To Print all the Windows */
	public void printAllTheWindows() 
	{
		ArrayList<String> al = new ArrayList<String>(win_driver.getWindowHandles());
		for(String window : al)
		{
			System.out.println(window);
		}
	}

	public void navigateToUrl(String url) {                                          
		
		win_driver.navigate().to(url);
	}

	public String getCurrentUrl() {
		return win_driver.getCurrentUrl();
	}

	
	
	
		/**
		 * @param driver
		 * @param testCaseID
		 * @return
		 * @throws IOException
		 */
		public String takeScreenshotAshot(WindowsDriver driver, String testCaseID) throws IOException {
			
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeStamp = dateFormat.format(date);
			timeStamp=timeStamp+"_";
			
			String path = USERDIR+ ConfigReader.getValue("screenshotPath") + "\\" +timeStamp+ testCaseID + ".jpg";
			// Screenshot screenshot = new AShot().shootingStrategy(new
			// ViewportPastingStrategy(100)).takeScreenshot(driver);

			Screenshot screenshot = new AShot().takeScreenshot(driver);
			File src = new File(path);
			LogUtil.infoLog(BaseActions.class, "Screenshot image path: " + src.getPath());
			ImageIO.write(screenshot.getImage(), "PNG", src);
			return ConfigReader.getValue("screenshotPath") + "\\" +timeStamp+ testCaseID + ".jpg";
		}
		public String takeScreenshot(WindowsDriver driver, String testCaseID)  {
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeStamp = dateFormat.format(date);
			timeStamp=timeStamp+"_";			
			String path = USERDIR + ConfigReader.getValue("screenshotPath") + "\\" +timeStamp+ testCaseID + ".jpg";	
			 //take screenshot of the page
			        File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			          try {
						FileUtils.copyFile(screenshot, new File(path));
						LogUtil.infoLog(BaseActions.class, "Screenshot path: " + path);
					} catch (IOException e) {
						LogUtil.infoLog(BaseActions.class, "Failed while taking screenshot image path: " + path);
						e.printStackTrace();
					}  
			         
			return path;
		}
		public String takeScreenshotWebElement(WebElement element, String testCaseID) throws IOException {
			
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String timeStamp = dateFormat.format(date);
			timeStamp=timeStamp+"_";			
			String path = USERDIR + "\\ExecutionReports\\HtmlReport\\"
					+ ConfigReader.getValue("screenshotPath") + "\\" +timeStamp+ testCaseID + ".jpg";				
			Screenshot screenshot = new AShot().
					shootingStrategy(ShootingStrategies.simple()).
					takeScreenshot(win_driver, element);
			File src = new File(path);
			LogUtil.infoLog(BaseActions.class, "Screenshot image path: " + src.getPath());
			ImageIO.write(screenshot.getImage(), "PNG", src);
			return ConfigReader.getValue("screenshotPath") + "\\" +timeStamp+ testCaseID + ".jpg";
			
		}
		
		
	
}
