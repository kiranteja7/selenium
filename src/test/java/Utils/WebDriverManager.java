package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class WebDriverManager {

    private WebDriver driver;

    public WebDriver launchBrowser(String browserName) {

        if(browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
            driver= new EdgeDriver();
        }
        else if(browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        }

        else
            Assert.fail("invalid browser name");

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
