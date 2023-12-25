package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;


public class AccountPageTest extends BaseTest
{
	@BeforeClass
	public void accSetUp()
	{
		
		//accPage = new AccountsPage(driver);
		
		accPage=loginPage.doLogin("gaurav@test.com", "test@123");
	}
	
	@Test
	public void isLogOutLinkExist()
	{
		Assert.assertTrue(accPage.isLogoutLinkExist());
		
	}
	
	@Test
	public void searchTest()
	{
		seachResultsPage=accPage.doSearch("MacBook");
		productInfoPage=seachResultsPage.selectProduct("MacBook Pro");
		String actualHeaderName=productInfoPage.getProductheader();
		Assert.assertEquals(actualHeaderName, "MacBook Pro");
	}
	
	
}
