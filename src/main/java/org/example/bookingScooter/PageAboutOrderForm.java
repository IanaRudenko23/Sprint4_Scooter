package org.example.bookingScooter;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageAboutOrderForm {//класс "Про аренду" (второе моадльное окошко при заказе скутера)
    private WebDriver driver;
    private By dateInput = By.cssSelector(".Order_MixedDatePicker__3qiay input[placeholder *= 'Когда']");//локатор для ввода даты в поле дата
    private By termInput = By.xpath(".//div[@class = 'Dropdown-placeholder']");//локатор для поля Срок аренды
    private By termList = By.xpath(".//div[@class = 'Dropdown-menu']");//меню с выбором срока аренды самоката
    private By termOption = By.xpath(".//div[@class = 'Dropdown-placeholder is-selected' and text()='сутки']");
    private By commentsInput = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Комментарий']");//поле комментарий
    private String comment;
    private String date;

    private By makeOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    private By orderConfirmationWindow = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Хотите оформить заказ?']");//модальное окошко с Подтверждение заказа
    private By yesConfirmationButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");//кнопка Да на окошке с подтверждением заказа
    private By finalOrderWindow = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and text() = 'Заказ оформлен']");
    private By bookingButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By orderNumber =By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text()='Заказ оформлен']/div[@class='Order_Text__2broi']");

    public PageAboutOrderForm (WebDriver driver){
        this.driver = driver;
    }

    public void setDate(String date) {// метод позволяет установить дату заказа
        driver.findElement(dateInput).sendKeys(date, Keys.ENTER);
    }

    public void setTerm() {//устанавливаем срок аренды самоката
        driver.findElement(termInput).click();
        driver.findElement(termList).click();
        //driver.findElement(termOption).click();

    }

    public void setComment (String comment){//ввели комментарий в поле Комментарии

        driver.findElement(commentsInput).sendKeys(comment);
    }

    public void fillAboutOrderForm (String comment, String date){//метод который заполняет вторую страницу оформления заказа
        setDate(date);
        setTerm();
        setComment(comment);
        driver.findElement(bookingButton).click();
    }

    public void waitForLoadConfirmationWindow() {//метод ожидания прогрузки модального окошка
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderConfirmationWindow));
    }

    public void clickYesButton (){ //жмем кнопку "Да" на модальном окошке
        driver.findElement(orderConfirmationWindow);
        driver.findElement(yesConfirmationButton).click();
    }

    public void checkFinalOrderWindow() {//Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.
        new WebDriverWait(driver, 10).until(driver -> (driver.findElement(finalOrderWindow).getText() != null
                && !driver.findElement(finalOrderWindow).getText().isEmpty()
        ));
    }

    public String getNumberOfOrder (){//текст о подтверждении заказа

       return driver.findElement(orderNumber).getText();
    }









}
