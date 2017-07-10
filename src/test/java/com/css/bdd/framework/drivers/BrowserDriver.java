package com.css.bdd.framework.drivers;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.css.bdd.framework.ExecutionException;
import com.css.bdd.framework.page.UIElement;

public class BrowserDriver implements IDriver {

	@Value("${ie.bin.path}")
	private String ieBinPath;

	private WebDriver driver;

	private boolean destoryAtTheEnd = true;

	private static final Logger logger = LoggerFactory
			.getLogger(BrowserDriver.class.getName());

	public void createNewDriverInstance() throws ExecutionException {
//		String browserType = detectBroserType();
//		if(browserType == "IE") {
//			createNewIeDriverInstance();
//		} else if(browserType=="chrome") {
//			createNewChromeDriverInstance();
//		} else {
//			//createFireFoxDriverInstance();
//			print();
			createNewIeDriverInstance();
//		}
	}


	private void createNewIeDriverInstance() {
		BrowserDriver.logger
				.debug("---------- Startup WebDriver on IE ----------");
		System.setProperty("webdriver.ie.driver",
				"C:\\IEDriverServer_x64_2.53.1.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities
				.internetExplorer();
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("ignoreZoomSetting", true);
		this.driver = new InternetExplorerDriver(ieCapabilities);
		this.driver.manage().window().maximize();
		this.driver.manage().deleteAllCookies();

	}

	private void createNewChromeDriverInstance() {
		BrowserDriver.logger
				.debug("---------- Startup WebDriver on Chrome ----------");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		//options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		this.driver = new ChromeDriver(capabilities);
		//this.driver=new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
    private void createFireFoxDriverInstance() {
    	System.setProperty("webdriver.firefox.bin",
				"C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
    	
    	this.driver = new FirefoxDriver();
    }
	private String detectBroserType() {
		try {
			return System.getProperty("browser.type").toLowerCase();
		} catch (NullPointerException e) {
			String browserType = BrowserTypes.IE.getDisplayName();
			System.setProperty("browser.type", browserType);
			return browserType;
		}
	}


	public void get(String url) {
		//this.driver.print();
		this.driver.get(url);

	}

	protected By getBy(final String param) {
		if(param !=null && (param.startsWith("/") || param.startsWith("(/") || param.startsWith("./"))) {
			return By.xpath(param);
		}
		int index = param.indexOf("=");
		if(index >=0) {
			String method = param.substring(0, index).trim();
			String key = param.substring(index+1).trim();
			if(method.equalsIgnoreCase("id")) {
				return By.id(key);
			} else if (method.equalsIgnoreCase("name")) {
				return By.name(key);
		    } else if (method.equalsIgnoreCase("xpath")) {
				return By.xpath(key);
		    } else if (method.equalsIgnoreCase("class")) {
			    return By.className(key);
		    } else if (method.equalsIgnoreCase("css")) {
		    	return By.cssSelector(key);
		    } else if (method.equalsIgnoreCase("link")) {
		    	return By.linkText(key);
		    } else if (method.equalsIgnoreCase("partiallink")) {
		    	return By.partialLinkText(key);
		    } else if (method.equalsIgnoreCase("text")) {
		    	return By.xpath("//*[@text='"+key+"']");
		    }
		}
			throw new RuntimeException("Wrong paramter format for By:"+ param);
	}
	
	public void switchToFrame(final UIElement element) {
		By by =getBy(element.locator);
		driver.switchTo().frame(driver.findElement(by));
	}
	
    public void click(final UIElement element){
    	By by = getBy(element.locator);
        this.driver.findElement(by).click();
    }
	public void elementTextClean(final UIElement element ) {
		By by = getBy(element.locator);
		this.driver.findElement(by).clear();
	}
	
	public boolean IsElementFound(final UIElement element) {
		By by = getBy(element.locator);
		if(this.driver.findElements(by).size() > 0) {
			try {
				return this.driver.findElement(by).isDisplayed();
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	public boolean waitForElement(final UIElement element,final int timeout) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, timeout);
		boolean result = false;
		Boolean jsEnd =false;
		int jsCounter = 0 ;
		while (calendar.getTimeInMillis() > new Date().getTime()) {
			try {
				while(jsEnd == false && jsCounter < 10 ) {
					jsEnd = (Boolean) ((JavascriptExecutor) this.driver).executeScript("return jQuery.active == 0");
					jsCounter++;
				}
			
			} catch (Exception e) {	
				
			} finally {
				
				if(IsElementFound(element)) {
					result =true;
					break;
				}
				
			}
			
			try {
				Thread.sleep(1000);
			
			} catch (InterruptedException ex){
				
			}
		}
				
		return result;
	}
	
	public void elementSendText(final UIElement element,final String text) {
        By by = getBy(element.locator);
        this.driver.findElement(by).sendKeys(text);
    }
	
	public void waitAndClick(final UIElement element,final int timeout){
		waitForElement(element,timeout);
		click(element);
	
    }
	public String elementGetText(final UIElement element) {
		By by = getBy(element.locator);
		return this.driver.findElement(by).getText();
	}
	public String elementGetProperty(final UIElement element) {
		By by=getBy(element.locator);
		return this.driver.getCurrentUrl();
	}  
	
	//@Override
	public byte[] getScreenShot() {
		return ((TakesScreenshot) this.driver)
				.getScreenshotAs(OutputType.BYTES);
	}

	//@Override
	public void destoryDriver() throws ExecutionException {
		try {
			if (destoryAtTheEnd && this.driver != null) {
				this.driver.close();
				this.driver.quit();
				this.driver = null;
			}
		} catch (Exception e) {
			throw new ExecutionException("Drive doesn't closed as expect!!");
		}
	}
}
