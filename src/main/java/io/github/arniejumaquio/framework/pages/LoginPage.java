package io.github.arniejumaquio.framework.pages;

import io.github.arniejumaquio.framework.base.BasePage;
import io.github.arniejumaquio.framework.config.ConfigReader;
import io.github.arniejumaquio.framework.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginErrorMessage = By.cssSelector("h3[data-test='error']");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open(){
        driver.get(ConfigReader.get("base.url"));
        return this;
    }

    public void waitForLoginPageToAppear() {
        WaitUtils.waitForElementToAppear(loginButton);
    }


    public ProductPage login(String username, String password) {
        type(usernameField,username);
        type(passwordField,password);
        click(loginButton);

        return new ProductPage(driver);
    }

    public ProductPage login(String username, String password,int numOfCLick) {
        type(usernameField,username);
        type(passwordField,password);
        multipleClick(loginButton,numOfCLick);

        return new ProductPage(driver);
    }



    public String getLoginErrorMessage(){
       return getText(loginErrorMessage);
    }

    public boolean isLoginErrorMessageNotDuplicated(){
        if(getElementCount(loginErrorMessage) == 1){
            return true;
        }

        return false;
    }





}
