import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class TestTdsMerchant2 {
  private WebDriver driver;
  private WebDriverWait wait;
  JavascriptExecutor js;

  @Before
  public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--disable-gpu");
    options.addArguments("--window-size=1302,816");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    js = (JavascriptExecutor) driver;
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Test
  public void test() {
    driver.get("https://dev-pf-igor.firisbe.com:8443");
    driver.manage().window().setSize(new Dimension(1302, 816));

    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).sendKeys("testmerchant2@firisbe.com");
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password"))).sendKeys("Aa123456!");
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(":r0:"))).sendKeys("1");
    driver.findElement(By.id(":r1:")).sendKeys("2");
    driver.findElement(By.id(":r2:")).sendKeys("3");
    driver.findElement(By.id(":r3:")).sendKeys("4");
    driver.findElement(By.id(":r4:")).sendKeys("5");
    driver.findElement(By.id(":r5:")).sendKeys("6");
    driver.findElement(By.id(":r5:")).sendKeys(Keys.ENTER);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("PaymentOperations-nav-group")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    wait.until(ExpectedConditions.elementToBeClickable(By.id("CommonPaymentPage-nav-link"))).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("createPayment.amount"))).sendKeys("0₺");
    driver.findElement(By.id("createPayment.basketAmount")).sendKeys("123₺");
    driver.findElement(By.id("createPayment.approve")).click();
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe")));
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.id("secure_common_payment:text.name"))).sendKeys("asf asg");
      driver.findElement(By.id("secure_common_payment:text.card_number")).sendKeys("5163 1030 0298 2563");
      driver.findElement(By.id("secure_common_payment:text.expiration_date")).sendKeys("12/26");
      driver.findElement(By.id("secure_common_payment:text.cvv")).sendKeys("000");
      driver.findElement(By.id("paymentAgreement")).click();
      driver.findElement(By.id("isThreeDSecure")).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.id("pay-button"))).click();
      wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-input"))).sendKeys("123456");
      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/button"))).click();
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h5[contains(text(), 'ödeme işleminiz başarıyla gerçekleştirildi')]")));
    }  catch (Exception e) {
      e.printStackTrace();
    } finally {
      driver.switchTo().defaultContent();
    }
    wait.until(ExpectedConditions.elementToBeClickable(By.id("user-dropdown-menu-avatar"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.id("user-dropdown-logout"))).click();
  }
}
