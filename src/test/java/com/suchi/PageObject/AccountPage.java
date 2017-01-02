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
				System.out.println("no element found");
			}
		return PageFactory.initElements(driver, CreateAccountPage.class);
		
	}

}
