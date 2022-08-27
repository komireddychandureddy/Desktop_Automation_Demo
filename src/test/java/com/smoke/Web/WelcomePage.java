package com.smoke.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import controllers.BaseActions;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class WelcomePage {

	private WindowsDriver<WindowsElement> driver;

	BaseActions actions =new BaseActions();
	public WelcomePage (WindowsDriver<WindowsElement> driver){
		this.driver=driver;
		actions.win_driver = driver;	
	}

	private static By tab_File = By.name("File");
	private static By tab_Edit = By.name("Edit");
	private static By tab_View = By.name("View");
	//private static By tab_edit = By.className("Edit");
	
	public boolean clickOnAllTabs() {
		actions.isWebElementVisible(tab_File, "Verify File tab is displayed");
		actions.click(tab_File, "Click on File tab");		
		actions.click(tab_Edit, "Click on Edit tab");		
		actions.click(tab_View, "Click on View tab");		
		return true;
	}
	
	public boolean clickOnFile() {
		actions.isWebElementVisible(tab_File, "Verify File tab is displayed");
		actions.click(tab_File, "Click on File tab");			
		return true;
	}
	public boolean clickOnEdit() {
		actions.isWebElementVisible(tab_Edit, "Verify Edit tab is displayed");
		actions.click(tab_Edit, "Click on Edit tab");		
		return true;
	}
	public boolean clickOnView() {
		actions.isWebElementVisible(tab_View, "Verify View tab is displayed");
		actions.click(tab_View, "Click on View tab");		
		return true;
	}
	public boolean enterData(String data) {
		actions.inputText(tab_Edit, data,"Enter on data on notepad");		
		return true;
	}	
	public String verifyNotepadTitle() {
		
		return driver.getTitle();
	}
}
