package CMS.pages;

import constants.ConfigData;
import keywords.WebUI;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By headerLogin = By.xpath("//h1[normalize-space()='Welcome to Active eCommerce CMS']");
    private String messageLoginFail = "Invalid login credentials";
    private By getMessage = By.xpath("//span[@data-notify='message']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }

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
        WebUI.assertContains(driver.getCurrentUrl(), "admin", "Still on Login Page");
    }

    public void verifyLoginFail(){
        WebUI.assertContains(driver.getCurrentUrl(), "login", "Go to Dashboard Page");
        WebUI.checkElementDisplay(getMessage);
        WebUI.getTextElement(By.xpath("//span[@data-notify='message']"));
        WebUI.assertEquals(WebUI.getWebElement(By.xpath("//span[@data-notify='message']")).getText(), messageLoginFail, "Message not Display");
    }

    public DashboardPage loginCMS(String email, String password){
        WebUI.openURL(ConfigData.URL);
        sendEmail(email);
        sendPassword(password);
        clickButtonLogin();
        return new DashboardPage(driver);
    }

}
