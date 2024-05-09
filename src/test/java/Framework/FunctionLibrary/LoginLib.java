package Framework.FunctionLibrary;

import Framework.PageObjects.LoginPO;
import org.openqa.selenium.WebDriver;

public class LoginLib {

    private WebDriver driver;
    

    public LoginLib(WebDriver driver) {
        this.driver= driver;
    }

    public void enterData(String username,String password) {
    	LoginPO loginPO = new LoginPO(driver); 	
        loginPO.acceptData(username, password);
        
    }
    
    public void buttonClick() {
    	LoginPO loginPO = new LoginPO(driver);
    	loginPO.clickBtn();
        
    }
    
    public String loginButtonTxt() {
    	LoginPO loginPO = new LoginPO(driver);
    	String logTxt=loginPO.clickBtnText();
        return logTxt;
    }
    
    public void logoutBtn() {
    	LoginPO loginPO = new LoginPO(driver);
    	loginPO.logoutBtn();
    }
    
    public String returnErrorMsg() {
    	LoginPO loginPO = new LoginPO(driver);
    	return loginPO.returnErrorMessage();
    }

}
