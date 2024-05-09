package Framework.FunctionLibrary;



import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Framework.PageObjects.AddProductsPO;

public class AddProductLib {
	
	private WebDriver driver;

	public AddProductLib(WebDriver driver) {
	this.driver=driver;
	}
	
	public void addingItemsAndVerify() throws InterruptedException {
		AddProductsPO addProduct = new AddProductsPO(driver);
		
		System.out.println("TOTAL NO OF ITEMS ARE : "+addProduct.addToCartBtn().size());
		
		// Calculate the total number of elements to iterate over
		int total = addProduct.addToCartBtn().size();

		// Loop from the last element to the second element
		for(int i = total - 1; i >= 0; i--) { // Corrected loop condition

		    // Click on the "Add to Cart" button
		    addProduct.addToCartBtn().get(i).click();
		    Thread.sleep(3000);

		    // Print the index of the product being added
		    System.out.println("ADDED THE " + (i + 1) + "th product to the cart");

		    // Verify the presence of the "Remove" button for the added product
		    String removeText = addProduct.removeFromCartBtn().get(total - i - 1).getText(); 
		    System.out.println(removeText + " button is present on the " + (i + 1) + "rd item"); 

		    // Verify the cart count
		    int removeBtnSize = addProduct.removeFromCartBtn().size();
		    int cartSize = Integer.parseInt(addProduct.cartCount().getText());
		    
		    // Compare the sizes and print the result
		    if (removeBtnSize == cartSize) {
		        System.out.println(cartSize + " items are present in the cart and is valid");
		    } else {
		        System.out.println(cartSize + " items are present in the cart and is not valid");
		    }
		}

		// Loop to remove all items from the cart
		for (int i = 0; i < total-1; i++) { // Loop through all items except the first one

		    // Click on the "Remove from Cart" button
		    addProduct.removeFromCartBtn().get(0).click();
		    Thread.sleep(3000);

		    // Get the updated sizes
		    int removeBtnSize = addProduct.removeFromCartBtn().size();
		    int cartSize = Integer.parseInt(addProduct.cartCount().getText());

		    // Verify the cart size after removing an item
		    if (removeBtnSize == cartSize) {
		        System.out.println("Remaining cart Items are " + cartSize+ " is valid");
		    } else {
		        System.out.println("Invalid cart item size " + cartSize+ " is invalid");
		    }
		}

	}
}
