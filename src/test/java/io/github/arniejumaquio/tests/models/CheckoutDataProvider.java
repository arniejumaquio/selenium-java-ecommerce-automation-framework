package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.List;

public final class CheckoutDataProvider {

    private CheckoutDataProvider() {
    }


    @DataProvider(name = "getContinueButtonNavigateTheUserToTheCheckoutOverviewPageTestData")
    public static Object[][] getContinueButtonNavigateTheUserToTheCheckoutOverviewPageTestData() {

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate continue button navigate the user to the checkout overview page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getCancelButtonNavigateTheUserToTheCartPageTestData")
    public static Object[][] getCancelButtonNavigateTheUserToTheCartPageTestData(){
        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate cancel button navigate the user to the cart page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getSelectedCartProductsMatchCheckoutOverviewTestData")
    public static Object[][] getSelectedCartProductsMatchCheckoutOverviewTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate selected cart products match checkout overview")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getEachCheckoutOverviewItemDisplaysCorrectProductInformationTestData")
    public static Object[][] getEachCheckoutOverviewItemDisplaysCorrectProductInformationTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate each checkout overview item displays correct product information")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);


    }

    @DataProvider(name = "getCheckoutOverviewDisplaysTheCorrectPaymentAndShippingInformationTestData")
    public static Object[][] getCheckoutOverviewDisplaysTheCorrectPaymentAndShippingInformationTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate checkout overview displays the correct payment and shipping information")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCheckoutOverviewPriceSummaryIsCalculatedCorrectlyTestData")
    public static Object[][] getCheckoutOverviewPriceSummaryIsCalculatedCorrectlyTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate checkout overview price summary is calculated correctly")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getCancelButtonOnCheckoutOverviewNavigateTheUserToTheProductPageTestData")
    public static Object[][] getCancelButtonOnCheckoutOverviewNavigateTheUserToTheProductPageTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate cancel button on checkout overview navigate the user to the product page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getFinishButtonOnCheckoutOverviewNavigateTheUserToTheCheckoutCompletePageTestData")
    public static Object[][] getFinishButtonOnCheckoutOverviewNavigateTheUserToTheCheckoutCompletePageTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate finish button on checkout overview navigate the user to the checkout complete page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);


    }

    @DataProvider(name = "getCheckoutCompleteDisplaysCorrectOrderConfirmationTestData")
    public static Object[][] getCheckoutCompleteDisplaysCorrectOrderConfirmationTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate checkout complete  displays correct order confirmation")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getBackHomeButtonOnCheckoutCompleteNavigateTheUserToTheProductPageTestData")
    public static Object[][] getBackHomeButtonOnCheckoutCompleteNavigateTheUserToTheProductPageTestData(){

        List<CheckoutTestData> testDatas = JSONUtils.readJSONAsList("checkout-data.json", CheckoutTestData.class);
        List<CheckoutTestData> filteredTestDatas = new ArrayList<>();

        for (CheckoutTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate back home button on checkout complete navigate the user to the product page")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }


}
