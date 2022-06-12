package com.antitime.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

	public class WebDriverCommonLib {
		public void waitForPageLoad(WebDriver driver,long time ){
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		}
}
