package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.suchi.UtilPage.WebUtil;

import junit.framework.Assert;

public class CheckoutPage {

	public void enterBillingDetails(WebDriver driver, String first_Name, String last_Name, String address, String city, String State,
			 String zip, String country, String telephone) throws Exception {
		try{
			//Send First_name
			WebUtil.sendInput(driver, By.id("billing:firstname"), first_Name);
			
			//Send last_name
			WebUtil.sendInput(driver, By.id("billing:lastname"), last_Name);
			
			//Send Address
			WebUtil.sendInput(driver, By.id("billing:street1"), address);
			
			//Send city
			WebUtil.sendInput(driver, By.id("billing:city"), city);
			Thread.sleep(3000);
			
			//Send State
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement Select_state = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:region_id")));
			Select State_id = new Select(Select_state);
			State_id.selectByVisibleText(State);
			
			Thread.sleep(3000);
			
			//Send Zip
			WebUtil.sendInput(driver, By.id("billing:postcode"), zip);
			Thread.sleep(3000);
			
			//Select Country
			
			WebElement Select_country = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:country_id")));
			Select country_id = new Select(Select_country);
			country_id.selectByVisibleText(country);
			Thread.sleep(3000);
			
			//Send Telephone Number
			WebUtil.sendInput(driver, By.id("billing:telephone"), telephone);
			Thread.sleep(3000);
			
			//clickContinueButton(driver);
			//Choose the radio button to select the same billing address as shipping address
			
			//WebElement Ship_to_this_address_radio = driver.findElement(By.id("billing:use_for_shipping_yes"));
			WebElement Ship_to_this_address_radio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:use_for_shipping_yes")));
			boolean radio_status = Ship_to_this_address_radio.isSelected();
			
			if(radio_status = true)
			{
				//Click Continue button
				clickContinueButton(driver);
			}
			else
			{
				//Select the required radio button
				Ship_to_this_address_radio.click();
				
				//Click Continue button
				clickContinueButton(driver);
				
			}
		}catch(NoSuchElementException e)
		{
			System.out.println("All Billing information are not filled");
		}
		
	}

	public void clickContinueButton(WebDriver driver) {
		try{
			WebElement continue_button = driver.findElement(By.xpath("//*[@id='billing-buttons-container']/button"));
			continue_button.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("Continue button not found");
		}
		
	}

	public void clickShippingContinue(WebDriver driver) {
		try{
			WebElement shipping_continue_button = driver.findElement(By.cssSelector("#shipping-method-buttons-container > button"));
			shipping_continue_button.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("Shipping Continue button not found");
		}
		
	}

	public void selectCheckPaymentMethod(WebDriver driver) {
		try{
			WebElement check_payment_radio = driver.findElement(By.id("p_method_checkmo"));
			check_payment_radio.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("Check Payment method not found");
		}
		
	}

	public void ReviewProductName(WebDriver driver, String wish_TV_Name) {
		try{
			String product_to_review = driver.findElement(By.xpath("//*[@id='checkout-review-table']/tbody/tr/td[1]/h3")).getText();
			Assert.assertEquals(product_to_review, wish_TV_Name);
		}catch(NoSuchElementException e)
		{
			System.out.println("Product to review not found");
		}
		
	}

	public SignInPage clickToPlaceOrder(WebDriver driver) {
		try{
			WebElement placeorder_button = driver.findElement(By.cssSelector("#review-buttons-container > button"));
			placeorder_button.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("PlaceOrder button not found");
		}
		return PageFactory.initElements(driver, SignInPage.class);
		
	}

}
