import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Class2JUnit {
    WebDriver driver;

    @Before
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headed");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void submitForm() {
        driver.get("https://demoqa.com/text-box");
        driver.findElement(By.id("userName")).sendKeys("Abdullah");
        driver.findElement(By.id("userEmail")).sendKeys("abdullah@test.com");
        driver.findElement(By.cssSelector("[id=currentAddress]")).sendKeys("Dhaka");
        //the next line will generate error since submit button is hidden under ads
//        driver.findElement(By.id("submit")).click();
    }

    @Test
    public void submitFormByUsingTag() {
        driver.get("https://demoqa.com/text-box");
        List<WebElement> textEelements = driver.findElements(By.tagName("input"));
        textEelements.get(0).sendKeys("Abdur Rahman");
        textEelements.get(1).sendKeys("ar@test.com");
    }

    @Test
    public void buttonClick() throws InterruptedException {
        driver.get("https://demoqa.com/buttons");
        Actions actions = new Actions(driver);
        //implementation -1
//        WebElement button1 = driver.findElement(By.id("doubleClickBtn"));
//        actions.doubleClick(button1).perform();
        //implementation - 2
        List<WebElement> buttons = driver.findElements(By.cssSelector("[type=button]"));
        actions.doubleClick(buttons.get(1)).perform();
        actions.contextClick(buttons.get(2)).perform();
        actions.click(buttons.get(3)).perform();

        List<WebElement> messages = driver.findElements(By.tagName("p"));
        String message1Actual = messages.get(0).getText();
        String message2Actual = messages.get(1).getText();
        String message3Actual = messages.get(2).getText();

//        String message1Expected = "You have done a double click";
        // we are using contains later, so this would work too
        String message1Expected = "double click";
        String message2Expected = "You have done a right click";
        String message3Expected = "You have done a dynamic click";
        // give some time to pop up message
        Thread.sleep(4000);

        Assert.assertTrue(message1Actual.contains(message1Expected));
        Assert.assertTrue(message2Actual.contains(message2Expected));
        Assert.assertTrue(message3Actual.contains(message3Expected));
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        // if we don't give delay, it will accept the alert very quickly
//        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        String messageActual = driver.findElement(By.id("confirmResult")).getText();
        String expectedMessage = "Cancel";

        Assert.assertTrue(messageActual.contains(expectedMessage));

        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("Abdus shakur");
        driver.switchTo().alert().accept();
    }

    @Test
    public void selectDate() {
        driver.get("https://demoqa.com/date-picker");
        driver.findElement(By.id("datePickerMonthYearInput")).clear();
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("10/17/2022");
        // implementation: we can close the date picker pop-up by using enter/return key
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys(Keys.ENTER);
    }

    @Test
    public void selectDateByKeyboard() {
        driver.get("https://demoqa.com/date-picker");
        Actions actions = new Actions(driver);
        WebElement datePicker = driver.findElement(By.id("datePickerMonthYearInput"));
        actions.moveToElement(datePicker);
        // implementation - 1: delete text from date picker
//        actions.doubleClick().
//                click().
//                keyDown(Keys.BACK_SPACE).
//                keyUp(Keys.BACK_SPACE)
//                .perform();
        // implementation - 2: delete text from date picker
        datePicker.sendKeys(Keys.CONTROL + "a");
        datePicker.sendKeys(Keys.BACK_SPACE);
    }

    @Test
    public void selectDropdown() {
        driver.get("https://demoqa.com/select-menu");
        WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
        Select option = new Select(dropdown);
        // implementation - 1 :
//        option.selectByIndex(1);
        // implementation - 2 :
//        option.selectByValue("2");
        // implementation - 3 :
        option.selectByVisibleText("Yellow");
    }

    @Test
    public void selectMultipleDropdown() {
        driver.get("https://demoqa.com/select-menu");
        WebElement dropdown = driver.findElement(By.name("cars"));
        Select option = new Select(dropdown);
        if (option.isMultiple()) {
            option.selectByVisibleText("Volvo");
            option.selectByVisibleText("Audi");
        }
    }

    @Test
    public void hoverMenu() {
        driver.get("https://green.edu.bd");
        // use xpath to get elements
        List<WebElement> aboutUsElements = driver.findElements(By.xpath("//a[contains(text(), 'About Us')]"));
        // implementation - 1: click element from hover
//        aboutUsElements.get(2).click();
        // implementation - 2: hover element from hover
        Actions actions = new Actions(driver);
        actions.moveToElement(aboutUsElements.get(2));
    }

    @Test
    public void takeScreenshot() throws IOException {
        driver.get("https://demoqa.com");
        Utils.takeScreenshot(driver);
    }

    @Test
    public void modalDialog() {
        driver.get("https://demoqa.com/modal-dialogs");
        driver.findElement(By.id("showSmallModal")).click();
        String text = driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        driver.findElement(By.id("closeSmallModal")).click();
    }

    @Test
    public void uploadImage() {
        driver.get("https://demoqa.com/upload-download");
        WebElement uploadElement = driver.findElement(By.id("uploadFile"));
        // give a file location from your directory
//        uploadElement.sendKeys("D:\\ad.png");
//        String text = driver.findElement(By.id("uploadedFilePath")).getText();
//        Assert.assertTrue(text.contains("ad.png"));
    }

    @Test
    public void downloadFile() {
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();
    }

    @After
    public void quit() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
}
