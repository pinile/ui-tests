import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UiTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/PLitvinov/Downloads/chromedriver-win64/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(20))
                .pageLoadTimeout(Duration.ofSeconds(20))
                .scriptTimeout(Duration.ofSeconds(20));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }

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
        String url = "https://www.google.com/";
        String expextedText = "Полетели в Калининград!";
        List<String> expectedTexts = List.of("Ticket search", "Online check-in", "Manage my booking");

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[aria-label=\"Найти\"]")));
        Thread.sleep(2000);

        assert searchBox != null;
        searchBox.sendKeys("Сайт компании Победа");
        searchBox.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("h3")).click();

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()=\"Полетели в Калининград!\"]")));
        driver.findElement(By.xpath("//button[@data-idx=\"10\"]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(element));

        Assertions.assertThat(element.getText()).isEqualTo(expextedText);
/*

        driver.findElement(By.cssSelector("button[aria-label=\"Меню\"]")).click();
        driver.findElement(By.cssSelector("button[aria-label=\"Меню\"]")).click();

*/
        driver.findElement(By.xpath("//button[@type=\"button\" and normalize-space(.)=\"РУС\"]")).click();

        driver.findElement(By.xpath("//div[@role=\"menuitem\" and text()=\"English\"]")).click();

        List<WebElement> englishButtons = driver.findElements(By.cssSelector("div[class*=\"tabsControlList\"] button"));
        List<String> actualTexts = englishButtons.stream()
                .map(WebElement::getText)
                .toList();

        Assertions.assertThat(actualTexts).containsExactlyInAnyOrderElementsOf(expectedTexts);
    }
}
