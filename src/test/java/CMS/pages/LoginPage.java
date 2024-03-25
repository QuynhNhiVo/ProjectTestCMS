package CMS.pages;

import constants.ConfigData;
import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import utils.LogUtils;

public class LoginPage {

    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By titleLogin = By.xpath("//h1[normalize-space()='Welcome to Active eCommerce CMS']");
    private String titleLoginText = "Welcome to Active eCommerce CMS";
    private String messageLoginFail = "Invalid login credentials";
    private By getMessage = By.xpath("//span[@data-notify='message']");

    private void clickButtonLogin(){
        WebUI.clickElement(buttonLogin);
    }

    private void setInputEmail(String email){
        WebUI.setTextElement(inputEmail, email);
    }

    private void setInputPassword(String password){
        WebUI.setTextElement(inputPassword, password);
    }

    private void loginCMS(String email, String password){
        WebUI.openURL(ConfigData.URL);
        setInputEmail(email);
        setInputPassword(password);
    }

    public void verifyLoginSuccess(){
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "admin", "Still on Login Page");
    }

    public void verifyLoginFail(){
        WebUI.checkElementDisplay(getMessage);
        WebUI.assertEquals(WebUI.getTextElement(getMessage), messageLoginFail, "Message not Display");
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "login", "Go to Dashboard Page");
    }

    private void verifyFormatValueInput(By by){
        LogUtils.info(WebUI.getAttributeElement(by, "id") + " Check Format Valid: " + ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", WebUI.getWebElement(by)));
        Assert.assertTrue((Boolean)((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", WebUI.getWebElement(by)), (WebUI.getWebElement(by).getAttribute("id") + " value not valid."));
    }

    private void verifyRequiredValidationAndMessage(By by){

        LogUtils.info(WebUI.getWebElement(by).getAttribute("id") + " Required: " + ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].required;", WebUI.getWebElement(by)));
        Assert.assertTrue((Boolean)((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].required;", WebUI.getWebElement(by)), (WebUI.getWebElement(by).getAttribute("id") + " not required field."));

        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        WebUI.assertEquals((jsExecutor.executeScript("return arguments[0].validationMessage;", WebUI.getWebElement(by))), "Please fill out this field.", "The validation message of Email field not match.");
    }

    public DashboardPage loginCMSSuccess(String email, String password){
        WebUI.openURL(ConfigData.URL);
        loginCMS(email, password);
        verifyFormatValueInput(inputEmail);
        verifyFormatValueInput(inputPassword);
        clickButtonLogin();
        verifyLoginSuccess();
        return new DashboardPage();
    }

    public void loginWithEmailInvalid(String email, String password){
        loginCMS(email, password);
        verifyFormatValueInput(inputEmail);
        verifyFormatValueInput(inputPassword);
        clickButtonLogin();
        verifyLoginFail();
    }

    public void loginWithPasswordInvalid(String email, String password){
        loginCMS(email, password);
        verifyFormatValueInput(inputEmail);
        verifyFormatValueInput(inputPassword);
        clickButtonLogin();
        verifyLoginFail();
    }

    public void loginWithEmailEmpty(String email, String password){
        loginCMS(email, password);
        clickButtonLogin();
        verifyRequiredValidationAndMessage(inputEmail);
        verifyFormatValueInput(inputPassword);
    }

    public void loginWithPasswordEmpty(String email, String password){
        loginCMS(email, password);
        clickButtonLogin();
        verifyRequiredValidationAndMessage(inputPassword);
        verifyFormatValueInput(inputEmail);
    }
}
