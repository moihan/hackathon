package team1.cabBooking;


import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import team1.cabBooking.*;

public class HotelPage extends BaseUI {
	
// HotelPage(){
//		
//		String Expected_Title="MakeMyTrip.com: Save upto 60% on Hotel Booking 4,442,00+ Hotels Worldwide";
//		String Actual_Title=Title();
//		Assert.assertEquals(Actual_Title, Expected_Title);
//		
//	}

	By hotel_btn=By.xpath("//li[@class='menu_Hotels']");
	By rooms=By.xpath("//input[@id='guest']");
	public void getAdultCount(String browserName) {
	
		logger=report.createTest("hotel test");
		
		// To launch browser
		invokeBrowser(browserName);
		logger.log(Status.INFO,"the browser is opened");
		// To launch url
		String url = "http://makemytrip.com";
		openURL(url);
		logger.log(Status.INFO,"the valid url is entered");
		//to get rid of alert
				driver.findElement(By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser']")).click();
			
		// to click Hotel
		toClick(hotel_btn);
		logger.log(Status.INFO,"the hotel button is clicked");
		// to click rooms
		toClick(rooms);
		logger.log(Status.INFO,"the room button is clicked");
		// to get data
		driver.findElement(By.xpath("//input[@id='guest']")).click();
		String adult=driver.findElement(By.xpath("//div[@class='hsw_inner']//ul[1]")).getText();
		System.out.println ("The numbers for Adult persons\n"
				+adult);
		logger.log(Status.INFO,"the number of adultperson is printed");
		// //to print dropdown
	
		driver.findElement(By.xpath("//input[@id='city']")).click();
		List<WebElement> cities = driver.findElements(By.xpath("//div[@id='react-autowhatever-1']//div[1]"));
		for (WebElement element : cities) {
			System.out.println(element.getText());
		}
		logger.log(Status.INFO,"the cities dropdown is printed");

		// to scroll down & up
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		logger.log(Status.INFO,"the scrollup scrolldown is done");
		// Navigate back to home page
		driver.navigate().back();
		
		//Quit browser
		driver.quit();

		logger.log(Status.PASS,"the hotelpage is passed");
	}

}
