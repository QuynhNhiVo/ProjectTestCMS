package CMS.testcases;

import CMS.pages.CategoryPage;
import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import dataprovider.DataProviderFactory;
import helpers.ExcelHelpers;
import keywords.WebUI;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class CategoryTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;

    private void loginCMS(){
        ExcelHelpers excelHelpersLogin = new ExcelHelpers();
        excelHelpersLogin.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMSSuccess(
                excelHelpersLogin.getCellData("EMAIL", 1),
                excelHelpersLogin.getCellData("PASSWORD", 1));
    }

    @Test(dataProvider = "data_add_category", dataProviderClass = DataProviderFactory.class)
    @DataProvider(parallel = true)
    public void testAddNewCategory(Hashtable<String, String> data){
        loginPage = new LoginPage();
        loginCMS();

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        WebUI.sleep(2);

        ExcelHelpers excelHelpersCate = new ExcelHelpers();
        excelHelpersCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");

        categoryPage.inputDataNewCategory(
                data.get("NAME"),
                data.get("PARENT"),
                data.get("ORDERING"),
                data.get("TYPE"),
                data.get("META_TITLE"),
                data.get("META_DES"),
                data.get("FILTERING")
        );
    }

    @Test(dataProvider = "data_add_category", dataProviderClass = DataProviderFactory.class)
    @DataProvider(parallel = true)
    public void testAddNewSaveCategory(Hashtable<String, String> data){
        loginPage = new LoginPage();
        ExcelHelpers excelHelpersLogin = new ExcelHelpers();
        excelHelpersLogin.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMSSuccess(
                excelHelpersLogin.getCellData("EMAIL", 1),
                excelHelpersLogin.getCellData("PASSWORD", 1));

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        WebUI.sleep(2);

        ExcelHelpers excelHelpersCate = new ExcelHelpers();
        excelHelpersCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");

        categoryPage.inputDataNewCategory(
                data.get("NAME"),
                data.get("PARENT"),
                data.get("ORDERING"),
                data.get("TYPE"),
                data.get("META_TITLE"),
                data.get("META_DES"),
                data.get("FILTERING")
        );
        categoryPage.saveCategory();
        categoryPage.verifySaveCategory();
        categoryPage.verifySearchCategory(excelHelpersCate.getCellData("NAME", 1));
    }

    @Test
    public void testDataCategory(){
        loginPage = new LoginPage();
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelpers excelHelpersCate = new ExcelHelpers();
        excelHelpersCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelpersCate.getCellData("NAME", 1));

        categoryPage.verifyDataAfterAdd(
                excelHelpersCate.getCellData("NAME", 1),
                excelHelpersCate.getCellData("PARENT",1),
                excelHelpersCate.getCellData("ORDERING", 1),
                excelHelpersCate.getCellData("TYPE",1),
                excelHelpersCate.getCellData("META_TITLE", 1),
                excelHelpersCate.getCellData("META_DES",1),
                excelHelpersCate.getCellData("FILTERING", 1));
    }

    @Test
    public void testSearchAndCancelDelete(){
        loginPage = new LoginPage();
        ExcelHelpers excelHelpersLogin = new ExcelHelpers();
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelpers excelHelpersCate = new ExcelHelpers();
        excelHelpersCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelpersCate.getCellData("NAME", 1));
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickCancelDelete();
    }

    @Test
    public void testSearchAndDelete(){
        loginPage = new LoginPage();
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelpers excelHelpersCate = new ExcelHelpers();
        excelHelpersCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelpersCate.getCellData("NAME", 1));
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickDelete();
        categoryPage.verifyCategoryAfterDelete(excelHelpersCate.getCellData("NAME", 1));
    }
}
