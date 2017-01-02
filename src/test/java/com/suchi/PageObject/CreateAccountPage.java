package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPage {

	public void fillNewUserDetails(WebDriver driver, String first_Name, String last_Name, String email, String password,
			String confirmpassword) {
		
		try{
			
			WebElement FirstName_textbox = driver.findElement(By.id("firstname"));
			FirstName_textbox.sendKeys(first_Name);
			
			WebElement LastName_textbox = driver.findElement(By.id("lastname"));
			LastName_textbox.sendKeys(last_Name);
			
			WebElement Email_textbox = driver.findElement(By.id("email_address"));
			Email_textbox.sendKeys(email);
			
			WebElement Password_textbox = driver.findElement(By.id("password"));
			Password_textbox.sendKeys(password);
			
			WebElement ConfirmPwd_textbox = driver.findElement(By.id("confirmation"));
			ConfirmPwd_textbox.sendKeys(confirmpassword);
			
			try
			{
				WebElement register_button = driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button"));
				register_button.click();
			}catch(NoSuchElementException e)
			{
				
			}
		}catch(Exception e)
		{
			
		}

		
	}

}
