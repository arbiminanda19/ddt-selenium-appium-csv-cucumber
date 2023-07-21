import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Login {

    @Test // add Test tag from Junit as runner to run the test
    public void main() {
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseUrl = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); // create object to setup option for chrome driver
        opt.setHeadless(false);  // set chrome driver to not using headless mode

        driver = new ChromeDriver(opt); // apply chrome driver setup to driver
        driver.manage().window().maximize(); // maximize window
        driver.get(baseUrl); // access base url
    }

}
