import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.faq.PageFAQScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTests {
    WebDriver driver;
    private final String faqQuestion;
    private final String faqAnswer;
    public QuestionsTests (String faqQuestion, String faqAnswer) {
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
    }

    @Parameterized.Parameters //тест-данные, вопросы: первый, посередине, последний
    public static Object[][] getFAQ() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setupAll() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();
    }
    @Test
    public void checkFAQTextOpen() { //тест проверяет что получили текст при клике
        driver.get("https://qa-scooter.praktikum-services.ru/");
        PageFAQScooter objPageFAQScooter = new PageFAQScooter(driver);
        assertEquals(faqAnswer, objPageFAQScooter.getQuestionsText(faqQuestion));
    }


    @After
    public void teardown() {
        driver.quit();
    }
}

