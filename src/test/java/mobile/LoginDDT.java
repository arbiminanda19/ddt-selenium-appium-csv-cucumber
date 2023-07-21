package mobile;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LoginDDT {

    public static AndroidDriver driver; // initiate android driver from appium
    public static DesiredCapabilities capabilities; // initiate capabilities settings object
    public static String baseUrl = "http://127.0.0.1:4723/wd/hub"; // set appium server URL

    @Test // add Test tag from Junit as runner to run the test
    public void main() {

        String fileDir = System.getProperty("user.dir") + "/src/test/data/test-data.csv"; // file directory

        // Read the CSV file and run the test for each row
        try (CSVReader reader = new CSVReader(new FileReader(fileDir))) { // try read csv data
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) { // loop for all row data in csv
                String email = nextLine[0]; // read column 1 for email
                String password = nextLine[1]; // read column 2 for password
                String status = nextLine[2]; // read column 3 for expected login status

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

                driver.findElement(By.xpath("//*[contains(@text,'email')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.EditText")).sendKeys(email);
                driver.findElement(By.xpath("//*[contains(@text, 'password')]/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.widget.EditText")).sendKeys(password);
                driver.findElement(By.xpath("//*[contains(@text, 'login')]/parent::android.widget.Button")).click();
                if (status.equals("success")) { // assert success login
                    driver.findElement(By.xpath("//*[contains(@text, 'kasirAja')]")).isEnabled(); // assert kasirAja title on dashboard
                } else { // assert error message
                    driver.findElement(By.xpath("//*[contains(@text,'Kredensial yang Anda berikan salah')]")).isDisplayed(); // assert error message
                }
                System.out.println("Run " + status + " case");
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
