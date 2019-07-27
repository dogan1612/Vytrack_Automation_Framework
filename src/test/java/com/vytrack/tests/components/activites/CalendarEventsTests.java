package com.vytrack.tests.components.activites;


import com.vytrack.pages.CalendarEventsPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.TestBase;
import utilities.VyTrackUtils;

import java.util.Arrays;
import java.util.List;

public class CalendarEventsTests extends TestBase {
    //BeforeMethod is coming from TestBase
    CalendarEventsPage calendarPage = new CalendarEventsPage();

    @Test
    public void verifyRepeatOptions(){
        String username = ConfigurationReader.getProperty("storemanagerusername");      //we are reading username from .properties file
        String password = ConfigurationReader.getProperty("storemanagerpassword");
        VyTrackUtils.login(driver, username, password);

        //go to Calendar Events
        VyTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");

        VyTrackUtils.waitUntilLoaderScreenDisappear(driver);
        driver.findElement(By.cssSelector(calendarPage.createCalendarEventBtnLocator)).click();

        VyTrackUtils.waitUntilLoaderScreenDisappear(driver);
        driver.findElement(By.cssSelector(calendarPage.repeatCheckBoxLocator)).click();

        List<String> expectedOptions = Arrays.asList("Daily", "Weekly","Monthly", "Yearly");
        List<String> actualOptions = calendarPage.getRepeatOptions();

        //correct way to check if 2 collections are equals
        Assert.assertEquals(actualOptions, expectedOptions);

    }
}


/*
Verify repeat options
Log in as Valid user
Go to Activities -> Calendar Events
Click on create new calendar event
Click on Repeat checkbox
Verify repeat options:
Daily
Weekly
Monthly
Yearly
*/