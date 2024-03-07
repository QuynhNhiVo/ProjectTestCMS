package CMS.pages;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;

    private By inputSearchDashboard = By.xpath("//input[@id='email']");
    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By subMenuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");
    private By subAllProducts = By.xpath("//span[normalize-space()='All products']");
    private By subMenuCategory = By.xpath("//span[normalize-space()='Category']");
    private By dropdownProfile = By.xpath("//span[@class='d-flex align-items-center']");
    private By buttonLogout = By.xpath("//a[@href='https://cms.anhtester.com/logout']");


    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        new WebUI(driver);
    }

    private void clickProfile(){
        WebUI.clickElement(dropdownProfile);
    }

    public void clickMenuDashboard(){
        WebUI.clickElement(menuDashboard);
        WebUI.assertContains(driver.getCurrentUrl(), "admin", "Still on Login Page");
    }

    public void clickLogout(){
        WebUI.clickElement(buttonLogout);
    }

    public LoginPage logoutCMS(){
        clickProfile();
        clickLogout();
        return new LoginPage(driver);
    }

    public CategoryPage openAndVerifyCategoryPage(){
        WebUI.clickElement(menuProducts);
        WebUI.clickElement(subMenuCategory);
        WebUI.assertContains(driver.getCurrentUrl(), "categories", "Still on Dashboard Page");
        return new CategoryPage(driver);
    }

    public ProductPage openAddNewProduct(){
        WebUI.clickElement(menuProducts);
        WebUI.clickElement(subMenuAddNewProduct);

        return new ProductPage(driver);
    }

    public ProductPage openAllProductPage(){
        WebUI.clickElement(menuProducts);
        WebUI.clickElement(subAllProducts);

        return new ProductPage(driver);
    }
}
