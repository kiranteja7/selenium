package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPO {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
        wait=new WebDriverWait(driver,20);
    }

    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement Username;

    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement Password;

    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginBtn;

    @FindBy(xpath="//div[@class='bm-burger-button']")
    private WebElement hamBtn;
    
    @FindBy(id="logout_sidebar_link")
    private WebElement logoutBtn;
    

    public void acceptData(String username,String password){
        Username.sendKeys(username);
        Password.sendKeys(password);
    }

    public void clickBtn(){
    	wait.until(ExpectedConditions.elementToBeClickable(loginBtn));
        loginBtn.click();
    }
    
    public String clickBtnText(){
       String loginTxt= loginBtn.getText();
        return loginTxt;
    }
    
    public void logoutBtn() {
    	hamBtn.click();
    	logoutBtn.click();
    }
}
