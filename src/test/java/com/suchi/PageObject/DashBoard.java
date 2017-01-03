package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.suchi.UtilPage.WebUtil;

public class DashBoard {
	CreateAccountPage createAccount;
	public void verifyRegistration(WebDriver driver) {
		
		try
		{
			String actual_success_msg = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span")).getText();
			String actual_Failure_msg = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span/text()[1]")).getText();
			
			if(actual_success_msg.equals(WebUtil.EXP_REG_SUCCESS_MSG)){
				System.out.println("You have successfully Registered");
			}
			else if(actual_Failure_msg.contains(WebUtil.EXP_REG_FAILURE_MSG))
			{
				System.out.println("You are already a registered user");
				AccountPage accountPage = createAccount.clickBackButton(driver);
			}
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void clickTVMenu(WebDriver driver) {
		try{
			WebElement TV_Menu = driver.findElement(By.linkText("TV"));
			TV_Menu.click();
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void addTVtoWishList(WebDriver driver, String wish_TV_Name) {
		try
		{
			String TV_AddtoWishlist_xpath = "//*[contains(text(),'"+wish_TV_Name+"')]//following::a[contains(text(),'Add to Wishlist')]";
			driver.findElement(By.xpath(TV_AddtoWishlist_xpath)).click();
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void clickShareWishlistButton(WebDriver driver) {
		try{
			WebElement WishList_button = driver.findElement(By.xpath("//*[@id='wishlist-view-form']/div/div/button[1]"));
			WishList_button.click();
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void enterSharingDeatils(WebDriver driver, String email, String message) {
		try{
			WebElement Email_textBox = driver.findElement(By.id("email_address"));
			Email_textBox.sendKeys(email);
			WebElement message_textBox = driver.findElement(By.id("message"));
			message_textBox.sendKeys(message);
			
			clickShareWishList(driver);
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void clickShareWishList(WebDriver driver) {
		try{
			WebElement share_button = driver.findElement(By.xpath("//*[@id='form-validate']/div[2]/button/span/span"));
			share_button.click();
		}catch(NoSuchElementException e)
		{
			
		}
		
	}
	public void verifySharing(WebDriver driver) {
		String actual_sharing_msg = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();
		Assert.assertEquals(actual_sharing_msg, WebUtil.EXP_WISHLIST_SUCCESS_MSG);
		
	}
	

}
