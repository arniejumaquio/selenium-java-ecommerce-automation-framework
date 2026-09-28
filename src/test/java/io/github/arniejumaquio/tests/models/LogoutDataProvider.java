package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class LogoutDataProvider {

    @DataProvider(name = "getSuccessfulLogoutWithValidCredentialsTestData")
    public static Object[][] getSuccessfulLogoutWithValidCredentialsTestData(){

        List<LogoutTestData> testDatas =  JSONUtils.readJSONAsList("logout-data.json", LogoutTestData.class);
        List<LogoutTestData> filteredTestDatas = new ArrayList<LogoutTestData>();

        for(LogoutTestData testData:testDatas){
            if(testData.getTitle().equalsIgnoreCase("Validate successful logout with valid credentials")){
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

}
