package io.github.arniejumaquio.tests.cart;

import io.github.arniejumaquio.framework.pages.*;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.CartDataProvider;
import io.github.arniejumaquio.tests.models.CartTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke"}, dataProviderClass = CartDataProvider.class,dataProvider = "getProductCanBeAddedToCartTestData")
    public void validateProductCanBeAddedToCart(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartItemNames(), cartTestData.getExpectedProductNames());

    }


    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getProductCanBeRemovedFromCartTestData")
    public void validateProductCanBeRemovedFromCart(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(), cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.removeItemToCart(cartTestData.getExpectedProductNames());

        Assert.assertTrue(cartPage.isCartEmpty());


    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getEachCartItemDisplaysCorrectProductInformationTestData")
    public void validateEachCartItemDisplaysCorrectProductInformation(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(), cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        Assert.assertEquals(cartPage.getCartItemNames(), cartTestData.getExpectedProductNames());
        Assert.assertEquals(cartPage.getCartItemDescriptions(), cartTestData.getExpectedProductDescriptions());
        Assert.assertEquals(cartPage.getCartItemPrices(), cartTestData.getExpectedPrices());


    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartBadgeIncrementsWhenAProductIsAddedTestData")
    public void validateCartBadgeIncrementsWhenAProductIsAdded(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();


        Assert.assertEquals( cartPage.getCartCount(),cartTestData.getExpectedProductNames().size());

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartBadgeDecrementsWhenAProductIsRemovedTestData")
    public void validateCartBadgeDecrementsWhenAProductIsRemoved(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.removeItemToCart(cartTestData.getExpectedProductNames().get(0));

        if(cartTestData.getExpectedProductNames().size() ==1){
            Assert.assertTrue(cartPage.isCartEmpty());
        }else {
            Assert.assertEquals(cartPage.getCartCount(), cartTestData.getExpectedProductNames().size() - 1);
        }
    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getACartProductNameOpensTheCorrespondingProductDetailsPageTestData")
    public void validateACartProductNameOpensTheCorrespondingProductDetailsPage(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

       ProductDetailsPage productDetailsPage = cartPage.clickCartName(cartTestData.getExpectedProductNames().get(0));
       Assert.assertEquals(productDetailsPage.getProductName(),cartTestData.getExpectedProductNames().get(0));

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getContinueShoppingReturnsTheUserToTheProductsPageTestData")
    public void validateContinueShoppingReturnsTheUserToTheProductsPage(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        productPage = cartPage.clickContinueShoppingButton();

        Assert.assertEquals(productPage.getCurrentUrl(),cartTestData.getExpectedUrl());
        Assert.assertTrue(productPage.isProductsLabelDisplayed());
        Assert.assertTrue(productPage.isProductsDisplayed());

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCheckoutButtonNavigateTheUserToTheCheckoutPageTestData")
    public void validateCheckoutButtonNavigateTheUserToTheCheckoutPage(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();

        Assert.assertEquals(productPage.getCurrentUrl(),cartTestData.getExpectedUrl());
        Assert.assertTrue(checkoutInformationPage.isCheckoutYourInformationLabelDisplayed());

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getRemovingOneProductDoesNotRemoveOtherCartProductsTestData")
    public void validateRemovingOneProductDoesNotRemoveOtherCartProducts(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(), cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.removeItemToCart(cartTestData.getProductToRemove());

        List<String> expectedRemainingCartItems = new ArrayList<>(cartTestData.getExpectedProductNames());
        expectedRemainingCartItems.remove(cartTestData.getProductToRemove());
        List<String> actualRemaningCartItems = new ArrayList<>(cartPage.getCartItemNames());
        Collections.sort(expectedRemainingCartItems);
        Collections.sort(actualRemaningCartItems);

        Assert.assertTrue(cartPage.isCartItemNameNotDisplayed(cartTestData.getProductToRemove()));
        Assert.assertEquals(actualRemaningCartItems,expectedRemainingCartItems);



    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartDisplaysTheEmptyStateWhenNoProductsAreAddedToCartTestData")
    public void validateCartDisplaysTheEmptyStateWhenNoProductsAreAddedToCart(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        CartPage cartPage = productPage.clickCartIcon();

        Assert.assertTrue(cartPage.isCartEmpty());


    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartStatePersistAfterRepeatedRefreshTestData")
    public void validateCartStatePersistAfterRepeatedRefresh(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        cartPage.multiplePageRefresh(5);


        Assert.assertEquals(cartPage.getCartItemNames(), cartTestData.getExpectedProductNames());


    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartStatePersistAfterRepeatedBrowserBackAndForwardNavigationTestData")
    public void validateCartStatePersistAfterRepeatedBrowserBackAndForwardNavigation(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        for(int i =0; i < 5; i++) {
            cartPage.clickBackBrowserButton();
            cartPage.clickForwardBrowserButton();
        }

        Assert.assertEquals(cartPage.getCartItemNames(), cartTestData.getExpectedProductNames());

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartStatePersistAfterRepeatedContinueShoppingAndCheckoutNavigationTestData")
    public void validateCartStatePersistAfterRepeatedContinueShoppingAndCheckoutNavigation(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();

        for(int i =0; i < 5; i++) {
            productPage = cartPage.clickContinueShoppingButton();
            cartPage = productPage.clickCartIcon();
        }

        for(int i =0; i < 5; i++) {
            CheckoutInformationPage checkoutInformationPage = cartPage.clickCheckoutButton();
            cartPage = checkoutInformationPage.clickCancelButton();
        }

        Assert.assertEquals(cartPage.getCartItemNames(), cartTestData.getExpectedProductNames());

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartStatePersistAfterRepeatedClickingOfRemoveButtonTestData")
    public void validateCartStatePersistAfterRepeatedClickingOfRemoveButton(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(), cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.removeItemToCart(cartTestData.getProductToRemove(),3);

        List<String> expectedRemainingCartItems = new ArrayList<>(cartTestData.getExpectedProductNames());
        expectedRemainingCartItems.remove(cartTestData.getProductToRemove());
        List<String> actualRemaningCartItems = new ArrayList<>(cartPage.getCartItemNames());
        Collections.sort(expectedRemainingCartItems);
        Collections.sort(actualRemaningCartItems);

        Assert.assertTrue(cartPage.isCartItemNameNotDisplayed(cartTestData.getProductToRemove()));
        Assert.assertEquals(actualRemaningCartItems,expectedRemainingCartItems);

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartPageIsInaccessibleWhenUserIsLoggedOutTestData")
    public void validateCartPageIsInaccessibleWhenUserIsLoggedOut(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.logout();
        cartPage.navigateTo(cartTestData.getExpectedUrl());

        Assert.assertEquals(loginPage.getLoginErrorMessage(),cartTestData.getExpectedMessage());
        Assert.assertFalse(loginPage.getCurrentUrl().equalsIgnoreCase(cartTestData.getExpectedUrl()));

    }

    @Test(dataProviderClass = CartDataProvider.class,dataProvider = "getCartPageIsInaccessibleWhenUserIsLoggedOutAndClickBackBrowserButtonTestData")
    public void validateCartPageIsInaccessibleWhenUserIsLoggedOutAndClickBackBrowserButton(CartTestData cartTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage = loginPage.open();
        ProductPage productPage = loginPage.login(cartTestData.getUsername(),cartTestData.getPassword());
        productPage.addToCart(cartTestData.getExpectedProductNames());
        CartPage cartPage = productPage.clickCartIcon();
        cartPage.logout();
        cartPage.clickBackBrowserButton();

        Assert.assertEquals(loginPage.getLoginErrorMessage(),cartTestData.getExpectedMessage());
        Assert.assertFalse(loginPage.getCurrentUrl().equalsIgnoreCase(cartTestData.getExpectedUrl()));

    }



}
