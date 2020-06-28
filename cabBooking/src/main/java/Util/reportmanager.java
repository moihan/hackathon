package Util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class reportmanager {
	//public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports report;
	
public static ExtentReports getReportInstance(){
	
	if(report==null){
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//target//ttt.html");
	report =new ExtentReports();
	report.attachReporter(htmlReporter);
	report.setSystemInfo("OS","Windows 10");
	report.setSystemInfo("Environment","UAT");
	report.setSystemInfo("Build Number","10.8.1");
	report.setSystemInfo("Browser","Chrome");
	htmlReporter.config().setDocumentTitle("UAT Automation Result");
	htmlReporter.config().setReportName("All headlines");
	htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
	htmlReporter.config().setTimeStampFormat("MM dd,yyyy HH:mm:ss");
	}
	return report;
}
	
}
