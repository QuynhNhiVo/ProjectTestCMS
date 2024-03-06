package common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    public static WebDriver driver;

    @BeforeMethod
    @Parameters({"browser"})
    public void createBrowser(@Optional("chrome") String browserName){
        if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        }if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        }if (browserName.equals("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

}
