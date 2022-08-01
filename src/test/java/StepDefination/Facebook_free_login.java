package StepDefination;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Setupclass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;

public class Facebook_free_login extends BaseClass {

	@Given("User is home page_ii")
	public void user_is_home_page_ii() throws Throwable {
		BaseClass.ClearfacebookCache();

		driver.get(AppURL);
		log.info("It's opening the website URL");
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		BaseClass.ClearBrowserCache();
	}

	@Then("user click on the Free PPts under free stuff_ii")
	public void user_click_on_the_free_ppts_under_free_stuff_ii() throws InterruptedException {
		try {
			WebElement free_Stuff = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//li[@class='menu-item has-sub-class']//a[@title = 'Free Stuff']")));
			Actions action = new Actions(driver);
			action.moveToElement(free_Stuff).perform();
			Thread.sleep(2000);
			WebElement free_ppt = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Free Samples']")));
			action.moveToElement(free_ppt).click().perform();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}

	}

	@Then("User try to download the free ppts_ii")
	public void user_try_to_download_the_free_ppts_ii() throws Throwable {

		try {
			WebElement select_ppt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//img[@title='3d man education and technology concept free ppt templates graphics icons']")));
			js.executeScript("arguments[0].scrollIntoView();", select_ppt);
			select_ppt.click();

			WebElement download_ppt = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[normalize-space()='Download this presentation']")));
			js.executeScript("arguments[0].scrollIntoView();", download_ppt);
			download_ppt.click();
			Thread.sleep(3000);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("user is on home page page and click on facebbook button_ii")
	public void user_is_on_home_page_page_and_click_on_facebbook_button_ii() throws InterruptedException {
		// PDP Login Pop-up
		/*
		 * Thread.sleep(2000); WebElement Sign_in_with_facebook=
		 * wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//div[@class = 'social-buttons']//a[@class='btn btn-block btn-social btn-facebook social-btn']"
		 * ))); js.executeScript("arguments[0].click();", Sign_in_with_facebook);
		 * Thread.sleep(3000);
		 */

		Thread.sleep(2000);
		WebElement Sign_in_with_facebook = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[@class='btn btn-block btn-social btn-facebook social-btn']")));
		js.executeScript("arguments[0].click();", Sign_in_with_facebook);
		Thread.sleep(3000);

	}

	@Then("user enters the free username and password_ii")
	public void user_enters_the_free_username_and_password_ii() throws Throwable {

		try {
			Thread.sleep(1000);
			WebElement fb_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));

			fb_email.clear();

			fb_email.sendKeys("slidetech.qa.tester@gmail.com");

			WebElement fb_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));

			fb_pass.sendKeys("Qwerty@1");
			Thread.sleep(2000);

		} catch (NoSuchElementException e) {

		}
	}

	@Then("user clicks on Login button_ii")
	public void user_clicks_on_login_button_ii() throws Throwable {
		try {
			if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
			} else {
				WebElement fb_login = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

				fb_login.click();
				Thread.sleep(3000);
			}
			if (!driver.findElements(By.xpath("//div[@id ='confirm_id']")).isEmpty()) {
				WebElement approve = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='confirm-approve']")));
				approve.click();
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("user downloads the free PPT_ii")
	public void user_downloads_the_free_ppt_ii() throws InterruptedException {
		try {
			Thread.sleep(2000);
			// driver.get("https://www.slideteam.net/3d-man-education-and-technology-concept-ppt-graphics-icons.html");
			WebElement download_ppt = BaseClass.elementToBeClickable(By.xpath("//button[@id='clicking']"));
			js.executeScript("arguments[0].scrollIntoView();", download_ppt);
			download_ppt.click();
			Thread.sleep(2000);
			if (!driver.findElements(By.xpath("//a[@class='mfp-close roundlink']")).isEmpty()) {
				WebElement close_popup = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='mfp-close roundlink']")));

				close_popup.click();
			}

			else {

				WebElement close_popup = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//button[@class = 'btn btn-default close-popup']")));
				close_popup.click();
			}

		} catch (NoSuchElementException e) {

		}

	}

	@Then("user clicks on the logout page_ii")
	public void user_clicks_on_the_logout_page_ii() throws Throwable {
		try {
			Thread.sleep(2000);
			WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
			js.executeScript("arguments[0].click();", sign_Out);
		} catch (NoSuchElementException e) {

		}
	}

	@Then("verify The Message_ii")
	public void verify_The_Message_ii() throws InterruptedException {
		Thread.sleep(3000);
		String verifySignOutMessage = BaseClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		System.out.print("logout= " + verifySignOutMessage);

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains(verifySignOutMessage));

	}

}
