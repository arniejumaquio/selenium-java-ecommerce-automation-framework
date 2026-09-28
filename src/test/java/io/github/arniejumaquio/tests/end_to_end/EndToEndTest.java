package io.github.arniejumaquio.tests.end_to_end;

import io.github.arniejumaquio.framework.pages.*;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.EndToEndDataProvider;
import io.github.arniejumaquio.tests.models.EndToEndTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EndToEndTest extends BaseTest {

    @Test(groups = {"smoke"}, dataProviderClass = EndToEndDataProvider.class,dataProvider = "getUserCanCompleteASingleProductPurchaseTestData")
    public void validateUserCanCompleteASingleAndMultipleProductPurchase(EndToEndTestData endToEndTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(endToEndTestData.getUsername(),endToEndTestData.getPassword());
        productPage.addToCart(endToEndTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        List<String> actualCartItemNames = new ArrayList<>(cartPage.getCartItemNames());
        List<String> actualCartItemDescriptions = new ArrayList<>(cartPage.getCartItemDescriptions());
        List<String> actualCartItemPrices = new ArrayList<>( cartPage.getCartItemPrices());
        List<String> expectedCartItemsNames = new ArrayList<>(endToEndTestData.getExpectedProductNames());
        List<String> expectedCartItemDescriptions = new ArrayList<>(endToEndTestData.getExpectedProductDescriptions());
        List<String> expectedCartItemPrices = new ArrayList<>(endToEndTestData.getExpectedPrices());
        Collections.sort(actualCartItemNames);
        Collections.sort(actualCartItemDescriptions);
        Collections.sort(actualCartItemPrices);
        Collections.sort(expectedCartItemsNames);
        Collections.sort(expectedCartItemDescriptions);
        Collections.sort(expectedCartItemPrices);
        Assert.assertEquals(actualCartItemNames,expectedCartItemsNames);
        Assert.assertEquals(actualCartItemDescriptions,expectedCartItemDescriptions);
        Assert.assertEquals(actualCartItemPrices,expectedCartItemPrices);

        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();
        checkoutInformationPage.fillUpCheckoutInfo(endToEndTestData.getFirstName(),endToEndTestData.getLastName(),endToEndTestData.getZipPostalCode());
        CheckoutOverviewPage checkoutOverviewPage = checkoutInformationPage.clickContinueButton();
        List<String> actualCheckoutItemNames = new ArrayList<>(checkoutOverviewPage.getCheckoutItemNames());
        List<String> actualCheckoutItemDescriptions  = new ArrayList<>(checkoutOverviewPage.getCheckoutItemDescriptions());
        List<String> actualCheckoutItemPrices = new ArrayList<>(checkoutOverviewPage.getCheckoutItemPrices());
        List<String> expectedCheckoutItemNames = new ArrayList<>(endToEndTestData.getExpectedProductNames());
        List<String> expectedCheckoutItemDescriptions = new ArrayList<>(endToEndTestData.getExpectedProductDescriptions());
        List<String> expectedCheckoutItemPrices = new ArrayList<>(endToEndTestData.getExpectedPrices());
        Collections.sort(actualCheckoutItemNames);
        Collections.sort(actualCheckoutItemDescriptions);
        Collections.sort(actualCheckoutItemPrices);
        Collections.sort(expectedCheckoutItemNames);
        Collections.sort(expectedCheckoutItemDescriptions);
        Collections.sort(expectedCheckoutItemPrices);
        Assert.assertEquals(actualCheckoutItemNames,expectedCheckoutItemNames);
        Assert.assertEquals(actualCheckoutItemDescriptions,expectedCheckoutItemDescriptions);
        Assert.assertEquals(actualCheckoutItemPrices,expectedCheckoutItemPrices);
        Assert.assertEquals(checkoutOverviewPage.getPaymentInformation(),endToEndTestData.getExpectedPaymentInformation());
        Assert.assertEquals(checkoutOverviewPage.getShippingInformation(),endToEndTestData.getExpectedShippingInformation());
        BigDecimal actualItemTotal = checkoutOverviewPage.getItemTotal();
        BigDecimal expectedItemTotal = BigDecimal.ZERO;
        List<String> expectedPricesText = endToEndTestData.getExpectedPrices();
        for(String expectedPriceText:expectedPricesText){
            BigDecimal price = new BigDecimal(checkoutOverviewPage.removeDollarSign(expectedPriceText));
            expectedItemTotal= expectedItemTotal.add(price);
        }
        BigDecimal actualTotal = checkoutOverviewPage.getTotal();
        BigDecimal expectedTotal = expectedItemTotal.add( checkoutOverviewPage.getTax());
        Assert.assertEquals(actualItemTotal,expectedItemTotal);
        Assert.assertEquals(actualTotal,expectedTotal);

        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickFinishButton();
        Assert.assertEquals(checkoutCompletePage.getThankYouForYourOrderMsg(),endToEndTestData.getExpectedMessages().get(0));
        Assert.assertEquals(checkoutCompletePage.getYourOrderDispatchMsg(),endToEndTestData.getExpectedMessages().get(1));
        checkoutCompletePage.clickBackHomeButton();


    }

}
