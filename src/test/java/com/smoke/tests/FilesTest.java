package com.smoke.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.smoke.Web.EditPage;
import com.smoke.Web.FilePage;
import com.smoke.Web.WelcomePage;

import controllers.BaseActions;
import controllers.DriverFactory;
import listeners.CustomListener;
import utils.ConfigReader;
import utils.ExcelTestDataReader;
import utils.ExtentReportsUtil;
@Listeners(CustomListener.class)
public class FilesTest extends DriverFactory
{	

	@Test(dataProvider="getExcelTestData",description ="Verify add and delete in TextEditor ", testName="Login", suiteName="User1")
	public void verifyTextEditor(HashMap<String, String> data)
	{	
		ExtentReportsUtil.getTest().assignAuthor("User2").assignCategory("Files").assignDevice("Windows 10");
		
		WelcomePage welcome =new WelcomePage(getWinDriver());
		welcome.clickOnEdit();
		EditPage edit =new EditPage(getWinDriver());
		
		edit.enterDateTime();
		//Assert.assertEquals(true, edit.enterDateTime());
		FilePage file =new FilePage(getWinDriver());
		file.deleteAllData();
		Assert.fail();
	}
	
	@DataProvider
	public Iterator<Object[]> getExcelTestData() 
	{
		//String sheetname = this.getClass().getSimpleName();
		String sheetname = "FileData";
		ExcelTestDataReader excelReader = new ExcelTestDataReader();
		LinkedList<Object[]> dataBeans = excelReader.getRowDataMap(PWD+ConfigReader.getValue("TestData"),sheetname);
		return dataBeans.iterator();
	}
}
