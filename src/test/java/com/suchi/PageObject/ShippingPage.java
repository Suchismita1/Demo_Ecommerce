package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class ShippingPage {
	
	public void selectCheckPaymentMethod(WebDriver driver) {
		try{
			
			WebElement check_payment_radio = driver.findElement(By.id("p_method_checkmo"));
			check_payment_radio.click();
			clickPaymentContinueButton(driver);
		}catch(NoSuchElementException e)
		{
			System.out.println("Check Payment method not found");
		}
		
	}

	public void clickPaymentContinueButton(WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='payment-buttons-container']/button"))).click();
		}catch(NoSuchElementException e)
		{
			System.out.println("Payment method continue button not found");
		}
		
	}
	
	public void ReviewProductName(WebDriver driver, String wish_TV_Name) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			String product_to_review = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-review-table']/tbody/tr/td[1]/h3"))).getText();
			Assert.assertEquals(product_to_review, wish_TV_Name);
		}catch(NoSuchElementException e)
		{
			System.out.println("Product to review not found");
		}
		
	}

	public SignInPage clickToPlaceOrder(WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			WebElement placeorder_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#review-buttons-container > button")));
			placeorder_button.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("PlaceOrder button not found");
		}
		return PageFactory.initElements(driver, SignInPage.class);
		
	}
	
	public void clickShippingContinue(WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			WebElement shipping_continue_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#shipping-method-buttons-container > button")));
			shipping_continue_button.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("Shipping Continue button not found");
		}
		
		
	}

}
