package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponant.AbstractMethods;


public class Shoppage extends AbstractMethods{

	WebDriver driver;

	public Shoppage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='header_label']/div")
	WebElement title;

	@FindBy(css = "button#react-burger-menu-btn")
	WebElement openMenu;
	
	@FindBy(xpath = "//div[@class='bm-menu']/nav/a")
	List<WebElement> menuitem;
	
	@FindBy(xpath="//div[@class='inventory_item_label']/a/div")
	List<WebElement> products;
	
	@FindBy(css="a.shopping_cart_link")	
	WebElement carticon;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement lgout;
	
	
	By follow=By.xpath("following::button");
	By prodslist=By.xpath("//div[@class='inventory_item_label']/a/div");
	
	
	public int getTotalmenu() throws InterruptedException
	{
	openMenu.click();
	Thread.sleep(1000);
	int total=menuitem.size();
	return total;
	}

	public String gettitle()
	{
		waitforVisible(title);			
	    String actualtitle=title.getText();
	    System.out.println("title is "+actualtitle);
	    return actualtitle;
    }
	
	public List<WebElement> getProducts()
	{
		waitforVisible(prodslist);
		return products;
		
	}
	public WebElement findProd(String prod)
	{
	
	  WebElement prodele=getProducts().stream().filter(product->product.getText().equals(prod)).findFirst().orElse(null);
	  return prodele;	  
}
	
	public void clickAddcart(String prod)
	{
		WebElement prodele=findProd(prod);
		prodele.findElement(follow).click();
	}
	
	public CheckoutPage clickCart()
	{
		waitforVisible(carticon);
		carticon.click();
		CheckoutPage chk=new CheckoutPage(driver);
		return chk;
	}
	
	public void Logout() {
		openMenu.click();
		waitforVisible(lgout);
		lgout.click();
		
	}
}