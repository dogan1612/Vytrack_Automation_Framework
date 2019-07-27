package com.vytrack.tests.components.activites;

import com.vytrack.pages.CalendarEventsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;


public class DateAndTimeTests extends TestBase {              // We are extending it, bcos before&after class is comming from Test Base

    private WebDriver driver = Driver.getDriver();
    CalendarEventsPage calendarPage = new CalendarEventsPage();
    private String today;

/*
Test Case 1  (Date Time, End date auto adjust)
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start date to future date
5. Verify that end date changes to the same date
6. Change back the start date to today’s date
7. Verify that end date changes back to today’s date   */


    @Test (priority = 1, enabled = true)
    public void TestCase_1(){
        VyTrackUtils.login();                                                                       // STEP 1
        VyTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");        // STEP 2
        driver.findElement(By.cssSelector(calendarPage.createCalendarEventBtnLocator)).click();     // STEP 3
        VyTrackUtils.waitUntilLoaderScreenDisappear(driver);
        // The wait object is used to wait for the Next or Prev button attached to DOM tree again after being clicked.
        // Because the re-attach time is uncertain, so we set a long time out value.
        WebElement startDate = driver.findElement(By.cssSelector("[class*=\"input start hasDatepicker\"]"));    // STEP 4
        startDate.click();
        startDate.clear();
        startDate.sendKeys("Dec 16, 2021", Keys.ENTER);
        SeleniumUtils.wait(2);

        // STEP 5  ==> Verify that end date changes to the same date
        WebElement endDate = driver.findElement(By.cssSelector("[class*=\"input end hasDatepicker\"]"));
        String expectedDate = startDate.getAttribute("value");
        String actualDate = endDate.getAttribute("value");
        Assert.assertEquals(actualDate, expectedDate);
        SeleniumUtils.wait(1);

        // STEP 6  ==> Change back the start date to today’s date
        startDate.clear();
        startDate.sendKeys(CurrentDate.currentDay(), Keys.ENTER);
        SeleniumUtils.wait(1);

        // STEP 7  ==> Verify that end date changes back to today’s date
        expectedDate = startDate.getAttribute("value");
        actualDate = endDate.getAttribute("value");
        driver.findElement(By.xpath("//div[@id='oro-dropdown-mask']")).click();
        driver.findElement(By.xpath("//button[@type='button' and @data-handler='today']")).click();
        Assert.assertEquals(actualDate, expectedDate);
    }

/*
Test Case 2  (Date Time, End time auto adjust)
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start time to any other time
5. Verify that end time changes exactly 1 hours later  */

    @Test (priority = 2, enabled = true)
    public void TestCase_2(){
        driver.findElement(By.xpath("//input[@placeholder='time']")).click();
        int r = (int)(Math.random()*48)+1;
        driver.findElement(By.cssSelector(".ui-timepicker-list>li:nth-child("+r+")")).click();
        WebElement startTimeLocator = driver.findElement(By.cssSelector("[class*=\"input start ui\"]"));
        WebElement endTimeLocator = driver.findElement(By.cssSelector("[class*=\"input end ui\"]"));

        String time1 = startTimeLocator.getAttribute("value");     // 4:01 PM (for example)
        String time2 = endTimeLocator.getAttribute("value");
        time1 = time1.replaceAll("\\D+", "");
        time2 =time2.replaceAll("\\D+", "");
        int startTime = Integer.parseInt(time1);
        int endTime = Integer.parseInt(time2);
        System.out.println("Start time: "+startTime+ " -- End Time: "+endTime);
        Assert.assertEquals(endTime, startTime+100, "Failed: Between Start and End Time should be 1 hour difference");
    }


/*
Test Case 3 (Date Time, End date/time auto adjust)
1. Log in as Valid user
2. Go to Activities -> Calendar Events
3. Click on create new calendar event
4. Change the start time to 11.30 PM
5. Verify that end date shows tomorrows date
6. Verify that end time is 12:30 AM  */

    @Test (priority = 3, enabled = true)
    public void TestCase_3(){
    driver.findElement(By.xpath("//input[@placeholder='time']")).click();
    driver.findElement(By.cssSelector(".ui-timepicker-list>li:nth-child(48)")).click();
    WebElement startTimeLocator = driver.findElement(By.cssSelector("[class*=\"input start ui\"]"));
    WebElement endTimeLocator = driver.findElement(By.cssSelector("[class*=\"input end ui\"]"));

    String time1 = startTimeLocator.getAttribute("value");
    String time2 = endTimeLocator.getAttribute("value");
    System.out.println(time1);
    time1 = time1.replaceAll("\\D+", "");
    time2 =time2.replaceAll("\\D+", "");
    int startTime = Integer.parseInt(time1);
    int endTime = Integer.parseInt(time2);
    System.out.println("Start time: "+startTime+ " -- End Time: "+endTime);
    Assert.assertEquals(endTime, startTime+100, "Failed: Between Start and End Time should be 1 hour difference");
    }
}
