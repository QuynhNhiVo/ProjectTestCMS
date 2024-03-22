package CMS.pages;

import constants.ConfigData;
import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

    private void sendEmail(String email){
        WebUI.setTextElement(inputEmail, email);
    }

    private void sendPassword(String password){
        WebUI.setTextElement(inputPassword, password);
    }

    private void clickButtonLogin(){
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginSuccess(){
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "admin", "Still on Login Page");
    }

    public void verifyLoginFail(){
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "login", "Go to Dashboard Page");
        WebUI.checkElementDisplay(getMessage);
        WebUI.assertEquals(WebUI.getTextElement(getMessage), messageLoginFail, "Message not Display");
    }

    public DashboardPage loginCMS(String email, String password){
        WebUI.openURL(ConfigData.URL);
        sendEmail(email);
        sendPassword(password);
        verifyFormatValueInput(WebUI.getWebElement(inputEmail));
        verifyFormatValueInput(WebUI.getWebElement(inputPassword));
        WebUI.sleep(1);
        clickButtonLogin();
        return new DashboardPage();
    }

    private String verifyLoginFieldBlank(WebElement element){

        LogUtils.info(element.getAttribute("id") + " Required: " + ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].required;", element));
        Assert.assertTrue((Boolean)((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].required;", element), "Email not required field.");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", element);
    }

    private void verifyFormatValueInput(WebElement element){
        LogUtils.info(element.getAttribute("id") + " Check Valid: " + ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", element));
        Assert.assertTrue((Boolean)((JavascriptExecutor) DriverManager.getDriver()).executeScript("return arguments[0].validity.valid;", element), "Email value not valid.");
    }

    public void verifyEmailEmpty(){
        WebUI.assertEquals(verifyLoginFieldBlank(WebUI.getWebElement(inputEmail)), "Please fill out this field.", "The validation message of Email field not match.");
        verifyFormatValueInput(WebUI.getWebElement(inputEmail));
    }

    public void verifyPasswordlEmpty(){
        WebUI.assertEquals(verifyLoginFieldBlank(WebUI.getWebElement(inputPassword)), "Please fill out this field.", "The validation message of Email field not match.");
        verifyFormatValueInput(WebUI.getWebElement(inputPassword));
    }

}
