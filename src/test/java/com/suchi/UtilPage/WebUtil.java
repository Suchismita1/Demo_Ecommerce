package com.suchi.UtilPage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.suchi.PageObject.MobilePage;
import com.suchi.PageObject.ProductPage;
import com.suchi.PageObject.SignInPage;

public class WebUtil {
	
	public static final String BASE_URL = "http://live.guru99.com/index.php/";
	
	public static final int WAIT_TIME = 30;
	
	public static final String WELCOME_MSG = "DEFAULT WELCOME MSG!";
	
	public static final String MOBILE_PAGE_TITLE = "MOBILE";

	public static final String EXPECTED_ERROR_MSG = "* The maximum quantity allowed for purchase is 500.";
	
	public MobilePage mobilePage;
	public ProductPage productPage;
	
	public static SignInPage getSigninPage(WebDriver driver) {
		driver.get(BASE_URL);
		return PageFactory.initElements(driver, SignInPage.class);
	}

	public static void clickElement(WebDriver driver, By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public static void getScreenShot(WebDriver driver) throws Exception {
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//String path = "src/test/resources/Screenshot";
		String currentDir = "src/test/resources";
		FileUtils.copyFile(scrFile, new File(currentDir + "\\screenshots\\" + System.currentTimeMillis() + ".png"));
		//FileUtils.copyFile(scrFile, new File("src/test/resources/Screenshot/test.png"));
	}

	public static void navigateBackMenu(WebDriver driver) {
		driver.navigate().back();
		
	}
	
}
