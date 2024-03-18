package keywords;

import drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.LogUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {
    private static int TIMEOUT = 10;
    private static double STEP_TIME = 0.5;
    private static int PAGE_LOAD_TIMEOUT = 20;

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);
        if (listElement.size() > 0) {
            LogUtils.info("Element: " + by + " EXIST.");
            return true;
        } else {
            LogUtils.error("Element: " + by + "  NOT EXIST.");
            return false;
        }
    }

    public static boolean checkElementDisplay(By by) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isDisplayed();
        return check;
    }

    public static boolean checkElementSelected(By by) {
        waitForElementVisible(by);
        boolean check = getWebElement(by).isSelected();
        if(check == true) {
            return true;
        }else {
            Assert.assertTrue(false, "Element not selected");
            return false;
        }
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        LogUtils.info("Open: " + url);
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        getWebElement(by).click();
        LogUtils.info("Click on Element: " + by);
    }

    public static void setTextElement(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text);
        LogUtils.info("Set text on Element: " + by + " - Text: " + text);
    }

    public static void clearAndSetTextElement(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        getWebElement(by).sendKeys(text);
        LogUtils.info("Set text on Element: " + by + " - Text: " + text);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        String text = getWebElement(by).getText();
        LogUtils.info("Text of Element: " + by + " - Is: " + text);
        return text;
    }

    public static String getAttributeElement(By by, String attribute){
        WebUI.getWebElement(by).getAttribute(attribute);
        String value = DriverManager.getDriver().findElement(by).getAttribute(attribute);
        LogUtils.info("Get attribute of element: " + by + " value is: " + value);
        return value;
    }

    public static void setTextAndKey(By by, String value, Keys key){
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        LogUtils.info("Set value: " + value + " and Key: " + key.name());
    }

    public static void setKey(By by, Keys key){
        waitForPageLoaded();
        getWebElement(by).sendKeys(key);
        LogUtils.info("Set Key: " + key);
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        LogUtils.info("Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        LogUtils.info("Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void select (By by, String type, String option){
        Select select = new Select(WebUI.getWebElement(by));
        switch (type){
            case "index":
                select.selectByIndex(Integer.parseInt(option));
                LogUtils.info("Select by Index (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
            case "value":
                select.selectByValue(option);
                LogUtils.info("Select by Value (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
            case "text":
                select.selectByVisibleText(option);
                LogUtils.info("Select by Visible Text (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
        }
    }

    public static String getFirstSelectOption(By by){
        Select select = new Select(WebUI.getWebElement(by));
        String string = select.getFirstSelectedOption().getText();
        LogUtils.info("Option Selected in Element: " + by + " Is:" + string);
        return string;
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogUtils.error("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public static void uploadFileWithRobot(String fileImage){
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        StringSelection str = new StringSelection(fileImage);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        WebUI.sleep(1);

        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.sleep(1);

        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

