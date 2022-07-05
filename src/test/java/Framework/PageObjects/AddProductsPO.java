package Framework.PageObjects;



import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;




public class AddProductsPO {

	private WebDriver driver;
	private WebDriverWait wait;

    public AddProductsPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
         wait=new WebDriverWait(driver,30);
    }

    @FindBy(xpath = "//input[@data-test='username']")
    private WebElement Username;

    @FindBy(xpath = "//input[@data-test='password']")
    private WebElement Password;

    @FindBy(xpath = "//input[@data-test='login-button']")
    private WebElement loginBtn;
    
    @FindBy(xpath="//select[@data-test='product_sort_container']")
    private WebElement sort;
    
   /* @FindBy(xpath="(//div[@class='inventory_item_price'])[1]")
    private WebElement checkSort;*/
    
    @FindBy(xpath="//div[@class='inventory_item_price']")
    private List<WebElement> prices;
    
    @FindBy(xpath="//option[@value='hilo']")
    
    private WebElement selectSort;
    
    @FindBy(id="add-to-cart-sauce-labs-fleece-jacket")
    private WebElement firstProduct;
    
    @FindBy(id="add-to-cart-sauce-labs-backpack")
    private WebElement secondProduct;
    
    @FindBy(id="add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement thirdProduct;
    
    @FindBy(id="shopping_cart_container")
    private WebElement cart;
    
    @FindBy(xpath="(//button[text()='Remove'])[2]")
    private WebElement removeBag;
    
    @FindBy(xpath="//button[@data-test='checkout']")
    private WebElement checkoutBtn;
    
    @FindBy(xpath="//input[@data-test='firstName']")
    private WebElement firstName;
    
    @FindBy(xpath="//input[@data-test='lastName']")
    private WebElement lastName;
    
    @FindBy(xpath="//input[@data-test='postalCode']")
    private WebElement postalCode;

    @FindBy(xpath="//input[@data-test='continue']")
    private WebElement continueBtn;
    
    @FindBy(xpath="//button[text()='Finish']")
    private WebElement finishBtn;
    
    @FindBy(xpath="//span[@class='shopping_cart_badge']")
    private WebElement cartCount;
    

    public void acceptData(String username,String password){
        Username.sendKeys(username);
        Password.sendKeys(password);
    }

    public void clickBtn(){
    	
        loginBtn.click();
    }
    
    public WebElement sort() {
    	
    	return sort;
    }
    
   /* public WebElement checkSort(){
    	return checkSort;
    }*/
    
    public List<WebElement> checkPrices(){
    	return prices;
    }
    
    public WebElement selectSort(){
    	wait.until(ExpectedConditions.elementToBeClickable(selectSort));
    	return selectSort;
    }
    
    public WebElement firstProduct(){
    	wait.until(ExpectedConditions.elementToBeClickable(firstProduct));
    	return firstProduct;
    }
    
    public WebElement secondProduct(){
    	return secondProduct;
    }
    
    public WebElement thirdProduct(){
    	return thirdProduct;
    }
    
    public WebElement cart(){
    	wait.until(ExpectedConditions.elementToBeClickable(cart));
    	return cart;
    }
    
    public WebElement removeBag(){
    	wait.until(ExpectedConditions.elementToBeClickable(removeBag));
    	return removeBag;
    }
    
    public WebElement checkoutBtn(){
    	return checkoutBtn;
    }
 
    public WebElement firstName(){
    	
    	return firstName;
    }
  
    public WebElement lastName(){
    	
    	return lastName;
    }
   
    public WebElement postalCode(){
    	return postalCode;
    }
    
    public WebElement continueBtn(){
    	wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
    	return continueBtn;
    }
    
    public WebElement finishBtn(){
    	
    	return finishBtn;
    }
    
    public WebElement cartCount() {
    	
    	return cartCount;
    }
    
    
}
