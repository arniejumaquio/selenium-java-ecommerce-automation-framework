package io.github.arniejumaquio.tests.login;

import io.github.arniejumaquio.framework.pages.LoginPage;
import io.github.arniejumaquio.framework.pages.ProductPage;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.LoginDataProvider;
import io.github.arniejumaquio.tests.models.LoginTestData;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {


    @Test(groups = {"smoke"}, dataProviderClass = LoginDataProvider.class,dataProvider = "getValidLoginTestData")
    public void validateValidLogin(LoginTestData loginTestData){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertEquals(productPage.getCurrentUrl(),loginTestData.getExpectedUrl());
    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getSuccessfulLoginAfterFailedLogin")
    public void validateSuccessfulLoginAfterFailedLogin(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.login(loginTestData.getUsername(),"WrongP@ssword@1");
        Assert.assertEquals(loginPage.getLoginErrorMessage(),loginTestData.getExpectedMessage());

        ProductPage productPage = loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertEquals(productPage.getCurrentUrl(),loginTestData.getExpectedUrl());

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getInvalidLoginTestData")
    public void validateInvalidLogin(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertEquals(loginPage.getLoginErrorMessage(),loginTestData.getExpectedMessage());
        Assert.assertEquals(loginPage.getCurrentUrl(),loginTestData.getExpectedUrl());

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getEdgeLoginTestData")
    public void validateEdgeLogin(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertEquals(loginPage.getLoginErrorMessage(),loginTestData.getExpectedMessage());
        Assert.assertEquals(loginPage.getCurrentUrl(),loginTestData.getExpectedUrl());

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getRepeatedInvalidLoginDoNotDuplicateTheErrorBannerTestData")
    public void validateRepeatedInvalidloginDoesNotDulicateError(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();

        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword(),5);

        Assert.assertTrue(loginPage.isLoginErrorMessageNotDuplicated());
        Assert.assertEquals(loginPage.getLoginErrorMessage(),loginTestData.getExpectedMessage());
        Assert.assertEquals(loginPage.getCurrentUrl(),loginTestData.getExpectedUrl());

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getRepeatedClickLLoginButtonMultipleTimesTriggersOnlyOneLoginSession")
    public void validateRepeatedClickLLoginButtonMultipleTimesTriggersOnlyOneLoginSession(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();

        ProductPage productPage = loginPage.login(loginTestData.getUsername(),loginTestData.getPassword(),5);

        Assert.assertFalse(loginPage.isMultipleTabDisplayed());
        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertEquals(productPage.getCurrentUrl(),loginTestData.getExpectedUrl());
    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getLoginSessionRemainsActiveAfterProductPageRefresh")
    public void validateLoginSessionRemainsActiveAfterProductPageRefresh(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertEquals(productPage.getCurrentUrl(),loginTestData.getExpectedUrl());

        loginPage.pageRefresh();

        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertEquals(productPage.getCurrentUrl(),loginTestData.getExpectedUrl());


    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getLoginPageIsAccessibleUsingASecureURL")
    public void validateLoginPageIsAccessibleUsingASecureURL(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("https"));


    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getSecurityLoginTestData")
    public void validateSecurityLogin(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertEquals(loginPage.getLoginErrorMessage(),loginTestData.getExpectedMessage());
        Assert.assertEquals(loginPage.getCurrentUrl(),loginTestData.getExpectedUrl());

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getLoginInputNotExposeInTheUrlTestData")
    public void validateLoginInputNotExposeInTheUrl(LoginTestData loginTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertFalse(loginPage.getCurrentUrl().contains(loginTestData.getUsername()));
        Assert.assertFalse(loginPage.getCurrentUrl().contains(loginTestData.getPassword()));

    }

    @Test(dataProviderClass = LoginDataProvider.class,dataProvider = "getPasswordAreNotExposedInTheLoginErrorMessage")
    public void validatePasswordAreNotExposedInTheLoginErrorMessage(LoginTestData loginTestData){
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        loginPage.login(loginTestData.getUsername(),loginTestData.getPassword());

        Assert.assertFalse(loginPage.getLoginErrorMessage().contains(loginTestData.getPassword()));
    }


}
