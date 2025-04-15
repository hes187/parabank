package test ;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegistrationTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToHomePage() {
        driver.get("https://www.demoblaze.com");
    }

    @Test
    public void TC_REG_001_RegisterWithValidCredentials() throws InterruptedException {
        openSignupModal();
        sendCredentials("user" + System.currentTimeMillis(), "Password123");
        submitAndVerifyAlert("Sign up successful.");
    }

    @Test
    public void TC_REG_002_RegisterWithExistingUsername() throws InterruptedException {
        openSignupModal();
        sendCredentials("existingUser", "Password123");
        submitAndVerifyAlert("This user already exist.");
    }

    @Test
    public void TC_REG_003_RegisterWithEmptyFields() throws InterruptedException {
        openSignupModal();
        sendCredentials("", "");
        submitAndVerifyAlert("Please fill out Username and Password.");
    }

    @Test
    public void TC_REG_004_RegisterWithEmptyUsername() throws InterruptedException {
        openSignupModal();
        sendCredentials("", "Password123");
        submitAndVerifyAlert("Please fill out Username and Password.");
    }

    @Test
    public void TC_REG_005_RegisterWithEmptyPassword() throws InterruptedException {
        openSignupModal();
        sendCredentials("user" + System.currentTimeMillis(), "");
        submitAndVerifyAlert("Please fill out Username and Password.");
    }

    @Test
    public void TC_REG_006_RegisterWithSpecialCharactersInUsername() throws InterruptedException {
        openSignupModal();
        sendCredentials("@!#$%", "Password123");
        submitAndVerifyAlert(); // check manually if accepted
    }

    @Test
    public void TC_REG_007_RegisterWithShortUsername() throws InterruptedException {
        openSignupModal();
        sendCredentials("a", "Password123");
        submitAndVerifyAlert(); // depends on validation
    }

    @Test
    public void TC_REG_008_RegisterWithShortPassword() throws InterruptedException {
        openSignupModal();
        sendCredentials("user" + System.currentTimeMillis(), "1");
        submitAndVerifyAlert(); // depends on validation
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void openSignupModal() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(1500);
    }

    private void sendCredentials(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        usernameField.clear();
        passwordField.clear();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
    }

    private void submitAndVerifyAlert(String expectedMessage) throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        Assert.assertTrue(alertText.contains(expectedMessage), "Expected alert: " + expectedMessage + ", but got: " + alertText);
        driver.switchTo().alert().accept();
    }

    private void submitAndVerifyAlert() throws InterruptedException {
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        Thread.sleep(2000);
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Received alert: " + alertText);
        driver.switchTo().alert().accept();
    }
}
