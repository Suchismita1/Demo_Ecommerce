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

import com.gargoylesoftware.htmlunit.Page;
import com.suchi.PageObject.AccountPage;
import com.suchi.PageObject.CreateAccountPage;
import com.suchi.PageObject.DashBoard;
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
	@Parameters({"Sort_Method","Product_Name","quantity","Product_choice_1","Product_choice_2"})
	@Test
	public void test_Home_Page(String Sort_Method,String Product_Name, String quantity, String Product_choice_1, String Product_choice_2) throws Exception{
		/*DAY1 SCENARIOS:
			1.  Go to http://live.guru99.com/index.php/
			2.  Verify title of the page
			3.  click on "Mobile" menu
			4.  Verify title of the page
			5.  In the list of all Mobile, Select Sort by dropdown as "name"
			6.  Verify all product are sorted by name*/
		
		//Goto URL
		SignInPage signinPage = WebUtil.getSigninPage(driver);
		System.out.println("Title is: "+driver.getTitle());
		
		//Verify the title of the URL
		//signinPage.verifyWelcomeMsg(driver);
		
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
	
		/*DAY2 SCENARIOS:
			1.  Go to http://live.guru99.com/index.php/
			2.  click on "Mobile" menu
			3.  In the list of all mobile, read the cost of Sony Xperia mobile and note this value.
			4.  Click on sony xperia mobile
			5.  Read the sony xperia mobile from detail Page.
			6.  compare value in step 3 and step 5*/
		
		//Read the user's choice mobile cost
		String Visible_Price = mobilePage.searchProductCost(driver,ProductList,Product_Name);
		
		//Click on the user's choice Product
		ProductPage productPage = mobilePage.clickOnProduct(driver,Product_Name);
		
		//Get the Product price on Product details page
		String Actual_price = productPage.getProductDetailPrice(driver,Product_Name);
		
		//Verify the detail price of product with visible product price on Mobile home page
		Assert.assertEquals(Actual_price, Visible_Price);
		
		/*DAY3 SCENARIOS:
		1.  Go to http://live.guru99.com/index.php/
		2.  click on "Mobile" menu
		3.  in the list of the mobile, click on "ADD TO CART" for sony xperia mobile
		4.  change quantity to 1000 and click 'UPDATE' button
		5.  verify the error message
		6.  Click on the 'EMPTY CART' link 
		7.  verify cart is EMPTY */
		
		//Navigate to Mobile page
		WebUtil.navigateBackMenu(driver);
		
		//Click on Add to cart button of user's choice Mobile
		ShoppingCartPage shoppingCart = mobilePage.clickOnAddToCart(driver,Product_Name);
		
		//Send quantity to the quantity input box
		shoppingCart.addQuantity(driver, quantity);
		
		/*DAY4 SCENARIOS:
		1.  Go to http://live.guru99.com/index.php/
		2.  click on "Mobile" menu
		3.  In the mobile products list, click on 'Add to compare' for 2 mobiles
		4.  click on 'COMAPRE' button
		5.  verify the pop-up window and check that the products are reflected
		6.  close the pop-up windows*/
		
		//Navigate to Mobile Page
		signinPage.clickMobileMenu(driver);
		
		//Add 2 mobile to Compare
		mobilePage.addMobileToCompare(driver,Product_choice_1,Product_choice_2);
		
		//Click the compare button and switch to compare window
		mobilePage.clickCompareButton(driver);
				
			
	}
	
	/*DAY5 SCENARIOS:
	1.  Go to http://live.guru99.com/index.php/
	2.  Click on My Account link
	3.  Click on Create account link and fill new user information 
	4.  clcik register
	5.  verify registraion is done
	6.  go to TV menu
	7.  Add product to your wish lsit
	8.  click SHARE WISHLIST
	9.  In the next page enter Email and message and click SHARE WISHLIST
	10. Check wishlist is shared */
	
	@Parameters({"First_Name","Last_Name","Email","password","Confirmpassword","Wish_TV_Name","Message"})
	@Test
	public void Test_Account_Creation(String First_Name, String Last_Name, String Email, String password, String Confirmpassword, String Wish_TV_Name, String Message)
	{
		//Goto URL
		SignInPage signinPage = WebUtil.getSigninPage(driver);
		
		//Click on 'MY ACCOUNT' link
		AccountPage accountPage = signinPage.clcikMyAccount(driver);
		
		//Click on Create account link
		CreateAccountPage createAccount = accountPage.click_Account_Creation(driver);
		
		//Fill the user details and click register
		DashBoard dashboard = createAccount.fillNewUserDetails(driver,First_Name,Last_Name,Email,password,Confirmpassword);
		
		//Go to TV menu from the DashBoard page
		dashboard.clickTVMenu(driver);
		
		//Add Product to your WishList
		dashboard.addTVtoWishList(driver,Wish_TV_Name);
		
		//Click on Share WishList
		dashboard.clickShareWishlistButton(driver);
		
		//In the next page enter Email and message and click SHARE WISHLIST
		dashboard.enterSharingDeatils(driver,Email,Message);
		
		//Check wishlist is shared
		dashboard.verifySharing(driver);
	}
	
	@AfterTest
	public void teardown()
	{
		driver.close();
	}

}
