package org.example.bookingscooter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageOrderScooter {//класс страницы "Для кого самокат", поля этого класса(первое модальное окошко)
    private WebDriver driver;
    private String name;//не поняла зачем убрать это поле, оно используется в методе который оформляет заказ (public void makeOrder(String name, String surname, String address,String telephone){//метод оформить заказ)
    private String surname;
    private String address;
    private String metro;
    private String telephone;
    private By nameInput = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *=' Имя']");
    private By surnameInput = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *=' Фамилия']");
    private By addressInput = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *=' Адрес: куда привезти заказ']");
    private By metroStationList = By.cssSelector(".select-search__value input[placeholder *=' Станция метро']");
    private By telephoneInput = By.cssSelector(".Input_InputContainer__3NykH input[placeholder *= 'Телефон']");
    private By continuingOrderButton = By.cssSelector(".Order_NextButton__1_rCA button");//кнопка Далее при оформлении заказа
    private By orderPage = By.xpath("//div[@class='Order_Content__bmtHS']"); //локатор для отображения следующей страницы при оформлении заказа

    public PageOrderScooter (WebDriver driver){
        this.driver = driver;
    }

    public PageOrderScooter(String name, String surname, String address, String metro, String telephone){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.telephone = telephone;

    }
    public void clickContinuingOrderButton(){
        driver.findElement(continuingOrderButton).click();
    }
    public void setName(String name){//ввели имя в поле Имя
        driver.findElement(nameInput).sendKeys(name);
    }
    public void setSurname(String surname){//ввели фамилию в поле Фамилия
        driver.findElement(surnameInput).sendKeys(surname);
    }
    public void setAddress(String address){//ввели адрес в поле Адрес
        driver.findElement(addressInput).sendKeys(address);
    }
    public void setTelephone(String telephone){//ввели телефон в поле Телефон
        driver.findElement(telephoneInput).sendKeys(telephone);
    }
    private void chooseMetroStation() {//метод чтобы кликнуть по станции метро
        driver.findElement(metroStationList).click();
        List<WebElement> list = driver.findElements(By.xpath("//li[@class='select-search__row']"));
        list.get(0).click();
    }


    public void waitForLoadAboutOrderForm() {//метод ожидания прогрузки следующей страницы для оформления заказа
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(orderPage));
    }
    public void makeOrder(String name, String surname, String address,String telephone){//метод оформить заказ
        setName(name);
        setSurname(surname);
        setAddress(address);
        chooseMetroStation();
        setTelephone(telephone);
        clickContinuingOrderButton();

    }
}
