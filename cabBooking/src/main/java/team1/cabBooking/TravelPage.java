package team1.cabBooking;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


/*TRAVEL PAGE
 * To Launch the Browser
 * To Launch the Website
 *  Click cab button
 * Click  Outstationed oneway button
 * From=fcity
 * TO=tcity
 * Departure = date
 * Pickup time =time
 * Call cab page*/
public class TravelPage extends BaseUI {

	
	// LOCATORS
	
	By cab=By.xpath("//span[@class='chNavIcon appendBottom2 chSprite chCabs']");
	By outstation=By.xpath("//li[@class='selected']");
	By fromcity=By.xpath("//input[@id='fromCity']");
	By tocity=By.xpath("//input[contains(@placeholder,'To')]");
	By next_month=By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");
	By prev_month=By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']");
	By search_btn=By.xpath("//a[@class='primaryBtn font24 latoBold widgetSearchBtn']");
	
	
	
//	public void TravelPage(){
//		
//		String Expected_Title="MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
//		String Actual_Title=Title();
//		Assert.assertEquals(Actual_Title, Expected_Title);
//		
//	}
	
	public CabPage bookCab(String browserName, String fcity, String tcity, String date, String time) throws IOException, InterruptedException {

		logger=report.createTest("my first test");
		// To launch browser
		invokeBrowser(browserName);
		//logger.log(Status.INFO, "intiallizing browser");
		reportPass("initializing browser");
		
		// To launch url
		String url = "http://makemytrip.com";
		openURL(url);
		//logger.log(Status.INFO,"opening the url");
		reportPass("opening the url");
		Thread.sleep(5000);
		
	//to get rid of alert
		driver.findElement(By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser']")).click();
		
		// To click CAB button
		toClick(cab);
		//logger.log(Status.INFO, "clicking the cab button");
		reportPass("clicking the cab button");
		
		// To click OutStationed Oneway radio button
		toClick(outstation);
		//logger.log(Status.INFO, "clicking the outstation oneway");
		reportPass("clicking the outstation oneway");
		// To enter city in From box
		try {
			toEnter(fromcity, fcity);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			// To check for valid city
			if (getText("//li[@id='react-autowhatever-1-section-0-item-0']").equalsIgnoreCase("No Data Found")) {
				System.out.println("Invalid " + fcity);
			    
			} else {
				// To select city from dropdown
				elementClick("//li[@id='react-autowhatever-1-section-0-item-0']");
							
				//logger.log(Status.INFO, "entering the from box");
				reportPass("the city is entered");
			}
		} catch (Exception e1) {
			reportFail(e1.getMessage());
		}

		// To enter city in To box
		try {
			toEnter(tocity, tcity);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(2000);
			// To check for valid city
			if (getText("//li[@id='react-autowhatever-1-section-0-item-0']").equalsIgnoreCase("No Data Found")) {
				 Assert.fail("Invalid " + tcity);
			} else {
				// To select city from dropdown
				elementClick("//li[@id='react-autowhatever-1-section-0-item-0']");
				//logger.log(Status.INFO, "entering the to city");
				reportPass("the to city is entered");
			}
		} catch (Exception e1) {
			reportFail(e1.getMessage());
		}

		// To select desired date

		// To choose desired month year.
		// To choose desired month year.
		//logger.log(Status.INFO, "selecting the date");
		Date currentdate = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date expectedDate = dateformat.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);

			String expectedMonthYear = month + " " + year;

			// To click month
			while (true) {
				String displayDate = getText(
						"//div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/div[1]/div[1]");
				if (expectedMonthYear.equals(displayDate)) {
					break;
				} else if (expectedDate.compareTo(currentdate) > 0) {
					elementClick("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']");
				} else {
					elementClick("//span[@class='DayPicker-NavButton DayPicker-NavButton--prev']");
				}
			}
		} catch (ParseException e) {
			reportFail(e.getMessage());
		}

		// To get xpath for date
		try {
			String input_date = date;
			SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
			Date dt1 = format1.parse(input_date);
			DateFormat format2 = new SimpleDateFormat("EEE");
			String finalDay = format2.format(dt1);

			Date expectedDate = dateformat.parse(date);

			String day = new SimpleDateFormat("dd").format(expectedDate);
			String month = new SimpleDateFormat("MMM").format(expectedDate);
			String year = new SimpleDateFormat("yyyy").format(expectedDate);
			String xDate = finalDay + " " + month + " " + day + " " + year;

			// To click date
			elementClick("//div[@aria-label=\"" + xDate + "\"]");
			reportPass("the date is clicked");
		} catch (ParseException e) {
			reportFail(e.getMessage());
		}
		// To click Pickup-time
		try {
			String str = time;
			String[] array = str.split(" ");

			WebElement element = driver.findElement(By.xpath("//div[contains(@class,'csw_inputBox timePicker')]//li[text()='"
					+ array[0] + "' and text()='" + array[1] + "']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
			element.click();
			reportPass("the pick up time is clicked");
		} catch (Exception e) {
			System.out.println("Invalid " +time+ " pickup-time");
			reportFail(e.getMessage());		}
		

		// To click search button
		toClick(search_btn);
		logger.log(Status.INFO, "the search button is clicked");
		return PageFactory.initElements(driver, CabPage.class);
		
	}


}