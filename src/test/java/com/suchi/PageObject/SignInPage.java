package com.suchi.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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

}
