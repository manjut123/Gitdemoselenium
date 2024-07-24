package baseComponants;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testsreport.ExtentreporterNG;
//import ExtentreporterNG;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listenertest extends Basetest implements ITestListener {
	//ExtentReports report= ExtentreporterNG.ExtentReports();
	ExtentTest test;
	ExtentReports report=ExtentreporterNG.getExtentreport();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		
		test=report.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	 public void onTestSuccess(ITestResult result) 
	 {
		 extentTest.get().log(Status.PASS,"Passed");
     }
	 
	 public void onTestFailure(ITestResult result) {
		 extentTest.get().fail(result.getThrowable());//
			
			try {
				driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
						.get(result.getInstance());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			String filePath = null;
			try {
				
				filePath = takescreenshot(result.getMethod().getMethodName(),driver);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	 }
	 
	 public void onStart(ITestContext context) {
		    // not implemented
		  }
	 public void onFinish(ITestContext context) {
		    // not implemented
		 
		 report.flush();
		 
		 
		  }
	 
	 
	 
	 
}
