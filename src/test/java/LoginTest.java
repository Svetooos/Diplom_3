import api.UserApi;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import page.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DriverConfiguration;
import util.URL;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    private final DriverConfiguration driverConfiguration = new DriverConfiguration();
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String token;

    @Before
    public void init() {
        driver = driverConfiguration.getWebDriver();
        driver.get(URL.BASE_URL);
        token = userApi.createUser(user).getAccessToken();
    }

    private final User user = new User((RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Test
    @DisplayName("Авторизация через кнопку 'Войти в аккаунт' на главной странице")
    public void login_mainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        mainPage.clickLoginButton();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        String text = accountPage.getEmail();
        assertEquals(user.getEmail(), text);
    }

    @Test
    @DisplayName("Авторизация через кнопку 'Личный кабинет'")
    public void login_accountPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);

        mainPage.clickAccountButton();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        String text = accountPage.getEmail();
        assertEquals(user.getEmail(), text);
    }

    @Test
    @DisplayName("Авторизация через страницу кнопку 'Зарегистрироваться' в личном кабинете")
    public void login_registerPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickAccountButton();
        accountPage.clickRegisterButton();
        registerPage.clickEnter();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        String text = accountPage.getEmail();
        assertEquals(user.getEmail(), text);
    }

    @Test
    @DisplayName("Авторизация через форму 'Восстановления пароля'")
    public void login_passwordPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        PasswordPage passwordPage = new PasswordPage(driver);

        mainPage.clickLoginButton();
        loginPage.clickResetPassword();
        passwordPage.clickLogin();
        loginPage.setProfile(user.getEmail(), user.getPassword());
        mainPage.clickAccountButton();
        String text = accountPage.getEmail();
        assertEquals(user.getEmail(), text);
    }

    @After
    public void teardown() {
        driver.quit();

        if (token != null && !token.isEmpty()) {
            userApi.deleteUser(token);
        }
    }
}
