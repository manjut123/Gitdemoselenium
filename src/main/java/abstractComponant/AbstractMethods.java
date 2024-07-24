package abstractComponant;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractMethods {
	
	WebDriver driver;

	public AbstractMethods(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void waitforVisible(WebElement e)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(e));
	}
		
	public void waitforVisible(By loc)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}

}
