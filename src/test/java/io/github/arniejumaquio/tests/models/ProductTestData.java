package io.github.arniejumaquio.tests.models;

public class ProductTestData {

    private String testCaseId;
    private String title;
    private String variation;
    private String category;
    private String username;
    private String password;
    private String expectedProductName;
    private String expectedProductDescription;
    private String expectedPrice;
    private String expectedUrl;
    private String sort;
    private String[] sorts;
    private int productCount;
    private String expectedMessage;

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

    public String getExpectedProductName() {
        return expectedProductName;
    }

    public String getExpectedProductDescription() {
        return expectedProductDescription;
    }

    public String getExpectedPrice() {
        return expectedPrice;
    }

    public String getExpectedUrl(){
        return expectedUrl;
    }

    public String getSort(){
        return sort;
    }

    public String[] getSorts(){
        return sorts;
    }

    public int getProductCount(){
        return productCount;
    }

    public String getExpectedMessage(){
        return expectedMessage;
    }

    @Override
    public String toString() {

        if(variation != null){
            return testCaseId + "["+variation+" ]" + title;
        }

        return testCaseId + " - " + title;
    }

}
