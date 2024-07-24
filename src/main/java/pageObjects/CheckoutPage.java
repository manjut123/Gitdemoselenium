package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import abstractComponant.AbstractMethods;

public class CheckoutPage extends AbstractMethods{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[@data-test='title']")
	WebElement title;
	
	@FindBy(xpath="//div[@class='cart_item_label']/a/div")
	List<WebElement> cartProd;
	
	public String getTitle()
	{
		waitforVisible(title);
		return title.getText();
	}
	
	public int getTotalcartitem()
	{
		return cartProd.size();
	}
	
	public boolean verifyItem(String prod)
	{
		List<String> items=new ArrayList<String>();
		for(WebElement e:cartProd)
		{
			items.add(e.getText());
		}
		
		return items.contains(prod);
	}
}