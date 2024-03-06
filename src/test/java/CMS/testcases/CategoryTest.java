package CMS.testcases;

import CMS.pages.CategoryPage;
import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;

    @Test
    public void testAddNewCategory(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        categoryPage.inputDataNewCategory(ConfigData.CATEGORY_NAME, "1", "cake", "1", "123", "new title", "Size");
    }

    @Test
    public void testSaveAddNewCategory(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        categoryPage.inputDataNewCategory(ConfigData.CATEGORY_NAME, "1", "cake", "1", "title", "new description", "Size");
        categoryPage.saveCategory();
        categoryPage.verifySaveCategory();
    }

    @Test
    public void testSearchAndCancelDelete(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifySearchCategory(ConfigData.CATEGORY_NAME);
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickCancelDelete();
    }

    @Test
    public void testSearchAndDelete(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifySearchCategory(ConfigData.CATEGORY_NAME);
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickDelete();
    }

    @Test
    public void testDataCategory(){
        loginPage = new LoginPage(driver);
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifySearchCategory(ConfigData.CATEGORY_NAME);
        categoryPage.verifyDataAfterAdd(ConfigData.CATEGORY_NAME, "1", "Cake", "Digital", "title", "new description", "Size");
    }
}
