package SatelliteOfficeTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestCase1 {
	
	WebDriver driver;
	private int BagSelectedindex = (int)(Math.random()*5);
	String property = "chromeWebDrayber\", \"C:\\\\Users\\\\allen\\\\Downloads\\\\chromedriver-win64\\\\chromedriver-win64\\\\chromedriver.exe";
	
	@Test(priority = 1)
	void login() {
		//WebDriver driver = Hooks.getDriver();
		
		System.setProperty("chromeWebDriver", property);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/v1/");
	
		
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		AssertJUnit.assertEquals("https://www.saucedemo.com/v1/inventory.html",driver.getCurrentUrl());
		
	}
	
	@Test(priority = 2)
	void addToCart()throws Exception {
		
		
		List<WebElement> bagsButton = driver.findElements(By.xpath("//button[@class='btn_primary btn_inventory']"));		
		bagsButton.get(BagSelectedindex).click();		
		System.out.println(bagsButton.get(BagSelectedindex).getText());
		assertEquals(bagsButton.get(BagSelectedindex).getText(), "REMOVE");
		
	}
	
	@Test(priority = 3)
	void cartCount() throws Exception {

		assertEquals(driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']")).getText(), "1");		
		Thread.sleep(2000);

	}
	
	

	@Test(priority = 4)
	void validateCart() throws Exception {
		List<WebElement> products = driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
		String productAdded = products.get(BagSelectedindex).getText();
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']")).click();
		assertEquals(productAdded,driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText());
	}
	
	@Test(priority = 5)
	void checkout() throws Exception {
		driver.findElement(By.xpath("//a[contains(text(),'CHECKOUT')]")).click();
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-one.html");

	}
	
	@Test(priority = 6)
	void nameDetail() throws Exception {
		driver.findElement(By.id("first-name")).sendKeys("fnme");
		driver.findElement(By.id("last-name")).sendKeys("lnme");
		driver.findElement(By.id("postal-code")).sendKeys("12-34");
		driver.findElement(By.xpath("//input[@value='CONTINUE']")).click();
		assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/v1/checkout-step-two.html");
	}
	
	
	@Test(priority = 7)
	void validateTotal() {
		assertEquals("1",driver.findElement(By.xpath("//div[@class='summary_quantity']")).getText());
		
		
	}
	
	@Test(priority = 8)
	void finish() {
		driver.findElement(By.xpath("//a[normalize-space()='FINISH']")).click();
		
		assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='THANK YOU FOR YOUR ORDER']")).isDisplayed());
		if (driver != null) {
            driver.quit();
	}
	

	}

}
