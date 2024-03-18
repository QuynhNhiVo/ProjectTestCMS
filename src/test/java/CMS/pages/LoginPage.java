package CMS.pages;

import constants.ConfigData;
import drivers.DriverManager;
import keywords.WebUI;
import org.openqa.selenium.By;
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
        clickButtonLogin();
        return new DashboardPage();
    }

}
