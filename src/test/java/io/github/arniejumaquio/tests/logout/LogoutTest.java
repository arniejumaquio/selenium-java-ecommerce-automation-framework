package io.github.arniejumaquio.tests.logout;

import io.github.arniejumaquio.framework.pages.LoginPage;
import io.github.arniejumaquio.framework.pages.ProductPage;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.LogoutDataProvider;
import io.github.arniejumaquio.tests.models.LogoutTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {


    @Test(groups = {"smoke"}, dataProviderClass = LogoutDataProvider.class,dataProvider = "getSuccessfulLogoutWithValidCredentialsTestData")
    public void validateSuccessfulLogoutWithValidCredentials(LogoutTestData logoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(logoutTestData.getUsername(), logoutTestData.getPassword());
        loginPage = productPage.logout();

        Assert.assertEquals(loginPage.getCurrentUrl(),logoutTestData.getExpectedUrl());

    }


}
