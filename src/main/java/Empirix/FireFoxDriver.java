package main.java.Empirix;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class FireFoxDriver {

    public FirefoxDriver getDriverInstance() {

        System.setProperty("webdriver.gecko.driver", new File("").getAbsolutePath() + "\\geckodriver.exe");

        FirefoxProfile fp = new FirefoxProfile();

        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        return firefoxDriver;
    }
}
