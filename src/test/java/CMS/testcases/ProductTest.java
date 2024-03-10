package CMS.testcases;

import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import CMS.pages.ProductPage;
import common.BaseTest;
import constants.ConfigData;
import keywords.WebUI;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProductPage productPage;

    @Test
    public void testAddProduct(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        productPage = dashboardPage.openAddNewProduct();
        productPage.verifyAddProductPage();
        productPage.inputDataNewProduct(ConfigData.PRODUCT_NAME, ConfigData.CATEGORY_NAME, "1", "kg", "10", "2", "tags", ConfigData.colorArray, "4", "0.1","new product test", ConfigData.META_TITLE, ConfigData.META_DESCRIPTION);
        productPage.verifySaveSuccess();
    }
    @Test
    public void testVerifySearch(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);

        productPage = dashboardPage.openAllProductPage();
        productPage.verifySearchProduct(ConfigData.PRODUCT_NAME);
    }

    @Test
    public void testDeleteProduct(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);

        productPage = dashboardPage.openAllProductPage();
        productPage.deleteAndVerify(ConfigData.PRODUCT_NAME);
    }

    @Test
    public void testVerifyData(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);

        productPage = dashboardPage.openAllProductPage();
        productPage.verifyDataNewProduct(ConfigData.PRODUCT_NAME, ConfigData.CATEGORY_NAME, "1", "kg", "10", "2", ((ConfigData.colorArray).length + " items selected"), "4", "0.1","new product test", ConfigData.META_TITLE, ConfigData.META_DESCRIPTION);

    }
}
