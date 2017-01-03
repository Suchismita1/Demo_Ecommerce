package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	public CreateAccountPage click_Account_Creation(WebDriver driver) {
		try{
			WebElement newAccCreationLink = driver.findElement(By.linkText("CREATE AN ACCOUNT"));
			newAccCreationLink.click();
			}catch(NoSuchElementException e)
			{
				System.out.println("no element found 3");
			}
		return PageFactory.initElements(driver, CreateAccountPage.class);
		
	}

	public DashBoard click_Account_Login(WebDriver driver, String email, String password) throws Exception {
		try{
			WebElement Email_inputbox = driver.findElement(By.name("login[username]"));
			Email_inputbox.sendKeys(email);
			Thread.sleep(1000);
			WebElement Password_inputbox = driver.findElement(By.id("pass"));
			Password_inputbox.sendKeys(password);
			
			DashBoard dashboard = clickLoginButton(driver);
		}catch(NoSuchElementException e)
		{
			System.out.println("no element found 1");
		}
		
		return PageFactory.initElements(driver, DashBoard.class);
	}

	public DashBoard clickLoginButton(WebDriver driver) {
		try{
			WebElement Login_button = driver.findElement(By.id("send2"));
			Login_button.click();
			
		}catch(NoSuchElementException e)
		{
			System.out.println("no element found 2");
		}
		return PageFactory.initElements(driver, DashBoard.class);
	}

}
