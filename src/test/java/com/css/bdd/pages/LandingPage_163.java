package com.css.bdd.pages;

import com.css.bdd.framework.drivers.BrowserDriver;
import com.css.bdd.framework.drivers.IDriver;
import com.css.bdd.framework.page.UIElement;
import com.css.bdd.steps.Hooks;

public class LandingPage_163 {
IDriver driver =Hooks.driver;
	
	UIElement logonInfo = new UIElement("id=useraddr");
	
	public String getLogonInfo() {
		driver.waitForElement(logonInfo, 30);
		return driver.elementGetText(logonInfo);
	}

}
