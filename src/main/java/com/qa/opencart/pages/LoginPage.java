package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constant.AppConstant;
import com.qa.opencart.util.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// By locators: OR
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	
	private By registerLink=By.linkText("Register");
	
	// page const...
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}

	// page actions/methods:
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstant.Login_Page_Title, AppConstant.SHORT_DEFAULT_WAIT);
		System.out.println("login page title:" + title);
		return title;
	}

	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstant.Login_Page_URL_Fraction, AppConstant.SHORT_DEFAULT_WAIT);
		System.out.println("login page url:" + url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstant.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstant.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForVisibilityOfElement(userName, AppConstant.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegisterPage NavigateToRegisterPage()
	{
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstant.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}





	

}