package com.vytrack.tests.smoke_tests;

import com.vytrack.utilities.SeleniumUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MenuOptionsTest {
    WebDriver driver;

    String truckDriver = "user23"; String storeManager = "storemanager66"; String salesManager = "salesmanager124";
    String password = "UserUser123";

    String vehiclesExpTitle = "Car - Entities - System - Car - Entities - System";
    String vehiclesExpTitleSM = "All - Car - Entities - System - Car - Entities - System";
    String vehiclesExpPageName = "All Cars";

    String accExpTitle = "Accounts - Customers"; String accExpTitleSM = "All Accounts - Customers";
    String accExpPageName = "Accounts";

    String accExpPageNameSM = "All Accounts";

    String contactsExpTitle = "Contacts - Customers"; String contactsExpTitleSM = "All - Contacts - Customers";
    String contactsExpPageName = "Contacts"; String contactsExpPageNameSM = "All Contacts";

    String calendarExpTitle = "Calendar Events - Activities";
    String calendarExpPageName = "Calendar Events"; String calendarExpPageNameSM = "All Calendar Events";

    String dashExpTitle = "Dashboard - Dashboards";
    String dashPageName = "Dashboard";

    String oppExpTitle = "Open Opportunities - Opportunities - Sales";
    String oppPName = "Open Opportunities";

    String actvExpTitle="All - Calls - Activities";
    String actvPName="All Calls";

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
    public void DriverLogin() {
        driver.findElement(By.id("prependedInput")).sendKeys(truckDriver);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
        SeleniumUtils.wait(4);
        System.out.println("\n===== TESTING >> MENU OPTIONS FOR DRIVER =====");
    }

    @Test (priority = 2)
    public void Driver_Vehicles() {
        driver.navigate().to("http://qa2.vytrack.com/entity/Extend_Entity_Carreservation");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nVEHICLES -- Title verification : \t");
        SeleniumUtils.verifyEquals(vehiclesExpTitle, driver.getTitle());
        System.out.print("VEHICLES -- Page Name verification : ");
        SeleniumUtils.verifyEquals(vehiclesExpPageName, actualPageName);
    }
    @Test (priority = 3)
    public void Driver_Accounts() {
        driver.navigate().to("http://qa2.vytrack.com/account");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nACCOUNTS -- Title verification : \t");
        SeleniumUtils.verifyEquals(accExpTitle, driver.getTitle());
        System.out.print("ACCOUNTS -- Page Name verification : ");
        SeleniumUtils.verifyEquals(accExpPageName, actualPageName);
    }
    @Test (priority = 4)
    public void Driver_Contacts() {
        driver.navigate().to("http://qa2.vytrack.com/contact");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nCONTACTS -- Title verification : \t");
        SeleniumUtils.verifyEquals(contactsExpTitle, driver.getTitle());
        System.out.print("CONTACTS -- Page Name verification : ");
        SeleniumUtils.verifyEquals(contactsExpPageName, actualPageName);
    }
    @Test (priority = 5)
    public void Driver_Calender() {
        driver.navigate().to("http://qa2.vytrack.com/calendar/event");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nCALENDAR -- Title verification : \t");
        SeleniumUtils.wait(2);
        SeleniumUtils.verifyEquals(calendarExpTitle, driver.getTitle());
        System.out.print("CALENDAR -- Page Name verification : ");
        SeleniumUtils.verifyEquals(calendarExpPageName, actualPageName);
    }
    @Test (priority = 6)
    public void LogOut() {
        SeleniumUtils.wait(2);
        driver.findElement(By.id("user-menu")).click();
        String storeUser = driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).getText();
        driver.findElement(By.xpath("//a[@class='no-hash']")).click();
    }
    @Test (priority = 7)
    public void StoreManagerLogin() {
        System.out.println("\n===== TESTING >> MENU OPTIONS FOR STORE MANAGER =====");
        SeleniumUtils.wait(2);
        driver.findElement(By.id("prependedInput")).sendKeys(storeManager);
        driver.findElement(By.id("prependedInput2")).sendKeys(password);
        driver.findElement(By.id("_submit")).click();
    }
    @Test (priority = 8)
    public void SM_Dashboard() {
        SeleniumUtils.wait(2);
        WebElement dashboard = driver.findElement(By.xpath("//h1[@class='oro-subtitle']"));
        System.out.print("\nDASHBOARD -- Title Verification: \t");
        SeleniumUtils.verifyEquals(dashExpTitle, driver.getTitle());
        System.out.print("DASHBOARD -- Page Name Verification: ");
        SeleniumUtils.verifyEquals(dashPageName, dashboard.getText());
    }
    @Test (priority = 9)
    public void SM_Vehicles() {
        driver.navigate().to("http://qa2.vytrack.com/entity/Extend_Entity_Carreservation");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nVEHICLES -- Title verification : \t");
        SeleniumUtils.verifyEquals(vehiclesExpTitleSM, driver.getTitle());
        System.out.print("VEHICLES -- Page Name verification : ");
        SeleniumUtils.verifyEquals(vehiclesExpPageName, actualPageName);
    }
    @Test (priority = 10)
    public void SM_Accounts() {
        driver.navigate().to("http://qa2.vytrack.com/account");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nACCOUNTS -- Title verification : \t");
        SeleniumUtils.verifyEquals(accExpTitleSM, driver.getTitle());
        System.out.print("ACCOUNTS -- Page Name verification : ");
        SeleniumUtils.verifyEquals(accExpPageNameSM, actualPageName);
    }
    @Test (priority = 11)
    public void SM_Contacts() {
        driver.navigate().to("http://qa2.vytrack.com/contact");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nCONTACTS -- Title verification : \t");
        SeleniumUtils.verifyEquals(contactsExpTitleSM, driver.getTitle());
        System.out.print("CONTACTS -- Page Name verification : ");
        SeleniumUtils.verifyEquals(contactsExpPageNameSM, actualPageName);
    }
    @Test (priority = 12)
    public void SM_Opportunities() {
        driver.navigate().to("http://qa2.vytrack.com/opportunity");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nOPPORTUNITIES -- Title verification : \t");
        SeleniumUtils.verifyEquals(oppExpTitle, driver.getTitle());
        System.out.print("OPPORTUNITIES -- Page Name verification : ");
        SeleniumUtils.verifyEquals(oppPName, actualPageName);
    }
    @Test (priority = 13)
    public void SM_Calls() {
        driver.navigate().to("http://qa2.vytrack.com/call/");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nCALLS -- Title verification : \t");
        SeleniumUtils.verifyEquals(actvExpTitle, driver.getTitle());
        System.out.print("CALLS -- Page Name verification : ");
        SeleniumUtils.verifyEquals(actvPName, actualPageName);
    }
    @Test (priority = 14)
    public void SM_Calender() {
        driver.navigate().to("http://qa2.vytrack.com/calendar/event");
        String actualPageName = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
        System.out.print("\nCALENDAR -- Title verification : \t");
        SeleniumUtils.wait(2);
        SeleniumUtils.verifyEquals(calendarExpTitle, driver.getTitle());
        System.out.print("CALENDAR -- Page Name verification : ");
        SeleniumUtils.verifyEquals(calendarExpPageNameSM, actualPageName);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}