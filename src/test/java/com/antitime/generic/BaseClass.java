package com.antitime.generic;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.antitime.pom.EnterTimeTrackPage;
import com.antitime.pom.LoginPage;

public class BaseClass {
	static {
		
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		}
	public static WebDriver driver;
	@BeforeTest
	public void openBrowser() {
		Reporter.log("open Browser",true);
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		WebDriverCommonLib w=new WebDriverCommonLib();
		w.waitForPageLoad(driver, 10);
	}
	@AfterTest
	public void closeBrowser() {
		Reporter.log("close Browser",true);	
		driver.close();
	}
	@BeforeMethod
	public void login() throws IOException {
		Reporter.log("login",true);	
		FileLib f=new FileLib();
		String url = f.getPropertyData("url");
		String un = f.getPropertyData("username");
		String pw = f.getPropertyData("password");
		driver.get(url);
		LoginPage l=new LoginPage(driver);
		l.setLogin(un, pw);
	}
	@AfterMethod
	public void logout() {
		Reporter.log("logout",true);
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.setLogout();
	}

}
