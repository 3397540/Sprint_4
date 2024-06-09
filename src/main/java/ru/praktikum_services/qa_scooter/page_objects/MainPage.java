package ru.praktikum_services.qa_scooter.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Main Page URL
    private String mainPageUrl = "https://qa-scooter.praktikum-services.ru/";

    //Question Link at accordion section
    private String questionLink = ".//div[@id='accordion__heading-%d']";

    //Answer Link at accordion section
    private String answerLink = ".//div[@id='accordion__panel-%d']/p";

    //Header 'Order' button locator
    private final By headerOrderButton = By
            .xpath(".//button[@class='Button_Button__ra12g' and text()='Заказать']");
    //Body 'Order' button locator
    private final By bodyOrderButton = By
            .xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //Click on one of Order buttons
    public void clickOnOrderButtons(String orderButtonName)  {
        if (orderButtonName.equalsIgnoreCase("Header")) {
            clickHeaderOrderButton();
        } else if (orderButtonName.equalsIgnoreCase("Body")) {
            clickBodyOrderButton();
        } else {
            throw new RuntimeException("Incorrect Order Button Name: " + orderButtonName);
        }
    }
    //Click on upper (header) Order button
    public void clickHeaderOrderButton() {
        //Find Button element
        WebElement headerButtonElement = driver.findElement(headerOrderButton);
        //If Button element is not visible - scroll till it fully shown
        checkVisibilityAndScroll(headerButtonElement);
        //Click button
        headerButtonElement.click();
    }
    //Click on middle (body) Order button
    public void clickBodyOrderButton() {
        //Find Button element
        WebElement bodyButtonElement = driver.findElement(bodyOrderButton);
        //If Button element is not visible - scroll till it fully shown
        checkVisibilityAndScroll(bodyButtonElement);
        //Click button
        bodyButtonElement.click();
    }

    //Get Answer text
    public String getAnswerText(int questionNumber) {
        //Find Question element
        WebElement question = driver.findElement(By.xpath(String.format(questionLink, questionNumber)));
        //If Question element is not visible - scroll till it fully shown
        checkVisibilityAndScroll(question);
        //Click on Question element
        question.click();
        //Find Answer element
        WebElement answer = driver.findElement(By.xpath(String.format(answerLink, questionNumber)));
        //If Answer element is not visible - scroll till it fully shown
        checkVisibilityAndScroll(answer);
        //Return Answer element text
        return driver.findElement(By.xpath(String.format(answerLink, questionNumber))).getText();
    }
}
