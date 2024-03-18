package CMS.testcases;

import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import helpers.ExcelHelper;
import helpers.PropertiesHelper;
import keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void loginSuccess(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1));
        loginPage.verifyLoginSuccess();
        dashboardPage.logoutCMS();
    }

    @Test
    public void loginWithEmailInvalid(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2));

        dashboardPage = loginPage.loginCMS("admin@emai.com", ConfigData.PASSWORD);
        loginPage.verifyLoginFail();
    }

    @Test
    public void loginWithPasswordInvalid(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3));

        dashboardPage = loginPage.loginCMS(ConfigData.EMAIL, "12346");
        loginPage.verifyLoginFail();
    }
}
