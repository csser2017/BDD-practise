package com.css.bdd.flow;

import com.css.bdd.pages.LandingPage_163;
import com.css.bdd.pages.LogonPage_163;
import org.hamcrest.core.IsEqual.
import org.hamcrest.MatcherAssert.*;
import org.hamcrest.Matcher;

public class LogonFlow_163 {
	
	LogonPage_163 logonPage = new LogonPage_163();
	LandingPage_163 landingPage = new LandingPage_163();
	
	public void OpenMailBox() {
		logonPage.openLognPage();
	}
	
	public void LogonMailBox(final String name,final String pwd) {
		logonPage.setUserName(name);
		logonPage.setPassword(pwd);
		logonPage.logonBtnClick();
	}
	
	public void verifyLogonInfo(String expInfo) {
		assertThat(expInfo,is(equalTo(landingPage.getLogonInfo())));
	}

}
