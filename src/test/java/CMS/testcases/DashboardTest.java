package CMS.testcases;

import CMS.pages.CategoryPage;
import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import CMS.pages.ProductPage;
import common.BaseTest;
import constants.ConfigData;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;
    ProductPage productPage;

    @Test
    public void testDashboardPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMSSuccess(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void testAddCategoryPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMSSuccess(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
    }

    @Test
    public void testSwitchToCategoryPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMSSuccess(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginPage.verifyLoginSuccess();

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        dashboardPage.clickMenuDashboard();

    }

}
