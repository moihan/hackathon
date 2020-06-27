package team1.cabBooking;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class MyRetryAnalyzer implements IRetryAnalyzer {
	int counter =0;
	int retryLimit=2;

	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(counter<retryLimit){
	counter++;
	return true;
		}
		else
		return false;
	}
 
}
