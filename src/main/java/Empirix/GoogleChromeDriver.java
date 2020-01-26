package Empirix;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.logging.Level;

public class GoogleChromeDriver {


    public org.openqa.selenium.chrome.ChromeDriver getDriverInstance() {
        System.setProperty("webdriver.chrome.driver", "C:\\D\\Official\\TimePass\\Practice\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        LoggingPreferences pref = new LoggingPreferences();
        pref.enable(LogType.BROWSER, Level.WARNING);
        org.openqa.selenium.chrome.ChromeDriver chromeDriver = new org.openqa.selenium.chrome.ChromeDriver(capabilities);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }
}
