package io.github.arniejumaquio.tests.product;
import io.github.arniejumaquio.framework.pages.LoginPage;
import io.github.arniejumaquio.framework.pages.ProductDetailsPage;
import io.github.arniejumaquio.framework.pages.ProductPage;
import io.github.arniejumaquio.tests.base.BaseTest;
import io.github.arniejumaquio.tests.models.ProductDataProvider;
import io.github.arniejumaquio.tests.models.ProductTestData;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Collections;
import java.util.List;

public class ProductTest extends BaseTest {

    @Test(groups = {"smoke"}, dataProviderClass = ProductDataProvider.class,dataProvider = "getProductDisplayCorrectInformationTestData")
    public void validateProductDisplayCorrectInformation(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());

        Assert.assertTrue(productPage.isProductImageDisplayed(productTestData.getExpectedProductName()));
        Assert.assertEquals(productPage.getProductName(productTestData.getExpectedProductName()),productTestData.getExpectedProductName());
        Assert.assertEquals(productPage.getProductDescription(productTestData.getExpectedProductName()),productTestData.getExpectedProductDescription());
        Assert.assertEquals(productPage.getProductPrice(productTestData.getExpectedProductName()),productTestData.getExpectedPrice());
        Assert.assertTrue(productPage.isAddToCartButtonDisplayed(productTestData.getExpectedProductName()));
    }


    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getDefaultProductOrderIsNameAToZTestData")
    public void validateDefaultProductOrderIsNameAToZ(ProductTestData productTestData){

            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.open();
            ProductPage productPage =  loginPage.login(productTestData.getUsername(),productTestData.getPassword());

            List<String> actualProductNames =productPage.getProductNames();
            List<String> expectedProductNames = actualProductNames.stream().sorted().toList();
            Assert.assertEquals(expectedProductNames, actualProductNames);

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductsCanBeSortedByNameFromAToZTestData")
    public void validateProductsCanBeSortedByNameFromAToZ(ProductTestData productTestData){

            LoginPage loginPage = new LoginPage(getDriver());
            loginPage.open();
            ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
            productPage.selectSort(productTestData.getSort());

            List<String> actualProductNames =productPage.getProductNames();
            List<String> expectedProductNames = actualProductNames.stream().sorted().toList();
            Assert.assertEquals(actualProductNames,expectedProductNames);


    }


    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductsCanBeSortedByNameFromZToATestData")
    public void validateProductsCanBeSortedByNameFromZToA(ProductTestData productTestData) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.selectSort(productTestData.getSort());


        List<String> actualProductNames =productPage.getProductNames();
        List<String> expectedProductNames = actualProductNames.stream().sorted(Collections.reverseOrder()).toList();
        Assert.assertEquals(actualProductNames,expectedProductNames);

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductsCanBeSortedByLowToHighTestData")
    public void validateProductsCanBeSortedByPriceFromLowToHigh(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.selectSort(productTestData.getSort());

        List<Double> actualProductPrices = productPage.getProductPrices();
        List<Double> expectedProductPrices = actualProductPrices.stream().sorted().toList();
        Assert.assertEquals(actualProductPrices,expectedProductPrices);
    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductsCanBeSortedByPriceFromHighToLowTestData")
    public void validateProductsCanBeSortedByPriceFromHighToLow(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.selectSort(productTestData.getSort());

        List<Double> actualProductPrices = productPage.getProductPrices();
        List<Double> expectedProductPrices = actualProductPrices.stream().sorted(Collections.reverseOrder()).toList();
        Assert.assertEquals(actualProductPrices,expectedProductPrices);

    }

    @Test(dataProviderClass = ProductDataProvider.class, dataProvider = "getProductDetailsPageOpensWhenAProductNameIsSelectedTestData")
    public void validateProductDetailsPageOpensWhenAProductNameIsSelected(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        ProductDetailsPage productDetailsPage = productPage.clickProductName(productTestData.getExpectedProductName());

        Assert.assertEquals(productDetailsPage.getProductName(),productTestData.getExpectedProductName());

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductDetailsPageOpensWhenAProductImageIsSelectedTestData")
    public void validateProductDetailsPageOpensWhenAProductImageIsSelectedTestData(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        ProductDetailsPage productDetailsPage = productPage.clickProductImage(productTestData.getExpectedProductName());

        Assert.assertTrue(productDetailsPage.isProductImageDisplayed());


    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductInformationMatchesOnTheProductDetailsPageTestData")
    public void validateProductInformationMatchesOnTheProductDetailsPage(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());

        Assert.assertTrue(productPage.isProductImageDisplayed(productTestData.getExpectedProductName()));
        Assert.assertTrue(productPage.isAddToCartButtonDisplayed(productTestData.getExpectedProductName()));
        String expectedProductDescription = productPage.getProductDescription(productTestData.getExpectedProductName());
        String expectedProductPrice= productPage.getProductPrice(productTestData.getExpectedProductName());

        ProductDetailsPage productDetailsPage = productPage.clickProductName(productTestData.getExpectedProductName());

        Assert.assertTrue(productDetailsPage.isProductImageDisplayed());
        Assert.assertTrue(productDetailsPage.isAddToCartButtonDisplayed());
        Assert.assertEquals(productDetailsPage.getProductDescription(),expectedProductDescription);
        Assert.assertEquals(productDetailsPage.getPrice(),expectedProductPrice);

    }

    @Test(dataProviderClass = ProductDataProvider.class, dataProvider = "getProductPageOpensWhenBackToProductsIsClickTestData")
    public void validateProductPageOpensWhenBackToProductsIsClick(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        ProductDetailsPage productDetailsPage = productPage.clickProductName(productTestData.getExpectedProductName());
        productPage = productDetailsPage.clickBackToProductsButton();

        Assert.assertEquals(productPage.getCurrentUrl(),productTestData.getExpectedUrl());
        Assert.assertTrue(productPage.isProductsDisplayed());

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedRefreshTestData")
    public void validateProductListContainsNoMissingOrDuplicateProductsAfterRepeatedRefresh(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.multiplePageRefresh(5);

        Assert.assertEquals(productTestData.getProductCount(), productPage.getProductCount());

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedSortingTestData")
    public void validateProductListContainsNoMissingOrDuplicateProductsAfterRepeatedSorting(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.selectSort(productTestData.getSorts());

        Assert.assertEquals(productTestData.getProductCount(), productPage.getProductCount());

    }

    @Test(dataProviderClass = ProductDataProvider.class,dataProvider = "getProductPageIsInaccessibleWhenUserIsLoggedOut")
    public void validateProductPageIsInaccessibleWhenUserIsLoggedOut(ProductTestData productTestData){

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        ProductPage productPage = loginPage.login(productTestData.getUsername(),productTestData.getPassword());
        productPage.logout();
        productPage.navigateTo(productTestData.getExpectedUrl());

        Assert.assertEquals(loginPage.getLoginErrorMessage(),productTestData.getExpectedMessage());
        Assert.assertFalse(loginPage.getCurrentUrl().equalsIgnoreCase(productTestData.getExpectedUrl()));

    }


}
