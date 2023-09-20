package com.qa.automation.stepdefs;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class StepDefs {
	WebDriver driver;
	String baseUrl="https://www.amazon.in/";
	int implicitywaitsec = 20;
	
			

@Given("User opened browser")
public void user_opened_browser() {

	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\chromedriver-win64\\chromedriver.exe");
ChromeOptions opt = new  ChromeOptions();
opt.setBinary("C:\\Users\\Admin\\eclipse-workspace\\chrome-win64\\chrome.exe");
 driver= new ChromeDriver(opt);
driver.manage().window().maximize();
driver.manage().deleteAllCookies();
driver.manage().timeouts().implicitlyWait(implicitywaitsec,TimeUnit.SECONDS);

	}


@Given("User navigated to the home application url")
public void user_navigated_to_the_home_application_url() {
	driver.get(baseUrl);
	String expectedTile ="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	String actualTitle =driver.getTitle();
	Assert.assertEquals("The Title of homepage is not matched",expectedTile, actualTitle);
	
	
}
@When("User Search for product {string}")
public void user_search_for_product(String ProductName) {
	//input(@id='twotabsearchtextbox')
		//input(@id='nav-search-submit-button')
		WebDriverWait wait= new WebDriverWait(driver,15);
		
		WebElement searchboxEle=driver.findElement(By.xpath("//input(@id='twotabsearchtextbox'"));
	 wait.until(ExpectedConditions.elementToBeClickable(searchboxEle));
	 searchboxEle.sendKeys(ProductName);
	 
	 WebElement searchbtnEle=driver.findElement(By.xpath("//input(@id='nav-search-submit-button'")); 
	 searchbtnEle.click();
}
@Then("Search Result page is displayed with page title keyword contains {string}")
public void search_result_page_is_displayed_with_page_title_keyword_contains(String titleproductkeywords) {
	String expectedTile="Amazon.in : "+titleproductkeywords;
	WebDriverWait wait= new WebDriverWait(driver,15);
	wait.until(ExpectedConditions.titleContains(baseUrl));
   Assert.assertEquals(expectedTile, driver.getTitle());
   driver.quit();
}


@Then("Search Result page is displayed")
public void search_result_page_is_displayed() {
}
}




