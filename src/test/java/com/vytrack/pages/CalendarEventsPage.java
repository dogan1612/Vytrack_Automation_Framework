package com.vytrack.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;

/*
User Story : As a user, when I already created an opportunity in CRM module of Bribe ERP application,
             I want to be able to delete it.
Acceptance Criteria :
1.	Verify that user should be able to see the list view.
2.	Verify that user should be able to delete the opportunity from action drop down list .
*/

public class CalendarEventsPage {
    private WebDriver driver = Driver.getDriver();
    public String createCalendarEventBtnLocator = "a[title='Create Calendar event']";
    public String repeatCheckBoxLocator = "input[id^='recurrence-repeat-view']";
    public String repeatsDropdownLocator = "select[id^='recurrence-repeats-view']";

    //    let's write a method that would return collection of repeat options
    public List<String> getRepeatOptions(){
        //we crated select object to work with dropdown
        Select select = new Select(driver.findElement(By.cssSelector(repeatsDropdownLocator)));
        //.getOptions(); return all available options in the dropdown.
        //every option is a webelement
        List<WebElement> options = select.getOptions();
        //this is a collection that will store text of every option
        List<String> optionsTextList = new ArrayList<>();
        for(WebElement option: options){
            //go through every option and retrieve text
            //add that text into collection of text options
            optionsTextList.add(option.getText());
        }
        return  optionsTextList;
    }
}
