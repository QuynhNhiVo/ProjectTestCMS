package CMS.testcases;

import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import helpers.ExcelHelper;
import keywords.WebUI;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void Test_LoginSuccess(){
        loginPage = new LoginPage();
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");
        dashboardPage = loginPage.loginCMSSuccess(
                excelHelper.getCellData("EMAIL", 1),
                excelHelper.getCellData("PASSWORD", 1));
        dashboardPage.logoutCMS();
    }

    @Test
    public void Test_LoginWithEmailInvalid(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithEmailInvalid(
                excelHelper.getCellData("EMAIL", 2),
                excelHelper.getCellData("PASSWORD", 2));
    }

    @Test
    public void Test_LoginWithPasswordInvalid(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithPasswordInvalid(
                excelHelper.getCellData("EMAIL", 3),
                excelHelper.getCellData("PASSWORD", 3));
    }

    @Test
    public void Test_LoginWithEmailEmpty(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithEmailEmpty(
                excelHelper.getCellData("EMAIL", 4),
                excelHelper.getCellData("PASSWORD", 4));
    }

    @Test
    public void Test_LoginWithPasswordEmpty(){
        loginPage = new LoginPage();

        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithPasswordEmpty(
                excelHelper.getCellData("EMAIL", 5),
                excelHelper.getCellData("PASSWORD", 5));
    }

}
