package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class PageAccessTest {
    WebDriver driver;

    String truckDriver = "user23";
    String storeManager = "storemanager66";
    String salesManager = "salesmanager124";
    String password = "UserUser123";

    public void LogOut() {
        driver.findElement(By.id("user-menu")).click();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
    }


    @BeforeClass
    public void GetReady() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void SetUp() {
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test (priority = 1)
    public void ContractP1() {
        driver.findElement(By.id("prependedInput")).sendKeys(storeManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        driver.navigate().to("http://qa2.vytrack.com/entity/Extend_Entity_VehicleContract");
        WebElement menu = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        System.out.println("\nStore Manager can access Vehicle Contract Page: ");
        System.out.println(menu.isDisplayed()? "\tPassed" : "FAILED!!");
        SeleniumUtils.wait(3);
        LogOut();
    }
    @Test (priority = 2)
    public void ContractP2() {
        driver.findElement(By.id("prependedInput")).sendKeys(salesManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.navigate().to("http://qa2.vytrack.com/entity/Extend_Entity_VehicleContract");
        WebElement menu = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        System.out.println("Sales Manager can access Vehicle Contract Page: ");
        System.out.println(menu.isDisplayed()? "\tPassed" : "\tFAILED!!");
        SeleniumUtils.wait(3);
        LogOut();
    }

    @Test (priority = 3)
    public void ContractP3() {
        driver.findElement(By.id("prependedInput")).sendKeys(truckDriver);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(4);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Fleet')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Vehicle Contracts')]")).click();
        WebElement warning = driver.findElement(By.xpath("//div[contains(text(),'You do not have permission to perform this action.')]"));
        System.out.println("Truck Driver can not access Vehicle Contract Page & Message Displayed :");
        System.out.println(warning.isDisplayed()? "\tPassed" : "\tFAILED!!");
        SeleniumUtils.wait(2);
        LogOut();
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
