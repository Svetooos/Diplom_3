package page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PasswordPage {
    private WebDriver driver;

    private final By LOGIN_BUTTON = By.xpath("//*[contains(text(),'Войти')]");

    public PasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step
    public void clickLogin() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        driver.findElement(LOGIN_BUTTON).click();
    }
}