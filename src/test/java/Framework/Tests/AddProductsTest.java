package Framework.Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Framework.FunctionLibrary.AddProductLib;
import Utils.WebDriverManager;

public class AddProductsTest {

	private WebDriverManager webDvrMgr;
    private WebDriver driver;
    public ExtentHtmlReporter htmlReport;
    public ExtentTest test;
    public ExtentReports extentReports;
    String propFile="src/main/resources/base.prop";
    String productDetails="src/main/resources/ProductDetails";
    String url=Utils.DataProvider.getTestData(propFile,"url");
    String username=Utils.DataProvider.getTestData(propFile,"username");
    String password=Utils.DataProvider.getTestData(propFile,"password");    
    String f_name=Utils.DataProvider.getTestData(productDetails,"f_name");
    String l_name=Utils.DataProvider.getTestData(productDetails,"l_name");
    String pos_code=Utils.DataProvider.getTestData(productDetails,"pos_code");
    String cartCount=Utils.DataProvider.getTestData(productDetails,"cartCount");
    private SoftAssert sf;
    
    @BeforeTest
    public void beforeSuite() {
    	 htmlReport=new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
    	htmlReport.config().setDocumentTitle("Automation Report");
    	htmlReport.config().setReportName("functional Report");
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
    public void addProductTest() throws InterruptedException {
    	
    	
    	 test= extentReports.createTest("addProductTest");
    	
    	AddProductLib add=new AddProductLib(driver);
    	//user enters the credentials enters the website
    	add.accessData(username, password);
    		
    	//Thread.sleep(3000);
    	
    	
    	//user clicks on the sort price from low to high and asserting the prices
    	
    	//Thread.sleep(3000);
    	add.clickSort();
    	add.clickselectSort();
    	
    	//Thread.sleep(3000);
    	List<String> priceSorting=add.beforeSortPrices();
    	List<String> afterPriceSort=add.checkSortedPrices();
    	
    	for(String price:priceSorting) {
    		for(String afterPrice:afterPriceSort) {
    			sf.assertEquals(price, afterPrice);
    		}
    	}
    	
    	//user adds 3 products into the cart
    	//Thread.sleep(2000);
    	
    	add.addProductsToCart();
    	//Thread.sleep(2000);
    	
    	
    	//user clicks on cart
    	add.clickOnCart();
    	//Asserting to remove the second highest price in the cart
    	String onCartBefSort=priceSorting.get(1);
    	String onCartAftSort=afterPriceSort.get(1);
    	sf.assertEquals(onCartBefSort, onCartAftSort);
    	
    	//Asserting the cart count before and after removing the items
    	String cartcnt=add.checkCartNumber();
    	sf.assertEquals(cartCount, cartcnt);
    	
    	//user removes the second item from the list
    	add.removeProductFromCart();
    	int count=Integer.parseInt(cartCount);
    	count=count--;
    	String countCart=String.valueOf(count);
    	sf.assertEquals(countCart, cartcnt);
    	
    	//Thread.sleep(3000);
    	//user clicks on checkout
    	add.clickCheckOut();
    	
    	//user enters the credentials and clicks on continue
    	add.addDetails(f_name,l_name,pos_code);
    	//Thread.sleep(2000);
    	add.continueBtn();
    	
    	//user clicks on finish button
    	//Thread.sleep(2000);
    	add.clickFinishBtn();   	
    	
    }
    
    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
    	if(result.getStatus()==ITestResult.FAILURE) {
    		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getName());
    		test.log(Status.FAIL, "TEST CASE FAILED IS :" +result.getThrowable());
    		
    		String screenShot= AddProductsTest.getScreenshot(driver,result.getName());
    		
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
