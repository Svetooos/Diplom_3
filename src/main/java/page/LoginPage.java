package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By HEADER_ENTER = By.xpath(".//h2[contains(text(), 'Вход')]");
    private final By EMAIL_FIELD = By.xpath(".//input[contains(@class, 'text') and @name='name']");
    private final By PASSWORD_FIELD = By.xpath(".//input[contains(@class, 'text') and @type='password']");
    private final By ENTER_BUTTON = By.xpath(".//button[text()='Войти']");
}