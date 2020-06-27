package team1.cabBooking;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Test;

import team1.cabBooking.*;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPage extends BaseUI{
	
	@Test(dataProvider = "data")
	public void TestOne(String browser, String fcity, String tcity, String date, String time, String cab_type) throws Exception {
		TravelPage travelpage = new TravelPage();
		CabPage cabpage = travelpage.bookCab(browser, fcity, tcity, date, time);
		cabpage.setCabType(cab_type);
	}

	@Test(dataProvider = "data2")
	public void bookGift(String browserName, String money, String date, String name, String email_id) throws IOException, InterruptedException {
		GiftPage gift = new GiftPage();
		 gift.bookGift(browserName, money, date, name, email_id);
		HotelPage hotel = new HotelPage();
		 hotel.getAdultCount(browserName);
		 quitBrowser();
	}

	
	

@AfterTest
	public void aftertest() {
		//Quit the Browser
		driver.quit();
       report.flush();
		
	}

	
	@DataProvider(name = "data")
	public Object[][] Test(){
		return TestDataProvider.getTestData("TestData-3.xlsx", "TestData");
	}

	@DataProvider(name = "data2")
	public Object[][] Test2(){
		return TestDataProvider.getTestData("TestDataGift-1.xlsx", "TestDataGift");
	}
	
}
