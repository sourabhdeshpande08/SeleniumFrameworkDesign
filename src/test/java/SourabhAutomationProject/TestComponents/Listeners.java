package SourabhAutomationProject.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import SourabhAutomationProject.Resources.ListenerNG;

public class Listeners extends BaseTest implements ITestListener,IRetryAnalyzer{

	
	public ExtentTest test;
	ListenerNG listen = new ListenerNG();
	ExtentReports extent = listen.extentReportsData();
	ThreadLocal<ExtentTest> thread = new ThreadLocal();
	
	int count = 0;
	int maxTry = 1;

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		thread.set(test);
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		thread.get().log(Status.PASS, "Test case is passed.");
	}

	
	@Override
	public void onTestFailure(ITestResult result) {

		thread.get().fail(result.getThrowable());
		
		try{
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
		

		String filePath = null;

		try {
			filePath = getScreenshot(result.getMethod().getMethodName());
		} catch (Exception e) {

			e.printStackTrace();

		}

		thread.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		

	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		extent.flush();
	    
	  }

	@Override
	public boolean retry(ITestResult result) {
		
		if(count<maxTry) {
			
			count++;
			return true;
			
		}
		
		return false;
	}

	

}
