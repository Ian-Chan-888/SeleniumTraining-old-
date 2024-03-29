import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class S11_Lesson82_83_Handlining_Mutiple_Windows {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriver driver;

		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\eclipse\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://accounts.google.com/signup");
		driver.manage().window().maximize();// max browser window
		driver.manage().deleteAllCookies();

		/*
		 * In this lession we talk about multiple windows. 1. Identify the id's of each
		 * window and set the in a "Set<String>" table 2. Use the Iterator<String> to
		 * traverse thru the table 3. Assign a Variable to each row of the table 4. Use
		 * the switchTo().window() to move to the different windows.
		 * 
		 */

		// click on the "Help" to open a child page
		driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/footer[1]/ul[1]/li[1]/a[1]")).click();
		System.out.println(driver.getTitle()); // describes your Parent page "Create your Google Account"

		// this will get all the window id's "titles" using the "Set<String>" function
		// which will store a series of rows where row 1 will be the Parent id.
		Set<String> ids = driver.getWindowHandles();
		System.out.println(ids); /*
									 * this should print out the following
									 * '[CDwindow-E70F1613E85214D0F4D455C7B1E31DE8,
									 * CDwindow-84682DECC11F1F98C4131D84B1CC56F4]'
									 * 
									 * Conceptualy this String of ids should be organized like this:
									 * |-----------------------------------------------------------|
									 * |CDwindow-E70F1613E85214D0F4D455C7B1E31DE8 | This is the Parent Window id
									 * |-----------------------------------------------------------|
									 * |CDwindow-84682DECC11F1F98C4131D84B1CC56F4 | This is the child Window id
									 * |-----------------------------------------------------------|
									 */

		Iterator<String> it = ids.iterator(); // import Iterator method to help navigate to specific id's.
		String parentid = it.next(); // comes to the first row and grabs the id and stores it into the variable
										// "parentid".
		String firstChildid = it.next(); // traverses down one next id and stores it into the variable "fristChildid'

		driver.switchTo().window(firstChildid); // now we can switch to the first child window.
		System.out.println(driver.getTitle()); // after switching to the child window we can get the title which should
												// be "Google Account Help"

		driver.switchTo().window(parentid); // switching back to parent window
		System.out.println(driver.getTitle()); // after switching to the parent window we can get the title which should
												// be "Create your Google Account"

		System.out.println("END PROGRAM");

		// driver.close();

	}

}