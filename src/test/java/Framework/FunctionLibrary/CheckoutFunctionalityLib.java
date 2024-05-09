package Framework.FunctionLibrary;
import Framework.PageObjects.CheckoutFunctionalityPO;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutFunctionalityLib {

	private WebDriver driver;

	WebDriverWait wait ;

	public CheckoutFunctionalityLib(WebDriver driver) {
	this.driver=driver;
	 wait = new WebDriverWait(this.driver, 10);
	}
	

	public void verifyCheckoutFunctionality(){

		CheckoutFunctionalityPO cart = new CheckoutFunctionalityPO(driver);

		boolean result= mainFunctionality(cart, cart.BagPackItem(), 0);

		if(result){
			String completeOrderMessage = cart.getCompleteOrder().getText();

			System.out.println(completeOrderMessage);
		}else{
			System.out.println("Order not successfully completed");
		}

	}

	public boolean mainFunctionality(CheckoutFunctionalityPO cart, WebElement ele, int number){

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


		wait.until(ExpectedConditions.elementToBeClickable(cart.CheckOutBtn()));
		cart.CheckOutBtn().click();

		//FILLING THE INPUTS FOR CHECKOUT
		wait.until(ExpectedConditions.elementToBeClickable(cart.getFirstNameInput()));
		cart.getLastNameInput().click();
		cart.getFirstNameInput().sendKeys("ABCD");

		wait.until(ExpectedConditions.elementToBeClickable(cart.getLastNameInput()));
		cart.getLastNameInput().click();
		cart.getLastNameInput().sendKeys("DEFG");

		wait.until(ExpectedConditions.elementToBeClickable(cart.getPostalCodeInput()));
		cart.getPostalCodeInput().click();
		cart.getPostalCodeInput().sendKeys("533101");

		//CLICK ON CONTINUE BUTTON
		wait.until(ExpectedConditions.elementToBeClickable(cart.getContinueBtn()));
		cart.getContinueBtn().click();

		//VALIDATING THE ITEM AND PRICE IN CHECKOUT AGAINST THE INVENTORY
		String itemActual = cart.cartItemFromCart().get(number).getText();

     	compareTwoItems(itemExpected, itemActual);

     	String priceActual = cart.cartPriceFromCart().get(number).getText();

    	compareTwoItems(priceExpected, priceActual);

		//CLICKING ON FINISH BUTTON
		wait.until(ExpectedConditions.elementToBeClickable(cart.finishBtn()));
		cart.finishBtn().click();

		return true;
	}

	public void compareTwoItems(String expected, String actual){
		 if(expected.contains("$") || actual.contains("$")){
			 expected= expected.replace("$", "");
			 actual= actual.replace("$", "");
		 }
		boolean result = expected.equals(actual);

		if(result){
			System.out.println("Item/Price of cart "+actual+" is matching with the inventory of " +expected);
		}else{
			System.out.println("Item/Price of cart "+actual+" is not matching with the inventory of " +expected);
		}
	}
}
