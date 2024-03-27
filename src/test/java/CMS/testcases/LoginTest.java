package CMS.testcases;

import CMS.pages.DashboardPage;
import CMS.pages.LoginPage;
import common.BaseTest;
import constants.ConfigData;
import helpers.ExcelHelpers;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void Test_LoginSuccess(){
        loginPage = new LoginPage();
        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");
        dashboardPage = loginPage.loginCMSSuccess(
                excelHelpers.getCellData("EMAIL", 2),
                excelHelpers.getCellData("PASSWORD", 1));
        dashboardPage.logoutCMS();
    }

    @Test
    public void Test_LoginWithEmailInvalid(){
        loginPage = new LoginPage();

        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithEmailInvalid(
                excelHelpers.getCellData("EMAIL", 2),
                excelHelpers.getCellData("PASSWORD", 2));
    }

    @Test
    public void Test_LoginWithPasswordInvalid(){
        loginPage = new LoginPage();

        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithPasswordInvalid(
                excelHelpers.getCellData("EMAIL", 3),
                excelHelpers.getCellData("PASSWORD", 3));
    }

    @Test
    public void Test_LoginWithEmailEmpty(){
        loginPage = new LoginPage();

        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithEmailEmpty(
                excelHelpers.getCellData("EMAIL", 4),
                excelHelpers.getCellData("PASSWORD", 4));
    }

    @Test
    public void Test_LoginWithPasswordEmpty(){
        loginPage = new LoginPage();

        ExcelHelpers excelHelpers = new ExcelHelpers();
        excelHelpers.setExcelFile(ConfigData.EXCEL_FILE_DATA, "Login");

        loginPage.loginWithPasswordEmpty(
                excelHelpers.getCellData("EMAIL", 5),
                excelHelpers.getCellData("PASSWORD", 5));
    }

}
