package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	By LogoutLink = By.linkText("Logout");
	
	By search = By.xpath("//input[@name='search']");
	By searchIcon = By.xpath("//div[@id='search']//button");
	
	// page const...
		public AccountsPage(WebDriver driver) {
			this.driver = driver;
			eleUtil = new ElementUtil(driver);
			
		}
		
		//page actions
		
		public boolean isLogoutLinkExist()
		{
			return eleUtil.waitForVisibilityOfElement(LogoutLink, AppConstant.SHORT_DEFAULT_WAIT).isDisplayed();
		}
		
		public void logOut()
		{
			if (isLogoutLinkExist())
			{
				eleUtil.doClick(LogoutLink);
			}
		}
		
		public SearchResultsPage doSearch(String searchKey)
		{
			eleUtil.waitForVisibilityOfElement(search, AppConstant.MEDIUM_DEFAULT_WAIT).clear();
			eleUtil.waitForVisibilityOfElement(search, AppConstant.MEDIUM_DEFAULT_WAIT).sendKeys(searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}


}
