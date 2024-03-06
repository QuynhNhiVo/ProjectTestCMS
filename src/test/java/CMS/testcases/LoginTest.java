package CMS.testcases;

import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void loginSuccess(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();
        dashboardPage.logoutCMS();
    }

    @Test
    public void loginWithEmailInvalid(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS("admin@emai.com", ConfigData.PASSWORD);
        loginPage.verifyLoginFail();
    }

    @Test
    public void loginWithPasswordInvalid(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, "12346");
        loginPage.verifyLoginFail();
    }
}
