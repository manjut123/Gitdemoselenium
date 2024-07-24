package testScript;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseComponants.Basetest;
import pageObjects.Landingpage;

public class ErrorValidatation extends Basetest{
	
	String username="standard_user";
	String wrongpass="secreate";
	String wronguser="standard";
	String passwordd="secret_sauce";
	//Landingpage lpage;

	@Test(groups = {"errorcheck"})
	public void passwordwrongerror()
	{
		String actual=lpage.errormsg(username,wrongpass);
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",actual);
		
	}
	
	@Test(priority=2)
	public void userwrongerror()
	{
		String actual=lpage.errormsg(wronguser,passwordd);
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",actual);
	}
}
