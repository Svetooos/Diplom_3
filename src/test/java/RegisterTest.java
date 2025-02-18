import api.UserApi;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import util.DriverConfiguration;
import util.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    private final DriverConfiguration driverConfiguration = new DriverConfiguration();
    private final UserApi userApi = new UserApi();
    private WebDriver driver;
    private String token;

    @Before
    public void init() {
        driver = driverConfiguration.getWebDriver();
        driver.get(URL.REGISTER_URL);
    }

    private final User user = new User((RandomStringUtils.randomAlphabetic(10) + "@yandex.ru").toLowerCase(),
            RandomStringUtils.randomAlphabetic(8),
            RandomStringUtils.randomAlphabetic(8));

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void register_success() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        boolean actual = loginPage.checkLoginButtonIsDisplayed();
        token = userApi.loginUser(user).getAccessToken();

        assertTrue(actual);
    }

    @Test
    @DisplayName("Регистрация пользователя с паролем меньше 6 символов")
    public void register_wrongPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(user.getName(), user.getEmail(), RandomStringUtils.randomAlphabetic(5));
        assertEquals("Некорректный пароль", registerPage.getErrorText());
    }

    @After
    public void teardown() {
        driver.quit();

        if (token != null && !token.isEmpty()) {
            userApi.deleteUser(token);
        }
    }
}