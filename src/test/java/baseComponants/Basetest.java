package baseComponants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.Landingpage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

public class Basetest {
	public WebDriver driver;
	public Landingpage lpage;
	
	public String readvalProperty(String key) throws IOException
	{
		FileInputStream file1=new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//propertiesFile//Globaldata.properties");
		Properties prop1=new Properties();
		prop1.load(file1);		
		
		String passwd=prop1.getProperty(key);
		return passwd;
	}
	
	public WebDriver intializeBrowser() throws IOException
	{
	FileInputStream file1=new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//propertiesFile//Globaldata.properties");
	Properties prop1=new Properties();
	prop1.load(file1);
	
	String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop1.getProperty("browser");
	//String browserName=prop1.getProperty("browser");
	if(browserName.equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver","C://seldriver//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		driver=new ChromeDriver(options);
	}	
	if(browserName.contains("headless"))
	{
		System.setProperty("webdriver.chrome.driver","C://seldriver//chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		
		driver=new ChromeDriver(options);
	}		
	else if(browserName.equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver","C://seldriver//geckodriver.exe");
		driver=new FirefoxDriver();
	}
	else
	{
		System.out.println("No Driver specified ");
	}
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	return driver;
}
	@BeforeTest(alwaysRun=true)
	public Landingpage intializeapp() throws IOException
	{
		driver=intializeBrowser();
		lpage=new Landingpage(driver);
		lpage.Goto();
		return lpage;
	}
	
	public static String takescreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
	}
	
	public List<HashMap<String, String>> getDatatomap() throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//data//productdata.json"), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Datbind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;
	}
	
	@AfterTest(alwaysRun=true)
	public void closebrowser() throws IOException
	{
		driver.close();
	}
}
