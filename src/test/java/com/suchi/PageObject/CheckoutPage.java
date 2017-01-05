package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
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

	public ShippingPage enterBillingDetails(WebDriver driver, String first_Name, String last_Name, String address, String city, String State,
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
			
			
			//Send State
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement Select_state = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:region_id")));
			Select State_id = new Select(Select_state);
			State_id.selectByVisibleText(State);
			
				
			//Send Zip
			WebUtil.sendInput(driver, By.id("billing:postcode"), zip);
			
			
			//Select Country
			WebElement Select_country = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:country_id")));
			Select country_id = new Select(Select_country);
			country_id.selectByVisibleText(country);
			
			
			//Send Telephone Number
			WebUtil.sendInput(driver, By.id("billing:telephone"), telephone);
		
			
			//clickContinueButton(driver);
			//Choose the radio button to select the same billing address as shipping address
			try{
			WebElement Ship_to_this_address_radio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("billing:use_for_shipping_yes")));
			boolean radio_status = Ship_to_this_address_radio.isSelected();
			
			if(radio_status = true)
			{
				//Click Continue button
				//clickContinueButton(driver);
				WebElement Ship_Continue_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")));
				Ship_Continue_button.click();
			}
			else
			{
				//Select the required radio button
				Ship_to_this_address_radio.click();
				
				//Click Continue button
				//clickContinueButton(driver);
				driver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).click();
			}
			}catch(ElementNotVisibleException e)
			{
				e.printStackTrace();
			}
		}catch(NoSuchElementException e)
		{
			System.out.println("All Billing information are not filled");
		}
		return PageFactory.initElements(driver, ShippingPage.class);
		
	}

	/*public void clickContinueButton(WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,10);
			WebElement continue_button = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")));
			boolean continue_button_status = driver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).isDisplayed();
			if(continue_button_status = true){
				driver.findElement(By.xpath("//div[@id='billing-buttons-container']//button[@title='Continue']")).click();}
			
		}catch(ElementNotVisibleException e)
		{
			e.printStackTrace();
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Continue button not found");
		}
		
	}*/

	

}
