package com.swaroop.seleniumAutomationAssignmentMarch20;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SwaroopAutomationAssignment {
	
	WebDriver wd;
	Actions actions;
	WebDriverWait wdWait;
	
	@BeforeMethod
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\DESKTOP-UP3KE4H_E\\QA Testing\\Lib\\ChromeDriver99\\chromedriver.exe");
		wd = new ChromeDriver();
		wdWait = new WebDriverWait(wd, 20);
		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		wd.manage().window().maximize();
		actions = new Actions(wd);
	}
	
	@Test
	public void verifyOrderFunctionality() {
		
		WebElement emailInput = wd.findElement(By.id("email")); // Email input field element
		WebElement passwordInput = wd.findElement(By.id("passwd"));	//Password input field Element
		WebElement clickSubmit = wd.findElement(By.id("SubmitLogin")); // Submit button element
		emailInput.sendKeys("spider@emal.com"); //Entering the email address
		passwordInput.sendKeys("Peter123"); //Entering the password
		clickSubmit.click(); //Clicking submit (1)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".account"))); //Wait command until the account element window appears
		WebElement nameOFUser = wd.findElement(By.cssSelector(".account")); //Account name Element
		Assert.assertEquals(nameOFUser.getText(), "Peter Parker"); //Assert the name on the account to be the same as the customer name (2)
		WebElement clickWomenButton = wd.findElement(By.xpath("//a [text() = 'Women']")); // Element for selecting the option Women
		clickWomenButton.click(); //Click the Women element (3)
		WebElement imageToBeSelected = wd.findElement(By.xpath("//img [@title = 'Faded Short Sleeve T-shirts']")); //Element for the item to be selected
		actions.moveToElement(imageToBeSelected).perform(); //Hover the mouse over the element to get the quick view option
		WebElement quickViewButton = wd.findElement(By.cssSelector("div .product-image-container :nth-of-type(2)")); //Element for the quick View Button
		quickViewButton.click(); //Click the quick view button (4)
//		//wdWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(wd.findElement(By.xpath("//iframe[@class = 'fancybox-iframe']"))));
		wd.switchTo().frame(wd.findElement(By.xpath(("//iframe[@class = 'fancybox-iframe']")))); // Switching to the quick view frame
		WebElement increaseQuantityIcon = wd.findElement(By.cssSelector(".icon-plus"));//Element for the "++ symbol to increase quantity
		increaseQuantityIcon.click(); // Click the "+" button (5)
		WebElement selectSize = wd.findElement(By.cssSelector("#group_1")); //Element for the select dropdown
		Select select = new Select(selectSize); //Instantiating the select variable by passing the element for drop down 
		select.selectByVisibleText("L"); //Selecting the option for Large Size (6)
		WebElement addtoCartButton = wd.findElement(By.cssSelector(".buttons_bottom_block.no-print .exclusive")); //Element for the ad to cart button
		actions.click(addtoCartButton).perform(); // Clicking the add to cart button (7)
		wd.switchTo().defaultContent();// Switching back to the main page from the frame
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default.button.button-medium i.icon-chevron-right.right"))); //Wait until the Proceed to check out button is available
		WebElement successMessageElement = wd.findElement(By.tagName("h2")); //Element for the success message
		Assert.assertEquals(successMessageElement.getText(),"Product successfully added to your shopping cart"); //Getting the text from the confirmation message (8)
		WebElement verifyProductName = wd.findElement(By.xpath("//span[@class= 'product-name']")); //Element with Product name
		WebElement verifyQuantity = wd.findElement(By.cssSelector("#layer_cart_product_quantity")); //Element for the quantity
		Assert.assertEquals(verifyProductName.getText(), "Faded Short Sleeve T-shirts"); //Asserting text from the product name element (9)
		Assert.assertEquals(verifyQuantity.getText(), "2"); //Asserting text from the quantity element (9)
		WebElement totalAmountInitial = wd.findElement(By.cssSelector(".layer_cart_row .ajax_block_cart_total")); //Element for the price
		String intialAmount = totalAmountInitial.getText(); //saving the text from the price element in a string
		WebElement proceedtoCheckoutButton = wd.findElement(By.cssSelector("a.btn.btn-default.button.button-medium i.icon-chevron-right.right")); //Proceed to check out element
		actions.click(proceedtoCheckoutButton).perform(); //Clicking proceed to check out button (10)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right")));//Wait until the check out button is present on the next page
		WebElement totalPriceElementFinal = wd.findElement(By.cssSelector("#total_price")); //Element showing the total price
		Assert.assertEquals(totalPriceElementFinal.getText(),intialAmount); //Asserting the text for the final price (11)
		WebElement proceedTOCheckOutButtonOnSummary = wd.findElement(By.cssSelector("a.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right"));//Element for the proceed on check out button on summary page
		proceedTOCheckOutButtonOnSummary.click(); //Click proceed to check out (12)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right"))); //Wait until the proceed to check out is visible from the Address Page
		WebElement commentBoxInput = wd.findElement(By.cssSelector("textarea.form-control")); //Comment Box Element
		WebElement proceedtoCheckOutNextButtonInAddressPage = wd.findElement(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right")); //Proceed to check out Element in Address Page
		commentBoxInput.sendKeys("Hello Please send my order"); //Entering text in the comment box (13)
		proceedtoCheckOutNextButtonInAddressPage.click(); //Clicking the proceed to check out page (14)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right"))); //Waiting until the proceed to check out button is visible from the shipping page
		WebElement checkBox = wd.findElement(By.cssSelector("#cgv")); //Check Box Element
		WebElement finalCheckOutButton = wd.findElement(By.cssSelector("button.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right")); //Element for check out in the shipping page
		checkBox.click(); //Click the check box (15)
		finalCheckOutButton.click(); //Click the checkout button (16)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bankwire")));//Wait until the payment by bank element is clickable
		WebElement paymentMethodSelectionBankWire = wd.findElement(By.cssSelector(".bankwire")); //Element for Bank Wire Method of Payment
		paymentMethodSelectionBankWire.click(); //Clicking the Bank wire Method (15)
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right")));//Wait until the confirm order button is visible
		WebElement verifyPaymentMethod = wd.findElement(By.cssSelector("h3[class = 'page-subheading']")); //Element for verifying payment method
		WebElement orderConfirmButton = wd.findElement(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right")); //Element for I confirm my order button
		Assert.assertEquals(verifyPaymentMethod.getText(), "BANK-WIRE PAYMENT."); //Asserting the payment method (16)
		orderConfirmButton.click(); //Clicking the confirm order button (17)
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cheque-indent"))); //Wait unti the order confirmation page is visible
		WebElement verifyConfirmationMessage = wd.findElement(By.cssSelector(".cheque-indent")); //Element for the confirmation message
		Assert.assertEquals(verifyConfirmationMessage.getText(), "Your order on My Store is complete."); //Asserting the confirmation message (18)
		
	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
