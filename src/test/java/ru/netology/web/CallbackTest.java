package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallbackTest {
    WebDriver driver;

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp1() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void close() {
        driver.quit();
        driver = null;
    }


    @Test
    public void shouldLastNameWithDash() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Лебедева-Парасюк Мария");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79060410000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
    @Test
    public void shouldNameAndLastNameWithDashe() {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Лебедева-Парасюк Мария-Анна");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79060410000");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();

        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
//        @Test
//        public void shouldBeACheckOfWritingOnlyRussian() {
//            driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Lebedeva Mariia");
//            driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79060410000");
//            driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//            driver.findElement(By.tagName("button")).click(); // Отправить
//
//            String text = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
//            assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
//        }



}

