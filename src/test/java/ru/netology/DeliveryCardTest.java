package ru.netology;

import ru.netology.CityCreater;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static org.openqa.selenium.By.cssSelector;

class DeliveryCardTest {
    String rightDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    String dateMore3 = LocalDate.now().plusDays(14).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    String dateLess3 = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    String datePast = LocalDate.now().plusDays(-5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    String buttonText = "Запланировать";
    CityCreater cityCreater = new CityCreater();

    /* Tests on different variants of city input */
    @Test
    void shouldSubmitDeliveryWhenAllFieldsRight() {

        String cityFromList = cityCreater.getCity();
        open("http://localhost:9999");
        $("[data-test-id= city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+71231234123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        $(withText("Успешно!")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotSubmitDeliveryWhenCityNotExist() {
        open("http://localhost:9999/");
        $("[data-test-id= city] input").setValue("неизвестный город");
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+71231234123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='city'] span.input__sub").waitUntil(visible, 15000).getText();
        assertEquals("Доставка в выбранный город недоступна", getText.trim());
    }

    @Test
    void shouldNotSubmitDeliveryWhenCityIsEmpty() {
        open("http://localhost:9999/");
        $("[data-test-id= city] input").setValue("");
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+71231234123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='city'] span.input__sub").waitUntil(visible, 15000).getText();
        assertEquals("Поле обязательно для заполнения", getText.trim());
    }

    /*Tests on data input*/


    @Test
    void shouldNotSubmitWhenDataLessThreeDaysLaterAfterToday() {
        open("http://localhost:9999/");
        String cityFromList = cityCreater.getCity();
        $("[data-test-id=city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(dateLess3);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+71231234123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $(".input_invalid .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", getText);


    }

    @Test
    void shouldNotSubmitIfCheckboxUnSelected() {
        open("http://localhost:9999/");
        String cityFromList = cityCreater.getCity();
        $("[data-test-id= city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+71231234123");
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='agreement'].input_invalid").getCssValue("color");
        assertEquals("rgba(255, 92, 92, 1)", getText);


    }

}

