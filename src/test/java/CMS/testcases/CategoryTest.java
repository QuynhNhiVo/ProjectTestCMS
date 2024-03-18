package CMS.testcases;

import CMS.pages.CategoryPage;
import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import helpers.ExcelHelper;
import keywords.WebUI;
import org.testng.annotations.Test;

public class CategoryTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CategoryPage categoryPage;

    private void loginCMS(){
        ExcelHelper excelHelperLogin = new ExcelHelper();
        excelHelperLogin.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelperLogin.getCellData("EMAIL", 1),
                excelHelperLogin.getCellData("PASSWORD", 1));
    }
    @Test
    public void testAddNewCategory(){
        loginPage = new LoginPage();
        loginCMS();
//        ExcelHelper excelHelperLogin = new ExcelHelper();
//        excelHelperLogin.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");
//
//        dashboardPage = loginPage.loginCMS(
//                excelHelperLogin.getCellData("EMAIL", 1),
//                excelHelperLogin.getCellData("PASSWORD", 1));

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        WebUI.sleep(2);

        ExcelHelper excelHelperCate = new ExcelHelper();
        excelHelperCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");

        categoryPage.inputDataNewCategory(
                excelHelperCate.getCellData("NAME", 1),
                excelHelperCate.getCellData("PARENT",1),
                excelHelperCate.getCellData("ORDERING", 1),
                excelHelperCate.getCellData("TYPE",1),
                excelHelperCate.getCellData("META_TITLE", 1),
                excelHelperCate.getCellData("META_DES",1),
                excelHelperCate.getCellData("FILTERING", 1)
        );
    }

    @Test
    public void testAddNewSaveCategory(){
        loginPage = new LoginPage();
        ExcelHelper excelHelperLogin = new ExcelHelper();
        excelHelperLogin.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelperLogin.getCellData("EMAIL", 1),
                excelHelperLogin.getCellData("PASSWORD", 1));

        categoryPage = dashboardPage.openAndVerifyCategoryPage();
        categoryPage.verifyCategoryPage();
        categoryPage.clickAddNewCategory();
        WebUI.sleep(2);

        ExcelHelper excelHelperCate = new ExcelHelper();
        excelHelperCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");

        categoryPage.inputDataNewCategory(
                excelHelperCate.getCellData("NAME", 1),
                excelHelperCate.getCellData("PARENT",1),
                excelHelperCate.getCellData("ORDERING", 1),
                excelHelperCate.getCellData("TYPE",1),
                excelHelperCate.getCellData("META_TITLE", 1),
                excelHelperCate.getCellData("META_DES",1),
                excelHelperCate.getCellData("FILTERING", 1)
        );
        categoryPage.saveCategory();
        categoryPage.verifySaveCategory();
        categoryPage.verifySearchCategory(excelHelperCate.getCellData("NAME", 1));
    }

    @Test
    public void testDataCategory(){
        loginPage = new LoginPage();
//        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, ConfigData.PASSWORD);
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelper excelHelperCate = new ExcelHelper();
        excelHelperCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelperCate.getCellData("NAME", 1));

        categoryPage.verifyDataAfterAdd(
                excelHelperCate.getCellData("NAME", 1),
                excelHelperCate.getCellData("PARENT",1),
                excelHelperCate.getCellData("ORDERING", 1),
                excelHelperCate.getCellData("TYPE",1),
                excelHelperCate.getCellData("META_TITLE", 1),
                excelHelperCate.getCellData("META_DES",1),
                excelHelperCate.getCellData("FILTERING", 1));
    }

    @Test
    public void testSearchAndCancelDelete(){
        loginPage = new LoginPage();
        ExcelHelper excelHelperLogin = new ExcelHelper();
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelper excelHelperCate = new ExcelHelper();
        excelHelperCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelperCate.getCellData("NAME", 1));
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickCancelDelete();
    }

    @Test
    public void testSearchAndDelete(){
        loginPage = new LoginPage();
        loginCMS();
        categoryPage = dashboardPage.openAndVerifyCategoryPage();

        ExcelHelper excelHelperCate = new ExcelHelper();
        excelHelperCate.setExcelFile(ConfigData.EXCEL_FILE_CATE, "Category");
        categoryPage.verifySearchCategory(excelHelperCate.getCellData("NAME", 1));
        categoryPage.deleteAndVerifyCategory();
        categoryPage.clickDelete();
        categoryPage.verifyCategoryAfterDelete(excelHelperCate.getCellData("NAME", 1));
    }
}
