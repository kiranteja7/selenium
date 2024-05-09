package Framework.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartFunctionalityPO {

	 private WebDriver driver;
	    private WebDriverWait wait;

	    public CartFunctionalityPO(WebDriver driver){
	        this.driver=driver;
	        PageFactory.initElements(this.driver,this);
	        wait=new WebDriverWait(driver,20);
	    }
	    
	   @FindBy(xpath = "//div[text()='Sauce Labs Backpack']")
	   private WebElement bagPack;

	   @FindBy(xpath = "//div[text()='Sauce Labs Fleece Jacket']")
	    private WebElement Jacket;

	   @FindBy(xpath = "//div[text()='Sauce Labs Onesie']")
	   private WebElement Onesie;

	   @FindBy(xpath = "//button[text()='ADD TO CART']")
	   private WebElement AddToCartBtn;

	   @FindBy(xpath="//a[@class='shopping_cart_link fa-layers fa-fw']")
	   private WebElement cartBtn;

	   @FindBy(xpath = "//a[text()='Continue Shopping']")
	   private WebElement continueShoppingBtn;

	   @FindBy(xpath = "//a[text()='CHECKOUT']")
	   private WebElement checkOutBtn;

	   @FindBy(xpath = "//div[@class='inventory_item_name']")
	   private List<WebElement> cartItemName;

	   @FindBy(xpath = "//div[@class='inventory_item_price']")
	   private List<WebElement> cartItemPrice;

	   @FindBy(xpath = "//div[@class='inventory_item_name']")
	   private WebElement inventoryItemOne;

	  @FindBy(xpath = "//div[@class='inventory_item_price']")
	   private WebElement inventoryPriceOne;

	   @FindBy(xpath = "//div[@class='inventory_details_price']")
	   private WebElement priceFromIventory;

	   public WebElement BagPackItem(){
		   return bagPack;
	   }

	   public WebElement JacketItem(){
		return Jacket;
	   }

	   public WebElement OnesieItem(){
		return Onesie;
	   }

	   public WebElement addTocartButton(){
		   return AddToCartBtn;
	   }

	   public WebElement cartImageBtn(){
		return cartBtn;
	   }

	   public WebElement continueShoppingBtn(){
		   return continueShoppingBtn;
	   }

	   public WebElement CheckOutBtn(){
		   return checkOutBtn;
	   }

	   public List<WebElement> cartItemFromCart(){
		   return cartItemName;
	   }

	  public List<WebElement> cartPriceFromCart(){
		return cartItemPrice;
	  }

	  public WebElement priceFromInventory(){
		   return priceFromIventory;
	  }

	  public WebElement inventoryItemOne(){
		   return inventoryItemOne;
	  }

	  public WebElement inventoryPriceOne(){
		   return inventoryPriceOne;
	  }

}
