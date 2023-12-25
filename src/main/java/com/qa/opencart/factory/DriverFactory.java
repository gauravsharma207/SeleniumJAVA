package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory 
{

	WebDriver driver;
	Properties prop;
	OptionManager optionManager;
	
	public WebDriver intiBrowser(Properties prop)
	
	
	{
		optionManager = new OptionManager(prop);
		
		String browserName=prop.getProperty("browser");
		System.out.println("Browsername is " +browserName);
		
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver(optionManager.getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver(optionManager.getFirefoxOptions());
			break;
		case "edge":
			driver = new EdgeDriver();
			break;

		default:
			System.out.println("Inavlid browser");
			throw new FrameworkException("Browser not found "+browserName);
			//break;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		return driver;
	}
	public Properties initProp()
	{
		prop=new Properties();
		try {
			FileInputStream ip = new FileInputStream(".\\src\\test\\resorces\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
}
