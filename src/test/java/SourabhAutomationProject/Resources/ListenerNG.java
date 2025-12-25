package SourabhAutomationProject.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ListenerNG {

	public ExtentReports extentReportsData() {

		ExtentSparkReporter extentReporter = new ExtentSparkReporter(
		System.getProperty("user.dir") + "/ExtentReports/ExtentReport1.html");
		extentReporter.config().setDocumentTitle("Error Validation Tests");
		extentReporter.config().setReportName("Web Automation Results");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(extentReporter);
		extent.setSystemInfo("QA Automation Tester", "Sourabh Deshpande");
		return extent;

	}
	
	
}
