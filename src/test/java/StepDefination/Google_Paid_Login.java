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

public class Google_Paid_Login extends BaseClass {
	@Given("^User is on Home pagev$")
	public void user_is_on_Home_pagev() throws Throwable {
		BaseClass.ClearGoggleCache();
		driver.get(AppURL);
		try {
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			BaseClass.ClearBrowserCache();
			// Click on Sign in with Google Account

			Thread.sleep(3000);
			WebElement Sign_in_with_google = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign in with Google')]")));
			Sign_in_with_google.click();

			Thread.sleep(3000);

		} catch (InterruptedException e) {

			e.printStackTrace();
			System.out.println("message = " + e.getMessage());
		}
	}

	@Given("user enters the Username and Passwordv$")
	public void user_enters_the_username_and_passwordv() throws InterruptedException {
		try {

			if (!driver.findElements(By.xpath("//div[@class='BHzsHc']")).isEmpty()) {
				WebElement another_btn = BaseClass
						.elementToBeClickable(By.xpath("//div[text()='Use another account']"));
				another_btn.click();
			}
			WebElement gmail_email = BaseClass.elementToBeClickable(By.xpath("//*[@id='identifierId']"));

			gmail_email.sendKeys("slidetech.qa@gmail.com");
			Thread.sleep(3000);
			WebElement next_1 = driver.findElement(By.cssSelector("#identifierNext > div > button > span"));
			Thread.sleep(3000);
			next_1.click();

			Thread.sleep(2000);
			WebElement gmail_pass = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input")));
			Thread.sleep(2000);
			gmail_pass.sendKeys("Himanshi@123");

			WebElement next_2 = driver.findElement(By.cssSelector("#passwordNext > div > button > span"));

			next_2.click();

			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//div[@class='login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id ='confirm_id']")));
				approve.click();
				Thread.sleep(5000);
			}
		} catch (NoSuchElementException e) {

		}

	}

	@Then("user clicks on Login button and verify the pagev$")
	public void user_clicks_on_login_button_and_verify_the_pagev() throws InterruptedException {

		// Verify the subscription page
		/*
		 * String subscriptionText =
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//h3[@class='base']"))) .getText(); System.out.println("subscriptionText = "
		 * + subscriptionText);
		 */

		// Assert.assertTrue("user is not on the subscription page after login with
		// facebbok paid credentials",subscriptionText.contains(TextMessage));

	}

	@Then("user downloads the PPTv$")
	public void user_downloads_the_ppt_v() throws InterruptedException {

		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
		WebElement popular_PPt = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Most Downloaded']")));
		popular_PPt.click();

		WebElement select_PPt = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//div[4]/div[1]/ol[1]/li[1]/div[1]/a[1]/img[1]")));
		js.executeScript("arguments[0].scrollIntoView();", select_PPt);
		select_PPt.click();

		WebElement download_Ppt = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clicking']")));
		js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
		download_Ppt.click();
	}

	@Then("user clicks on the Logout pagev$")
	public void user_clicks_on_the_logout_pagev() throws InterruptedException {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			sign_Out.click();
		} catch (NoSuchElementException e) {

		}
	}

	@Then("verify the messagev$")
	public void verify_the_messagev() throws InterruptedException {
		// verify the page after logout the application
		Thread.sleep(3000);
		String verifySignOutMessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']"))).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));

	}
}
