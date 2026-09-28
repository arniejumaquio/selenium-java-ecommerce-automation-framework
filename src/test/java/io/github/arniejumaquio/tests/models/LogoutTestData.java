package io.github.arniejumaquio.tests.models;

public class LogoutTestData {

    private String testCaseId;
    private String title;
    private String variation;
    private String category;
    private String username;
    private String password;
    private String expectedMessage;
    private String expectedUrl;

    public LogoutTestData() {
    }

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

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    @Override
    public String toString() {
        if(variation != null){
            return testCaseId + "["+variation+" ]" + title;
        }

        return testCaseId + " - " + title;
    }

}
