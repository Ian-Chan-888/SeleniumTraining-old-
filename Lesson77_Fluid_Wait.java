import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Lesson77_Fluid_Wait {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver;

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
			
		String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};  // we are defining an array of strings in by listing the items in '{}'. Imprortant to define the array by "String[]"
		
		
		driver.get("https://rahulshettyacademy.com/seleniumPractise");
		Thread.sleep(3000);
		driver.manage().window().maximize();// max browser window
		driver.manage().deleteAllCookies();

		/* We can use Pauses or waits within the code so we can organically allow elements to properly load. 
		 * We will need to understand the differnet types of waits.
		 *
		 * Lession 74-75: Practical excercise
		 * 
		 * 
		 */
		
		//*****Explicit Wait DEFINED *****
		WebDriverWait w = new WebDriverWait(driver,5); //5 seconds. This can be added to the top of the code and does not neccessarily have to be implemented at this line.
		//********************************
		
		addItems(driver,itemsNeeded); //calling addItems method
		Lesson77_Fluid_Wait b = new Lesson77_Fluid_Wait();
		b.addItems(driver, itemsNeeded);
		
		//click on view cart
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		//Proceed to Checkout
				
		driver.findElement(By.xpath("//button[contains(text(),'PROCEED TO CHECKOUT')]")).click(); //given that the text is not dynamic we can use the text xpath.
				
		//*****Explicit Wait Called here*****
		//Enter in a Promo code 
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.promoCode")));
		
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		//click on Apply button
		driver.findElement(By.cssSelector("button.promoBtn")).click(); 
		
		//*****Explicit Wait Called here*****
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.promoInfo")));

		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText()); 
		
		//Expected result is "Promo code applied successfully!"
		
		
		
		System.out.println("END PROGRAM");
}
	
	public static void addItems(WebDriver driver,String[] itemsNeeded)
	{
		//Adding multiple products. Note that we Variablized an Array in Line 19 above. (Lesson 67)
				int itemNumber=0;
				List<WebElement> multiProduct=driver.findElements(By.cssSelector("h4.product-name")); // find all the elements with this product-name
				
				for(int i=0;i<multiProduct.size();i++) //looks for all instances of products 
				{
					/* Format name to get the actual vegitable name Lesson 68  i.e "Brocolli - 1 Kg"
					 * the .split() with "-" 
					 * Note You are now using String[] instead of String because you have effectively split out mulitple strings. 
					 * Therefore you get 2 instances 
					 * From 'Brocolli - 1 kg'
					 * TO: name[0]= 'Brocolli '
					 *     name[1]= '1 kg'
					 */
					String[] name=multiProduct.get(i).getText().split("-"); 
					String formattedName=name[0].trim(); // since we still have a space after the split to deal with we will use the ".trim()".
					
					/* 1. convert array into ArrayList for easy search. doing it this way saves memory
					 * 2. check whether name you extracted is present in array List or not			
					 */
					List itemsNeededList= Arrays.asList(itemsNeeded); //step 1
					
								
					if(itemsNeededList.contains(formattedName)) //step 2
					{
						itemNumber++;
						//click on Add to Cart for that given index i
				      //driver.findElements(By.xpath("//button[text()='ADD TO CART']")).get(i).click();
						driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
						if(itemNumber==itemsNeeded.length) //we get the size of the String[] array on Line 19. This is so we can define when we need to break out of this loop
						{
							break;
						}
					}
				}

				

				// driver.close();

			}
			
			
			
			
	}
	
	