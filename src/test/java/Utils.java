import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Utils {
    public static void explicitWaitForTheElement(WebDriver driver, WebElement element, int SECOND) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SECOND));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
