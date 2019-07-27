package com.briteErp;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

/*
User Story : As a user, when I already created an opportunity in CRM module
of Bribe ERP application, I want to be able to delete it.

Acceptance Criteria :
Verify that user should be able to see the list view.
Verify that user should be able to delete the opportunity from action drop down list .

 */


public class deleteOpportunity {

    WebDriver driver;
    WebDriverWait wait;
    String crmUser = "eventscrmmanager25@info.com";
    String crmPassword = "eventscrmmanager";
    String opportunity = "Mentor-7";        // test data
    String actionButton = "btn-group";
    String deleteButton = "//a[contains(text(),'Delete')]" ;
    String okLocator= "//span[text()='Ok']";
    String listView ="//button[@accesskey='l']";

    @BeforeClass
    public void GetReady(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://34.220.250.213/web/login");
    }

    @Test(priority = 1, description = "Login to application")
    public void login(){
        driver.findElement(By.id("login")).sendKeys(crmUser);
        driver.findElement(By.id("password")).sendKeys(crmPassword);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        String expectedTitle = "Odoo";
        Assert.assertEquals(driver.getTitle(),expectedTitle);
    }

    @Test(priority = 2, description = "Verify that user should be able to see the list view")
    public void AC1(){
        driver.findElement(By.xpath("//span[@class='oe_menu_text' and contains(text(),'CRM')]")).click();   // ENTER CRM MENU
        SeleniumUtils.wait(2);

        boolean listButtonDisplayed = driver.findElement(By.xpath(listView)).isDisplayed();      // LIST VIEW OPTION
        boolean listButtonEnabled = driver.findElement(By.xpath(listView)).isEnabled();
        if(listButtonEnabled == true && listButtonDisplayed == true){
            System.out.println("\n\nPASS ==> List view is displayed and enabled");
        }else if(listButtonDisplayed == false){
            System.out.println("FAILED ==> List view is not displayed");
        }else if(listButtonEnabled == false && listButtonDisplayed == true) {
            System.out.println("FAILED ==> List view is displayed BUT disabled");
        }
        SeleniumUtils.wait(2);

    }
    @Test(priority = 3, description = "Creating test opp for Acceptance Criteria 2", enabled = true)
    public void createToDelete(){
        driver.findElement(By.xpath(listView)).click();       // Choose List View Option
        SeleniumUtils.wait(2);
        driver.findElement(By.xpath("//button[@accesskey='c']")).click();       // Click on create button
        SeleniumUtils.wait(2);
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys(""+opportunity+"");     // Enter "Mentor-7" as opp name in text field
        SeleniumUtils.wait(2);
        driver.findElement(By.xpath("//button[@accesskey='s']")).click();           // Click on save button
        SeleniumUtils.wait(2);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[5]/ul[1]/li[1]/a/span")).click();      // Click on Pipeline menu
        SeleniumUtils.wait(2);
        driver.findElement(By.xpath(listView)).click();           // Choose List View Option
        SeleniumUtils.wait(2);

    }

    @Test(priority = 4, description = "Verify that user should be able to delete the opportunity from action drop down list")
    public void AC2(){
        String locator = "//td[text()='"+opportunity+"']/preceding-sibling::td//input";
        driver.findElement(By.xpath(locator)).click();                                      // choose created opportunity

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(actionButton))));
        driver.findElement(By.className(actionButton)).click();                              // wait and then click Action button

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(deleteButton))));
        driver.findElement(By.xpath(deleteButton)).click();             // wait and then click Delete button

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(okLocator)));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(okLocator))));
        driver.findElement(By.xpath(okLocator)).click();                        // wait and then click OK button
    }

   @AfterClass
    public void tearDown(){
        driver.quit();
    }
}


