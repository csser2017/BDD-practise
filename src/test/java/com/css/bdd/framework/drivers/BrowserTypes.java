package com.css.bdd.framework.drivers;

public enum BrowserTypes {
	IE("ie"), FF("firefox"), CC("chrome");

	private String displayName;

	BrowserTypes(final String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return this.displayName;
	}
}
