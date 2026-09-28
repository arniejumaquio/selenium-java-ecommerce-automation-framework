package io.github.arniejumaquio.tests.checkout;

import io.github.arniejumaquio.framework.pages.*;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.CheckoutDataProvider;
import io.github.arniejumaquio.tests.models.CheckoutTestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckoutTest extends BaseTest {

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getContinueButtonNavigateTheUserToTheCheckoutOverviewPageTestData")
    public void validateContinueButtonNavigateTheUserToTheCheckoutOverviewPage(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();

        Assert.assertEquals(checkoutOverviewPage.getCurrentUrl(),checkoutTestData.getExpectedUrl());
        Assert.assertTrue(checkoutOverviewPage.isCheckoutOverviewLabelDisplayed());
    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getCancelButtonNavigateTheUserToTheCartPageTestData")
    public void validateCancelButtonNavigateTheUserToTheCartPage(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();
        cartPage = checkoutInformationPage.clickCancelButton();

        Assert.assertEquals(cartPage.getCurrentUrl(),checkoutTestData.getExpectedUrl());
        Assert.assertTrue(cartPage.isYourCartLabelDisplayed());

    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getSelectedCartProductsMatchCheckoutOverviewTestData")
    public void validateSelectedCartProductsMatchCheckoutOverview(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        List<String> expectedCheckoutItemNames = new ArrayList<>(cartPage.getCartItemNames());
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(), checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        List<String> actualCheckoutItemNames = new ArrayList<>(checkoutOverviewPage.getCheckoutItemNames());
        Collections.sort(actualCheckoutItemNames);
        Collections.sort(expectedCheckoutItemNames);


        Assert.assertEquals(actualCheckoutItemNames,expectedCheckoutItemNames);


    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getEachCheckoutOverviewItemDisplaysCorrectProductInformationTestData")
    public void validateEachCheckoutOverviewItemDisplaysCorrectProductInformation(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        List<String> actualCheckoutItemNames = new ArrayList<>(checkoutOverviewPage.getCheckoutItemNames());
        List<String> actualCheckoutItemDescriptions = new ArrayList<>(checkoutOverviewPage.getCheckoutItemDescriptions());
        List<String> actualCheckoutItemPrices = new ArrayList<>(checkoutOverviewPage.getCheckoutItemPrices());
        List<String> expectedCheckoutItemNames = new ArrayList<>(checkoutTestData.getExpectedProductNames());
        List<String> expectedCheckoutItemDescriptions = new ArrayList<>(checkoutTestData.getExpectedProductDescriptions());
        List<String> expectedCheckoutItemPrices = new ArrayList<>(checkoutTestData.getExpectedPrices());
        Collections.sort(actualCheckoutItemNames);
        Collections.sort(actualCheckoutItemDescriptions);
        Collections.sort(actualCheckoutItemPrices);
        Collections.sort(expectedCheckoutItemNames);
        Collections.sort(expectedCheckoutItemDescriptions);
        Collections.sort(expectedCheckoutItemPrices);

        Assert.assertEquals(actualCheckoutItemNames,expectedCheckoutItemNames);
        Assert.assertEquals(actualCheckoutItemDescriptions,expectedCheckoutItemDescriptions);
        Assert.assertEquals(actualCheckoutItemPrices,expectedCheckoutItemPrices);

    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getCheckoutOverviewDisplaysTheCorrectPaymentAndShippingInformationTestData")
    public void validateCheckoutOverviewDisplaysTheCorrectPaymentAndShippingInformation(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();

        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),checkoutTestData.getExpectedPaymentInformation());
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(),checkoutTestData.getExpectedShippingInformation());

    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getCheckoutOverviewPriceSummaryIsCalculatedCorrectlyTestData")
    public void validateCheckoutOverviewPriceSummaryIsCalculatedCorrectly(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        BigDecimal actualItemTotal = checkoutOverviewPage.getItemTotal();
        BigDecimal expectedItemTotal = BigDecimal.ZERO;
        List<String> expectedPricesText = checkoutTestData.getExpectedPrices();
        for(String expectedPriceText:expectedPricesText){
            BigDecimal price = new BigDecimal(checkoutOverviewPage.removeDollarSign(expectedPriceText));
            expectedItemTotal= expectedItemTotal.add(price);
        }
        BigDecimal actualTotal = checkoutOverviewPage.getTotal();
        BigDecimal expectedTotal = expectedItemTotal.add( checkoutOverviewPage.getTax());


        Assert.assertEquals(actualItemTotal,expectedItemTotal);
        Assert.assertEquals(actualTotal,expectedTotal);

    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getCancelButtonOnCheckoutOverviewNavigateTheUserToTheProductPageTestData")
    public void validateCancelButtonOnCheckoutOverviewNavigateTheUserToTheProductPage(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        productPage = checkoutOverviewPage.clickCancelButton();

        Assert.assertEquals(productPage.getCurrentUrl(),checkoutTestData.getExpectedUrl());
        Assert.assertTrue(productPage.isProductsLabelDisplayed());
    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getFinishButtonOnCheckoutOverviewNavigateTheUserToTheCheckoutCompletePageTestData")
    public void validateFinishButtonOnCheckoutOverviewNavigateTheUserToTheCheckoutCompletePage(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();

        Assert.assertEquals(checkoutCompletePage.getCurrentUrl(),checkoutTestData.getExpectedUrl());
        Assert.assertTrue(checkoutCompletePage.isCheckoutCompleteLabelDisplayed());

    }

    @Test(groups = {"smoke"}, dataProviderClass = CheckoutDataProvider.class,dataProvider = "getCheckoutCompleteDisplaysCorrectOrderConfirmationTestData")
    public void validateCheckoutCompleteDisplaysCorrectOrderConfirmation(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();

        Assert.assertEquals(checkoutCompletePage.getThankYouForYourOrderMsg(),checkoutTestData.getExpectedMessages().get(0));
        Assert.assertEquals(checkoutCompletePage.getYourOrderDispatchMsg(),checkoutTestData.getExpectedMessages().get(1));
        Assert.assertTrue(checkoutCompletePage.isBackHomeButtonDisplayed());


    }

    @Test(dataProviderClass = CheckoutDataProvider.class,dataProvider = "getBackHomeButtonOnCheckoutCompleteNavigateTheUserToTheProductPageTestData")
    public void validateBackHomeButtonOnCheckoutCompleteNavigateTheUserToTheProductPage(CheckoutTestData checkoutTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(checkoutTestData.getUsername(),checkoutTestData.getPassword());
        productPage.addToCart(checkoutTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage =  cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(checkoutTestData.getFirstName(),checkoutTestData.getLastName(),checkoutTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        productPage = checkoutCompletePage.clickBackHomeButton();

        Assert.assertEquals(productPage.getCurrentUrl(),checkoutTestData.getExpectedUrl());
        Assert.assertTrue(productPage.isProductsLabelDisplayed());

    }


}
