package team1.cabBooking;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.Status;

import junit.framework.Assert;
import team1.cabBooking.*;

/*Choose the desired CabType
 * Click book now*/
public class CabPage extends BaseUI {
	// public void CabPage(){
	//
	// String Expected_Title="Online Cab Booking - Book Outstation Cabs at
	// Lowest Fare @ MakeMyTrip";
	// String Actual_Title=Title();
	// Assert.assertEquals(Actual_Title, Expected_Title);
	//
	// }

	public void setCabType(String cab_type) {
		// To select SUV cab
		logger = report.createTest("my second test");

		logger.log(Status.INFO, "the cabpage is opened");
		try {
			for (int i = 1; i < 20; i++) {
				String type = driver.findElement(By.xpath("//li[" + i + "]/div[2]/div[1]/div[1]/div[1]/p[2]"))
						.getText();
				if (type.equalsIgnoreCase(cab_type)) {
					// To click Book Now
					driver.findElement(By.xpath("//li[" + i + "]/div[3]/a[1]/span[1]")).click();
					break;
				}
			}
		}

		catch (Exception e) {
			reportFail(e.getMessage());
		}
		// To Agree Terms and Conditions
		driver.findElement(By.xpath("//input[@id='epass']")).click();
		// To click Continue
		driver.findElement(By.xpath("//button[@class='tncBtn pushRight']")).click();

		// logger.log(Status.PASS,"the testcase got passed");
		reportPass("the complete testcase got passed");
		closeBrowser();

	}

}
