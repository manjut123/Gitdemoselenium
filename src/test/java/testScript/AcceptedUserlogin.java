package testScript;

import java.io.IOException;

import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseComponants.Basetest;
import baseComponants.Retry;
import pageObjects.Landingpage;
import pageObjects.Shoppage;

public class AcceptedUserlogin extends Basetest {
	
	//Landingpage lpage;
	Shoppage shops;
	String expTitle="Swag Labs";
	String pwd="secret_sauce";
	
@DataProvider(name="acceptedusers")
public Object[][] getUsers()
{
	return new Object[][] {
			{"standard_user"},
				{"performance_glitch_user"},
					{"error_user"},
					{"visual_user"}
	};
}


@Test(dataProvider="acceptedusers")
public void checklogin(String user) throws IOException, InterruptedException
{
	
	shops=lpage.clickonSubmit(user,pwd);
	String title = shops.gettitle();
	Assert.assertEquals(title,expTitle);
	shops.Logout();
	Thread.sleep(1000);
}

@Test
public void testLogin()
{
	String pwd="secret_sauce";
	shops=lpage.clickonSubmit("problem_user",pwd);
	String title = shops.gettitle();
	Assert.assertEquals(title,"lajd");
	
	shops.Logout();
	
}
}
