package ru.praktikum_services.qa_scooter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.page_objects.MainPage;
import ru.praktikum_services.qa_scooter.page_objects.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests extends BaseTests {

    //Use "chrome" for ChromeDriver and "firefox" for FirefoxDriver
    private final String browser = "chrome";
    //Set page url
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;
    private final String orderButtonName;
    private final int day;
    private final String rentPeriod;
    private final String colour;
    private final String comment;


    public OrderTests(String name, String surname, String address, String metroStation, String phone, String orderButtonName, int day, String rentPeriod, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.orderButtonName = orderButtonName;
        this.day = day;
        this.rentPeriod = rentPeriod;
        this.colour = colour;
        this.comment = comment;
        driver = getWebDriver(browser);
        driver.get(url);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Иван", "Петров", "г.Москва, ул. Кузнецкий Мост, 22", "Лубянка", "81234567890", "Header", 11, "сутки", "black", "Оставьте у двери"},
                {"Петр", "Иванов", "г.Москва, ул. 8 Марта, 1, стр. 1", "Динамо", "80987654321", "Body", 29, "пятеро суток", "grey", "Не звоните в звонок, пожалуйста!"},
        };
    }

    @Test
    public void testOrderIsSuccessful() {
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookies();
        mainPage.clickOnOrderButtons(orderButtonName);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.inputData(name, surname, address, phone);
        orderPage.selectMetroStation(metroStation);
        orderPage.clickNextButton();
        orderPage.populateRentDetails(day, rentPeriod, colour, comment);
        orderPage.clickConfirmButton();
        assertTrue(orderPage.isCheckOrderStatusButtonIsShown());
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
