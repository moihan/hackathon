package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.io.Files;

import team1.cabBooking.*;

public class BaseUI {

	public static  WebDriver driver;
    //public static Properties prop;
   public ExtentReports report = reportmanager.getReportInstance();
    public static ExtentTest logger;
    
    	public  void invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\Users\\ELCOT\\git\\hackathon\\cabBooking\\src\\test\\java\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						"C:\\Users\\ELCOT\\workspace\\project\\src\\test\\java\\makemytrip\\driver\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("ie ")) {
				System.setProperty("webdriver.ie.driver",
						"C:\\Users\\ELCOT\\workspace\\project\\src\\test\\java\\makemytrip\\driver\\msedgedriver.exe");
				driver = new InternetExplorerDriver();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Invalid Browser " + browserName);
		}

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
//		if(prop==null){
//			prop=new Properties();
//			 FileInputStream file;
//			try {
//				file = new FileInputStream("C:\\Users\\ELCOT\\workspace\\final\\src\\main\\java\\makemytrip\\projectConfig.properties");
//				prop.load(file);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			}
	}

//	public static void openURL(String websiteURLKey) {
//		try {
//			driver.get(prop.getProperty(websiteURLKey));
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail("Invalid URL " + websiteURLKey);
//		}
//	}
    	public void reportFail(String reportString){
    		logger.log(Status.FAIL,reportString);
    		takeScreenShot(driver);
    		Assert.fail(reportString);
    		closeBrowser();
    		
    	}
        public void reportPass(String reportString){
        	logger.log(Status.PASS,reportString);
        	
        }
    
    	
    	public static void openURL(String websiteURL) {
    		try {
    			driver.get((websiteURL));
    		} catch (Exception e) {
    			e.printStackTrace();
    			Assert.fail("Invalid URL " + websiteURL);
    			
    		}
    	}


	// To take screenshot

	public static void takeScreenShot(WebDriver driver) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String dateTime = dtf.format(now);
		String path = (System.getProperty("user.dir") +"//target"+dateTime+".png");
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(path));
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") +"//target"+dateTime+".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	// To get page title
	public String Title() {
		return driver.getTitle();
	}

	// To get text
	public  String getText(String path) {
		return driver.findElement(By.xpath(path)).getText();
	}

	// To close the browser
	public  void closeBrowser() {
		driver.close();
	}

	// To quit the browser
	public  void quitBrowser() {
		driver.quit();

}

	// To enter date
//	public  void enterText(String xpathKey, String data) {
//		driver.findElement(By.xpath(prop.getProperty(xpathKey))).sendKeys(data);
//	}
	public  void toEnter(By path, String data) {
		driver.findElement(path).sendKeys(data);
	}

	// To click the web element
//	public  void elementClick(String pathKey) {
//		try {
//			driver.findElement(By.xpath(prop.getProperty(pathKey))).click();
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail("No clickable element " + pathKey);
//		}
//		
//	}
	public  void elementClick(String path) {
		try {
			driver.findElement(By.xpath(path)).click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("No clickable element " + path);
		}
		
	}
	
	

	
	public  void toClick(By path) {
		try {
			driver.findElement(path).click();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("No clickable element " + path);
		}
	
	

}
	




}