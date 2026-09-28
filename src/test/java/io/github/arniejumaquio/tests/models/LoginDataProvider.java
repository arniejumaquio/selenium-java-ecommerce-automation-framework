package io.github.arniejumaquio.tests.models;

import io.github.arniejumaquio.framework.utils.DataProviderUtils;
import io.github.arniejumaquio.framework.utils.JSONUtils;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

public final class LoginDataProvider {

    private LoginDataProvider() {
    }

    @DataProvider(name = "getValidLoginTestData")
    public static Object[][] getValidLoginTestData() {
        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate successful login with valid credentials") && testData.getCategory().equalsIgnoreCase("Positive")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name="getSuccessfulLoginAfterFailedLogin")
    public static Object[][] getSuccessfulLoginAfterFailedLogin(){

       List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json",LoginTestData.class);
       List<LoginTestData> filteredTestDatas = new ArrayList<LoginTestData>();

       for(LoginTestData testData: testDatas){
           if(testData.getTitle().equalsIgnoreCase("Validate successful login after failed login due to wrong credentials") && testData.getCategory().equalsIgnoreCase("Positive") ){
               filteredTestDatas.add(testData);
           }
       }

       return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getInvalidLoginTestData")
    public static Object[][] getInvalidLoginTestData() {
        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getCategory().equalsIgnoreCase("Negative")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getEdgeLoginTestData")
    public static Object[][] getEdgeLoginTestData() {
        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getCategory().equalsIgnoreCase("Edge")
                    && !testData.getTitle().equalsIgnoreCase("Validate repeated invalid login do not duplicate the error banner")
                    && !testData.getTitle().equalsIgnoreCase("Validate  repeated clicking the login button multiple times triggers only one login session")
                    && !testData.getTitle().equalsIgnoreCase("Validate login session remains active after product page refresh")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name = "getRepeatedInvalidLoginDoNotDuplicateTheErrorBannerTestData")
    public static Object[][] getRepeatedInvalidLoginDoNotDuplicateTheErrorBannerTestData(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate repeated invalid login do not duplicate the error banner")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getRepeatedClickLLoginButtonMultipleTimesTriggersOnlyOneLoginSession")
    public static Object[][] getRepeatedClickLLoginButtonMultipleTimesTriggersOnlyOneLoginSession(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate  repeated clicking the login button multiple times triggers only one login session")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getLoginSessionRemainsActiveAfterProductPageRefresh")
    public static Object[][] getLoginSessionRemainsActiveAfterProductPageRefresh(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate login session remains active after product page refresh")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }

    @DataProvider(name = "getLoginPageIsAccessibleUsingASecureURL")
    public static Object[][] getLoginPageIsAccessibleUsingASecureURL(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getTitle().equalsIgnoreCase("Validate login page is accessible using a secure URL")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }


    @DataProvider(name = "getSecurityLoginTestData")
    public static Object[][] getSecurityLoginTestData(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for (LoginTestData testData : testDatas) {
            if (testData.getCategory().equalsIgnoreCase("Security")
                    && !testData.getTitle().equalsIgnoreCase("Validate login page is accessible using a secure URL")
                    && !testData.getTitle().equalsIgnoreCase("Validate login input are not exposed in the browser URL after submission")
                    && !testData.getTitle().equalsIgnoreCase("Validate password  are not exposed in the login error message")) {
                filteredTestDatas.add(testData);
            }
        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }

    @DataProvider(name="getLoginInputNotExposeInTheUrlTestData")
    public static Object[][] getLoginInputNotExposeInTheUrlTestData(){

       List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
       List<LoginTestData> filteredTestDatas = new ArrayList<>();

       for(LoginTestData testData:testDatas){

           if(testData.getTitle().equalsIgnoreCase("Validate login input are not exposed in the browser URL after submission")){
               filteredTestDatas.add(testData);
           }

       }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);
    }


    @DataProvider(name = "getPasswordAreNotExposedInTheLoginErrorMessage")
    public static Object[][] getPasswordAreNotExposedInTheLoginErrorMessage(){

        List<LoginTestData> testDatas = JSONUtils.readJSONAsList("login-data.json", LoginTestData.class);
        List<LoginTestData> filteredTestDatas = new ArrayList<>();

        for(LoginTestData testData:testDatas){

            if(testData.getTitle().equalsIgnoreCase("Validate password  are not exposed in the login error message")){
                filteredTestDatas.add(testData);
            }

        }

        return DataProviderUtils.toDataProviderArray(filteredTestDatas);

    }


}
