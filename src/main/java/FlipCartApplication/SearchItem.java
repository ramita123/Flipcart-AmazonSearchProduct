package FlipCartApplication;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchItem {
	public static  WebDriver driver;
	
	static String items[]= {"Zulay Kitchen Chopper for Vegetables","Kent Electric Vegetable Chopper","Rico CH2112 Blue Portable USB Rechargeable Mini Chopper"};
	static List<String> ExpectedProductlist= Arrays.asList(items);
	
	
	 static WebDriverWait wait= new WebDriverWait(driver,Duration.ofMillis(2000));
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver();
		 driver= new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.flipkart.com/");
		 System.out.println( driver.getTitle());
		 System.out.println(driver.getCurrentUrl());
		 
		 driver.findElement(By.xpath("//input[@type='text']")).click();
		 driver.findElement(By.xpath("//input[@type='text']")).sendKeys("chopper");
		 Thread.sleep(3000);
		 List<WebElement> searchLists=	 driver.findElements(By.xpath("//a[contains(@href,'/search?')]"));
		 Thread.sleep(2000);
		 System.out.println(searchLists.size());
		
		 
		 ///searching the desired results
	 try {	
			 for(WebElement element:searchLists) {
		Thread.sleep(2000);
		String text=element.findElement(By.xpath(".//div[@class='YGcVZO _2VHNef']")).getText();
		Thread.sleep(2000);
		System.out.println(text);
		
		
		if(text.contains("chopper electric")) {
			
			element.click();
			break;
		}
		}
		
	
	
	
			//Adding products to cart
		String[] productName = null;
	
	 int j=0;
	 do {
		 
		 Thread.sleep(3000);
		 List<WebElement> productsList= driver.findElements(By.xpath("//a[@class='wjcEIp']"));
		 System.out.println( productsList.size());
	 for(WebElement element:productsList) {
		
		 wait.until(ExpectedConditions.visibilityOf(element));
		
		 
		 productName= element.getText().split("[/.]+");
		 String ActualProduct=	 productName[0];
		// Thread.sleep(3000);
		System.out.println(ActualProduct);
		if(ExpectedProductlist.contains(ActualProduct)) {
		
			element.click();

			
			j++;
			
		}}
		
		
		if(j<ExpectedProductlist.size()) {
			
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,4500)");
			 Thread.sleep(2000);
			driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		}
		
		 }while(j<ExpectedProductlist.size());
	 
	 
	 Thread.sleep(3000);
		String parent=driver.getWindowHandle();
		System.out.println(parent);
		Set<String> childWindows=	driver.getWindowHandles();
		Iterator<String> it=	childWindows.iterator();
		while(it.hasNext()) {
			String child=it.next();
			if(!child.equals(parent)) {
				System.out.println("child window is "+ child);
			driver.switchTo().window(child);
			Thread.sleep(3000);
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0,1000)");
		        
		        driver.findElement(By.xpath(("//button[@class='QqFHMw vslbG+ In9uk2']"))).click();
		       
		
		 
		 
		}}
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='QqFHMw zA2EfJ _7Pd1Fp']"))));
		
		 driver.findElement(By.xpath("//button[@class='QqFHMw zA2EfJ _7Pd1Fp']")).click();
		
		
	
	}
	 catch(Exception e) {
			System.out.println("No product found as per the search request" + e.getMessage());
		}
	
	
	}

}
