package mobile.cucumber.stepDef;

import io.appium.java_client.android.AndroidDriver;
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
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Login {

    public static AndroidDriver driver; // initiate android driver from appium
    public static DesiredCapabilities capabilities; // initiate capabilities settings object
    public static String baseUrl = "http://127.0.0.1:4723/wd/hub"; // set appium server URL

    @Given("user is on KasirAja login page")
    public void user_is_on_kasir_aja_login_page() throws MalformedURLException {
        // open apps
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "emulator-5554"); // set udid of used android devices
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/java/mobile/apk/kasirAja.apk"); // set apk file directory
        capabilities.setCapability("autoGrantPermissions", true); // set auto accept permission request setting
        capabilities.setCapability("autoAcceptAlerts", true); // set auto accept all possible appearing alert
        URL url = new URL(baseUrl);
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
    }
    @When("user input (.*) as email$")
    public void user_input_tdd_selenium_gmail_com_as_email(String email) {
        driver.findElement(By.xpath("//*[contains(@text,'email')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.EditText")).sendKeys(email);
    }
    @And("user input (.*) as password$")
    public void user_input_tdd_selenium_as_password(String password) {
        driver.findElement(By.xpath("//*[contains(@text, 'password')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.EditText")).sendKeys(password);
    }
    @And("user click submit")
    public void user_click_submit() {
        driver.findElement(By.xpath("//*[contains(@text, 'login')]/parent::android.widget.Button")).click();
    }
    @Then("user verify (.*) login result$")
    public void user_verify_success_login_result(String status) {
        if (status.equals("success")) { // assert success login
            driver.findElement(By.xpath("//*[contains(@text, 'kasirAja')]")).isEnabled(); // assert kasirAja title on dashboard
        } else { // assert error message
            driver.findElement(By.xpath("//*[contains(@text,'Kredensial yang Anda berikan salah')]")).isDisplayed(); // assert error message
        }
    }

}
