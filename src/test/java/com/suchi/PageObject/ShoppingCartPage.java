package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.suchi.UtilPage.WebUtil;

public class ShoppingCartPage {
	
	public void addQuantity(WebDriver driver, String quantity) {
		WebElement quantity_input_box = driver.findElement(By.xpath("//*[@id='shopping-cart-table']/tbody/tr/td[4]/input"));
		quantity_input_box.clear();
		quantity_input_box.sendKeys(quantity);
		clickUpdateButton(driver);
		String actual_error_msg = driver.findElement(By.xpath("//*[@class='item-msg error']")).getText();
		try{
			if(actual_error_msg.equalsIgnoreCase(WebUtil.EXPECTED_ERROR_MSG))
			{
				clickEmptyCartButton(driver);
				//verifyEmptyCart(driver);
			}
			else{
				System.out.println("Test case failed");
			}
		}
		catch(Exception ex){
			verifyQuantityInCart(driver,quantity);
			clickEmptyCartButton(driver);
		}
	}

	public void verifyEmptyCart(WebDriver driver) {
		String cart_quantity = driver.findElement(By.className("count")).getText();
		String expected_quantity = "0";
		Assert.assertEquals(cart_quantity, expected_quantity);
	}

	public void verifyQuantityInCart(WebDriver driver, String quantity) {
		String cart_quantity = driver.findElement(By.className("count")).getText();
		Assert.assertEquals(quantity, cart_quantity);
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
