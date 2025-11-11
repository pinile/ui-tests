package pobeda.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "header")
    WebElement header;

    @FindBy(css = "img")
    WebElement logo;


    @FindBy(xpath = "//a[@href=\"/information\"]")
    WebElement triggerInformation;

    @FindBy(xpath = "//a[@href=\"/information#flight\"]")
    WebElement dropDownInformationFlight;

    @FindBy(xpath = "//a[@href=\"/information#useful\"]")
    WebElement dropDownInformationUseful;

    @FindBy(xpath = "//a[@href=\"/information#company\"]")
    WebElement dropDownInformationCompany;

    @FindBy(xpath = "//div[text()=\"Полетели в Калининград!\"]")
    WebElement goToKaliningrad;

    @FindBy(xpath = "//button[@data-idx=\"10\"]")
    WebElement buttonAd10;

    @FindBy(xpath = "//button[@type=\"button\" and normalize-space(.)=\"РУС\"]")
    WebElement buttonLanguage;

    @FindBy(xpath = "//div[@role=\"menuitem\" and text()=\"English\"]")
    WebElement dropDownMenuItemEnglishLanguage;

    @FindBy(xpath = "//span[text()=\"Ticket search\"]")
    WebElement buttonTicketSearchEnglish;

    @FindBy(xpath = "//span[text()=\"Online check-in\"]")
    WebElement buttonOnlineCheckInEnglish;

    @FindBy(xpath = "//span[text()=\"Manage my booking\"]")
    WebElement buttonManageMyBookingEnglish;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.flypobeda.ru/");
        wait.until(ExpectedConditions.visibilityOf(header));
        wait.until(ExpectedConditions.visibilityOf(logo));
    }

    public boolean isAdGoToKaliningradVisible() {
        buttonAd10.click();
        wait.until(ExpectedConditions.visibilityOf(goToKaliningrad));
        return goToKaliningrad.isDisplayed();
    }

    public void setEnglishLanguage() {
        buttonLanguage.click();
        dropDownMenuItemEnglishLanguage.click();
    }

    public void moveToTriggerInformation() {
        Actions actions = new Actions(driver);
        actions.moveToElement(triggerInformation).perform();
    }

    public String getText_buttonTicketSearchEnglish() {
        return buttonTicketSearchEnglish.getAttribute("textContent");
    }

    public String getText_buttonOnlineCheckInEnglish() {
        return buttonOnlineCheckInEnglish.getAttribute("textContent");
    }

    public String getText_buttonManageMyBookingEnglish() {
        return buttonManageMyBookingEnglish.getAttribute("textContent");
    }

    public String getText_dropDownInformationFlight() {
        return dropDownInformationFlight.getAttribute("textContent");
    }

    public String getText_dropDownInformationUseful() {
        return dropDownInformationUseful.getAttribute("textContent");
    }

    public String getText_dropDownInformationCompany() {
        return dropDownInformationCompany.getAttribute("textContent");
    }

}
