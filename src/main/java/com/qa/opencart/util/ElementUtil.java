package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exception.FrameworkException;

import io.netty.handler.timeout.TimeoutException;

public class ElementUtil 
{
	 private WebDriver driver;
	public ElementUtil(WebDriver driver)
	{
		this.driver =driver;
	}
	//LocatorType = "id", locatorValue = "input-email",
	public By getBy(String locatorType, String locatorValue) {
		By by = null;

		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;

		default:
			System.out.println("wrong locator type is passed..." + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}

		return by;

	}
	
	
	
	// locatorType = "id", locatorValue = "input-email", value = "tom@gmail.com"
		public void doSendKeys(String locatorType, String locatorValue, String value) {
			getElement(locatorType, locatorValue).sendKeys(value);
		}

		public void doSendKeys(By locator, String value) {
			getElement(locator).sendKeys(value);
		}

		public void doClick(By locator) {
			getElement(locator).click();
		}
		
		public void doClick(String locatorType, String locatorValue) {
			getElement(locatorType, locatorValue).click();
		}

		public String doElementGetText(By locator) {
			return getElement(locator).getText();
		}
		
		public String doElementGetText(String locatorType, String locatorValue) {
			return getElement(locatorType, locatorValue).getText();
		}

	public WebElement getElement(By locator) {
			return driver.findElement(locator);
		}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
		//LocatorType = "id", locatorValue = "input-email",
		public WebElement getElement(String locatorType, String locatorValue) {
			return driver.findElement(getBy(locatorType, locatorValue));
			//driver.findElement(By.id("input-email"));
		}
		
		public  List<String> getElementsTextList(By loactors)
		
		{
			List<WebElement> eleList=findElements(loactors);
			List<String> textList = new ArrayList<String>();
			for(WebElement e:eleList)
			{
				String text=e.getText();
				if(text.length()!=0) {
				textList.add(text);
				}
				
			}
			return textList;
		}
		
		// WAF: capture specific attribute from the list:
			public  List<String> getElementsAttributeList(By locator, String attrName) {
				List<WebElement> eleList = findElements(locator);
				List<String> eleAttrList = new ArrayList<String>();// pc=0 {}

				for (WebElement e : eleList) {
					String attrValue = e.getAttribute(attrName);
					eleAttrList.add(attrValue);
				}

				return eleAttrList;

			}
		
		
		
		public  int getElementCount(By locators)
		{
			return findElements(locators).size();
		}
		
		public  List<WebElement> findElements(By locators)
		{
			return driver.findElements(locators);
		}
		
		public  boolean checkSingleElementPresent(By locator) {
			return driver.findElements(locator).size() == 1 ? true : false;
		}
		
		public boolean checkElementPresent(By locator) {
			return driver.findElements(locator).size()>=1 ? true : false;
		}
		
		public boolean checkElementPresent(By locator, int totalElements) {
			return driver.findElements(locator).size()==totalElements ? true : false;
		}
		
		
		public void googleSearch(By SearchField, By suggestions, String searchKey, String SuggestionClick) throws InterruptedException
		{
			driver.findElement(SearchField).sendKeys(searchKey);
			Thread.sleep(3000);
			
			List<WebElement> listOfSerch=driver.findElements(suggestions);
			for(WebElement e: listOfSerch)
			{
				String actList=e.getText();
				System.out.println(actList);
				if (actList.contains(SuggestionClick))
				{
					e.click();
					break;
				}
				
			}
		}
		
		public void clikcOnElement(By locator, String eleText) {
			List<WebElement> eleList = findElements(locator);
			System.out.println(eleList.size());		
			for(WebElement e : eleList) {
				String text = e.getText();
				System.out.println(text);
					if(text.contains(eleText)) {
						e.click();
						break;
					}
			}
		}

		
		//***************** DropDown utility*******************
		private  Select createSelect(By locator)
		{
			Select select = new Select(getElement(locator));
			return select;
		}
		
		
		public  void doClickByVisibleTextDropDown(By locator, String value) 
		{
			//Select select = new Select(getElement(locator));
			createSelect(locator).selectByVisibleText(value);
		}
		
		public  void doClickByIndexDropDown(By locator, int value) 
		{
			//Select select = new Select(getElement(locator));
			createSelect(locator).selectByIndex(value);
		}
		
