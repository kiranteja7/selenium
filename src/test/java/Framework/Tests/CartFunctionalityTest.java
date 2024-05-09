package Framework.Tests;

import Framework.FunctionLibrary.CartFunctionalityLib;
import Framework.FunctionLibrary.LoginLib;
import Utils.WebDriverManage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CartFunctionalityTest {

    private WebDriverManage webDvrMgr;
    private WebDriver driver;
    public ExtentSparkReporter htmlReport;
    public ExtentTest test;
    public ExtentReports extentReports;

    public CartFunctionalityLib cart;

    private SoftAssert sf;
    String propFile="resources/base.prop";
    String url=Utils.DataProvider.getTestData(propFile,"url");
    String username=Utils.DataProvider.getTestData(propFile,"username");
    String password=Utils.DataProvider.getTestData(propFile,"password");

    
 @BeforeTest
public void beforeSuite() {
    	 htmlReport=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/myReport3.html");
    	htmlReport.config().setDocumentTitle("Automation Report1");
    	htmlReport.config().setReportName("functional Report1");
    	htmlReport.config().setTheme(Theme.DARK);
    	 extentReports=new ExtentReports();
    	extentReports.attachReporter(htmlReport);
    	extentReports.setSystemInfo("HostName", "LocalHost");
    	extentReports.setSystemInfo("OS", "Windows");
    	extentReports.setSystemInfo("TesterName", "Kiran");
    	extentReports.setSystemInfo("Browser", "Chrome");
    }

@BeforeClass
public  void beforeClass() {
    webDvrMgr = new WebDriverManage();
    driver = webDvrMgr.launchBrowser("chrome");
    driver.get(url);
   }
 

   @BeforeMethod
   public void beforeMethod() {
       sf=new SoftAssert();
       LoginLib loginLib=new LoginLib(driver);
       loginLib.enterData(username,password);    
       loginLib.buttonClick();
   }

   @Test
    public void cartFunctionality() throws InterruptedException{
	   test= extentReports.createTest("CartFunctionality");
       cart= new CartFunctionalityLib(driver);
	   cart.verifyCartFunctionality();
       driver.close();
   }

   
   @AfterMethod
   public void getResult(ITestResult result) throws IOException {
   	if(result.getStatus()==ITestResult.FAILURE) {
   		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getName());
   		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getThrowable());
   		
   		String screenShot= CartFunctionalityTest.getScreenshot(driver,result.getName());
   		
   		test.addScreenCaptureFromPath(screenShot);
   	}
   	else if(result.getStatus()==ITestResult.SKIP) {
   		test.log(Status.SKIP, "TEST CASE SKIPPED IS :" +result.getName());
   	}
   	else if(result.getStatus()==ITestResult.SUCCESS) {
   		test.log(Status.PASS, "TEST CASE SUCCESS IS :" +result.getName());
   	}
   }
   
   public static String getScreenshot(WebDriver driver,String Screenshot ) throws IOException {
   	String datename= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
   	TakesScreenshot ts=(TakesScreenshot) driver;
   	File source=ts.getScreenshotAs(OutputType.FILE);
   	
   	String destination=System.getProperty("user.dir")+"/Screenshots/"+ Screenshot + datename+ ".png";
   	File finalDestination=new File(destination);
   	FileUtils.copyFile(source, finalDestination);
   	return destination;
   }
   
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    @AfterSuite
    public void afterSuite() {
    	
    	extentReports.flush();
    }
}
