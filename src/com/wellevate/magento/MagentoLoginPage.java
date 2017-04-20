package com.wellevate.magento;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.wellevate.PractitionerAccount.DispensaryPageAccountSetting;
import com.wellevate.base.BasePage;

public class MagentoLoginPage extends BasePage {
	static WebElement element;
	static WebDriver driver;
	static List<WebElement> elementList;

	public MagentoLoginPage(WebDriver driver) {
		MagentoLoginPage.driver = driver;
	}

	// magento user name
	public WebElement magentousername() {
		return getElementfluent(By.name("login[username]"));
	}

	// magento password
	public WebElement magentopassword() {
		return getElementfluent(By.name("login[password]"));
	}

	// magento Login Button
	public WebElement magentoLoginButton() {
		return getElementfluent(By.xpath("//input[@title='Login']"));
	}
	// Magento Login
	public void signup(String username,String password){
		
		
	}
}
