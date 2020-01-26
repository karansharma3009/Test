package main.java.Empirix;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UIClass implements Empirix.HomePageConstants {


    public WebDriver InititeEmpirixdriver(String browser)
    {
        if(browser.equals("CHROME"))
        {
            Empirix.GoogleChromeDriver chrome = new Empirix.GoogleChromeDriver();
           return chrome.getDriverInstance();
        }
        else if(browser.equals("FireFox"))
        {
            FireFoxDriver ffdriver = new FireFoxDriver();
            return ffdriver.getDriverInstance();
        }
        else if(browser.equals("IE"))
        {
            IEDriver ieDriver = new IEDriver();
            return ieDriver.getDriverInstance();
        }

        else {
            return new HtmlUnitDriver();
        }
    }


    public void performvalidations(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait (driver, 40);
        driver.get("https://services.empirix.com");
        // waiting for page to upload properly basis of "forgetPassword" element for 40 sec else timeout
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(forgotPassword)));
        driver.findElement(By.xpath(loginuserTextBox)).sendKeys("QA_traininguser40");
        driver.findElement(By.xpath(loginPasswordTextBox)).sendKeys("Empirix!");
        driver.findElement(By.xpath(signIn)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(voiceWatch)));
        driver.findElement(By.xpath(dropdown)).click();
        driver.findElement(By.xpath(clientLink)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(clientDetails)));

        // perform validations to check all the details on client tab present
        Map<String,String> originalmap = new HashMap<String, String>();
        Map<String,String> Expectedmap = new HashMap<String, String>();
        // Manually fill the values in ExpectedMap or any datasource such as DB as a source of truth and match with values
        // fetched from UI
        List<WebElement> listLabel = driver.findElements(By.xpath(getClientDetailsLabels));
        // Putting all the value of table in client detaisl  into map and then verifying
        // the same with ExpectedValue MAP we have with known value
        for(int count = 1; count<=listLabel.size();count++)
        {
            originalmap.put(driver.findElement(By.xpath(getClientDetailsLabels+"["+count+"]")).getText(),
                    driver.findElement(By.xpath(getGetClientDetailsLabelsValues+"["+count+"]")).getText());
        }

        compareMaps(originalmap,Expectedmap);


        Assert.assertEquals(true,driver.findElement(By.xpath(dashboard)).getText().equals("Dashboard"));
        Assert.assertEquals(true,driver.findElement(By.xpath(alerts)).getText().equals("Alerts"));
        Assert.assertEquals(true,driver.findElement(By.xpath(variables)).getText().equals("Variables"));
        Assert.assertEquals(true, driver.findElement(By.xpath(notifs)).getText().equals("Notifications"));
    }

    private boolean compareMaps(Map<String, String> originalmap, Map<String, String> expectedmap) {
        Set<String> originalKeys = originalmap.keySet();
        Set<String> expectedMapKeys = expectedmap.keySet();
        for ( String s:originalKeys)
        {
            if(!originalmap.get(s).equals(expectedmap.get(s))) {
                return false;
            }
        }
        return true;
    }

    /*
    public void MoveToElement(WebDriver driver , String elem )
    {
        Actions action=new Actions(driver);
       action.moveToElement(driver.findElement(By.xpath(""))).perform();
    }*/
}
