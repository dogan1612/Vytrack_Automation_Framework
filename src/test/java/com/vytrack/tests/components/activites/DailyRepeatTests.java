package com.vytrack.tests.components.activites;

import com.vytrack.pages.CalendarEventsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.*;

public class DailyRepeatTests extends TestBase {

    private WebDriver driver = Driver.getDriver();
    CalendarEventsPage calendarPage = new CalendarEventsPage();

    public String repeatsDropdownLocator = "select[id^='recurrence-repeats-view']";
    public String repeatCheckBoxLocator = "input[id^='recurrence-repeat-view']";
    public String radioButtonLocator = "label[class='fields-row']>input[checked='checked']";
    public String dailyLocator = "label[class='fields-row']>input[value='daily']+input";
    public String summary = "//div[@class='controls' and @data-name='recurrence-summary']//span";
    public String weekdayRadioButton = "//label[@data-role='control-section-switcher']//span[contains(text(),'Weekday')]";
    public String eMessage= "//span[@class='validation-failed']//span//span";

    @Test(priority = 1)
    public void TestCase_1_2() {
        VyTrackUtils.login();
        VyTrackUtils.navigateToModule(driver, "Activities", "Calendar Events");
        driver.findElement(By.cssSelector(calendarPage.createCalendarEventBtnLocator)).click();
        VyTrackUtils.waitUntilLoaderScreenDisappear(driver);
        driver.findElement(By.cssSelector(repeatCheckBoxLocator)).click();      //Click on Repeat checkbox

        // Verify that Daily is selected by default
        Select select = new Select(driver.findElement(By.cssSelector(repeatsDropdownLocator)));
        Assert.assertTrue(select.getFirstSelectedOption().getText().equals("Daily"));

        // Verify day(s) checkbox is selected and default value is 1
        WebElement radioButton = driver.findElement(By.cssSelector(radioButtonLocator));
        Assert.assertTrue(radioButton.isEnabled());

        WebElement value = driver.findElement(By.cssSelector(dailyLocator));
        Assert.assertTrue(value.getAttribute("value").equals("1"));

        // Verify summary says Daily every 1 day
        Assert.assertTrue(driver.findElement(By.xpath(summary)).getText().equals("Daily every 1 day"));

        // Check the Weekday checkbox
        driver.findElement(By.xpath(weekdayRadioButton)).click();

        // Verify that days input now disabled
        Assert.assertTrue(value.isEnabled()==false);

        // Verify summary says Daily every weekday
        Assert.assertTrue(Driver.getDriver().findElement(By.xpath(summary)).getText().equals("Daily, every weekday"));
    }

    @Test(priority = 2, description = "boundary value analysis")
    public void TestCase_3() {
        SeleniumUtils.wait(2);
        driver.findElement(By.cssSelector(dailyLocator)).click();
        WebElement dayBox = driver.findElement(By.cssSelector(dailyLocator));
        for(int i=-2; i<1; i++) {
            dayBox.click();
            dayBox.clear();
            dayBox.sendKeys(String.valueOf(i));
            SeleniumUtils.wait(1);
            String errorMessage = driver.findElement(By.xpath(eMessage)).getText();
            Assert.assertTrue(errorMessage.equals("The value have not to be less than 1."));
        }

        for(int i=100; i<103; i++) {
            dayBox.click();
            dayBox.clear();
            dayBox.sendKeys(String.valueOf(i));
            SeleniumUtils.wait(1);
            String errorMessage = driver.findElement(By.xpath(eMessage)).getText();
            Assert.assertTrue(errorMessage.equals("The value have not to be more than 99."));
        }

        // Verify that error messages disappear when valid values are entered
        dayBox.click();dayBox.clear();dayBox.sendKeys("23");
        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='validation-failed']")).getText().equals(""));

    }
}
