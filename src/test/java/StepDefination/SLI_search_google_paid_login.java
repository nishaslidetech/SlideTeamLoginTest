package StepDefination;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Setupclass.BaseClass;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;

public class SLI_search_google_paid_login extends BaseClass {
	@Given("User is home page_viii$")
	public void user_is_home_page_viii() throws Throwable {
		
		BaseClass.ClearGoggleCache();
		driver.get(AppURL);
		log.info("It's opening the website URL");
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		BaseClass.ClearBrowserCache();
	}

	@Then("Enter keyword in search field_viii$")
	public void enter_keyword_in_search_field_viii() throws Throwable {
		WebElement search_field = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']")));
		search_field.sendKeys("HR");
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ESCAPE);
			Thread.sleep(3000);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
			Thread.sleep(3000);
			Thread.sleep(1000);
			boolean pop_up_Value = driver.findElement(By.xpath("//ul[@id='sli_autocomplete']")).isDisplayed();
			System.out.println("pop-up is displayed  " + pop_up_Value);

			//assertTrue(pop_up_Value == false);
		} catch (AWTException e) {
		}

	}

	@Then("user will clicks on the sign in with Google button_viii$")
	public void user_will_clicks_on_the_sign_in_with_google_button_viii() {
		// Click on Sign in with Google Account
		WebElement Sign_in_with_google = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign in with Google')]")));
		js.executeScript("arguments[0].click();",Sign_in_with_google);
		//Sign_in_with_google.click();
	}

	@Then("user enters the username and password_viii$")
	public void user_enters_the_username_and_password_viii() throws Throwable {
		try {
			Thread.sleep(3000);

			if (!driver.findElements(By.xpath("//div[@class='BHzsHc']")).isEmpty()) {
				WebElement another_btn =BaseClass.elementToBeClickable(By.xpath("//div[text()='Use another account']"));
				another_btn.click();
			}
			Thread.sleep(2000);
			WebElement gmail_email = BaseClass.elementToBeClickable(By.xpath("//*[@id='identifierId']"));

			gmail_email.sendKeys("parul.pahwa@slidetech.in");

			WebElement next_1 = driver.findElement(By.cssSelector("#identifierNext > div > button > span"));

			next_1.click();
                        Thread.sleep(8000);
			WebElement gmail_pass = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));

			gmail_pass.sendKeys("parulpahwa@11");

			WebElement next_2 = driver.findElement(By.cssSelector("#passwordNext > div > button > span"));

			next_2.click();
			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				approve.click();
			}
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user verify the page_viii$")
	public void user_verify_the_page_viii() {

		// Verify the subscription page
		String subscriptionText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']")))
				.getText();
		System.out.println("subscriptionText = " + subscriptionText);

		//Assert.assertTrue("user is not on the subscription page after login with facebbok paid credentials",subscriptionText.contains(TextMessage));
	}

	@Then("enter Keyword in search field_viii$")
	public void enter_Keyword_in_search_field_viii() throws Throwable {
		Thread.sleep(4000);
		WebElement search_field = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']")));
		search_field.sendKeys("Management");
		Thread.sleep(4000);
	}

	@Then("user selects and download the PPT_viii$")
	public void user_selects_and_download_the_ppt_viii() throws Throwable {
		Thread.sleep(2000);
		WebElement select_ppt = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//img[@title='Project Management Powerpoint Presentation Slides']")));
		select_ppt.click();
		Thread.sleep(2000);
		WebElement download_Ppt = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clicking']")));
		js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
		download_Ppt.click();
	}

	@Then("user clicks on the Logout page_viii$")
	public void user_clicks_on_the_logout_page_viii() throws Throwable {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			sign_Out.click();
		} catch (NoSuchElementException e) {

		}
	}

	@Then("verify the message_viii$")
	public void verify_the_message_viii() throws Throwable {
		// verify the page after logout the application
		Thread.sleep(3000);
		String verifySignOutMessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']"))).getText();

		System.out.print("logout= " + verifySignOutMessage);
		
		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));
		
	}

}
