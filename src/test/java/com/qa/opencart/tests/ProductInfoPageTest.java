package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {
	@BeforeClass
	public void productInfo()
	{
		
		//accPage = new AccountsPage(driver);
		
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@DataProvider
	
	public Object getSerachData()
	{
		return new Object[][] {{"MacBook","MacBook Pro",4},{"Macbook","MacBook Air",4},{"apple","Apple Cinema 30\"",6}
			
		};
	}
	
	@Test(dataProvider="getSerachData")
	public void imageCountTest(String searchKey, String productName, int imageCount)
	{
		seachResultsPage=accPage.doSearch(searchKey);
		productInfoPage=seachResultsPage.selectProduct(productName);
		//int productImgCount=productInfoPage.getProductImageCount();
		//Assert.assertEquals(productImgCount, 4);
		Assert.assertEquals(productInfoPage.getProductImageCount(), imageCount);
	}
	

//    Brand: Apple
//    Product Code: Product 18
//    Reward Points: 800
//    Availability: In Stock
@Test(dataProvider="getSerachData")
	public void productInfoTest()
	{
		seachResultsPage=accPage.doSearch("MacBook");
		productInfoPage=seachResultsPage.selectProduct("MacBook Pro");
		Map<String, String> productDeatilsMap=productInfoPage.getProductDetails();
		softAssert.assertEquals(productDeatilsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDeatilsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDeatilsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDeatilsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDeatilsMap.get("extaprice"), "$2,000.00");
		softAssert.assertAll();
	}

}
