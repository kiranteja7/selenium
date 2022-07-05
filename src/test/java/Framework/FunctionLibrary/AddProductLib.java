package Framework.FunctionLibrary;



import java.util.ArrayList;

import java.util.Collections;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import Framework.PageObjects.AddProductsPO;

public class AddProductLib {
	
	private WebDriver driver;

	public AddProductLib(WebDriver driver) {
	this.driver=driver;
	}
	
	
	public void accessData(String username,String password) {
		AddProductsPO add=new AddProductsPO(driver);
		add.acceptData(username, password);
		add.clickBtn();
	}
	
	public void clickSort() {
		AddProductsPO add=new AddProductsPO(driver);	
		add.sort().click();
    }
	
	public void clickselectSort() {
		AddProductsPO add=new AddProductsPO(driver);
		add.selectSort().click();
    }
	
	
	
	public List<String> beforeSortPrices(){
		AddProductsPO add=new AddProductsPO(driver);
		List<String> prodNames = new ArrayList<String>();
		List<WebElement> oSearchResults = add.checkPrices();
		for(WebElement oElem: oSearchResults) {
			String strProdName = oElem.getText();
			prodNames.add(strProdName);
		}
		return prodNames;
	}
	
	public List<String> checkSortedPrices(){
		AddProductsPO add=new AddProductsPO(driver);
		String strSysProdPrice="";
		   List<String> prodPrices = new ArrayList<String>();
		   List<Double> priceValue= new ArrayList<Double>();
	       List<WebElement> elements=add.checkPrices();
	       for(WebElement oElem: elements) {
	   	   
			    strSysProdPrice = oElem.getText().substring(0,1);
			    String prices= oElem.getText().substring(1);
			   Double price= Double.parseDouble(prices);
				priceValue.add(price);
				Collections.sort(priceValue,Collections.reverseOrder());
				 
				
			}
	       for(Double priceString: priceValue) {
	    	   String convStringPrice=priceString.toString();
	    	   String total= strSysProdPrice+convStringPrice;
	    	   prodPrices.add(total);
	       }
	       return prodPrices;
	     
	      }
	
	
	
	public void addProductsToCart() {
		AddProductsPO add=new AddProductsPO(driver);
		add.firstProduct().click();
		add.secondProduct().click();
		add.thirdProduct().click();
	}
	
	public void clickOnCart() {
		AddProductsPO add=new AddProductsPO(driver);
		add.cart().click();
	}
	
	public void removeProductFromCart() {
		AddProductsPO add=new AddProductsPO(driver);
		add.removeBag().click();
	}
	
	public void clickCheckOut() {
		AddProductsPO add=new AddProductsPO(driver);
		add.checkoutBtn().click();
	}
	
	public void addDetails(String f_name,String l_name,String pos_code) {
		AddProductsPO add=new AddProductsPO(driver);
		add.firstName().sendKeys(f_name);
		add.lastName().sendKeys(l_name);
		add.postalCode().sendKeys(pos_code);
	}
	
	public void continueBtn() {
		AddProductsPO add=new AddProductsPO(driver);
		add.continueBtn().click();
	}
	
	public void clickFinishBtn() {
		AddProductsPO add=new AddProductsPO(driver);
		add.finishBtn().click();
	}
	
	public String checkCartNumber() {
		AddProductsPO add=new AddProductsPO(driver);
		String count=add.cartCount().getText();
		return count;
	}
	
}
