package cucumber.stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver; // set driver for test using webdriver from selenium
    String baseUrl = "https://kasirdemo.belajarqa.com/"; // set base url

    @Given("user is on KasirAja login page")
    public void user_is_on_kasir_aja_login_page() {
        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); // create object to setup option for chrome driver
        opt.setHeadless(false);  // set chrome driver to not using headless mode

        driver = new ChromeDriver(opt); // apply chrome driver setup to driver
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
        driver.manage().window().maximize(); // maximize window
        driver.get(baseUrl); // access base url
    }
    @When("user input (.*) as email$")
    public void user_input_tdd_selenium_gmail_com_as_email(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }
    @And("user input (.*) as password$")
    public void user_input_tdd_selenium_as_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }
    @And("user click submit")
    public void user_click_submit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Then("user verify (.*) login result$")
    public void user_verify_success_login_result(String status) {
        if (status.equals("success")) { // assert success login
            driver.findElement(By.xpath("//div[contains(text(),'dashboard')]"));
            String username = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
            Assert.assertEquals(username, "tdd-selenium");
        } else { // assert error message
            String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
            Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
        }
    }

}
