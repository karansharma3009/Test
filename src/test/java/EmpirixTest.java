package test.java;

import com.beust.jcommander.Parameter;
import main.java.Empirix.UIClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class EmpirixTest {

    WebDriver driver = null;
    UIClass ui ;
    @BeforeTest
    @Parameters("browser")
    public void init()
    {
        ui = new UIClass();
        // By default i am sending here CHROME . if you run multibrowser in parallel send browser parameter here
        //driver = ui.InititeEmpirixdriver(browser);

        driver = ui.InititeEmpirixdriver("CHROME");
    }

    @Test
    public void UItest1() throws InterruptedException {
        ui.performvalidations(driver);
    }

}

