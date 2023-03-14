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

//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;

public class Facebook_existing_paid_login extends BaseClass {
	@Given("User is home pagei$")
	public void user_is_home_pagei() throws Throwable {
		BaseClass.ClearfacebookCache();
		driver.get(AppURL);
		log.info("It's opening the website URL");
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		BaseClass.ClearBrowserCache();
	}

	@Then("user clicks on the Popular PPTsi$")
	public void user_clicks_on_the_popular_pp_tsi() {

		WebElement popular_Ppts = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular PPTs")));
		popular_Ppts.click();
	}

	@Then("User try to Download the ppti$")
	public void user_try_to_Download_the_ppti() throws InterruptedException {

		try {
			Thread.sleep(2000);
			WebElement select_Ppt = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[4]/div[1]/ol[1]/li[2]/div[1]/a[1]/img[1]")));
			js.executeScript("arguments[0].scrollIntoView();", select_Ppt);
			select_Ppt.click();

			WebElement download_Ppt = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[normalize-space()='Download this presentation']")));
			js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
			download_Ppt.click();
			System.out.println("user is on Login page");
			Thread.sleep(2000);

			// PDP Login popup
			/*
			 * WebElement Sign_in_with_facebook=
			 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
			 * "//div[@class = 'social-buttons']//a[@class='btn btn-block btn-social btn-facebook social-btn']"
			 * ))); js.executeScript("arguments[0].click();", Sign_in_with_facebook);
			 * Thread.sleep(3000); System.out.println("facebook button clicked");
			 */

			WebElement Sign_in_with_facebook = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//a[@class='btn btn-block btn-social btn-facebook social-btn']")));
			js.executeScript("arguments[0].click();", Sign_in_with_facebook);
			Thread.sleep(3000);

		} catch (NoSuchElementException e) {

		}
	}

	@Then("user is on Login pagei$")
	public void user_is_on_login_pagei() throws InterruptedException {
		log.info("Hi! I am on Home page");
		System.out.println("Hi! I am on Home page");
	}

	@Then("user Enters the username and passwordi$")
	public void user_Enters_the_username_and_passwordi() throws Throwable {

		try {

			Thread.sleep(2000);
			WebElement fb_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));

			fb_email.clear();

			fb_email.sendKeys("sumit.kumar@slidetech.in");
			Thread.sleep(2000);
			WebElement fb_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));

			fb_pass.sendKeys("redhat2090");

		} catch (NoSuchElementException e) {

		}

	}

	@Then("user clicks on Login buttoni$")
	public void user_clicks_on_Login_buttoni() throws Throwable {

		try {
			if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
			} else {
				WebElement fb_login = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

				fb_login.click();
				Thread.sleep(3000);
			}
			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//div[@class=' login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				Thread.sleep(3000);
				approve.click();
				
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("user downloads the PPTi$")
	public void user_downloads_the_ppti() throws Throwable {

		driver.navigate().refresh();
		Thread.sleep(5000);
		WebElement download_Ppt = BaseClass.elementToBeClickable(By.xpath("//button[@id='clicking']"));
		js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
		download_Ppt.click();

		if (!driver.findElements(By.xpath("//a[@class='mfp-close roundlink']")).isEmpty()) {
			WebElement close_popup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='mfp-close roundlink']")));

			close_popup.click();
		}
	}

	@Then("user clicks on the Logout pagei$")
	public void user_clicks_on_the_logout_pagei() throws InterruptedException {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			js.executeScript("arguments[0].click();", sign_Out);
		} catch (NoSuchElementException e) {

		}

	}

	@Then("verify the Signout Messagei$")
	public void verify_the_Signout_Messagei() throws Throwable {

		Thread.sleep(3000);
		String verifySignOutMessage = BaseClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains("YOU ARE NOW LOGGED OUT"));

	}

}
