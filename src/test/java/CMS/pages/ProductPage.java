package CMS.pages;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.StringTokenizer;

public class ProductPage {
    private WebDriver driver;

    private By headerAddNewProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    private String textHeader = "Add New Product";
    private By inputSearchProduct = By.xpath("//input[@id='search']");
    private By firstResult = By.xpath("//tbody/tr[1]/td[2]/div[1]/div[2]/span");

    private By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    private By dropdownCategory = By.xpath("//button[@title='Sport shoes']");
    private By inputCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By dropdownBrand = By.xpath("//button[@title='Select Brand']");
    private By inputBrand= By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By inputUnit = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    private By inputWeight = By.xpath("//input[@placeholder='0.00']");
    private By inputMinPurchase = By.xpath("//input[@name='min_qty']");
    private By inputTags = By.xpath("//span[@role='textbox']");

    private By getGalleryImage = By.xpath("//label[contains(.,'Gallery Images')]/following-sibling::div/div[contains(.,'Browse')]/div[1]");
    private By inputSearchFile = By.xpath("//input[@placeholder='Search your files']");
    private By getThumbnailImage = By.xpath("(//label[normalize-space()='Thumbnail Image (300x300)']/following-sibling::div//div[normalize-space()='Browse'])[1]");
    private By image = By.xpath("//img[@class='img-fit']");
    private By buttonAddFile = By.xpath("//button[normalize-space()='Add Files']");

    private By dropdownVideoProduct = By.xpath("//select[@id='video_provider']");
    private By inputLinkvideo = By.xpath("//input[@placeholder='Video Link']");

    private By checkBoxColor = By.xpath("//input[@name='colors_active']/following-sibling::span");
    private By dropdownColor = By.xpath("//button[@data-id='colors']//div[@class='filter-option-inner-inner']");
    private By inputColor = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");

    private By inputUnitPrice = By.xpath("//input[@placeholder='Unit price']");
    private By inputDiscount = By.xpath("//input[@placeholder='Discount']");
    private By inputQuantity = By.xpath("//input[@placeholder='Quantity']");
    private By inputSKU = By.xpath("//input[@placeholder='SKU']");
    private By inputDesciption= By.xpath("//div[@role='textbox']");
    private By inputMetaTitle= By.xpath("//input[@placeholder='Meta Title']");
    private By inputMetaDescription = By.xpath("//textarea[@name='meta_description']");

    private By flipSwitchFeatured = By.xpath("//input[@name='featured']");
    private By flipSwitchTodaysDeal = By.xpath("//input[@name='todays_deal']");
    private By buttonSaveAndPublish = By.xpath("//button[normalize-space()='Save & Publish']");
    private By buttonSaveAndUnpublish = By.xpath("//button[normalize-space()='Save & Unpublish']");

    private By messageSave = By.xpath("//span[@data-notify='message']");
    private String textMessageSave = "Product has been inserted successfully";

    private By buttonDelete = By.xpath("(//a[@title='Delete'])[1]");
    private By headerModalDel = By.xpath("//h4[normalize-space()='Delete Confirmation']");
    private String textModalDel = "Delete Confirmation";
    private By optionCancel = By.xpath("//button[normalize-space()='Cancel']");
    private By optionDelete = By.xpath("//a[@id='delete-link']");
    private By messageDelete = By.xpath("//span[@data-notify='message']");
    private String textMessageDelete = "Product has been deleted successfully";


    private By buttonEdit = By.xpath("(//a[@title='Edit'])[1]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyAddProductPage(){
        WebUI.assertContains(WebUI.getDriver().getCurrentUrl(), "create", "Can't go to Add New Product Page.");
        WebUI.checkElementDisplay(headerAddNewProduct);
        WebUI.assertEquals(WebUI.getTextElement(headerAddNewProduct), textHeader, "Header Not True");
    }

    private void chooseColors(String[] List){
        WebUI.clickElement(checkBoxColor);
        WebUI.clickElement(dropdownColor);
        WebUI.sleep(1);
        for(String color: List){
            Actions actions = new Actions(driver);
            actions.sendKeys(WebUI.getWebElement(inputColor), color + Keys.ENTER).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).build().perform();
        }
    }

    public void inputDataNewProduct(String name, String category, String brand, String kg, String weight, String minpur, String tag, String[] List, String unit, String discount, String des, String title, String metades){
        WebUI.setTextElement(inputProductName, name);
        WebUI.clickElement(dropdownCategory);
        WebUI.setTextAndKey(inputCategory, category, Keys.ENTER);
        WebUI.clickElement(dropdownBrand);
        WebUI.setTextAndKey(inputBrand, brand, Keys.ENTER);
        WebUI.setTextElement(inputUnit, kg);
        WebUI.setTextElement(inputWeight, weight);
        WebUI.setTextElement(inputMinPurchase, minpur);
        WebUI.setTextAndKey(inputTags, tag, Keys.ENTER);
        WebUI.sleep(2);

        WebUI.clickElement(getGalleryImage);
        WebUI.setTextElement(inputSearchFile, "123");
        WebUI.sleep(2);
        WebUI.clickElement(image);
        WebUI.clickElement(buttonAddFile);

        chooseColors(List);
        WebUI.sleep(2);

        WebUI.setTextElement(inputUnitPrice, unit);
        WebUI.setTextElement(inputDiscount, discount);
        WebUI.setTextElement(inputDesciption, des);
        WebUI.setTextElement(inputMetaTitle, title);
        WebUI.setTextElement(inputMetaDescription, metades);

        WebUI.clickElement(buttonSaveAndPublish);
    }
    public void verifySaveSuccess(){
        WebUI.checkElementDisplay(messageSave);
        WebUI.assertEquals(WebUI.getTextElement(messageSave), textMessageSave, "Not Message Save");
    }

    public void verifySearchProduct(String name){
        WebUI.setTextAndKey(inputSearchProduct, name, Keys.ENTER);
        WebUI.checkElementDisplay(firstResult);
        WebUI.assertEquals(WebUI.getTextElement(firstResult), name, "Not found product");
    }

    public void deleteAndVerify(String name){
        verifySearchProduct(name);
        WebUI.clickElement(buttonDelete);
        WebUI.assertEquals(WebUI.getTextElement(headerModalDel), textModalDel, "Incorrect Text Of Modal Delete.");
        WebUI.clickElement(optionDelete);
        WebUI.checkElementDisplay(messageDelete);
        WebUI.assertEquals(WebUI.getTextElement(messageDelete), textMessageDelete, "Incorrect Message Delete.");
    }

}