		public  void doClickByValueDropDown(By locator, String value) 
		{
			//Select select = new Select(getElement(locator));
			createSelect(locator).selectByValue(value);
		}
		
		public  void selectDropDownOption(By locator, String dropdownvalue) 
		{
			//Select select = new Select(getElement(locator));
			//createSelect(locator)
			List<WebElement> optionList=createSelect(locator).getOptions();
			
			
			for(WebElement ele:optionList)
			{
				String dropdownText=ele.getText();
				System.out.println(dropdownText);
				if(dropdownText.contains(dropdownvalue))
				{
					ele.click();
					break;
				}
				
			}
			
			
		}
		
		public  int gettDropDownOptionCount(By locator) 
		{
			//Select select = new Select(getElement(locator));
			return createSelect(locator).getOptions().size();
		}
		
		public  List <String> GetDropDownOptionList(By locator, String dropdownvalue) 
		{
			//Select select = new Select(getElement(locator));
			List<WebElement> optionList=createSelect(locator).getOptions();
			List <String> dropdownList = new ArrayList<String>();
			for(WebElement ele:optionList)
			{
				String dropdownText=ele.getText();
				System.out.println(dropdownText);
				dropdownList.add(dropdownText);
			}
			return dropdownList;
			
		}
		public  void selectDropDownValue(By locator, String value) 
		{
			List<WebElement> optionsList = getElements(locator);
			for(WebElement e : optionsList) {
				String text = e.getText();
				if (text.equals(value)){
					e.click();
					break;
				}
		  
			
			
			}
			
			
	
		}
		
		public  boolean isDropDownMultiple(By locator) {
			//locator(getElement(locator));
			return createSelect(locator).isMultiple() ? true : false;
		}
		
		/**
		 * This method is used to select the values from dropdown
		 * 1. to select single value
		 * 2. to select multiple value
		 * 3. to select All values ( for all values you just need to select "all"
		 * @param locator
		 * @param values
		 */
		
		public  void selectDropDownMultipleValues(By locator, By optionLocator, String...values) 
		
		{
			//Select select =new Select(getElement(locator));
			if(isDropDownMultiple(locator))
			{
				if (values[0].equalsIgnoreCase("all"))
					
				{
					List<WebElement> option=getElements(optionLocator);
					for(WebElement e:option)
					{
						e.click();
					}
					
				}
				else {
				for(String value: values)
				{
					createSelect(locator).selectByVisibleText(value);
				
//				for(int value=0;value<values.length;value++)
//				{
//					String  str=(values[value]);
//				}
				}
				}
			}
		}
		
		//*****************Actions utils ***************//
		
		public void doActionsSendKeys(By locator, String value) {
			Actions act = new Actions(driver);
			act.sendKeys(getElement(locator), value).perform();
		}
		
		public void doActionsClick(By locator) {
			Actions act = new Actions(driver);
			act.click(getElement(locator)).perform();
		}
		
		
		
