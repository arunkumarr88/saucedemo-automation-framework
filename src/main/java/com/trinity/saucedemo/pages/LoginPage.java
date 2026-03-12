package com.trinity.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.trinity.saucedemo.base.ProjectSpecificFunctions;

public class LoginPage extends ProjectSpecificFunctions {

    public LoginPage() {
		super("//div[@class='login_logo']","Swag Labs");
	}


	@FindBy(id="user-name")
    WebElement username;

    @FindBy(id="password")
    WebElement password;

    @FindBy(id="login-button")
    WebElement loginButton;

   
    public HomePage login(String user,String pass){
    	type(username, user,"UserName");
    	type(password, pass,"Password");
    	click(loginButton,"Login");
    	return new HomePage();
    }
}
