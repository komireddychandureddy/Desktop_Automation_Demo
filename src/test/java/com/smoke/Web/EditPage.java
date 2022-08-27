package com.smoke.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import controllers.BaseActions;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class EditPage {

	private WindowsDriver<WindowsElement> driver;
	BaseActions actions =new BaseActions();
	public EditPage (WindowsDriver<WindowsElement> driver){
		this.driver=driver;
		actions.win_driver = driver;	
	}
	
	//private static By tab_Timedate = By.xpath("//MenuItem[starts-with(@name, 'Time/Date')]");
	private static By tab_Timedate = By.name("Time/Date");
	private static By tab_Copy = By.name("Copy");
	private static By tab_Paste= By.name("Paste");
	private static By tab_Select = By.xpath("//MenuItem[starts-with(@name, 'Select All')]");
	//private static By tab_Copy = By.xpath("//MenuItem[starts-with(@name, 'Copy')]");
	//private static By tab_Paste = By.xpath("//MenuItem[starts-with(@name, 'Paste')]");
	private static By tab_timedate = By.xpath("//MenuItem[starts-with(@name, 'Paste')]");
	//private static By tab_timedate = By.xpath("//MenuItem[starts-with(@Name, 'Select All')]");
	
	
	
	public boolean enterDateTime() {
		actions.click(tab_Timedate, "Click on Timedate");		
		
		return true;
	}
	
		

}
