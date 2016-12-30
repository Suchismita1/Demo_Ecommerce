package com.suchi.PageObject;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	String product_detail_price = null;

	public String getProductDetailPrice(WebDriver driver, String productName) {
		try{
		if(productName.equalsIgnoreCase("Sony Xperia") || productName.equalsIgnoreCase("IPhone"))
		{
			String product_detail_price_xpath = "//*[@id='product_addtocart_form']//*[contains(text(),'"+productName+"')]//following::span[2]";
			product_detail_price = driver.findElement(By.xpath(product_detail_price_xpath)).getText();
			System.out.println("Detail price of the "+productName+" is: "+product_detail_price);
		}
		else if(productName.equalsIgnoreCase("Samsung Galaxy"))
		{
			String product_detail_price_xpath = "//*[@id='product_addtocart_form']//*[contains(text(),'"+productName+"')]//following::span[5]";
			product_detail_price = driver.findElement(By.xpath(product_detail_price_xpath)).getText();
			System.out.println("Detail price of the "+productName+" is: "+product_detail_price);
		}
		}
		catch(NoSuchElementException e)
		{
			System.out.println("No such product found in the list "+e.toString());
		}
		return product_detail_price;
		
	}

}
