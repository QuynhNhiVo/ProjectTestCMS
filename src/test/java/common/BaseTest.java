package common;

import drivers.DriverManager;
import helpers.PropertiesHelpers;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;
import utils.LogUtils;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browserName){
        WebDriver driver = setupBrowser(browserName);
        DriverManager.setDriver(driver);
    }

    private WebDriver setupBrowser(String browserName){
        WebDriver driver;
        switch (browserName.trim().toLowerCase()){
            case "chrome":
                driver = initChromeDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                LogUtils.info("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        if(PropertiesHelpers.getValue("HEADLESS").equals("true")){
            LogUtils.info("Launching Chrome browser (Headless)...");
            options.addArguments("--headless");
        } else {
            LogUtils.info("Launching Chrome browser...");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        WebDriver driver;
        EdgeOptions options = new EdgeOptions();
        if(PropertiesHelpers.getValue("HEADLESS").equals("true")){
            LogUtils.info("Launching Edge browser (Headless)...");
            options.addArguments("--headless");
        }else {
        LogUtils.info("Launching Edge browser...");
        }
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeBrowser(){
        DriverManager.quit();
    }

}