		public void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
			Actions act = new Actions(driver);
			act.moveToElement(getElement(parentMenuLocator)).build().perform();
			Thread.sleep(1000);
			doClick(childMenuLocator);
		}
		
		public void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocaor, 
				By secondChildMenuLocaor,
				By thirdChildMenuLocaor) throws InterruptedException {

			Actions act = new Actions(driver);

			doClick(parentMenuLocator);
			Thread.sleep(1000);

			act.moveToElement(getElement(firstChildMenuLocaor)).build().perform();

			Thread.sleep(1000);

			act.moveToElement(getElement(secondChildMenuLocaor)).build().perform();

			Thread.sleep(1000);

			doClick(thirdChildMenuLocaor);
		}
		
		public void doSendKeysWithPause(By loactor, String value)
		{
			//String value1 = "Gaurav.sharma@gmail.com";
			WebElement ele=getElement(loactor);
			char val[]=value.toCharArray();
			Actions act = new Actions(driver);
			for(char c: val)
			{
				act.sendKeys(ele,String.valueOf(c)).pause(5000).build().perform();
			}
			
		}
		
		//****************Wait Utils***************//
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page. 
		 * This does not necessarily mean that the element is visible on the page.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForPresenceOfElement(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		}
		/**
		 * An expectation for checking that an element is present on the DOM of a page. 
		 * This does not necessarily mean that the element is visible on the page.
		 * @param locator
		 * @param intervalTime
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(intervalTime));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

		}
		
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible. 
		 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		}
		
		/**
		 * An expectation for checking that an element is present on the DOM of a page and visible. 
		 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @param timeInterval
		 * @return
		 */
		public WebElement waitForVisibilityOfElement(By locator, int timeOut, int timeInterval) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofSeconds(timeInterval));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

		}
		
		
		public void doClickWithWait(By locator, int timeOut) {
			waitForVisibilityOfElement(locator, timeOut).click();
		}
		
		public void doSendKeysWithWait(By locator, String value, int timeOut) {
			waitForVisibilityOfElement(locator, timeOut).sendKeys(value);
		}
		
		
		/**
		 * An expectation for checking that all elements present on the web page that match the locator are visible. 
		 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

		}
		
		/**
		 * An expectation for checking that there is at least one element present on a web page.
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
		}
		
		public  String waitForTitleContains(String titleFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
					return driver.getTitle();
				}
			} catch (TimeoutException e) {
				System.out.println(titleFraction + " title value is not present....");
				e.printStackTrace();
			}
			return null;

		}
		
		
		public  String waitForTitleIs(String title, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.titleIs(title))) {
					return driver.getTitle();
				}
			} catch (TimeoutException e) {
				System.out.println(title + " title value is not present....");
				e.printStackTrace();
			}
			return null;

		}
		
		
		
		public  String waitForURLContains(String urlFraction, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
					return driver.getCurrentUrl();
				}
			} catch (TimeoutException e) {
				System.out.println(urlFraction + " url value is not present....");
				e.printStackTrace();
			}
			return null;

		}
		
		
		public  String waitForURLToBe(String url, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.urlToBe(url))) {
					return driver.getCurrentUrl();
				}
			} catch (TimeoutException e) {
				System.out.println(url + " url value is not present....");
				e.printStackTrace();
			}
			return null;

		}
		public  void waitForFrameByLocator(By frameLocator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}
		
		public  void waitForFrameByIndex(int frameIndex, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}
		
		public  void waitForFrameByIDOrName(String IDOrName, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
		}
		
		public  void waitForFrameByElement(WebElement frameElement, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
		}
		
		public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindows) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

			try {
				if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
					return true;
				}
			} catch (TimeoutException e) {
				System.out.println("number of windows are not same....");
			}
			return false;
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public void clickElementWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
			} catch (TimeoutException e) {
				System.out.println("element is not clickable or enabled...");
			}
		}
		
		
		
		public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done...element is not found....")
					.ignoring(NoSuchElementException.class)
					.ignoring(ElementNotInteractableException.class);
				
					

			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}
		
		
		public void waitForFrameWithFluentWait(String frameIDORName, int timeOut, int intervalTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done...frame is not found....")
					.ignoring(NoSuchFrameException.class);
					

			 wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIDORName));
		}
		
		
		public Alert waitForJSAlertWithFluentWait(int timeOut, int intervalTime) {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(intervalTime))
					.withMessage("--time out is done...alert is not appeared....")
					.ignoring(NoAlertPresentException.class);

			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		
		//*****************Custom Wait***********************//
		
		public WebElement retryingElement(By locator, int timeOut) {

			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element is found...." + locator + " in attempt " + attempts);
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found...." + locator + " in attempt " + attempts);
					try {
						Thread.sleep(500);// default polling time = 500 ms
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				}

				attempts++;
			}

			if (element == null) {
				System.out.println("element is not found....tried for " + timeOut + " times " + " with the interval of "
						+ 500 + " milli seconds ");
				throw new FrameworkException("No Such Element");
			}

			return element;
		}

		public WebElement retryingElement(By locator, int timeOut, int intervalTime) {

			WebElement element = null;
			int attempts = 0;

			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element is found...." + locator + " in attempt " + attempts);
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found...." + locator + " in attempt " + attempts);
					try {
						Thread.sleep(intervalTime);// custom polling time
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}

				}

				attempts++;
			}

			if (element == null) {
				System.out.println("element is not found....tried for " + timeOut + " times " + " with the interval of "
						+ 500 + " milli seconds ");
				throw new FrameworkException("No Such Element");
			}

			return element;
		}
		
		
		public boolean isPageLoaded(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'")).toString();
			return Boolean.parseBoolean(flag);
		}
		

	
		
		}
