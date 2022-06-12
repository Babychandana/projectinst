package com.antitime.testscript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.antitime.generic.BaseClass;
import com.antitime.generic.FileLib;
import com.antitime.pom.EnterTimeTrackPage;
import com.antitime.pom.TaskListPage;

	@Listeners(com.antitime.generic.ListenerImplementation.class)
	public class CustomerModule extends BaseClass{

		@Test
		public void testCreateCustomer() throws InterruptedException, EncryptedDocumentException, IOException, InvalidFormatException
		{
			Reporter.log("CreateCustomer",true);
			FileLib f=new FileLib();
			String custName = f.getExcelData("CreateCustomer", 1, 2);
			String custDesc = f.getExcelData("CreateCustomer", 1, 3);
			EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
			e.clickTaskTab();
			TaskListPage t=new TaskListPage(driver);
			t.getAddNewBtn().click();
			t.getNewCustOption().click();
			t.getEnterCustNameTbx().sendKeys(custName);
			t.getEnterCustDescTbx().sendKeys(custDesc);
			t.getSelectCustDD().click();
			t.getOurCompany().click();
			t.getCreateCustBtn().click();
		WebDriverWait wait=new WebDriverWait(driver, 10);
	wait.until(ExpectedConditions.textToBePresentInElement(t.getActualCustCreated(), custName));
			String actualCustText = t.getActualCustCreated().getText();
			Assert.assertEquals(actualCustText, custName);
			Reporter.log("Customer successfully created ="+custName,true);
			f.setExcelData("CreateCustomer", 1, 4, "pass");
		}
}
