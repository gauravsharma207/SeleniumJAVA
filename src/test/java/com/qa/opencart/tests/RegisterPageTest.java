package com.qa.opencart.tests;

import java.util.UUID;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.util.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	@BeforeClass
	public void RegisterPageSetup()
	{
		registerPage=loginPage.NavigateToRegisterPage();
	}
	
	public String getRandomEmail()
	{
	
	return "testautomation"+System.currentTimeMillis()+"@abc.com";
	//return "testautomation"+UUID.randomUUID()+"abc.com";
	}
	@DataProvider
	public Object getRegisterationData()
	{
		return new Object[][] {
			{"Tom11","Kumar","987456123","12345","yes"},
			//{"Tom11","Kumar","Abc11@abc.com","987456123","12345","yes"},
			{"Tom21","Kumar","987456123","12345","yes"},
			{"Tom31","Kumar","987456123","12345","yes"},
		
		
		};
	        
	}
	
	//read from excel
	@DataProvider
	public Object[][] getRegisterationExcelData()
	{
	Object regData[][]=ExcelUtil.getTestData(AppConstant.REGISTER_DATA_SHEET);
	return regData;
	}
	
	@Test(dataProvider="getRegisterationExcelData")
	public void userRegTest(String firstName,String lastName,String phoneNo, String password, String sub)
	{
		//boolean useRreg=registerPage.userRegisteration("Te2st", "LastName", "a1bc@abc.com", "98724512222", "12345", "yes");
		boolean useRreg=registerPage.userRegisteration(firstName, lastName, getRandomEmail(), phoneNo, password, sub);
		//Assert.assertTrue(registerPage.userRegisteration("Test", "LastName", "abc@abc.com", "9874512222", "12345", "yes"));
		Assert.assertTrue(useRreg);
	}
}
