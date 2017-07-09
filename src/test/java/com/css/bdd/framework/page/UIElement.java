package com.css.bdd.framework.page;

public class UIElement {
	public String name;
	public String locator;
	public String zone;
	public int    index;
	public String desc;
	
	public UIElement(final String name,final String locator,final String zone,final String index,final String desc) {
		this.name = name;
		this.locator = locator;
		this.zone = zone;
		this.index = 0 ;
		if(index !=null && !"".equals(index.trim()) ) {
			try{
				this.index = Integer.parseInt(index.trim());
			} catch(NumberFormatException ex) {
				
			}
		}
		this.desc = desc;
	}
    
	public UIElement(final String name,final String locator,final String desc) {
		this.name = name;
		this.locator = locator;
		this.desc = desc;
	}
	public UIElement(final String locator) {
		this.locator =locator;
	}
}
