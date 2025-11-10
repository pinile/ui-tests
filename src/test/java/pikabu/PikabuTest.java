package pikabu;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PikabuTest {

    WebDriver driver;
    String url = "https://www.pikabu.ru/";
    String expectedTitle = "Горячее – самые интересные и обсуждаемые посты | Пикабу";
    String wrongCredentialsMessage = "Ошибка. Вы ввели неверные данные авторизации";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/PLitvinov/Downloads/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

    @Test
    public void pikabuTest() throws InterruptedException {
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.getTitle() != null && !d.getTitle().isEmpty());

        Assertions.assertThat(driver.getCurrentUrl()).contains("pikabu.ru");
        Assertions.assertThat(driver.getTitle()).isEqualTo(expectedTitle);

        driver.findElement(By.cssSelector(".header-right-menu > button")).click();

        driver.findElement(By.cssSelector(".auth-modal")).isDisplayed();
        driver.findElement(By.cssSelector(".auth-modal > div .input__input")).isDisplayed();
        driver.findElement(By.cssSelector(".auth-modal input[name=\"username\"]")).isDisplayed();
        driver.findElement(By.cssSelector(".auth-modal input[name=\"password\"]")).isDisplayed();
        driver.findElement(By.cssSelector(".auth-modal button[type=\"submit\"]")).isDisplayed();

        driver.findElement(By.cssSelector(".auth-modal input[name=\"username\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector(".auth-modal input[name=\"password\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector(".auth-modal button[type=\"submit\"]")).click();

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup__container .auth__error.auth__error_top")));

        Assertions.assertThat(element.getText()).isEqualTo(wrongCredentialsMessage);
    }
}
