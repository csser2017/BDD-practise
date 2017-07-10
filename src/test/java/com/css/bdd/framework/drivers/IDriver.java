package com.css.bdd.framework.drivers;

import com.css.bdd.framework.ExecutionException;
import com.css.bdd.framework.page.*;

public interface IDriver {
	
	void createNewDriverInstance() throws ExecutionException;

	void destoryDriver() throws ExecutionException;

	byte[] getScreenShot();
	
	void click(final UIElement element);
	
	boolean IsElementFound(final UIElement element);
	
	boolean waitForElement(final UIElement element,final int timeout);
	
	void waitAndClick(final UIElement element,final int timeout);
	
	void elementSendText(final UIElement element,final String text);
	
	String elementGetText(final UIElement element);
	
	String elementGetProperty(final UIElement element);
	
	void get(final String url);
	
	 void switchToFrame(final UIElement element);
	 void elementTextClean(final UIElement element);
	
}
