import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.bookingscooter.MainPageScooterBooking;
import org.example.bookingscooter.PageAboutOrderForm;
import org.example.bookingscooter.PageOrderScooter;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(Parameterized.class)
    public class ScooterBookingTests {
        private final static String URL_SCOOTER = "https://qa-scooter.praktikum-services.ru/";

        private final String name;
        private final String surname;
        private final String address;
        private final String telephone;
        private final String date;
        private final String comment;
        private final boolean isbookingButtonUp;
        WebDriver driver;

        public ScooterBookingTests(String name, String surname, String address, String telephone, String date, String comment, boolean isbookingButtonUp) {
            this.name = name;
            this.surname = surname;
            this.address = address;
            this.telephone = telephone;
            this.date = date;
            this.comment = comment;
            this.isbookingButtonUp = isbookingButtonUp;
        }


        @Parameterized.Parameters
        public static Object[][] getOrderData() {
            return new Object[][]{
                    {"Ольга", "Семенова", "Нагатинская набережная 14", "+79788283067", "25.06.2023", "Тест", true},
                    {"Дмитрий", "Семенов", "Нагатинский бульвар 6", "+79788283068", "26.06.2023", "Тест Тест", false},
            };
        }

        @Before
        public void setupAll() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().clearDriverCache().setup();
        driver = new FirefoxDriver();
        }


        @Test
        public void checkBookingScooter() {
            driver.get(URL_SCOOTER);
            MainPageScooterBooking objMainPageScooterBooking = new MainPageScooterBooking(driver);
            PageOrderScooter objPageOrderScooter = new PageOrderScooter(driver);
            PageAboutOrderForm objPageAboutOrderForm = new PageAboutOrderForm(driver);
            objMainPageScooterBooking.acceptCookie();
            objMainPageScooterBooking.clickBookingButton();
            objPageOrderScooter.makeOrder(name, surname, address, telephone);
            objPageOrderScooter.waitForLoadAboutOrderForm();
            objPageAboutOrderForm.fillAboutOrderForm(comment, date);
            objPageAboutOrderForm.waitForLoadConfirmationWindow();
            objPageAboutOrderForm.clickYesButton();
            objPageAboutOrderForm.checkFinalOrderWindow();
            String orderNumber = objPageAboutOrderForm.getNumberOfOrder();
            MatcherAssert.assertThat("Заказ не оформлен", orderNumber, notNullValue());
            System.out.println(orderNumber);


        }

        @After
        public void tearDown() {

            driver.quit();
        }


    }

