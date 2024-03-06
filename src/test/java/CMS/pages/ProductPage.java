package CMS.pages;

import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private WebDriver driver;

    private By headerAddNewProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    private String textHeader = "Add New Product";

    private By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    private By dropdownCategory = By.xpath("//select[@id='category_id']");
    private By inputCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By dropdownBrand = By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div//select");
    private By inputBrand= By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By inputUnit = By.xpath("//input[@placeholder='Unit (e.g. KG, Pc etc)']");
    private By inputWeight = By.xpath("//input[@placeholder='0.00']");
    private By inputMinPurchase = By.xpath("///input[@name='min_qty']");
    private By inputTags = By.xpath("//tags[@role='tagslist']");

    private By getGalleryImage = By.xpath("(//label[normalize-space()='Gallery Images (600x600)']/following-sibling::div//div[normalize-space()='Browse'])[1]");
    private By inputSearchFile = By.xpath("//input[@placeholder='Search your files']");
    private By getThumbnailImage = By.xpath("(//label[normalize-space()='Thumbnail Image (300x300)']/following-sibling::div//div[normalize-space()='Browse'])[1]");
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
    private By inputMetaTitle= By.xpath("//input[@placeholder='Meta Title']");
    private By inputMetaDescription = By.xpath("//textarea[@name='meta_description']");

    private By flipSwitchFeatured = By.xpath("//input[@name='featured']");
    private By flipSwitchTodaysDeal = By.xpath("//input[@name='todays_deal']");
    private By buttonSaveAndPublish = By.xpath("//button[normalize-space()='Save & Publish']");
    private By buttonSaveAndUnpublish = By.xpath("//button[normalize-space()='Save & Unpublish']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyAddProductPage(){
        WebUI.assertContains(WebUI.getDriver().getCurrentUrl(), "create", "Can't go to Add New Product Page.");
        WebUI.checkElementDisplay(headerAddNewProduct);
        WebUI.assertEquals(WebUI.getTextElement(headerAddNewProduct), textHeader, "Header Not True");
    }

    public void inputDataNewProduct(String name, String category, String weight, String minpur, String tag){
        WebUI.setTextElement(inputProductName, name);
        WebUI.clickElement(dropdownCategory);
        WebUI.setTextAndKey(inputCategory, category, Keys.ENTER);
        WebUI.clickElement(dropdownBrand);
        WebUI.setTextAndKey(inputBrand, category, Keys.ENTER);
        WebUI.setTextElement(inputWeight, weight);
        WebUI.setTextElement(inputMinPurchase, minpur);
        WebUI.setTextAndKey(inputTags, tag, Keys.ENTER);

        WebUI.clickElement(getGalleryImage);
        WebUI.setTextElement(inputSearchFile, "123");
        WebUI.sleep(2);
        WebUI.clickElement(buttonAddFile);

    }
}
