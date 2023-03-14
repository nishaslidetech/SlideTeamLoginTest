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

public class Normal_paid_login extends BaseClass {

	@Given("User is home page_vi$")
	public void user_is_home_page_vi() throws InterruptedException {

		driver.get(AppURL);
		log.info("It's opening the website URL");
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(3000);
	}

	@Then("user click on the Popular PPTs_vi$")
	public void user_click_on_the_popular_PPts_vi() {
		WebElement popular_Ppts = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Popular PPTs")));
		popular_Ppts.click();
	}

	@Then("User try to download the ppt_vi$")
	public void user_try_to_download_the_ppt_vi() throws InterruptedException {
		try {
			WebElement select_Ppt = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[4]/div[1]/ol[1]/li[1]/div[1]/a[1]/img[1]")));
			js.executeScript("arguments[0].scrollIntoView();", select_Ppt);
			select_Ppt.click();

			WebElement download_btn_pdp_fp = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[normalize-space()='Download this presentation']")));
			js.executeScript("arguments[0].scrollIntoView();", download_btn_pdp_fp);
			download_btn_pdp_fp.click();
			Thread.sleep(3000);
			/*
			 * WebElement registeredUsers = wait
			 * .until(ExpectedConditions.elementToBeClickable(By.linkText("Registered Users"
			 * )));
			 * 
			 * js.executeScript("arguments[0].click();", registeredUsers);
			 * Thread.sleep(2000);
			 */
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user is on Login page_vi$")
	public void user_is_on_login_page_vi() {
		log.info("Hi! I am on Home page");
	}

	@Then("user enters the username and password_vi$")
	public void user_enters_the_username_and_password_vi() throws Throwable {
		try {
			WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));
			email.sendKeys("sumit.kumar@slidetech.in");

			WebElement password_field = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//fieldset[@class='fieldset login']//input[@id='pass']")));
			password_field.sendKeys("redhat2090");
			Thread.sleep(2000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user clicks on Login button_vi$")
	public void user_clicks_on_login_button_vi() throws Throwable {
		try {
			WebElement login_btn = BaseClass.elementToBeClickable(By.xpath("//span[normalize-space()='Login']"));
			// js.executeScript("arguments[0].click();", login_btn );
			login_btn.click();
			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//div[@class=' login-attempt-popup']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				Thread.sleep(3000);
				approve.click();
				driver.get(
						"https://www.slideteam.net/business-puzzle-list-diagarm-free-powerpoint-templates-ppt-presentation-slides-0812.html");
				Thread.sleep(3000);
			}
			Thread.sleep(5000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user downloads the PPT_vi$")
	public void user_downloads_the_ppt_vi() throws Throwable {
		

		WebElement download_Ppt = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='clicking']")));
		// js.executeScript("arguments[0].scrollIntoView();", download_Ppt);
		download_Ppt.click();
		if (!driver.findElements(By.xpath("//a[@class='mfp-close roundlink']")).isEmpty()) {
			WebElement close_popup = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='mfp-close roundlink']")));

			close_popup.click();
		}

	}

	@Then("user clicks on the Logout page_vi$")
	public void user_clicks_on_the_logout_page() throws InterruptedException {
		try {
			Thread.sleep(3000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			sign_Out.click();
		} catch (NoSuchElementException e) {

		}
	}

	@Then("verify the message_vi$")
	public void verify_the_message_vi() throws InterruptedException {
		Thread.sleep(3000);
		String verifySignOutMessage = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']"))).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains("YOU ARE NOW LOGGED OUT"));
	}

}
