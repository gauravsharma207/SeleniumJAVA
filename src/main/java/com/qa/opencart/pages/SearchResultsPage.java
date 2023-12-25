package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// page const...
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver); //Test Driven Development approach

	}
	
	public ProductInfoPage selectProduct(String productName)
	{
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstant.MEDIUM_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}

}
