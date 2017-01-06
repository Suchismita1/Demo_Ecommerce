package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {

	public void clickPrintOrder(WebDriver driver) {
		try{
			WebElement PrintOrderButton = driver.findElement(By.linkText("Print Order"));
			PrintOrderButton.click();
			
		}catch(NoSuchElementException e)
		{
			System.out.println("Print Order option not found");
		}
		
	}

}
