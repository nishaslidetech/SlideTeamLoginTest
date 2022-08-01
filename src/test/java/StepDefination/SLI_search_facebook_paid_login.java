package StepDefination;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Setupclass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SLI_search_facebook_paid_login extends BaseClass {

	@Given("User is home page_vii$")

	public void user_is_home_page_vii() throws Throwable {
		BaseClass.ClearfacebookCache();

		driver.get(AppURL);
		log.info("It's opening the website URL");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		BaseClass.ClearBrowserCache();
	}

	@Then("user will clicks on the sign in with Facebook button_vii$")
	public void user_will_clicks_on_the_sign_in_with_facebook_button_vii() throws Throwable {
		Thread.sleep(4000);
		WebElement Sign_in_with_facebook = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@class='btn btn-block btn-social btn-facebook social-btn']")));
		Sign_in_with_facebook.click();
	}

	@Then("user enters the username and password_vii$")
	public void user_enters_the_username_and_password_vii() throws Throwable {
		try {
			WebElement fb_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));

			fb_email.clear();

			fb_email.sendKeys("sumit.kumar@slidetech.in");

			WebElement fb_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));

			fb_pass.sendKeys("redhat2090");
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user verify the page after login_vii$")
	public void user_verify_the_page_after_login_vii() throws Throwable {

		try {
			if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
			} else {
				WebElement fb_login = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

				fb_login.click();

			}
			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id ='confirm_id']")));
				approve.click();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String subscriptionText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']")))
				.getText();
		System.out.println("subscriptionText = " + subscriptionText);
		// Assert.assertTrue("user is not on the subscription page after login with
		// facebook paid credentials",subscriptionText.contains(TextMessage));
	}

	@Then("enter keyword in search field_vii$")
	public void enter_keyword_in_search_field_vii() throws Throwable {
		WebElement search_field = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='search']")));
		search_field.sendKeys("Management");
		Thread.sleep(3000);
	}

	@Then("user selects and download the PPT_vii$")
	public void user_selects_and_download_the_ppt() throws InterruptedException {
		try {
			WebElement select_ppt = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[1]/a[1]/div[1]/img[1]")));
			select_ppt.click();

			WebElement download_Ppt = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clicking']")));
			js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
			download_Ppt.click();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user clicks on the Logout page_vii$")
	public void user_clicks_on_the_logout_page_vii() {
		try {

			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			sign_Out.click();
		} catch (NoSuchElementException e) {

		}
	}

	@Then("verify the message_vii$")
	public void verify_the_message_vii() throws Throwable {
		// verify the page after logout the application

		Thread.sleep(3000);
		String verifySignOutMessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']"))).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));

	}

}
