package CMS.pages;

import constants.ConfigData;
import keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class CategoryPage {

    private By headerCategory = By.xpath("//h1[normalize-space()='All categories']");
    private String textHeaderCategory = "All categories";
    private By buttonAddNewCategory = By.xpath("//span[normalize-space()='Add New category']");
    private By inputSearchCategory = By.xpath("//input[@id='search']");

    private By inputNameCategory = By.xpath("//input[@id='name']");
    private By inputOrderingCategory = By.xpath("//input[@id='order_level']");
    private By inputMetaTitle= By.xpath("//input[@placeholder='Meta Title']");
    private By inputMetaDescription = By.xpath("//textarea[@name='meta_description']");
    private By dropdownParentCategory = By.xpath("//label[normalize-space()='Parent Category']/following-sibling::div//button");
    private By inputParentCategory = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By dropdownTypeCategory = By.xpath("//label[normalize-space()='Type']/following-sibling::div//select");
    private By addFileBannerCategory = By.xpath("//label[normalize-space()='Banner (200x200)']/following::div[normalize-space()='Browse'][1]");
    private By addFileIconCategory = By.xpath("//label[normalize-space()='Icon (32x32)']/following::div[normalize-space()='Browse'][2]");
    private By uploadFile = By.xpath("//a[normalize-space()='Upload New']");
    private By browser = By.xpath("//button[normalize-space()='Browse']");
    private By searchFile = By.xpath("//input[@placeholder='Search your files']");
    private By buttonAddFile = By.xpath("//button[normalize-space()='Add Files']");
    private String nameImage = "cookingrice";
    private By fileImage = By.xpath("//span[contains(text(),'cookingrice')]");
    private By dropdownFilterCategory = By.xpath("//label[normalize-space()='Filtering Attributes']/following-sibling::div//select");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");
    private By messageSave = By.xpath("//span[@data-notify='message']");

    private By firstItemSearchCategory = By.xpath("//tbody/tr[1]/td[2]");
    private By deleteCategory = By.xpath("//tbody/tr[1]/td[10]/a[2]");
    private By messageDelete = By.xpath("//h4[normalize-space()='Delete Confirmation']");
    private String textMessageDel = "Delete Confirmation";
    private By optionCancelDel = By.xpath("//button[normalize-space()='Cancel']");
    private By optionDelete = By.xpath("//a[@id='delete-link']");

    private By editCategory = By.xpath("//tbody/tr[1]/td[10]/a[1]");

    public void clickAddNewCategory(){
        WebUI.clickElement(buttonAddNewCategory);
    }

    public void clickCancelDelete(){
        WebUI.clickElement(optionCancelDel);
    }

    public void clickDelete() {
        WebUI.clickElement(optionDelete);
    }

    public void inputDataNewCategory(String name, String parent, String ordering,String value, String title, String description, String text){
        WebUI.setTextElement(inputNameCategory, name);
        WebUI.clickElement(dropdownParentCategory);
        WebUI.setTextAndKey(inputParentCategory, parent, Keys.ENTER);
        WebUI.setTextElement(inputOrderingCategory, ordering);
        WebUI.select(dropdownTypeCategory, "value", value);
        WebUI.clickElement(addFileBannerCategory);
        WebUI.setTextElement(searchFile, nameImage);
        WebUI.sleep(2);
        WebUI.clickElement(fileImage);
        WebUI.clickElement(buttonAddFile);
        WebUI.setTextElement(inputMetaTitle, title);
        WebUI.setTextElement(inputMetaDescription, description);
        WebUI.select(dropdownFilterCategory, "text", text);
    }

    public void saveCategory(){
        WebUI.clickElement(buttonSave);
    }

    public void verifyCategoryPage(){
        WebUI.checkElementDisplay(headerCategory);
        WebUI.assertEquals(WebUI.getWebElement(headerCategory).getText(), textHeaderCategory, "Title Category Page Incorrect.");
    }

    public void verifySaveCategory(){
        WebUI.checkElementDisplay(messageSave);
        WebUI.assertEquals(WebUI.getWebElement(messageSave).getText(), "Category has been inserted successfully", "Message save incorrect");
    }

    public void verifySearchCategory(String nameCategory){
        WebUI.setTextAndKey(inputSearchCategory, nameCategory, Keys.ENTER);
        WebUI.checkElementDisplay(firstItemSearchCategory);
        WebUI.assertEquals(WebUI.getWebElement(firstItemSearchCategory).getText(), nameCategory, "Can't Find Category");
    }

    public void verifyDataAfterAdd(String categoryname, String parent, String ordering,String type, String title, String description, String text) {
        SoftAssert softAssert = new SoftAssert();
        WebUI.clickElement(editCategory);
        WebUI.waitForPageLoaded();
        softAssert.assertTrue((WebUI.getDriver().getCurrentUrl()).contains("edit"), "URL not contains keyword edit.");
        softAssert.assertEquals(WebUI.getAttributeElement(inputNameCategory, "value"), categoryname, "Company Name incorrect");
        softAssert.assertEquals(WebUI.getTextElement(dropdownParentCategory), parent, "parent incorrect");
        softAssert.assertEquals(WebUI.getAttributeElement(inputOrderingCategory, "value"), ordering, "ordering incorrect");
        softAssert.assertEquals((WebUI.getFirstSelectOption(dropdownTypeCategory)), type, "type incorrect");
        softAssert.assertEquals(WebUI.getAttributeElement(inputMetaTitle, "value"), title, "title incorrect");
        softAssert.assertEquals(WebUI.getAttributeElement(inputMetaDescription, "value"), description, "value incorrect");
        softAssert.assertEquals(WebUI.getFirstSelectOption(dropdownFilterCategory), text, "value incorrect");
        softAssert.assertAll();
    }

    public void deleteAndVerifyCategory(){
        WebUI.clickElement(deleteCategory);
        WebUI.checkElementDisplay(messageDelete);
        WebUI.assertEquals(WebUI.getWebElement(messageDelete).getText(), textMessageDel, "Not message delete");
    }

    public void verifyCategoryAfterDelete(String nameCategory){
        WebUI.setTextAndKey(inputSearchCategory, nameCategory, Keys.ENTER);
        WebUI.checkElementDisplay(firstItemSearchCategory);
        Assert.assertNotEquals(WebUI.getWebElement(firstItemSearchCategory).getText(), nameCategory, "Category has not been Delete");
    }

    public void checkNumberItemSearch(){

    }
}
