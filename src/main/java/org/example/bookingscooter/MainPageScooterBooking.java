package org.example.bookingscooter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class MainPageScooterBooking {//главная страница где лежит FAQ и кнопки Заказать
    private WebDriver driver;
    private By bookingButtonUp = By.cssSelector(".Header_Nav__AGCXC button.Button_Button__ra12g");//кнопка Заказать вверху страницы
    //private By bookingButtonDown = By.cssSelector(".Home_FinishButton__1_cWm button");//кнопка Заказать внизу страницы
    //private boolean isbookingButtonUp; //условие когда Заказ оформляем через верхнюю кнопку
    private By cookieButton = By.id("rcc-confirm-button");//кнопка принять куки на странице
    public void acceptCookie(){//метод принять куки
        driver.findElement(cookieButton).click();
    }


    public MainPageScooterBooking(WebDriver driver){
        this.driver = driver;
    }

    public void clickBookingButton() {//метод для клика по кнопке Заказать
            driver.findElement(bookingButtonUp).click();

        }

    }






