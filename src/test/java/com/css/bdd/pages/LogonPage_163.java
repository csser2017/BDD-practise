package com.css.bdd.pages;

import java.sql.Driver;

import org.openqa.selenium.By;

import com.css.bdd.framework.drivers.BrowserDriver;
import com.css.bdd.framework.drivers.IDriver;
import com.css.bdd.framework.page.UIElement;
import com.css.bdd.steps.Hooks;

public class LogonPage_163 {
	
	IDriver driver =Hooks.driver;
	
	
	UIElement logonFrame = new UIElement("id=login_frame");
	UIElement username = new UIElement("id=u");
	UIElement password = new UIElement("id=p");
	UIElement logonBtn = new UIElement("id=login_button");
	
	public void openLognPage() {
		this.driver.get("https://mail.qq.com");
		switchFrame();
		this.driver.elementTextClean(username);
	}
	public void switchFrame() {
		this.driver.switchToFrame(logonFrame);
	}
	
	public void setUserName(final String name) {
		this.driver.elementSendText(username, name);
	}
	public void setPassword(final String pwd) {
		driver.elementSendText(password, pwd);
	}
	public void logonBtnClick() {
		driver.waitAndClick(logonBtn, 20);
	}
}
