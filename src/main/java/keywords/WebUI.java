package keywords;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebUI {
    private static WebDriver driver;

    private static int TIMEOUT = 10;
    private static double STEP_TIME = 0.5;
    private static int PAGE_LOAD_TIMEOUT = 20;

    public WebUI(WebDriver driver) {
        this.driver = driver;
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = driver.findElements(by);
        if (listElement.size() > 0) {
            System.out.println("Element: " + by + " EXIST.");
            return true;
        } else {
            System.out.println("Element: " + by + "  NOT EXIST.");
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
        return driver.findElement(by);
    }

    public static void logConsole(Object message) {
        System.out.println(message);
    }

    public static void openURL(String url) {
        driver.get(url);
        logConsole("Open: " + url);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void clickElement(By by) {
        waitForElementClickable(by);
        getWebElement(by).click();
        logConsole("Click on Element: " + by);
    }

    public static void setTextElement(By by, String text) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(text);
        logConsole("Set text on Element: " + by + " - Text: " + text);
    }

    public static String getTextElement(By by) {
        waitForElementVisible(by);
        String text = getWebElement(by).getText();
        logConsole("Text of Element: " + by + " - Is: " + text);
        return text;
    }

    public static String getAttributeElement(By by, String attribute){
        WebUI.getWebElement(by).getAttribute(attribute);
        String value = driver.findElement(by).getAttribute(attribute);
        logConsole("Get attribute of element: " + by + " value is: " + value);
        return value;
    }

    public static void setTextAndKey(By by, String value, Keys key){
        waitForPageLoaded();
        getWebElement(by).sendKeys(value, key);
        logConsole("Set value: " + value + " and Key: " + key);
    }

    public static void setKey(By by, Keys key){
        waitForPageLoaded();
        getWebElement(by).sendKeys(key);
        logConsole("Set Key: " + key);
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        System.out.println("Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static boolean verifyContains(String actual, String expected) {
        waitForPageLoaded();
        System.out.println("Verify contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        return check;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        System.out.println("Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static boolean verifyEquals(Object actual, Object expected) {
        waitForPageLoaded();
        System.out.println("Verify equals: " + actual + " and " + expected);
        boolean check = actual.equals(expected);
        return check;
    }

    public static void select (By by, String type, String option){
        Select select = new Select(WebUI.getWebElement(by));
        switch (type){
            case "index":
                select.selectByIndex(Integer.parseInt(option));
                System.out.println("Select by Index (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
            case "value":
                select.selectByValue(option);
                System.out.println("Select by Value (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
            case "text":
                select.selectByVisibleText(option);
                System.out.println("Select by Visible Text (" + option + ") : "+ select.getFirstSelectedOption().getText());
                break;
        }
    }

    public static String getFirstSelectOption(By by){
        Select select = new Select(WebUI.getWebElement(by));
        String string = select.getFirstSelectedOption().getText();
        System.out.println("Option Selected in Element: " + by + " Is:" + string);
        return string;
    }

    public static void waitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(getWebElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            logConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            logConsole("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
