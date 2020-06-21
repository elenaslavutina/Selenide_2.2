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

class DeliveryOnPhoneNumberTest {
    String rightDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
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
    void shouldNotSubmitWhenPhoneIsEmpty() {
        String cityFromList = cityCreater.getCity();
        open("http://localhost:9999");
        $("[data-test-id= city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='phone'] span.input__sub").getText();
        assertEquals("Поле обязательно для заполнения", getText.trim());
    }

    //тест не проходит так как это не проверяется программой
    @Test
    void shouldNotSubmitWhenPhoneNumberLess11() {
        String cityFromList = cityCreater.getCity();
        open("http://localhost:9999");
        $("[data-test-id= city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+34123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='phone'] span.input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", getText.trim());
    }

    //если вводим неверный номер телефона например +09998283231 то не должне проходить, так как после
    //+ должна быть 7, но проходит
    @Test
    void shouldNotSubmitWhenPhoneNotStartFromPlus7() {
        String cityFromList = cityCreater.getCity();
        open("http://localhost:9999");
        $("[data-test-id= city] input").setValue(cityFromList);
        $(cssSelector("[data-test-id=date] input")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $(cssSelector("[data-test-id=date] input")).doubleClick().sendKeys(Keys.DELETE);
        $(cssSelector("[data-test-id=date] input")).sendKeys(rightDate);
        $(By.xpath("//span[@data-test-id='name']/descendant::input[@class='input__control']")).sendKeys("Василий Пупкин");
        $("[data-test-id=phone] input").sendKeys("+01231234123");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText(buttonText)).click();
        String getText = $("[data-test-id='phone'] span.input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", getText);
    }


}



