package Framework.PageObjects;



import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddProductsPO {

	private WebDriver driver;
	private WebDriverWait wait;

    public AddProductsPO(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
         wait=new WebDriverWait(driver,30);
    }

	
	@FindBy(xpath= "//button[text()='ADD TO CART']")
	private List<WebElement> addToCart;
    
	@FindBy(xpath= "//button[@class='btn_secondary btn_inventory']")
	private List<WebElement> removeFromCart;
    
	@FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
	private WebElement cartCount;
	
	
	public List<WebElement> addToCartBtn(){
		return addToCart;
	}
	
	public List<WebElement> removeFromCartBtn(){
		return removeFromCart;
	}
	
	public WebElement cartCount(){
		return cartCount;
	}
}
