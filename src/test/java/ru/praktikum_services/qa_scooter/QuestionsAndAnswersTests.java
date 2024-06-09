package ru.praktikum_services.qa_scooter;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.page_objects.MainPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsAndAnswersTests extends BaseTests {

    //Use "chrome" for ChromeDriver and "firefox" for FirefoxDriver
    private final String browser = "chrome";
    //Set page url
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    private final int questionNumber;
    private final String expectedAnswer;

    public QuestionsAndAnswersTests(int questionNumber, String expectedAnswer) {
        this.questionNumber = questionNumber;
        this.expectedAnswer = expectedAnswer;
        driver = getWebDriver(browser);
        driver.get(url);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. " +
                        "Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                        "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если " +
                        "будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Test
    public void checkThatProperAnswerIsShown() {
        MainPage mainPage = new MainPage(driver);
        mainPage.closeCookies();
        String actualAnswer = mainPage.getAnswerText(questionNumber);
        assertEquals(expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
