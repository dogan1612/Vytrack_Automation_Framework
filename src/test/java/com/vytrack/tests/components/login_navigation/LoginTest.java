package com.vytrack.tests.components.login_navigation;


import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;
    Faker faker = new Faker();

    String truckDriver = "user23";
    String storeManager = "storemanager66";
    String salesManager = "salesmanager124";
    String password = "UserUser123";

    String fpass = faker.internet().password();
    String fname = faker.name().username();

    @BeforeClass
    public void GetReady(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void SetUp(){
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test(priority = 1)
    public void ValidLogin(){

    // 1 \\
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("prependedInput")).sendKeys(storeManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(4);
        System.out.println("Login as Store Manager: ");
        WebElement check1 = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));      // locate "Dasboard" or Quick	Launchpad
        System.out.println(check1.isDisplayed()? "\tPassed" : "Failed");
        driver.findElement(By.id("user-menu")).click();
        String storeUser = driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).getText();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();

    // 2 \\
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("prependedInput")).sendKeys(salesManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(4);
        System.out.println("Login as Sales Manager: ");
        WebElement check2 = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        System.out.println(check2.isDisplayed()? "\tPassed" : "Failed");
        driver.findElement(By.id("user-menu")).click();
        String salesUser = driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).getText();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();

     // 3 \\

        driver.findElement(By.id("prependedInput")).sendKeys(truckDriver);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(4);
        driver.findElement(By.id("user-menu")).click();      // dynamic (hidden) --> need to click first to ativate
        String truckUser = driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).getText();
        System.out.println("Login as Truck Driver: ");
        WebElement check3 = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        System.out.println(check3.isDisplayed()? "\tPassed" : "Failed");
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();      //logout

        if(!storeUser.equals(salesUser) && !storeUser.equals(truckUser) && !salesUser.equals(truckUser)){
            System.out.println("User Name Verification: PASSED");
        }else{
            System.out.println("User Name Verification: FAILED");
            System.out.println("User names are not unique");
        }
    }

    @Test (priority = 2)
    public void NegativeLogin1(){
        // valid username | invalid password
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("prependedInput")).sendKeys(storeManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(fpass);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(3);
        System.out.println("\nLogin test with valid user & invalid password: ");
        WebElement warning = driver.findElement(By.xpath("//div[contains(text(),'Invalid user name or password.')]"));
        System.out.println(warning.isDisplayed()? "\tFailed (Expected Result)" : "PASSED!!");
        String expected = "Invalid user name or password.";
        String actual = warning.getText();
        Assert.assertEquals(actual,expected,"=> Warning Text Verifivation Failure =>");
    }

    @Test (priority = 3)
    public void NegativeLogin2(){
        // invalid username | valid password
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("prependedInput")).sendKeys(fname);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(3);
        System.out.println("Login test with invalid user & valid password: ");
        WebElement warning = driver.findElement(By.xpath("//div[contains(text(),'Invalid user name or password.')]"));
        System.out.println(warning.isDisplayed()? "\tFailed (Expected Result)" : "PASSED!!");
        String expected = "Invalid user name or password.";
        String actual = warning.getText();
        Assert.assertEquals(actual,expected,"=> Warning Text Verifivation Failure =>");
    }

    @Test (priority = 4)
    public void NegativeLogin3(){
        // invalid username | invalid password
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.id("prependedInput")).sendKeys(storeManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(fpass);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(3);
        System.out.println("Login test with invalid user & invalid password: ");
        WebElement warning = driver.findElement(By.xpath("//div[contains(text(),'Invalid user name or password.')]"));
        System.out.println(warning.isDisplayed()? "\tFailed (Expected Result)" : "PASSED!!");
        String expected = "Invalid user name or password.";
        String actual = warning.getText();
        Assert.assertEquals(actual,expected,"=> Warning Text Verifivation Failure =>");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}

