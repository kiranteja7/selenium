package Framework.FunctionLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.WebDriver;

import Framework.PageObjects.FilterFunctionalityPO;

public class FilterFunctionalityLib {
	
	private WebDriver driver;

	public FilterFunctionalityLib(WebDriver driver) {
	this.driver=driver;
	}
	
	
	public boolean filterByNamesATOZ() throws InterruptedException {
		List<String> namesArray = new ArrayList<>();
		FilterFunctionalityPO filter = new FilterFunctionalityPO(driver);
		namesArray = filter.returnnames();
		List<String> sortedList = sorting(namesArray);
		Thread.sleep(2000);
		filter.returnFilterBtnClick().click();
		Thread.sleep(2000);
		filter.returnazOption().click();
		Thread.sleep(2000);
		namesArray = filter.returnnames();
		return compareTwoLists(namesArray,sortedList);
	}
	
	public boolean filterByNamesZTOA() throws InterruptedException {
		List<String> namesArray = new ArrayList<>();
		FilterFunctionalityPO filter = new FilterFunctionalityPO(driver);
		namesArray = filter.returnnames();
		List<String> sortedList = reverseSorting(namesArray);
		Thread.sleep(2000);
		filter.returnFilterBtnClick().click();
		Thread.sleep(2000);
		filter.returnzaOption().click();
		Thread.sleep(2000);
		namesArray = filter.returnnames();
		return compareTwoLists(namesArray,sortedList);
	}
	
	public boolean filterPriceFromHighToLow() throws InterruptedException {
		List<String> priceArray = new ArrayList<>();
		List<String> prices = new ArrayList<>();
		FilterFunctionalityPO filter = new FilterFunctionalityPO(driver);
		
		//GETTING PRICES FROM XPATH
		priceArray = filter.returnprices();
		//REMOVING DOLLAR TO SORT
		prices = removeDollar(priceArray);
		//SORTING THE PRICES
		List<String> sortedList = reverseNumericSorting(prices);
		Thread.sleep(2000);
		filter.returnFilterBtnClick().click();
		Thread.sleep(2000);
		filter.returnLowtoHighOption().click();
		Thread.sleep(2000);
		priceArray = filter.returnprices();
		prices = removeDollar(priceArray);
		return compareTwoLists(prices,sortedList);
	}
	
	public boolean filterPriceFromLowToHigh() throws InterruptedException{
		List<String> priceArray = new ArrayList<>();
		List<String> prices = new ArrayList<>();
		FilterFunctionalityPO filter = new FilterFunctionalityPO(driver);
		
		//GETTING PRICES FROM XPATH
		priceArray = filter.returnprices();
		//REMOVING DOLLAR TO SORT
		prices = removeDollar(priceArray);
		//SORTING THE PRICES
		List<String> sortedList = numericSorting(prices);
		Thread.sleep(2000);
		filter.returnFilterBtnClick().click();
		Thread.sleep(2000);
		filter.returnHightoLowOption().click();
		Thread.sleep(2000);
		priceArray = filter.returnprices();
		prices = removeDollar(priceArray);
		return compareTwoLists(prices,sortedList);
	}
	
	
	public List<String> sorting(List<String> elements) {
		  Collections.sort(elements);
		  return elements;
	}
	
	public List<String> reverseSorting(List<String> elements) {
		  Collections.sort(elements, Collections.reverseOrder());
		  return elements;
	}
	
	public List<String> numericSorting(List<String> elements) {
		List<Double> numericNumbers = new ArrayList<>();
        for (String number : elements) {
            numericNumbers.add(Double.parseDouble(number));
        }

        // Sorting the list of numbers
        Collections.sort(numericNumbers);
        
        // Converting sorted numbers back to strings
        List<String> sortedStrings = new ArrayList<>();
        for (Double number : numericNumbers) {
            sortedStrings.add(String.valueOf(number));
        }
		  return sortedStrings;
	}
	
	public List<String> reverseNumericSorting(List<String> elements) {
		List<Double> numericNumbers = new ArrayList<>();
        for (String number : elements) {
            numericNumbers.add(Double.parseDouble(number));
        }

        // Sorting the list of numbers
        Collections.sort(numericNumbers, Collections.reverseOrder());

        // Converting sorted numbers back to strings
        List<String> sortedStrings = new ArrayList<>();
        for (Double number : numericNumbers) {
            sortedStrings.add(String.valueOf(number));
        }
		  return sortedStrings;
	}
	
	public boolean compareTwoLists(List<String> Xpath, List<String> sortedList) {
		ArrayList<String> XpathText = new ArrayList<String>();
		for(int i=0; i<Xpath.size(); i++) {
			XpathText.add(Xpath.get(i));
		}
		System.out.println(XpathText);
		System.out.println(sortedList);
		return XpathText.equals(sortedList);
	 }
	
	public ArrayList<String> removeDollar(List<String> priceArray){
		ArrayList<String> removeDollarList = new ArrayList<String>();
		for(int i=0; i<priceArray.size(); i++) {
			removeDollarList.add(priceArray.get(i).replace("$", ""));
		}
		return removeDollarList;
	}
}
