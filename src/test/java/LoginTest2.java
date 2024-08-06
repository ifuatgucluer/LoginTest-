import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

    public class LoginTest2 {
        private WebDriver driver;

        @BeforeMethod
        public void setUp() {
            // ChromeDriver yolunu ayarlayın (kendi yolunuza göre düzenleyin)
            System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

            // ChromeOptions ile başlatma
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

            // Uygulama URL'sine git
            driver.get("https://example.com/login");
        }

        @Test
        public void testIncorrectLogin() {
            // Kullanıcı adı ve şifre alanlarını bul
            WebElement usernameField = driver.findElement(By.id("username"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            // Yanlış kullanıcı adı ve şifre gir
            usernameField.sendKeys("yanlisKullanici");
            passwordField.sendKeys("yanlisSifre");
            loginButton.click();

            // Hata mesajını kontrol et
            WebElement errorMessage = driver.findElement(By.id("errorMessage"));
            String expectedMessage = "Geçersiz kullanıcı adı veya şifre.";
            String actualMessage = errorMessage.getText();

            Assert.assertEquals(actualMessage, expectedMessage, "Hata mesajı beklenen ile eşleşmiyor.");
        }

        @AfterMethod
        public void tearDown() {
            // Tarayıcıyı kapat
            if (driver != null) {
                driver.quit();
            }
        }
    }

