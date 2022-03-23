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
		System.setProperty("webdriver.chrome.driver", "E:\\QA Testing\\Lib\\ChromeDriver99\\chromedriver.exe");
		wd = new ChromeDriver();
		wdWait = new WebDriverWait(wd, 20);
		wd.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		wd.manage().window().maximize();
		actions = new Actions(wd);
	}
	
	@Test
	public void verifyOrderFunctionality() {
		WebElement emailInput = wd.findElement(By.id("email"));
		WebElement passwordInput = wd.findElement(By.id("passwd"));	
		WebElement clickSubmit = wd.findElement(By.id("SubmitLogin"));
		emailInput.sendKeys("spider@emal.com");
		passwordInput.sendKeys("Peter123");
		clickSubmit.click();
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".account")));
		WebElement nameOFUser = wd.findElement(By.cssSelector(".account"));
		Assert.assertEquals(nameOFUser.getText(), "Peter Parker");
		WebElement clickWomenButton = wd.findElement(By.xpath("//a [text() = 'Women']"));
		clickWomenButton.click();
		WebElement hoverMouseOverImage = wd.findElement(By.xpath("//img [@title = 'Faded Short Sleeve T-shirts']"));
		actions.moveToElement(hoverMouseOverImage).perform();
		WebElement clickQuickView = wd.findElement(By.cssSelector("div .product-image-container :nth-of-type(2)"));
		clickQuickView.click();
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//wdWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(wd.findElement(By.xpath("//iframe[@class = 'fancybox-iframe']"))));
		wd.switchTo().frame(wd.findElement(By.xpath(("//iframe[@class = 'fancybox-iframe']"))));
		WebElement increaseQuaantity = wd.findElement(By.cssSelector(".icon-plus"));
		increaseQuaantity.click();
		WebElement selectSize = wd.findElement(By.cssSelector("#group_1"));
		Select select = new Select(selectSize);
		select.selectByVisibleText("L");
		WebElement clickAddtoCart = wd.findElement(By.cssSelector(".buttons_bottom_block.no-print .exclusive"));
		actions.click(clickAddtoCart).perform();
		//wdWait.until(ExpectedConditions.)
		wd.switchTo().defaultContent();
//		WebElement successMessageElement = wd.findElement(By.cssSelector("div [id = 'layer_cart'] "));
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.btn.btn-default.button.button-medium i.icon-chevron-right.right")));
		WebElement successMessageElement = wd.findElement(By.tagName("h2"));
//		WebElement successMessageElement = wd.findElement(By.cssSelector(".clearfix .icon-ok"));
		Assert.assertEquals(successMessageElement.getText(),"Product successfully added to your shopping cart");
		WebElement verifyProduct = wd.findElement(By.xpath("//span[@class= 'product-name']"));
//		WebElement verifyProduct = wd.findElement(By.cssSelector("#layer_cart_product_title"));
		WebElement verifyQuantity = wd.findElement(By.cssSelector("#layer_cart_product_quantity"));
		Assert.assertEquals(verifyProduct.getText(), "Faded Short Sleeve T-shirts");
		Assert.assertEquals(verifyQuantity.getText(), "2");
		WebElement totalAmountInitial = wd.findElement(By.cssSelector(".layer_cart_row .ajax_block_cart_total"));
		String intialAmount = totalAmountInitial.getText();
		WebElement proceedtoCheckout = wd.findElement(By.cssSelector("a.btn.btn-default.button.button-medium i.icon-chevron-right.right"));
		actions.click(proceedtoCheckout).perform();
		WebElement totalElementFinal = wd.findElement(By.cssSelector("#total_price"));
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right")));
		Assert.assertEquals(totalElementFinal.getText(),intialAmount);
		WebElement proceedTOCheckOutButton = wd.findElement(By.cssSelector("a.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right"));
		proceedTOCheckOutButton.click();
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right")));
		WebElement enterCommentBox = wd.findElement(By.cssSelector("textarea.form-control"));
		WebElement proceedtoCheckOutNextButton = wd.findElement(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right"));
		enterCommentBox.sendKeys("Hello Please send my order");
		proceedtoCheckOutNextButton.click();
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right")));
		WebElement clickCheckBox = wd.findElement(By.cssSelector("#cgv"));
		WebElement finalCheckOut = wd.findElement(By.cssSelector("button.button.btn.btn-default.standard-checkout.button-medium i.icon-chevron-right.right"));
		clickCheckBox.click();
		finalCheckOut.click();
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bankwire")));
		WebElement paymentMethodSelection = wd.findElement(By.cssSelector(".bankwire"));
		paymentMethodSelection.click();
		wdWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right")));
		WebElement verifyPaymentMethod = wd.findElement(By.cssSelector("h3[class = 'page-subheading']"));
		WebElement OrderConfirmButton = wd.findElement(By.cssSelector("button.button.btn.btn-default.button-medium i.icon-chevron-right.right"));
		Assert.assertEquals(verifyPaymentMethod.getText(), "BANK-WIRE PAYMENT.");
		OrderConfirmButton.click();
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cheque-indent")));
		WebElement verifyConfirmationMessage = wd.findElement(By.cssSelector(".cheque-indent"));
		Assert.assertEquals(verifyConfirmationMessage.getText(), "Your order on My Store is complete.");
		
	}
	
	@AfterMethod
	public void tearDown() {
		wd.quit();
	}

}
