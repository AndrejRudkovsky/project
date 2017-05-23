import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.concurrent.TimeUnit;
import static com.codeborne.selenide.Configuration.timeout;

/**
 * Created by Alex on 04.04.2016.
 */
public class WebDriverTestBase {
    protected WebDriver driver;

    @Before
    public void setUp()
    {
        //FirefoxBinary binary = new FirefoxBinary(new File("/home/kslisenko/Environment/firefox17/firefox"));

        FirefoxProfile profile = new FirefoxProfile();

        // Настройка поведения WebDriver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
        capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
        capabilities.setBrowserName(BrowserType.FIREFOX);

        driver = new FirefoxDriver(null, profile, capabilities);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        //--------------------------------------------------------------------------------------------------------------
        // Инициализируем Selenide нашим экземпляром WebDriver
        WebDriverRunner.setWebDriver(driver);
        System.out.println(">>> The static instance of Selenium WebDriver was initialized.");
        // Инициализируем время ожидания по умолчанию в миллисекундах для Selenide
        timeout = 30000;
        //--------------------------------------------------------------------------------------------------------------


    }

    @After
    public void tearDown() {
        driver.close();
    }
}
