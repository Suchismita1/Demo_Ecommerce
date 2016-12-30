package com.suchi.Scenarios;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.suchi.PageObject.MobilePage;
import com.suchi.PageObject.ProductPage;
import com.suchi.PageObject.ShoppingCartPage;
import com.suchi.PageObject.SignInPage;
import com.suchi.UtilPage.WebUtil;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class EcommerceTest {
	public WebDriver driver;
	
	@BeforeTest
	public void setup(){
		ChromeDriverManager.getInstance().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(WebUtil.WAIT_TIME, TimeUnit.SECONDS);
	}
	@Parameters({"Sort_Method","Product_Name","quantity"})
	@Test
	public void test_Home_Page(String Sort_Method,String Product_Name, String quantity) throws Exception{
		//Goto URL
		SignInPage signinPage = WebUtil.getSigninPage(driver);
		System.out.println("Title is: "+driver.getTitle());
		
		//Verify the title of the URL
		signinPage.verifyWelcomeMsg(driver);
		
		//Click on MOBILE Menu option
		MobilePage mobilePage = signinPage.clickMobileMenu(driver);
		
		//Verify Mobile Title Page
		mobilePage.verifyMobilePageTitle(driver);
		
		//Sort the displayed item by Name
		mobilePage.selectSortBy(driver,Sort_Method);
		
		//Get the list of product
		List<WebElement> ProductList = mobilePage.getAllProducts(driver);
		
		//Verify the list of product are sorted alphabetically
		mobilePage.verifyProductSorting(driver,ProductList);
	
		//Read the user's choice mobile cost
		String Visible_Price = mobilePage.searchProductCost(driver,ProductList,Product_Name);
		
		//Click on the user's choice Product
		ProductPage productPage = mobilePage.clickOnProduct(driver,Product_Name);
		
		//Get the Product price on Product details page
		String Actual_price = productPage.getProductDetailPrice(driver,Product_Name);
		
		//Verify the detail price of product with visible product price on Mobile home page
		Assert.assertEquals(Actual_price, Visible_Price);
		
		//Navigate to Mobile page
		WebUtil.navigateBackMenu(driver);
		
		//Click on Add to cart button of user's choice Mobile
		ShoppingCartPage shoppingCart = mobilePage.clickOnAddToCart(driver,Product_Name);
		
		//Send quantity to the quantity input box
		shoppingCart.addQuantity(driver, quantity);
		
	}
	@AfterTest
	public void teardown()
	{
		driver.close();
	}

}
