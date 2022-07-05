package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPO {

    private WebDriver driver;

    public LoginPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
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
