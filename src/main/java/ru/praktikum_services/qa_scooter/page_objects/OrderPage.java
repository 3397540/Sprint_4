package ru.praktikum_services.qa_scooter.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage extends BasePage {

    private String metroStation;
    private int day;
    private String rentPeriod;
    private String colour;
    private String comment;

    public OrderPage(WebDriver driver) {
        super(driver);
    }
    //Page Locators

    //Name input field
    private final By nameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Surname input field
    private final By surnameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    //Address input field
    private final By addressInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Metro station select field
    private final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //Phone input field
    private final By phoneInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Metro stations drop-down list item
    private String metroListItemLocator = ".//div[text()='%s']";

    //Next Button
    private final By nextButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");

    //Rent Details Screen
    //Delivery Date select field
    private final By deliveryDateSelect = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Rent Period select field
    private final By rentPeriodSelect = By.xpath(".//div[text()='* Срок аренды']");
    //Colour select checkboxes
    private final String colourCheckBox = ".//input[@id='%s']";

    //Commentary input field
    private final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Month locators for future use - please disregard!
    //Current Month display
    //private final By currentMonthDisplay = By.xpath(".//div[@class='react-datepicker__current-month']");
    //Next Month button
    //private final By nextMonthButton = By.xpath(".//button[@aria-label='Next Month']");
    //Previous Month button
    //private final By previousMonthButton = By.xpath(".//button[@aria-label='Previous Month']");

    //Day select button
    private String daySelect = ".//div[text()='%d']";
    //Rent period dropdown item
    private String rentPeriodDropdownItem = ".//div[@class='Dropdown-option' and text()='%s']";


    //Order Button
    private final By orderButton = By.
            xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //Confirm Button
    private final By confirmButton = By.
            xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    //Check order status button
    private final By checkOrderStatusButton = By.
            xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Посмотреть статус']");

    //Page Methods

    //TODO Create a method, that selects a month by clicking nextMonthButton or previousMonthButton
    // depending on month input in test parameters when i have some free time

    //Get completed order modal text
    public boolean isCheckOrderStatusButtonIsShown() {
        WebElement orderCompletedModalElement = driver.findElement(checkOrderStatusButton);
        checkVisibilityAndScroll(orderCompletedModalElement);
        return orderCompletedModalElement.isDisplayed();
    }

    //Populate Rent Details
    public void populateRentDetails(int day, String rentPeriod, String colour, String comment) {
        setDeliveryDate(day);
        selectRentPeriod(rentPeriod);
        selectScooterColour(colour);
        populateCommentary(comment);
        clickOrderButton();
    }
    //Populate commentary
    public void populateCommentary(String comment) {
        setComment(comment);
        WebElement commentInputElement = driver.findElement(commentInput);
        checkVisibilityAndScroll(commentInputElement);
        commentInputElement.sendKeys(comment);
    }
    //Set comment
    public void setComment(String comment) {
        this.comment = comment;
    }

    //Select scooter colour
    public void selectScooterColour(String colour) {
        setColour(colour);
        WebElement colourCheckBoxElement = driver.findElement(By.xpath(String.format(colourCheckBox, colour)));
        checkVisibilityAndScroll(colourCheckBoxElement);
        colourCheckBoxElement.click();
    }
    //Set colour
    public void setColour(String colour) {
        this.colour = colour;
    }

    //Select Rent Period
    public void selectRentPeriod(String rentPeriod) {
        setRentPeriod(rentPeriod);
        WebElement rentPeriodDropdownElement = driver.findElement(rentPeriodSelect);
        checkVisibilityAndScroll(rentPeriodDropdownElement);
        rentPeriodDropdownElement.click();
        WebElement rentPeriodDropdownItemElement = driver.findElement(By.xpath(String.
                format(rentPeriodDropdownItem, rentPeriod)));
        checkVisibilityAndScroll(rentPeriodDropdownItemElement);
        rentPeriodDropdownItemElement.click();
    }
    //Set rentPeriod
    public void setRentPeriod(String rentPeriod) {
        this.rentPeriod = rentPeriod;
    }

    //Set Delivery Date
    public void setDeliveryDate(int day) {
        setDay(day);
        WebElement dateSelector = driver.findElement(deliveryDateSelect);
        checkVisibilityAndScroll(dateSelector);
        dateSelector.click();
        WebElement daySelector = driver.findElement(By.xpath(String.format(daySelect, day)));
        checkVisibilityAndScroll(daySelector);
        daySelector.click();
    }
    //Set day
    public void setDay(int day) {
        this.day = day;
    }

    //Click Order Button
    public void clickOrderButton() {
        WebElement orderButtonElement = driver.findElement(orderButton);
        checkVisibilityAndScroll(orderButtonElement);
        orderButtonElement.click();
    }

    //Click confirm button
    public void clickConfirmButton() {
        WebElement confirmButtonElement = driver.findElement(confirmButton);
        checkVisibilityAndScroll(confirmButtonElement);
        confirmButtonElement.click();
    }

    //Populate Order fields
    public void inputData(String name, String surName, String address, String phone) {
        WebElement nameElement = driver.findElement(nameInput);
        checkVisibilityAndScroll(nameElement);
        nameElement.sendKeys(name);
        WebElement surnameElement = driver.findElement(surnameInput);
        checkVisibilityAndScroll(surnameElement);
        surnameElement.sendKeys(surName);
        WebElement addressElement = driver.findElement(addressInput);
        checkVisibilityAndScroll(addressElement);
        addressElement.sendKeys(address);
        WebElement phoneElement = driver.findElement(phoneInput);
        checkVisibilityAndScroll(phoneElement);
        phoneElement.sendKeys(phone);
    }
    //Select metro station
    public void selectMetroStation(String metroStation) {
        setMetroStation(metroStation);
        WebElement metroInputElement = driver.findElement(metroInput);
        checkVisibilityAndScroll(metroInputElement);
        metroInputElement.click();
        WebElement metroListItemElement = driver.findElement(By.xpath(String.format(metroListItemLocator, metroStation)));
        checkVisibilityAndScroll(metroListItemElement);
        metroListItemElement.click();
    }
    //Set metroStation
    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }
    //Click Next button
    public void clickNextButton() {
        WebElement buttonElement = driver.findElement(nextButton);
        checkVisibilityAndScroll(buttonElement);
        buttonElement.click();
    }

}
