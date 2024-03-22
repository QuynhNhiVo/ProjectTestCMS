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

        loginPage.verifyLoginFail();
    }

    @Test
    public void loginWithEmailEmpty(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelper.getCellData("EMAIL", 4),
                excelHelper.getCellData("PASSWORD", 4));

        WebUI.sleep(1);
        loginPage.verifyEmailEmpty();
    }

    @Test
    public void loginWithPasswordEmpty(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        dashboardPage = loginPage.loginCMS(
                excelHelper.getCellData("EMAIL", 5),
                excelHelper.getCellData("PASSWORD", 5));

        WebUI.sleep(3);
        loginPage.verifyPasswordlEmpty();
    }

}
