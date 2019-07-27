package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class SeleniumUtils {

    public static void verifyEquals(String expectedResult, String actualResult) {
        if (expectedResult.equals(actualResult)) {
            System.out.println("\tPassed");
        } else {
            System.out.println("\tFAILED!!");
            System.out.println("\tExpected Result: " + expectedResult);
            System.out.println("\tActual Result: " + actualResult);
        }
    }

    public static void verifyEquals(int expectedResult, int actualResult) {
        if (expectedResult == actualResult) {
            System.out.println("\tPassed");
        } else {
            System.out.println("\tFAILED!!");
            System.out.println("\tExpected Result: " + expectedResult);
            System.out.println("\tActual Result: " + actualResult);
        }
    }

    public static void check(String actual, String expected) {
        if (actual.equals(expected)) {
            System.out.println("\tPassed");
        }else{
            System.out.println("FAILED!!");
        }
        Assert.assertEquals(actual,expected);
    }


    public static void wait(int seconds){
        try {
            Thread.sleep(seconds * 1000 );
        }catch (Exception e){
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
    }
    // This method will open example page based on link name
    public static void openPage(String page, WebDriver driver){
        //we will all examples on the home page
        List<WebElement> list = driver.findElements(By.tagName("a"));
        for(WebElement example: list){
            if(example.getText().contains(page)){
                example.click();
                break;
            }
        }
    }

    public static void verifyIsDisplayed(WebElement element){
        if(element.isDisplayed()){
            System.out.println("Passed");
            System.out.println(element.getText()+": is visible");
        }else{
            System.out.println("Failed");
            System.out.println(element.getText()+": is not visible");
        }
    }

    /**
     * This method will recover in case of exception after unsuccessful click,
     * and will try to click on element again.
     * @param driver
     * @param by
     * @param attempts
     */

    public static void clickWithWait(WebDriver driver, By by, int attempts){
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while(counter < attempts) {
            try {
                driver.findElement(by).click();     //selenium must look for element again
                break;                              //if click is successful - then break
            } catch (WebDriverException e) {
                System.out.println(e);              //print exception if click fails
                System.out.println("Attempt :: " + ++counter);      //print attempt
                wait(1);            //wait for 1 second, and try to click again
            }
        }

        // for example
        //  driver.findElement(By.xpath(tabLocator)).click();
        //  SeleniumUtils.clickWithWait(driver, By.xpath(tabLocator), 5);
        // click ile problem yasiyorsak bunu kullan.


    }
}
