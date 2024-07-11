package amazon;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class search {

	public static  WebDriver driver;
	
	static String items[]= {"Pro-Series 16-in-1 Vegetable Chopper w","Fullstar Vegetable Chopper","Hamilton Beach Electric Vegetable Chopper & Mini Food Processor"};
	static List<String> ActualProductlist= Arrays.asList(items);
	
	static WebDriverWait wait;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver();
		 driver= new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.amazon.com/ref=nav_logo");
		 
		//to handle the captach
		 boolean imageFound=false;
		 Thread.sleep(2000);
		 List<WebElement> imagelements=	 driver.findElements(By.xpath("//img"));
		 imagelements.size();
		 
		 for(WebElement imageElement:imagelements) {
		 String src=imageElement.getAttribute("src");
		 System.out.println(src);
		 if(src!=null && src.contains("captcha")) {
			 imageFound=true;
			 break;
		 }
		 }
		 
		 if(imageFound) {
		 System.out.println( driver.getTitle());
		 System.out.println(driver.getCurrentUrl());
		 Thread.sleep(2000);
		 
		 //login to website
		 login(driver);
		 
		 driver.findElement(By.id("twotabsearchtextbox")).sendKeys("chopper");
		 Thread.sleep(4000);
		 
		List<WebElement> searchedList= driver.findElements(By.xpath("//div[@class='s-suggestion-container']/div"));
		System.out.println(	searchedList.size());
		
		System.out.println(	"the size of the items"+	ActualProductlist.size());
		
		for(WebElement element:searchedList) {
			
			String text=	element.getAttribute("aria-label");
			System.out.println(text);
			try {
			if(text.equals("chopper")) {
				
				wait= new WebDriverWait(driver,Duration.ofMillis(3000));
				wait.until(ExpectedConditions.visibilityOf(element));
				element.click();
				break;
			}
			
	}catch(Exception e) {
		e.printStackTrace();}
	}
		
		
		List<WebElement> ProductList=	driver.findElements(By.xpath("//span[@data-component-type='s-search-results'] //div[@data-component-type='s-search-result']"));
		Thread.sleep(3000);
		System.out.println(	ProductList.size());
		
		//traversing in the list to get desired products
		int j=0;
		for(WebElement product:ProductList) {
			Thread.sleep(3000);
			
			String[] ItemName=	product.findElement(By.xpath(".//span[@class='a-size-base-plus a-color-base a-text-normal']")).getText().split("[,/]");
		String ExpectedProduct=	ItemName[0];
		
		System.out.println(ExpectedProduct);
		
			if(ActualProductlist.contains(ExpectedProduct)) {
				product.findElement(By.xpath(".//button[text()='Add to cart']")).click();
				j++;
				System.out.println(j);
				
				if(ActualProductlist.size()==j) {
					break;
				}
			}
			
			
		
		}
		
		
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("nav-cart"))));
		driver.findElement(By.id("nav-cart")).click();
		
	driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();
	}}
	
	
	

  public static void login(WebDriver driver) throws InterruptedException {
	
	Thread.sleep(3000);
	driver.findElement(By.id("nav-link-accountList")).click();
	
	
	driver.findElement(By.id("ap_email")).sendKeys("845378289");
	driver.findElement(By.id("continue")).click();
	driver.findElement(By.id("ap_password")).sendKeys("1234");
	driver.findElement(By.id("signInSubmit")).click();
}

}
