package io.github.arniejumaquio.tests.models;

import java.util.List;

public class CheckoutTestData {

    private String testCaseId;
    private String title;
    private String variation;
    private String category;
    private String username;
    private String password;
    private List<String> expectedProductNames;
    private List<String>  expectedProductDescriptions;
    private List<String>  expectedPrices;
    private List<String> expectedMessages;
    private String expectedUrl;
    private String productToRemove;
    private String firstName;
    private String lastName;
    private String zipPostalCode;
    private String expectedPaymentInformation;
    private String expectedShippingInformation;



    public String getTestCaseId() {
        return testCaseId;
    }

    public String getTitle() {
        return title;
    }

    public String getVariation() {
        return variation;
    }

    public String getCategory() {
        return category;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String>  getExpectedProductNames() {
        return expectedProductNames;
    }

    public List<String>  getExpectedProductDescriptions() {
        return expectedProductDescriptions;
    }

    public List<String>  getExpectedPrices() {
        return expectedPrices;
    }

    public List<String> getExpectedMessages() {
        return expectedMessages;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public String getProductToRemove(){
        return productToRemove;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getZipPostalCode(){
        return zipPostalCode;
    }

    public String getExpectedPaymentInformation(){
        return expectedPaymentInformation;
    }

    public String getExpectedShippingInformation(){
        return expectedShippingInformation;
    }

    @Override
    public String toString() {
        if(variation != null){
            return testCaseId + "["+variation+" ]" + title;
        }

        return testCaseId + " - " + title;
    }

}
