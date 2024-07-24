package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.Shoppage;

import abstractComponant.AbstractMethods;

public class Landingpage extends AbstractMethods{
	
	WebDriver driver;
	
	public Landingpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="input#user-name")
	WebElement usrnm;
	
	@FindBy(css="input#password")
	WebElement pwd;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitbtn;
	
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement errmsg;

	public void Goto()
	{
		driver.get("https://www.saucedemo.com/");
	}

	public Shoppage clickonSubmit(String user,String password)
	{
	usrnm.sendKeys(user);
	pwd.sendKeys(password);	
	submitbtn.click();
	Shoppage shop=new Shoppage(driver);
	return shop;
	}
	
	public String errormsg(String user,String password)
	{
	usrnm.sendKeys(user);
	pwd.sendKeys(password);	
	submitbtn.click();
	waitforVisible(errmsg);
	String actual=errmsg.getText();
	return actual;
	
	}
	
	
}
