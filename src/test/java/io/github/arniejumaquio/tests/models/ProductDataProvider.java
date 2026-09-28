package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ProductDataProvider {

    @DataProvider(name = "getProductDisplayCorrectInformationTestData")
    public static Object[][] getProductDisplayCorrectInformationTestData(){

      List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
      List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

      for(ProductTestData testData:testDatas){
          if(testData.getTitle().equalsIgnoreCase("Validate product display correct information")){
              filteredTestDatas.add(testData);
          }
      }

      return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getDefaultProductOrderIsNameAToZTestData")
    public static Object[][] getDefaultProductOrderIsNameAToZTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate default product order is name A to Z")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductsCanBeSortedByNameFromAToZTestData")
    public static Object[][] getProductsCanBeSortedByNameFromAToZTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate products can be sorted by name from A to Z")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }


    @DataProvider(name = "getProductsCanBeSortedByNameFromZToATestData")
    public static Object[][] getProductsCanBeSortedByNameFromZToATestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate products can be sorted by name from Z to A")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductsCanBeSortedByLowToHighTestData")
    public static Object[][]  getProductsCanBeSortedByLowToHighTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate products can be sorted by price from low to high")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductsCanBeSortedByPriceFromHighToLowTestData")
    public static Object[][] getProductsCanBeSortedByPriceFromHighToLowTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate products can be sorted by price from high to low")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductDetailsPageOpensWhenAProductNameIsSelectedTestData")
    public static Object[][] getProductDetailsPageOpensWhenAProductNameIsSelectedTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product details page opens when a product name is selected")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductDetailsPageOpensWhenAProductImageIsSelectedTestData")
    public static Object[][] getProductDetailsPageOpensWhenAProductImageIsSelectedTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product details page opens when a product image is selected")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name="getProductInformationMatchesOnTheProductDetailsPageTestData")
    public static Object[][] getProductInformationMatchesOnTheProductDetailsPageTestData(){


        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product information matches on the product details page")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductPageOpensWhenBackToProductsIsClickTestData")
    public static Object[][] getProductPageOpensWhenBackToProductsIsClickTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product  page opens when back to products is click")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedRefreshTestData")
    public static Object[][] getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedRefreshTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product list contains no missing or duplicate products after repeated refresh")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedSortingTestData")
    public static Object[][] getProductListContainsNoMissingOrDuplicateProductsAfterRepeatedSortingTestData(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product list contains no missing or duplicate products after repeated sorting")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);


    }

    @DataProvider(name = "getProductPageIsInaccessibleWhenUserIsLoggedOut")
    public static Object[][] getProductPageIsInaccessibleWhenUserIsLoggedOut(){

        List<ProductTestData> testDatas =  JSONUtils.readJSONAsList("product-data.json", ProductTestData.class);
        List<ProductTestData> filteredTestDatas = new ArrayList<ProductTestData>();

        for(ProductTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate product page is inaccessible when user is logged out")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }





}
