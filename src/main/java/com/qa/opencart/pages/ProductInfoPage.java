package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	private By productheader = By.xpath("//div[@id='content']//h1");
	
	private By productImages = By.cssSelector(".thumbnails img");
	
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");
	
	//private Map<String, String> productMap=new HashMap<String, String>(); // without order
	private Map<String, String> productMap=new LinkedHashMap<String, String>();  // for order based
	//private Map<String, String> productMap=new TreeMap<String, String>();// for alphabetical order
	// page const...
			public ProductInfoPage(WebDriver driver) {
				this.driver = driver;
				eleUtil = new ElementUtil(driver);
				
			}
			
			public String getProductheader()
			{
				String actHeader=eleUtil.doElementGetText(productheader);
				System.out.println("Product header value  "+actHeader);
				return actHeader;
			}
			
			public int getProductImageCount()
			{
				int productImageCount=eleUtil.waitForVisibilityOfElements(productImages, AppConstant.MEDIUM_DEFAULT_WAIT).size();
				System.out.println("Image count is  "+productImageCount);
				return productImageCount;
			}
			

//		    Brand: Apple
//		    Product Code: Product 18
//		    Reward Points: 800
//		    Availability: In Stock

			
			private void getProductMeataData()
			{
				List <WebElement> metaDataList=eleUtil.waitForVisibilityOfElements(productMetaData, AppConstant.MEDIUM_DEFAULT_WAIT);
				for(WebElement e:metaDataList)
				{
					String metaData=e.getText(); //Brand: Apple
					String metaKey=metaData.split(":")[0].trim();
					String metaValue=metaData.split(":")[1].trim();
					productMap.put(metaKey, metaValue);
				}
			}
			
			

//		    $2,000.00
//		    Ex Tax: $2,000.00

			private void getProductPrice()
			{
				List <WebElement> metaPriceList=eleUtil.waitForVisibilityOfElements(productPriceData, AppConstant.MEDIUM_DEFAULT_WAIT);
				
				String productPrice=metaPriceList.get(0).getText(); //$2,000.00
				String productExPrice=metaPriceList.get(1).getText().split(":")[1].trim(); //Ex Tax: $2,000.00
				productMap.put("price", productPrice);
				productMap.put("extaprice", productExPrice);
				
				
				}
			
			public Map<String, String> getProductDetails()
			{
				productMap.put("productName", getProductheader());
				getProductMeataData();
				getProductPrice();
				System.out.println(productMap);
				return productMap;
			}
			}


