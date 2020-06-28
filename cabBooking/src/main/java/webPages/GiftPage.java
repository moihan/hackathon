package webPages;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import Util.BaseUI;
import team1.cabBooking.*;


/*To Launch Browser
 *To Launch Website
 *Click giftcard
 *Click groupgifting
 *Enter money
 *Enter date
 *Agree terms&conditions
 *Click next
 *Enter name
 *Enter email
 *Click next
 *Print alert message*/

public class GiftPage extends BaseUI {	
//
//GiftPage(){
//		
//		String Expected_Title="Gift Cards - Buy Gift Vouchers Online, Gift Vouchers | MakeMyTrip.com";
//		String Actual_Title=Title();
//		Assert.assertEquals(Actual_Title, Expected_Title);
//		
//	}
 


	By gift_btn=By.xpath("//li[@class='menu_Giftcards']//a[@class='makeFlex hrtlCenter column']");
	By groupgift=By.xpath("//li[@class='noPadding']//a[contains(text(),'Group Gifting')]");
	By amt=By.xpath("//input[@id='amount']");
	By Date=By.xpath("//span[@class='input-group-addon']");
	By toagree=By.xpath("//div[@id='form1']//div[@class='col-sm-12 col-md-12 col-lg-12']//input[1]");
	By next1=By.xpath("//button[@id='next1']");
	By Name=By.xpath("//input[@id='recipient']");
	By email=By.xpath("//input[@id='recipientemail']");
	By next2=By.xpath("//button[@id='next2']");
	
	public void bookGift(String browserName, String money, String date, String name, String email_id) throws IOException, InterruptedException {

		// To launch browser
		invokeBrowser(browserName);

		// To launch url
		String url = "http://makemytrip.com";
		openURL(url);
		
		//to get rid of alert
		driver.findElement(By.xpath("//li[@class='makeFlex hrtlCenter font10 makeRelative lhUser']")).click();

		// to click gift
		toClick(gift_btn);
		
		// to click grp gift
		toClick(groupgift);
				
		// to click card
		// driver.findElement(By.xpath("//a[@class='nav-link lowertabs']")).click();

		// to enter amount
		toEnter(amt,money);
		
		// to select date
		toClick(Date);
		
		// to click date
		//clickDate_gift(date);

			// to select month
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
			Date currentdate = new Date();
			try {
				Date expectedDate = dateformat.parse(date);

				String day = new SimpleDateFormat("dd").format(expectedDate);
				String month = new SimpleDateFormat("MMMM").format(expectedDate);
				String year = new SimpleDateFormat("yyyy").format(expectedDate);

				String expectedMonthYear = month + " " + year;
				// System.out.println(month + " " + year);

				// To click month
				while (true) {
					String displayDate = driver.findElement(By.xpath("//div[6]/div[1]/table[1]/thead[1]/tr[2]/th[2]"))
							.getText();
					if (expectedMonthYear.equals(displayDate)) {
						break;
					} else if (expectedDate.compareTo(currentdate) > 0) {
						driver.findElement(
								By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'»')]"))
								.click();
					} else {
						driver.findElement(By
								.xpath("//div[@class='datepicker-days']//th[@class='prev disabled'][contains(text(),'«')]"))
								.click();
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
				takeScreenShot(driver);
				Assert.fail("Invalid month : " +date);
			}

			// to click date
			Date expectedDate;
			try {
				expectedDate = dateformat.parse(date);
				String day = new SimpleDateFormat("dd").format(expectedDate);
				driver.findElement(By.xpath("//td[contains(text(),'" + day + "')]")).click();
			} catch (ParseException e1) {
				e1.printStackTrace();
				takeScreenShot(driver);
				Assert.fail("Invalid date : " +date);
			}

		


		// to agree
				toClick(toagree);

		// to click next button
		toClick(next1);

		// to enter name
		toEnter(Name,name);

		// to enter email
		toEnter(email,email_id);

		// to click next
		toClick(next2);
		// to print alert message
		takeScreenShot(driver);
		
		
		 String msg=getText("//div[@id='flash-message']");
		 System.out.println(msg);
	//	driver.findElement(By.xpath("//div[@id='okay']")).click();
		 closeBrowser();
	}

}