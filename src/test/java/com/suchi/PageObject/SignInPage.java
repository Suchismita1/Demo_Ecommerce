package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.suchi.UtilPage.WebUtil;

//import junit.framework.Assert;

public class SignInPage {

	public void verifyWelcomeMsg(WebDriver driver) {
		String welcome_msg=driver.findElement(By.cssSelector("body > div > div > div.header-language-background > div > p")).getText();
		Assert.assertEquals(welcome_msg, WebUtil.WELCOME_MSG);
		
	}

	public MobilePage clickMobileMenu(WebDriver driver) {
		WebUtil.clickElement(driver,By.linkText("MOBILE"));
		return PageFactory.initElements(driver, MobilePage.class);
	}

	public AccountPage clcikMyAccount(WebDriver driver) {
		try{
		WebElement myAccountLinK = driver.findElement(By.linkText("MY ACCOUNT"));
		myAccountLinK.click();
		}catch(NoSuchElementException e)
		{
			System.out.println("no element found");
		}
		return PageFactory.initElements(driver, AccountPage.class);
		
	}

	public void verifyPurchaseOrder(WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver,30);
			String actual_purchase_status_msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='page-title']"))).getText();
			//System.out.println(actual_purchase_status_msg);
			if(actual_purchase_status_msg.equalsIgnoreCase(WebUtil.EXP_PURCHASE_STATUS_MSG)){
				String purchase_order = getPurchaseOrder(driver);
				System.out.println("Your Purchase order no is : "+purchase_order);
			}
			else
			{
				System.out.println("Purchase unsuccessful");
			}
		}catch(NoSuchElementException e)
		{
			System.out.println("No Order Placed successfully");
		}
		
	}

	public String getPurchaseOrder(WebDriver driver) {
		String purchase_order = null;
		try{
		purchase_order = driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div/p[1]/a")).getText();
		}catch(NoSuchElementException e)
		{
			System.out.println("No Purchase Order found");
		}
		
		return purchase_order;
	}

}
