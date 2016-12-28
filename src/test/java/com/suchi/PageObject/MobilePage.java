package com.suchi.PageObject;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.suchi.UtilPage.WebUtil;

import junit.framework.Assert;

public class MobilePage {

	public void verifyMobilePageTitle(WebDriver driver) {
		String Mobile_Pagetitle= driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
		Assert.assertEquals(Mobile_Pagetitle, WebUtil.MOBILE_PAGE_TITLE);
	}

	public void selectSortByName(WebDriver driver) {
		Select dropdown = new Select(driver.findElement(By.xpath("//*[@id='top']/body/div/div/div[2]/div/div[2]/div[1]/div[3]/div[1]/div[1]/div/select")));
		dropdown.selectByIndex(1);
	}

	public List<WebElement> getAllProducts(WebDriver driver) {
		List<WebElement> products = driver.findElements(By.className("product-name"));
		System.out.println("Number of product on the page: "+products.size());
			
		return products;
		
	}

	public void verifyProductSorting(WebDriver driver, List<WebElement> productList) {
		
		for(int i=0;i<productList.size();i++)
		{
			System.out.println("Products Name at "+i+" location is: "+productList.get(i).getText());
		}
		
		//Collections.sort(productList);
	}

	public void searchProductCost(WebDriver driver, List<WebElement> productList, String ProductName) {
		for(int i=0;i<productList.size();i++)
		{
			if((productList.get(i).getText()).equalsIgnoreCase(ProductName))
			{
				System.out.println("Product "+ProductName+" found in the list");
			}
			else
			{
				System.out.println("Product not found");
			}
		}
	}
}
