package com.css.bdd.framework.drivers;

import com.css.bdd.framework.ExecutionException;

public interface IDriver {
	void createNewDriverInstance() throws ExecutionException;

	void destoryDriver() throws ExecutionException;

	byte[] getScreenShot();
}
