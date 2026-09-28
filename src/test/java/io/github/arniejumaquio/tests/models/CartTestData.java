package io.github.arniejumaquio.tests.models;

import java.util.List;

public class CartTestData {

    private String testCaseId;
    private String title;
    private String variation;
    private String category;
    private String username;
    private String password;
    private List<String> expectedProductNames;
    private List<String>  expectedProductDescriptions;
    private List<String>  expectedPrices;
    private String expectedMessage;
    private String expectedUrl;
    private String productToRemove;



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

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public String getProductToRemove(){
        return productToRemove;
    }

    @Override
    public String toString() {
        if(variation != null){
            return testCaseId + "["+variation+" ]" + title;
        }

        return testCaseId + " - " + title;
    }

}
