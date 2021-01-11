import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.security.Key;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class bookingTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, 3);

    String URL = "http://www.booking.com";
    String destination = "Paris";
    String hotel = "Novotel Paris Les Halles";
    String hotelNameCssLocator = ".sr_item.sr_item_new.sr_item_default.sr_property_block.sr_flex_layout.sr_item--highlighted.with_dates" +
            ".soldout_property>div:nth-child(2)>div:nth-child(1)>div:nth-child(1)>div:nth-child(1)>.sr-hotel__title";

    @Before
    public void openApp() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
        System.out.println("1. Open www.booking.com");
    }

    @Test
    public void test1() {

        // Wait element for destination field
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#ss")));

        driver.findElement(By.cssSelector("#ss")).sendKeys(destination);
        System.out.println("2. Enter destination");

        driver.findElement(By.cssSelector(".xp__dates.xp__group")).click();
        for (int i = 1; i <= 8; i++) {
            driver.findElement(By.cssSelector(".bui-calendar__control.bui-calendar__control--next")).click();
        }
        driver.findElement(By.cssSelector(".bui-calendar__content>div:nth-child(2)>table>tbody>tr:nth-child(4)>td:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".bui-calendar__content>div:nth-child(2)>table>tbody>tr:nth-child(4)>td:nth-child(6)")).click();
        System.out.println("4. Enter check in and check out date");

        driver.findElement(By.cssSelector(".xp__input-group.xp__guests")).click();
        driver.findElement(By.cssSelector(".sb-group__field.sb-group-children>div>div:nth-child(2)>.bui-button.bui-button--secondary.bui-stepper__add-button")).click();
        System.out.println("5. Define family members");

        driver.findElement(By.cssSelector(".sb-searchbox__button")).click();
        System.out.println("6. Click on the search button");

        driver.findElement(By.cssSelector("#ss")).clear();
        driver.findElement(By.cssSelector("#ss")).sendKeys(hotel, Keys.ENTER);
        System.out.println("7. Search for the hotel");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(hotelNameCssLocator)));

        String hotelName = driver.findElement(By.cssSelector(hotelNameCssLocator)).getText();
        Assert.assertEquals(driver.findElement(By.cssSelector(hotelNameCssLocator)).getText(), hotelName);
        System.out.println(hotelName);

    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
