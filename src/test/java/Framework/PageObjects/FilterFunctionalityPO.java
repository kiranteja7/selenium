package Framework.PageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilterFunctionalityPO {

	 private WebDriver driver;
	    private WebDriverWait wait;

	    public FilterFunctionalityPO(WebDriver driver){
	        this.driver=driver;
	        PageFactory.initElements(this.driver,this);
	        wait=new WebDriverWait(driver,20);
	    }
	    
	    @FindBy(xpath= "//select[@class='product_sort_container']")
	    private WebElement FilterBtnClick;
	    
	    @FindBy(xpath= "//option[@value='az']")
	    private WebElement nameAtoZ;
	    
	    @FindBy(xpath= "//option[@value='za']")
	    private WebElement nameZtoA;
	    
	    @FindBy(xpath= "//option[@value='lohi']")
	    private WebElement priceHighToLow;
	    
	    @FindBy(xpath= "//option[@value='hilo']")
	    private WebElement priceLowtoHigh;
	    
	    @FindBy(xpath= "//div[@class='inventory_item_name']")
	    private List<WebElement> names;
	    
	    @FindBy(xpath= "//div[@class='inventory_item_price']")
	    private List<WebElement> prices;
	    
	    public WebElement returnFilterBtnClick(){
	    	return FilterBtnClick;
	    }
	    
	    public WebElement returnazOption(){
	    	return nameAtoZ;
	    }
	    
	    public WebElement returnzaOption(){
	    	return nameZtoA;
	    }
	    
	    public WebElement returnHightoLowOption(){
	    	return priceHighToLow;
	    }
	    
	    public WebElement returnLowtoHighOption(){
	    	return priceLowtoHigh;
	    }
	    
	    public List<String> returnnames(){
	    	List<String> namesList = new ArrayList<>();
	    	for(int i=0; i< names.size(); i++) {
	    		namesList.add(names.get(i).getText());
	    	}
	    	return namesList;
	    }
	    
	    public List<String> returnprices(){
	    	List<String> priceList = new ArrayList<>();
	    	for(int i=0; i< prices.size(); i++) {
	    		priceList.add(prices.get(i).getText());
	    	}
	    	return priceList;
	    }
}
