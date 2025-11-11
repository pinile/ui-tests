import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pobeda.base.BaseTest;

import java.time.Duration;
import java.util.List;

public class UiTests extends BaseTest {

    @Test
    public void pikabuTest() {
        String url = "https://www.pikabu.ru/";
        String expectedTitle = "Горячее – самые интересные и обсуждаемые посты | Пикабу";
        String wrongCredentialsMessage = "Ошибка. Вы ввели неверные данные авторизации";

        driver.get(url);

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

        assert element != null;
        Assertions.assertThat(element.getText()).isEqualTo(wrongCredentialsMessage);
    }

    @Test
    public void googleTest() throws InterruptedException {
        String url = "https://www.flypobeda.ru/";
        String expextedText = "Полетели в Калининград!";
        List<String> expectedTexts = List.of("Ticket search", "Online check-in", "Manage my booking");

        driver.get(url);
        Thread.sleep(5000);

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Полетели в Калининград!\"]")));
        driver.findElement(By.xpath("//button[@data-idx=\"10\"]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));

        Assertions.assertThat(element.getText()).isEqualTo(expextedText);

        driver.findElement(By.xpath("//button[@type=\"button\" and normalize-space(.)=\"РУС\"]")).click();

        driver.findElement(By.xpath("//div[@role=\"menuitem\" and text()=\"English\"]")).click();

        List<WebElement> englishButtons = driver.findElements(By.cssSelector("div[class*=\"tabsControlList\"] button"));
        List<String> actualTexts = englishButtons.stream()
                .map(WebElement::getText)
                .toList();

        Assertions.assertThat(actualTexts).containsExactlyInAnyOrderElementsOf(expectedTexts);
    }
}
