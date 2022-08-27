package com.smoke.Web;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import controllers.BaseActions;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

public class FilePage {

	private WindowsDriver<WindowsElement> driver;
	BaseActions actions =new BaseActions();
	public FilePage (WindowsDriver<WindowsElement> driver){
		this.driver=driver;
		actions.win_driver = driver;	
	}
	private static By btn_DontSave = By.name("Don't Save");
	private static By tab_Edit = By.name("Edit");
	private static By tab_view = By.name("View");
	
	public String deleteAllData() {
		String data ="";
		actions.inputText(tab_Edit, Keys.CONTROL + "a" + Keys.CONTROL, "Selected all the data");
		actions.delay(2000);
		//String data= driver.findElement(tab_Edit).getText();
		//actions.inputText(tab_Edit, Keys.DELETE, "Deleted the data");
		actions.hitDelete();
		actions.delay(2000);
		
		//String empty= driver.findElement(tab_Edit).getText();
		return data;
	}
		
}
