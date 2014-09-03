
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RedmineTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  //@Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver(new FirefoxBinary(new File("c:\\tools\\mozilla-firefox-27\\firefox.exe")),null);
    baseUrl = "https://redmine.zapix.com.br/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();

  }

  //@Test
  public void testRedmine() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.linkText("Sign in")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("arthur.zavadski");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("senha");
    driver.findElement(By.name("login")).click();
    driver.findElement(By.linkText("Projects")).click();
    driver.findElement(By.linkText("EzERP Pro")).click();
    driver.findElement(By.linkText("Issues")).click();
    driver.findElement(By.linkText("Issues by status")).click();
    driver.findElement(By.linkText("Sign out")).click();
  }

  //@After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
