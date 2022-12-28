import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class FirstClassJUnit {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void _1_getTitle() {
        driver.get("https://demoqa.com");
        String titleActual = driver.getTitle();
        String titleExpected = "ToolsQA";
        Assert.assertEquals(titleExpected, titleActual);
    }

    @Test
    public void _2_checkIfImageExists() {
        driver.get("https://demoqa.com");
//        implementation-1
//        WebElement imageElement = driver.findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']"));
//        boolean statusActual = imageElement.isDisplayed();
//        Assert.assertTrue(statusActual);
//        implementation-2
//        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']")).isDisplayed());
//        implementation-3
        WebElement imageElement = driver.findElement(By.xpath("//img[@src='/images/Toolsqa.jpg']"));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//        wait.until(ExpectedConditions.visibilityOf(imageElement));
//        implementation-4
        Utils.explicitWaitForTheElement(driver, imageElement, 40);
        Assert.assertTrue(imageElement.isDisplayed());
    }

    @After
    public void quit() {
        driver.quit();
    }
}
