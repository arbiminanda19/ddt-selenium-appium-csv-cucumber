package mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Test;

public class OpenApps {

    public static AndroidDriver driver; // initiate android driver from appium
    public static DesiredCapabilities capabilities; // initiate capabilities settings object
    public static String baseUrl = "http://127.0.0.1:4723/wd/hub"; // set appium server URL

    @Test
    public void main() throws MalformedURLException {

        capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "emulator-5554"); // set udid of used android devices
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "12");
        capabilities.setCapability("app", System.getProperty("user.dir") + "/src/test/java/mobile/apk/kasirAja.apk"); // set apk file directory
        capabilities.setCapability("autoGrantPermissions", true); // set auto accept permission request setting
        capabilities.setCapability("autoAcceptAlerts", true); // set auto accept all possible appearing alert

        URL url = new URL(baseUrl);
        driver = new AndroidDriver(url, capabilities); // set new driver with Android Driver using base url and capabilities setting, will open apps
    }

}
