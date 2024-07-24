package testScript;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseComponants.Basetest;
import pageObjects.CheckoutPage;
import pageObjects.Landingpage;
import pageObjects.Shoppage;

public class E2Eflow extends Basetest{
	
	
	String expTitle="Swag Labs";
	//String uname="standard_user";
	//String pwd="secret_sauce";
	Shoppage shop;
	CheckoutPage chkpage;
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String,String> dataset1=new HashMap<String,String>();
		dataset1.put("user","standard_user");
		dataset1.put("pwd", "secret_sauce");
		dataset1.put("product1", "Sauce Labs Backpack");
		dataset1.put("product2", "Sauce Labs Fleece Jacket");
		
		HashMap<String,String> dataset2=new HashMap<String,String>();
		dataset2.put("user","visual_user");
		dataset2.put("pwd", "secret_sauce");
		dataset2.put("product1", "Sauce Labs Onesie");
		dataset2.put("product2", "Sauce Labs Bolt T-Shirt");*/
		
		List<HashMap<String,String>> datalist = getDatatomap();
		
		return new Object[][] {{datalist.get(0)},{datalist.get(1)}};
		
	}
	
	@Test(dataProvider="getData",groups="purchase")	
	public void Addtocart(HashMap<String,String> data) throws InterruptedException
	{	
		shop=lpage.clickonSubmit(data.get("user"),data.get("password"));
		String actual=shop.gettitle();	
	    Assert.assertEquals(actual,expTitle);
	    int total=shop.getTotalmenu();		
		Assert.assertEquals(total,4);
		shop.clickAddcart(data.get("product1"));
		shop.clickAddcart(data.get("product2"));
		chkpage=shop.clickCart();
		String title=chkpage.getTitle();
		Assert.assertEquals(title,"Your Cart");		
		chkpage.verifyItem(data.get("product1"));
		chkpage.verifyItem(data.get("product2"));
		System.out.println("total item "+chkpage.getTotalcartitem());
		shop.Logout();
		Thread.sleep(1000);
			
	}
	
	
	
	
	
	
	
		
	
	
		
}
