package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryCardTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldRegisterByAccountNumberDOMModification() {
        open("http://localhost:9999");
        //  $$(".tab-item").find(exactText("По номеру счёта")).click();
        $("[data-test-id='city']").setValue("Казань");
        $("[data-test-id = 'date']").setValue("2020-06-20");
        $("[data-test-id= 'name']").setValue("Василий Пупкин");
        $("[data-test-id= 'phone']").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).waitUntil(visible, 5000);
        //$(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

/*    @Test
    void shouldRegisterByAccountNumberVisibilityChange() {
        open("http://localhost:9999");
    //    $$(".tab-item").find(exactText("По номеру счёта")).click();
        $$("[name='number']").last().setValue("4055 0100 0123 4613 8564");
        $$("[name='phone']").last().setValue("+792000000000");
        $$("button").find(exactText("Продолжить")).click();
        $(withText("Успешная авторизация")).waitUntil(visible, 5000);
        $(byText("Личный кабинет")).waitUntil(visible, 5000);
    }

*/
}

