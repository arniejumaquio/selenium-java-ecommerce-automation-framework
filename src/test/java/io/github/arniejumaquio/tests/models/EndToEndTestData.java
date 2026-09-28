package io.github.arniejumaquio.tests.models;

import java.util.List;

public class EndToEndTestData {

    private String testCaseId;
    private String title;
    private String category;
    private String username;
    private String password;
    private List<String> expectedProductNames;
    private List<String> expectedProductDescriptions;
    private List<String> expectedPrices;
    private String firstName;
    private String lastName;
    private String zipPostalCode;
    private String expectedPaymentInformation;
    private String expectedShippingInformation;
    private List<String> expectedMessages;

    public String getTestCaseId() {
        return testCaseId;
    }

    public String getTitle() {
        return title;
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

    public List<String> getExpectedProductNames() {
        return expectedProductNames;
    }

    public List<String> getExpectedProductDescriptions() {
        return expectedProductDescriptions;
    }

    public List<String> getExpectedPrices() {
        return expectedPrices;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipPostalCode() {
        return zipPostalCode;
    }

    public String getExpectedPaymentInformation(){
        return expectedPaymentInformation;
    }

    public String getExpectedShippingInformation(){
        return expectedShippingInformation;
    }

    public List<String> getExpectedMessages(){
        return expectedMessages;
    }

}
