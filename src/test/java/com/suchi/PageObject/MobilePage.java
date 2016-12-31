package com.suchi.PageObject;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.suchi.UtilPage.WebUtil;

import junit.framework.Assert;

public class MobilePage {
	
	String PrPrice =null;
	public void verifyMobilePageTitle(WebDriver driver) {
		String Mobile_Pagetitle= driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
		Assert.assertEquals(Mobile_Pagetitle, WebUtil.MOBILE_PAGE_TITLE);
	}

	public void selectSortBy(WebDriver driver,String Value) {
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
		dropdown.selectByVisibleText(Value);
	}

	public List<WebElement> getAllProducts(WebDriver driver) {
		List<WebElement> products = driver.findElements(By.className("product-name"));
		System.out.println("Number of product on the page: "+products.size());
			
		return products;
		
	}

	public void verifyProductSorting(WebDriver driver, List<WebElement> productList) throws Exception {
		
		for(int i=0;i<productList.size();i++)
		{
			System.out.println("Products Name at "+i+" location is: "+productList.get(i).getText());
		}
		WebUtil.getScreenShot(driver);
	}

	public String searchProductCost(WebDriver driver, List<WebElement> productList, String ProductName) 
	{
		String productPrice = null;
		try{
		for(int i=0;i<productList.size();i++)
		{
			if((productList.get(i).getText()).equalsIgnoreCase(ProductName))
			{
				System.out.println("Product "+ProductName+" found in the list");
				productPrice= this.getProductPrice(driver,ProductName);
				
			}
			else
			{
				System.out.println("Product not found");
			}
		}
		}
		catch(Exception e)
		{
			System.out.println("Product not found in the list ");
		}
		return productPrice;
	}

	public String getProductPrice(WebDriver driver, String productName) {
			
		if(productName.equalsIgnoreCase("Sony Xperia") || productName.equalsIgnoreCase("IPhone"))
		{
			String Product_Price_xpath = "//*[contains(text(),'"+productName+"')]//following::span";
			PrPrice=driver.findElement(By.xpath(Product_Price_xpath)).getText();
			System.out.println("price of "+productName+" is :"+PrPrice);
		}
		else if(productName.equalsIgnoreCase("Samsung Galaxy"))
		{
			String Product_Price_xpath = "//*[contains(text(),'"+productName+"')]//following::span[4]";
			PrPrice=driver.findElement(By.xpath(Product_Price_xpath)).getText();
			System.out.println("price of "+productName+" is :"+PrPrice);
		}
		return PrPrice;
		
	}

	public ProductPage clickOnProduct(WebDriver driver, String productName) {
		try
		{
		String Product_Link_xpath= "//*[contains(text(),'"+productName+"')]";
		driver.findElement(By.xpath(Product_Link_xpath)).click();
		
		}
		catch(ElementNotFoundException e){
			System.out.println("No such product found :"+e.toString());
		}
		return PageFactory.initElements(driver, ProductPage.class);
	}

	public ShoppingCartPage clickOnAddToCart(WebDriver driver, String productName) {
		String Add_to_Cart_button_xpath = "//*[contains(text(),'"+productName+"')]//following::button/span";
		WebElement Add_to_cart_button = driver.findElement(By.xpath(Add_to_Cart_button_xpath));
		try
		{
			if(Add_to_cart_button!=null)
			{
				Add_to_cart_button.click();
			}
			else
			{
				System.out.println("Element not found");
			}
		}
		catch(NoSuchElementException e){
			System.out.println(e.toString());
		}
		return PageFactory.initElements(driver, ShoppingCartPage.class);
	}

	public void addMobileToCompare(WebDriver driver, String product_choice_1, String product_choice_2) {
		addProductToCompareList(driver,product_choice_1);
		addProductToCompareList(driver,product_choice_2);
		
	}

	public void addProductToCompareList(WebDriver driver, String product_choice) {
		try{
		String Add_to_Copmare_xpath = "//*[contains(text(),'"+product_choice+"')]//following::a[contains(text(),'Add to Compare')]";
		WebElement Add_To_Compare_button = driver.findElement(By.xpath(Add_to_Copmare_xpath));
		Add_To_Compare_button.click();
		}
		catch(NoSuchElementException ex)
		{
			System.out.println(ex.toString());
		}
		
	}

	public void clickCompareButton(WebDriver driver) {
		try
		{
			WebElement Compare_button = driver.findElement(By.xpath("//*[@id='compare-items']//following::button"));
			Compare_button.click();
			String WinHandleParent = driver.getWindowHandle();
			try{
				Set<String> handles =  driver.getWindowHandles();
				for(String winHandle : handles)
				{
					if(!winHandle.equals(WinHandleParent))
					{
						driver.switchTo().window(winHandle);
						checkProductOnComaprePage(driver);
						driver.close();
						driver.switchTo().window(WinHandleParent);
					}
				}
			}
			catch(NoSuchWindowException e)
			{
				
			}
		}
		catch(NoSuchElementException ex)
		{
			System.out.println(ex.toString());
		}
		
	}

	public void checkProductOnComaprePage(WebDriver driver) {
		try{
			List<WebElement> compare_pro_list = driver.findElements(By.className("product-name"));
			
			for(int i=0; i<compare_pro_list.size();i++)
			{
				System.out.println("Product on comapre list :"+compare_pro_list.get(i).getText());
			}
			
		}catch(NoSuchElementException e){
			
		}
		
	}
}
