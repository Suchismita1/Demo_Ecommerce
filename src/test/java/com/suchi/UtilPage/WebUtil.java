package com.suchi.UtilPage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.suchi.PageObject.MobilePage;
import com.suchi.PageObject.SignInPage;

public class WebUtil {
	
	public static final String BASE_URL = "http://live.guru99.com/index.php/";
	
	public static final int WAIT_TIME = 30;
	
	public static final String WELCOME_MSG = "DEFAULT WELCOME MSG!";
	
	public static final String MOBILE_PAGE_TITLE = "MOBILE";
	
	public static SignInPage getSigninPage(WebDriver driver) {
		driver.get(BASE_URL);
		return PageFactory.initElements(driver, SignInPage.class);
	}

	public static void clickElement(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

}
