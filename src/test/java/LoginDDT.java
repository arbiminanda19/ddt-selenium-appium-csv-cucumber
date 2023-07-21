import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.opencsv.CSVReader;

public class LoginDDT {

    @Test // add Test tag from Junit as runner to run the test
    public void main() {
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseUrl = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions(); // create object to setup option for chrome driver
        opt.setHeadless(false);  // set chrome driver to not using headless mode

        String fileDir = System.getProperty("user.dir") + "/src/test/data/test-data.csv"; // file directory

        // Read the CSV file and run the test for each row
        try (CSVReader reader = new CSVReader(new FileReader(fileDir))) { // try read csv data
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) { // loop for all row data in csv
                String email = nextLine[0]; // read column 1 for email
                String password = nextLine[1]; // read column 2 for password
                String status = nextLine[2]; // read column 3 for expected login status

                driver = new ChromeDriver(opt); // apply chrome driver setup to driver
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
                driver.manage().window().maximize(); // maximize window
                driver.get(baseUrl); // access base url
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                if (status.equals("success")) { // assert success login
                    driver.findElement(By.xpath("//div[contains(text(),'dashboard')]"));
                    String username = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
                    Assert.assertEquals(username, "tdd-selenium");
                } else { // assert error message
                    String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
                }
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
