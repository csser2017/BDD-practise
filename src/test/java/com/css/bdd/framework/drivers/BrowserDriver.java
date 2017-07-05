package com.css.bdd.framework.drivers;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.css.bdd.framework.ExecutionException;

@Component
public class BrowserDriver implements IDriver {

	@Value("${ie.bin.path}")
	private String ieBinPath;

	private WebDriver driver;

	private boolean destoryAtTheEnd = true;

	private static final Logger logger = LoggerFactory
			.getLogger(BrowserDriver.class.getName());

	public void createNewDriverInstance() throws ExecutionException {
		String browserType = detectBroserType();
		switch (browserType) {
		case "ie":
			createNewIeDriverInstance();
			break;
		case "chrome":
			createNewChromeDriverInstance();
			break;
		default:
			throw new ExecutionException(String.format(
					"%s is an unsupport browser type!!", browserType));
		}
	}

	private void createNewIeDriverInstance() {
		BrowserDriver.logger
				.debug("---------- Startup WebDriver on IE ----------");
		System.setProperty("webdriver.ie.driver",
				"D:\\Development\\drivers\\IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities
				.internetExplorer();
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("ignoreZoomSetting", true);
		this.driver = new InternetExplorerDriver();
		this.driver.manage().window().maximize();
	}

	private void createNewChromeDriverInstance() {
		BrowserDriver.logger
				.debug("---------- Startup WebDriver on Chrome ----------");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		options.addArguments("--start-maximized");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		this.driver = new ChromeDriver(capabilities);
		this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

	@Override
	public byte[] getScreenShot() {
		return ((TakesScreenshot) this.driver)
				.getScreenshotAs(OutputType.BYTES);
	}

	@Override
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
