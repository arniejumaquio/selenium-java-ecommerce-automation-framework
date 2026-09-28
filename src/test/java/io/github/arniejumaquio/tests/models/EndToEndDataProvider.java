package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class EndToEndDataProvider {

    @DataProvider(name = "getUserCanCompleteASingleProductPurchaseTestData")
    public static Object[][] getUserCanCompleteASingleProductPurchaseTestData(){

       List<EndToEndTestData> testDatas = JSONUtils.readJSONAsList("end-to-end-data.json",EndToEndTestData.class);
       List<EndToEndTestData> filteredTestDatas = new ArrayList<EndToEndTestData>();

       for(EndToEndTestData testData:testDatas){
           if(testData.getTitle().equalsIgnoreCase("Validate  user can complete a single product purchase") || testData.getTitle().equalsIgnoreCase("Validate  user can complete a multiple product purchase")){
               filteredTestDatas.add(testData);
           }
       }

       return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

}
