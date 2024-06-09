package ru.praktikum_services.qa_scooter.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookies() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.id("rcc-confirm-button")));
        if (driver.findElement(By.id("rcc-confirm-button")).isDisplayed()) {
            driver.findElement(By.id("rcc-confirm-button")).click();
        }
    }
    //Scroll to Element if it is not visible
    public void checkVisibilityAndScroll(WebElement element) {
        checkElementIsVisible(element);
        scrollToElement(element);
    }
    //Check if Element is Visible
    public boolean checkElementIsVisible(WebElement element) {
        return element.isDisplayed();
    }
    //Scrolling to Element
    public void scrollToElement(WebElement element) {
        WebElement elementToShow = element;
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", elementToShow);
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(elementToShow));
    }
}
