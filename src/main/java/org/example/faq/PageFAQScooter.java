package org.example.faq;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFAQScooter {
    private WebDriver driver;
    private final By questionField = By.className("accordion__heading");//поле аккореднона
    private final By questionText = By.className("accordion__panel");//текст под Аккордеоном после нажатия кнопки
    private String fieldQuestionsText;//тут сам текст вопроса в FAQ

    public PageFAQScooter(WebDriver driver) {
        this.driver = driver;
    }

    public String getQuestionsText(String fieldQuestionsText) {//метод который будет проверять соотвествие текста при клике
        //находим текст элемента FAQ
        WebElement accordionFAQElement = driver.findElement(By.xpath("//div[@class='accordion__button' and text()= '" + fieldQuestionsText + "']"));
        //скролим до него
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", accordionFAQElement);
        //ждем когда можно кликнуть на элемент и кликаем
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(accordionFAQElement));
        accordionFAQElement.click();
        //возвращаем полученный ответ и сравниваем его
        return accordionFAQElement.findElement(By.xpath("./../../div[@class='accordion__panel']/p")).getText();
    }
}
