
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page.MainPage;
import util.DriverConfiguration;
import util.URL;

public class TabTest {

    private final DriverConfiguration driverConfiguration = new DriverConfiguration();
    private WebDriver driver;

    @Before
    public void init() {
        driver = driverConfiguration.getWebDriver();
        driver.get(URL.BASE_URL);
    }

    @Test
    @DisplayName("Переход в раздел Соусы")
    public void clickTab_sauce() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauce();
        String text = mainPage.getCurrentTabText();
        Assert.assertEquals("Соусы", text);
    }

    @Test
    @DisplayName("Переход в раздел Булки")
    public void clickTab_buns() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSauce();
        mainPage.clickBuns();
        String text = mainPage.getCurrentTabText();
        Assert.assertEquals("Булки", text);
    }

    @Test
    @DisplayName("Переход в раздел Начинки")
    public void clickTab_filling() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFilling();
        String text = mainPage.getCurrentTabText();
        Assert.assertEquals("Начинки", text);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}

