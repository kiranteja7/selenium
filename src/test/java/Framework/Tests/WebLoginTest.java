package Framework.Tests;

import Framework.FunctionLibrary.LoginLib

;
import Utils.WebDriverManager;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class WebLoginTest {

    private WebDriverManager webDvrMgr;
    private WebDriver driver;
    public ExtentHtmlReporter htmlReport;
    public ExtentTest test;
    public ExtentReports extentReports;

    private SoftAssert sf;
    String propFile="src/main/resources/base.prop";
    String url=Utils.DataProvider.getTestData(propFile,"url");
    String username=Utils.DataProvider.getTestData(propFile,"username");
    String password=Utils.DataProvider.getTestData(propFile,"password");
    String expected=Utils.DataProvider.getTestData(propFile,"expected");
    
 @BeforeTest
public void beforeSuite() {
    	 htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport1.html");
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
    webDvrMgr = new WebDriverManager();
    driver = webDvrMgr.launchBrowser("chrome");
    driver.get(url);
   }

   @BeforeMethod
   public void beforeMethod() {
       sf=new SoftAssert();
   }

   @Test
    public void loginTest() throws InterruptedException{
	   test= extentReports.createTest("loginTest");
       LoginLib loginLib=new LoginLib(driver);
       loginLib.enterData(username,password);
       Thread.sleep(2000);
       loginLib.buttonClick();
       loginLib.logoutBtn();
       String btnText=loginLib.loginButtonTxt().toLowerCase();
       sf.assertEquals(btnText,expected);
       Reporter.log("logout succesfully done!!!");
       driver.close();
   }
   
   @AfterMethod
   public void getResult(ITestResult result) throws IOException {
   	if(result.getStatus()==ITestResult.FAILURE) {
   		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getName());
   		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getThrowable());
   		
   		String screenShot= WebLoginTest.getScreenshot(driver,result.getName());
   		
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
