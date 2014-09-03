
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CobrirLeilaoTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver(new FirefoxBinary(new File("c:\\tools\\mozilla-firefox-27\\firefox.exe")), null);
        //driver = new FirefoxDriver();
        baseUrl = "http://192.168.1.200:8080";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCobrirLeilao() throws Exception {
        driver.get(baseUrl + "/leilao/");
        driver.findElement(By.id("j_username")).clear();
        driver.findElement(By.id("j_username")).sendKeys("arthur");
        driver.findElement(By.id("j_password")).clear();
        driver.findElement(By.id("j_password")).sendKeys("senhaboa");
        driver.findElement(By.id("entrar")).click();
        driver.findElement(By.id("form:entrarUsuario")).click();
        driver.findElement(By.id("Formulario:tabelaLeiloes:0:participar")).click();
        for (int i = 0; i < 100; i++) {
            driver.findElement(By.id("Formulario:botaoCobrir")).click();
        }
    }

    @After
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
