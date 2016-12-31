package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.suchi.UtilPage.WebUtil;

public class ShoppingCartPage {
	
	public void addQuantity(WebDriver driver, String quantity) throws Exception {
		WebElement quantity_input_box = driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"));
		quantity_input_box.clear();
		quantity_input_box.sendKeys(quantity);
		clickUpdateButton(driver);
		
		try{
			String actual_error_msg = driver.findElement(By.xpath("//*[@class='item-msg error']")).getText();
			
			if(actual_error_msg.equalsIgnoreCase(WebUtil.EXPECTED_ERROR_MSG))
			{
				clickEmptyCartButton(driver);
				Thread.sleep(2000);
				verifyEmptyCart(driver);
				
			}
			else{
				System.out.println("Test case failed");
			}
			
		}
		catch(NoSuchElementException ex){
			//ex.printStackTrace();	
			System.out.println("Update success");
			verifyQuantityInCart(driver,quantity);
			clickEmptyCartButton(driver);
			Thread.sleep(2000);
			verifyEmptyCart(driver);
			 
		}
	
	}

	public void verifyEmptyCart(WebDriver driver) {
		WebElement cart_button= driver.findElement(By.linkText("CART"));
		cart_button.click();
		
		String actual_Cart_msg = driver.findElement(By.xpath("//*[@id='header-cart']/div[3]/p[2]")).getText();
		try
		{
			Assert.assertEquals(actual_Cart_msg, WebUtil.EXPECTED_CART_MSG);
		}
		catch(Exception e)
		{
			
		}
	}

	public void verifyQuantityInCart(WebDriver driver, String quantity) {
		String cart_quantity = driver.findElement(By.className("count")).getText();
		System.out.println("Updated quantity: "+cart_quantity);
		try{
		Assert.assertEquals(quantity, cart_quantity);
		}
		catch(Exception e)
		{
			
		}
	}

	public void clickEmptyCartButton(WebDriver driver) {
		WebElement Empty_button = driver.findElement(By.xpath("//*[@id='empty_cart_button']"));
		Empty_button.click();
		
	}

	public void clickUpdateButton(WebDriver driver) {
		WebElement update_button=driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/button"));
		update_button.click();
	}

}
