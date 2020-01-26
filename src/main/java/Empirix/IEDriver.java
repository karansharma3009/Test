package main.java.Empirix;

import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;

public class IEDriver {

    public InternetExplorerDriver getDriverInstance() {

        InternetExplorerDriver ieDriver = new InternetExplorerDriver();
        ieDriver.manage().window().maximize();
        return ieDriver;
    }
}
