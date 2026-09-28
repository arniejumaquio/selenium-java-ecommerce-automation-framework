package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public final class CartDataProvider {

    private CartDataProvider() {
    }

    @DataProvider(name = "getProductCanBeAddedToCartTestData")
    public static Object[][] getProductCanBeAddedToCartTestData() {
        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate product can be added from cart")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }


    @DataProvider(name = "getProductCanBeRemovedFromCartTestData")
    public static Object[][] getProductCanBeRemovedFromCartTestData(){
        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate  product can be removed from Cart")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getEachCartItemDisplaysCorrectProductInformationTestData")
    public static Object[][] getEachCartItemDisplaysCorrectProductInformationTestData(){
        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate each Cart item displays correct product information")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getCartBadgeIncrementsWhenAProductIsAddedTestData")
    public static Object[][] getCartBadgeIncrementsWhenAProductIsAddedTestData(){
        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate cart badge increments when a product is added")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getCartBadgeDecrementsWhenAProductIsRemovedTestData")
    public static Object[][] getCartBadgeDecrementsWhenAProductIsRemovedTestData(){
        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate cart badge decrements when a product is removed")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name="getACartProductNameOpensTheCorrespondingProductDetailsPageTestData")
    public static Object[][] getACartProductNameOpensTheCorrespondingProductDetailsPageTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate a cart product name opens the corresponding product details page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getContinueShoppingReturnsTheUserToTheProductsPageTestData")
    public static Object[][]  getContinueShoppingReturnsTheUserToTheProductsPageTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate continue shopping button navigate the user to the products page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCheckoutButtonNavigateTheUserToTheCheckoutPageTestData")
    public static Object[][] getCheckoutButtonNavigateTheUserToTheCheckoutPageTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate checkout button navigate the user to the checkout page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getRemovingOneProductDoesNotRemoveOtherCartProductsTestData")
    public static Object[][] getRemovingOneProductDoesNotRemoveOtherCartProductsTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for (CartTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate removing one product does not remove other cart products")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name="getCartDisplaysTheEmptyStateWhenNoProductsAreAddedToCartTestData")
    public static Object[][] getCartDisplaysTheEmptyStateWhenNoProductsAreAddedToCartTestData(){

       List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
       List<CartTestData> filteredTestDatas = new ArrayList<>();

       for(CartTestData testData:testDatas){
           if(testData.getTitle().equalsIgnoreCase("Validate cart displays the  empty state when no products are added to cart")){
               filteredTestDatas.add(testData);
           }
       }

       return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCartStatePersistAfterRepeatedRefreshTestData")
    public static Object[][] getCartStatePersistAfterRepeatedRefreshTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart state persist after repeated refresh")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCartStatePersistAfterRepeatedBrowserBackAndForwardNavigationTestData")
    public static Object[][] getCartStatePersistAfterRepeatedBrowserBackAndForwardNavigationTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart state persist after repeated browser back and forward navigation")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCartStatePersistAfterRepeatedContinueShoppingAndCheckoutNavigationTestData")
    public static Object[][]  getCartStatePersistAfterRepeatedContinueShoppingAndCheckoutNavigationTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart state persist after repeated continue shopping and checkout navigation")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCartStatePersistAfterRepeatedClickingOfRemoveButtonTestData")
    public static Object[][] getCartStatePersistAfterRepeatedClickingOfRemoveButtonTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart state persist after repeated clicking of remove button")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);


    }

    @DataProvider(name = "getCartPageIsInaccessibleWhenUserIsLoggedOutTestData")
    public static Object[][] getCartPageIsInaccessibleWhenUserIsLoggedOutTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart page is inaccessible when user is logged out")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCartPageIsInaccessibleWhenUserIsLoggedOutAndClickBackBrowserButtonTestData")
    public static Object[][] getCartPageIsInaccessibleWhenUserIsLoggedOutAndClickBackBrowserButtonTestData(){

        List<CartTestData> testDatas = JSONUtils.readJSONAsList("cart-data.json", CartTestData.class);
        List<CartTestData> filteredTestDatas = new ArrayList<>();

        for(CartTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate cart page is inaccessible when user is logged out and click back browser button")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }


}
