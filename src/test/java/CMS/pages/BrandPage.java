package CMS.pages;

import keywords.WebUI;
import org.openqa.selenium.By;

public class BrandPage {
    private By title = By.xpath("//span[normalize-space()='Brand']");
    private By headerBrands = By.xpath("//span[normalize-space()='Brand']");
    private By inputSearch = By.xpath("//input[@id='search']");
    private By headerAddNewBrand = By.xpath("//h5[normalize-space()='Add New Brand']");
    private By inputName = By.xpath("//input[@placeholder='Name']");
    private By chooseFile = By.xpath("//div[normalize-space()='Choose File']");
    private By uploadNew = By.xpath("//a[normalize-space()='Upload New']");
    private By browser = By.xpath("//button[normalize-space()='Browse']");
    private By addFiles = By.xpath("//button[normalize-space()='Add Files']");
    private By metaTitle = By.xpath("//input[@placeholder='Meta Title']");
    private By metaDes = By.xpath("//input[@placeholder='Meta Title']");
    private By buttonSave = By.xpath("//input[@placeholder='Meta Title']");

    public void verifyBrandPage(){
//        WebUI.assertEquals(WebUI.getTextElement(title), tilte, "Not the title of the page");
//        WebUI.assertEquals(WebUI.getTextElement(headerBrands), tilte, "Not the title of the page");
//        WebUI.assertEquals(WebUI.getTextElement(headerAddNewBrand), tilte, "Not the title of the page");
    }

    public void addNewBrand(){

    }
}
