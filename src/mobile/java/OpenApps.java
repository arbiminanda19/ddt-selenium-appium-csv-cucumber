import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class OpenApps {

    public static AndroidDriver driver; // initiate android driver from appium
    public static DesiredCapabilities capabilities; // initiate capabilities settings object
    public static String baseUrl = "http://127.0.0.1:4723/wd/hub"; // set appium server URL

    @Test
    public void main() {

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel 3a XL API 32");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/resources/app/alfagift.apk");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("autoAcceptAlerts", true);

        URL url = new URL(baseUrl);
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

}
