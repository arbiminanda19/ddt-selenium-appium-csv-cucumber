import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class FailedLogin {

    @Test // add Test tag from Junit as runner to run the test
    public void main() {
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseUrl = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); // create object to setup option for chrome driver
        opt.setHeadless(false);  // set chrome driver to not using headless mode

        driver = new ChromeDriver(opt); // apply chrome driver setup to driver
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
        driver.manage().window().maximize(); // maximize window
        driver.get(baseUrl); // access base url

        // login using unregistered credential
        driver.findElement(By.id("email")).sendKeys("failed-login@gmail.com");
        driver.findElement(By.id("password")).sendKeys("failed-login");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // assert error message
        String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");

    }

}
