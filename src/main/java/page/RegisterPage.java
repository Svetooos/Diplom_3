package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;

    private final By NAME_FIELD = By.xpath(".//label[contains(text(), 'Имя')]/following-sibling::input");
    private final By EMAIL_FIELD = By.xpath(".//label[contains(text(), 'Email')]/following-sibling::input");
    private final By PASSWORD_FIELD = By.xpath(".//label[contains(text(), 'Пароль')]/following-sibling::input");
    private final By REGISTER_BUTTON = By.className("button_button__33qZ0");
    private final By ENTER_BUTTON = By.xpath(".//a[text()='Войти' and contains(@class, 'Auth_link')]");
    private final By ERROR = By.className("input__error");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public String getErrorText() {
        return driver.findElement(ERROR).getText();
    }

    @Step
    public void clickEnter() {
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegister();
    }

    @Step
    public void setName(String name) {
        driver.findElement(NAME_FIELD).clear();
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    @Step
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).clear();
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }

    @Step
    public void setPassword(String password) {
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }

    @Step
    public void clickRegister() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(REGISTER_BUTTON));
        driver.findElement(REGISTER_BUTTON).click();
    }
}