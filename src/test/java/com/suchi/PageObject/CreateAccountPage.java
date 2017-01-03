package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.suchi.UtilPage.WebUtil;

public class CreateAccountPage {

	public DashBoard fillNewUserDetails(WebDriver driver, String first_Name, String last_Name, String email, String password,
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
			
			DashBoard dashboard = clickRegisterButton(driver);
			
			try
			{
				String actual_validation_advice = driver.findElement(By.className("validation-advice")).getText();
				if(actual_validation_advice.equals(WebUtil.EXPECETD_VALIDATION_ADVICE))
				{
					System.out.println("Please enter the required fields");
					//fillNewUserDetails(driver,first_Name,last_Name,email,password,confirmpassword);
					AccountPage accountPage = clickBackButton(driver);
				}
				
			}catch(NoSuchElementException e)
			{
				dashboard.verifyRegistration(driver);
			}
		}catch(Exception e)
		{
			
		}
		return PageFactory.initElements(driver, DashBoard.class);

		
	}

	public DashBoard clickRegisterButton(WebDriver driver) {
		try{
			WebElement register_button = driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button"));
			register_button.click();
		}catch(NoSuchElementException e)
		{
			
		}
		return PageFactory.initElements(driver, DashBoard.class);
	}

	public AccountPage clickBackButton(WebDriver driver) {
		try{
			WebElement back_button = driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/p/a"));
			back_button.click();
		}catch(NoSuchElementException e)
		{
			
		}
		return PageFactory.initElements(driver, AccountPage.class);
		
		
	}

}
