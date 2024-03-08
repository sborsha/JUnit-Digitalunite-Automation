import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnitOfDigitalunite {
    static WebDriver driver;
    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver ();
        driver.manage ().window ().maximize ();
        driver.manage ().timeouts ().implicitlyWait (Duration.ofSeconds (10));

    }
    @Test
    public void getImage() throws IOException {
        driver.get ("https://www.digitalunite.com/practice-webform-learners");
        Utils.getScreenshot (driver);
    }

    @Test
    public void submissionsData() throws InterruptedException {
        driver.get ("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement (By.id ("edit-name")).sendKeys ("Demo User");
        driver.findElement (By.id ("edit-number")).sendKeys ("01313572572");
        Utils.scroll (driver,0,700);
        WebElement element = driver.findElement(By.cssSelector("button[aria-label='Close']"));
        element.click();
        driver.findElements(By.className ("ui-checkboxradio-label")).get(0).click();
        WebElement date= driver.findElement (By.id ("edit-date"));
        date.click();

        date.sendKeys (Keys.CONTROL+"a", Keys.BACK_SPACE);
        date.sendKeys ("02/22/2024");
        driver.findElement (By.id ("edit-email")).sendKeys ("test@email.com");
        driver.findElement (By.id ("edit-tell-us-a-bit-about-yourself-")).sendKeys ("It's just for practice");
        driver.findElement (By.id ("edit-uploadocument-upload")).sendKeys (System.getProperty ("user.dir")+"./src/test/resources/screenshots/image.png");
        Utils.scroll(driver,0,550);
        Thread.sleep (2000);

        WebElement checkBox = driver.findElement(By.cssSelector("[type=checkbox]"));
        checkBox.click();
        WebElement submitBtn = driver.findElement(By.id("edit-submit"));
        submitBtn.click();
        Thread.sleep (3000);

        WebElement successMessage = driver.findElement(By.id("block-pagetitle-2"));
        assertEquals("Thank you for your submission!", successMessage.getText());


    }
    @AfterAll
    public static void closeDriver(){

        driver.quit ();
    }
}