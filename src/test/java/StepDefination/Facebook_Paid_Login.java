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

public class Facebook_Paid_Login extends BaseClass {
	@Given("Enter the url")
	public void enter_the_url() throws Throwable {

		BaseClass.ClearfacebookCache();
		try {
			driver.get(AppURL);
			log.info("It's opening the website URL");
			Thread.sleep(2000);
			driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			BaseClass.ClearBrowserCache();
			Thread.sleep(2000);
			WebElement Sign_in_with_facebook = BaseClass
					.elementToBeClickable(By.xpath("//a[@class='btn btn-block btn-social btn-facebook social-btn']"));
			Thread.sleep(2000);
			Sign_in_with_facebook.click();
			Thread.sleep(3000);
		} catch (InterruptedException e) {

			e.printStackTrace();
			System.out.println("message = " + e.getMessage());
		}

	}

	@Given("user enters the Username and passwordiv")
	public void user_enters_the_Username_and_passwordiv() throws InterruptedException {

		try {
			Thread.sleep(2000);
			WebElement fb_email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='email']")));

			fb_email.clear();

			fb_email.sendKeys("sumit.kumar@slidetech.in");
			Thread.sleep(2000);
			WebElement fb_pass = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='pass']")));
			Thread.sleep(2000);
			fb_pass.sendKeys("redhat2090");

		} catch (NoSuchElementException e) {

		}

	}

	@Then("user clicks on Login button and verify the pageiv")
	public void user_clicks_on_login_button_and_verify_the_pageiv() throws InterruptedException {
		try {
			if (!driver.findElements(By.xpath("//input[@value='Log In']")).isEmpty()) {
				driver.findElement(By.xpath("//input[@value='Log In']")).click();
			} else {
				WebElement fb_login = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='loginbutton']")));

				fb_login.click();
				Thread.sleep(3000);
			}
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

		String subscriptionText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[@class='base']")))
				.getText();
		System.out.println("subscriptionText = " + subscriptionText);

	}

	@Then("user downloads the popular PPTiv")
	public void user_downloads_the_popular_ppiv() throws InterruptedException {
		System.out.println("user will download the ppt");
		driver.navigate().refresh();

		Thread.sleep(4000);

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

	@Then("user clicks on the logout pageiv")
	public void user_clicks_on_the_logout_pageiv() throws InterruptedException {
		Thread.sleep(3000);
		WebElement sign_Out = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign Out")));
		js.executeScript("arguments[0].click();", sign_Out);

		Thread.sleep(3000);
		String verifySignOutMessage = BaseClass.elementToBeClickable(By.xpath("//h3[@class='base']")).getText();

		Assert.assertTrue("user is not logout from the application",
				verifySignOutMessage.contains("YOU ARE NOW LOGGED OUT"));

	}

}
