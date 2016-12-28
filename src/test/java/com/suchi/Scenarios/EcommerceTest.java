package com.suchi.Scenarios;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.suchi.PageObject.MobilePage;
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
	@Test
	public void test_Home_Page(){
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
		mobilePage.selectSortByName(driver);
		
		//Get the list of product
		List<WebElement> ProductList = mobilePage.getAllProducts(driver);
		
		//Verify the list of product are sorted alphabetically
		mobilePage.verifyProductSorting(driver,ProductList);
		
		//Read the Sony Xperia mobile cost
		mobilePage.searchProductCost(driver,ProductList,"SONY XPERIA");
	}
	@AfterTest
	public void teardown()
	{
		driver.close();
	}

}
