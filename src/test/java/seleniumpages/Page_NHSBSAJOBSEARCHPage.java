package seleniumpages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import common.Page_BasePage;
import org.openqa.selenium.JavascriptExecutor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Page_NHSBSAJOBSEARCHPage extends Page_BasePage {

	By cookies = By.xpath("//button[text()='Accept analytics cookies']");

	By lnkGoToSearch = By.xpath("//*[text()='Go to search']");
	By searchTextBox = By.xpath("//input[@id='keyword']");
	By locationDrpdown = By.xpath("//input[@id='location']");

	By resultText = By.xpath("//h2[@data-test='search-result-query']");
	By selectvalue = By.xpath("//ul[@role='listbox']/li[1][contains(text(),'Sunderland')]");

	By dropdownDateElement=By.id("sort");

	By dateElementsXpath = By.xpath("//li[@data-test='search-result-closingDate']/strong");

	Page_Common common = new Page_Common();

	public void openNHSSBAPage() throws InterruptedException
	{
		common.navigateToPage("https://www.jobs.nhs.uk/candidate");

	    common.setImplicitWait(driver,10);

		driver.findElement(cookies).click();
	
	Thread.sleep(2000);
	
	}

	public void verifyHomePageTitle() {
		String title = driver.getTitle();

		System.out.println("title:  " + title);

		Assert.assertEquals(title,"NHS Jobs");
	}
	
	public void clickOnGoToSearchLink() throws InterruptedException
	
	{
		WebElement lnkGoToSearchElement = common.waitUntilElementIsClickable(driver, lnkGoToSearch, 10);

		lnkGoToSearchElement.click();
		//driver.findElement(lnkGoToSearch).click();
		
		//Thread.sleep(1000);
		
	}

	public void enterTextInSearchbox(String jobtitle) {

		driver.findElement(searchTextBox).sendKeys(jobtitle);

	}

	public void selectValueFromDropdown(String location) throws InterruptedException {

		driver.findElement(locationDrpdown).sendKeys(location);
		Thread.sleep(2000);
		driver.findElement(selectvalue).click();



	}

	public void clickOnSearch() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('#search').click();");
	}

	public void validateResults(String jobtitle,String location) {

		WebElement element =driver.findElement(resultText);

		System.out.println("RESULT TEXT is: "+ element.getText());;

// Get the text from the element
		String fullText = element.getText().trim();

// Define the partial text to validate
		String partialText = "jobs found for "+jobtitle+" within 5 miles of "+location+"";

// Validate if the full text contains the partial text
		if (fullText.contains(partialText)) {
			System.out.println("Partial text is present.");
			Assert.assertTrue(true);
		} else {
			System.out.println("Partial text is not present.");
			Assert.assertTrue(false);
		}

	}

	public void selectdateValueFromDropdown() throws InterruptedException {

// Locate the dropdown element
		WebElement dropdownElement = driver.findElement(dropdownDateElement);

		// Create a Select object
		Select dropdown = new Select(dropdownElement);

		// Select an option by visible text
		dropdown.selectByVisibleText("Date Posted (newest)");

	}

	public void validateResultBydate() throws InterruptedException {

		System.out.println("inside result by date");

		List<WebElement> dateElements = new ArrayList<>();

		List<LocalDate> dates = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Click on the first search result
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-test='search-result'][1]//a[@data-test='search-result-job-title']"))).click();

		// Wait for the date element to be present and add it to the list
		WebElement datePostedElement1 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='hide-mobile']//p[@id='date_posted']"))
		);
		dateElements.add(datePostedElement1);

		System.out.println("first text : "+ datePostedElement1.getText());

		LocalDate date1 = LocalDate.parse(datePostedElement1.getText(), formatter);
		dates.add(date1);

		// Navigate back to the search results page
		driver.navigate().back();

		// Wait until the second search result is clickable and click it
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-test='search-result'][2]//a[@data-test='search-result-job-title']"))).click();

		// Wait for the date element to be present and add it to the list
		WebElement datePostedElement2 = wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='hide-mobile']//p[@id='date_posted']"))
		);
		dateElements.add(datePostedElement2);
		System.out.println("second text : "+ datePostedElement2.getText());

		LocalDate date2 = LocalDate.parse(datePostedElement2.getText(), formatter);
		dates.add(date2);

		// Validate the dates are in descending order
		boolean isSortedDescending = true;
		for (int i = 0; i < dates.size() - 1; i++) {
			if (dates.get(i).isBefore(dates.get(i + 1))) {
				isSortedDescending = false;
				break;
			}
		}

		if (isSortedDescending) {
			System.out.println("Dates are sorted in descending order.");
			Assert.assertTrue(true);
		} else {
			System.out.println("Dates are NOT sorted in descending order.");
			Assert.assertTrue(false);
		}


	}




	
}
