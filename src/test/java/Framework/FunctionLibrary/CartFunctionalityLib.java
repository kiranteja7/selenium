package Framework.FunctionLibrary;


import Framework.PageObjects.AddProductsPO;
import Framework.PageObjects.CartFunctionalityPO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartFunctionalityLib {

	private WebDriver driver;

	WebDriverWait wait ;

	public CartFunctionalityLib(WebDriver driver) {
	this.driver=driver;
	 wait = new WebDriverWait(this.driver, 10);
	}
	

	public void verifyCartFunctionality(){

		CartFunctionalityPO cart = new CartFunctionalityPO(driver);

		mainFunctionality(cart, cart.BagPackItem(), 0);

		mainFunctionality(cart, cart.JacketItem(), 1);

		mainFunctionality(cart, cart.OnesieItem(), 2);

	}

	public void mainFunctionality(CartFunctionalityPO cart, WebElement ele, int number){

		wait.until(ExpectedConditions.elementToBeClickable(ele));
		// CLICK TO GO INSIDE THE ITEM
		String itemExpected = ele.getText();
		ele.click();

		String priceExpected = cart.priceFromInventory().getText();

		//ADDING ITEM TO THE CART
		wait.until(ExpectedConditions.elementToBeClickable(cart.addTocartButton()));
		cart.addTocartButton().click();


		//CLICKING ON THE CART
		wait.until(ExpectedConditions.elementToBeClickable(cart.cartImageBtn()));
		cart.cartImageBtn().click();

		//VALIDING THE PRICE AND ITEM MATCHING WITH THE INVENTORY
		String itemActual = cart.cartItemFromCart().get(number).getText();

		compareTwoItems(itemExpected, itemActual);

		String priceActual = cart.cartPriceFromCart().get(number).getText();
		compareTwoItems(priceExpected, priceActual);

		wait.until(ExpectedConditions.elementToBeClickable(cart.continueShoppingBtn()));
		cart.continueShoppingBtn().click();
	}

	public void compareTwoItems(String expected, String actual){
		 if(expected.contains("$")){
			 expected= expected.replace("$", "");
		 }
		boolean result = expected.equals(actual);

		if(result){
			System.out.println("Item/Price of cart "+actual+" is matching with the inventory of " +expected);
		}else{
			System.out.println("Item/Price of cart "+actual+" is not matching with the inventory of " +expected);
		}
	}
}
