package at.positionsapp.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Locale;
import java.util.function.Function;

public class Browser {

    public static WebDriver driver;

    // Variables
    private String user = "iss@agilethought.com";
    private String pwd = "NewPassword!";

    // Locators
    By emailTextBox = By.xpath("//input[@type='email']");
    By emailNextBttn = By.xpath("//input[@type='submit']");
    By aTLogoPwd = By.id("companyLogo");
    By pwdTextBox = By.xpath("//input[@type='password']");
    By loginBttn = By.id("submitButton");
    By activeSessionBttnYes = By.id("idSIButton9");
    By backlogPage = By.tagName("h2");


    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://positionsapp-uat.azurewebsites.net/#");
    }

    public void setUp(String browser) throws Exception {
        switch (browser.toLowerCase(Locale.ROOT)){
            case("chrome"):
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case("firefox"):
                System.setProperty("webdriver.gecko.driver", "./src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case("edge"):
                System.setProperty("webdriver.edge.driver", "./src/test/resources/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Browser not supported!");
                break;
        }
        driver.manage().window().maximize();
        driver.get("https://positionsapp-uat.azurewebsites.net/#");
    }

    // Uses default login
    public void appLogin() throws InterruptedException {
        waitForElement(emailTextBox);
        driver.findElement(emailTextBox).sendKeys(user);
        driver.findElement(emailNextBttn).click();
        waitForElement(aTLogoPwd);
        driver.findElement(pwdTextBox).sendKeys(pwd);
        driver.findElement(loginBttn).click();
        waitForElement(activeSessionBttnYes);
        driver.findElement(activeSessionBttnYes).click();
        waitForElement(backlogPage);
    }

    // Uses default login
    public void appLogin(String user, String pwd) throws InterruptedException {
        waitForElement(emailTextBox);
        driver.findElement(emailTextBox).sendKeys(user);
        driver.findElement(emailNextBttn).click();
        waitForElement(aTLogoPwd);
        driver.findElement(pwdTextBox).sendKeys(pwd);
        driver.findElement(loginBttn).click();
        waitForElement(activeSessionBttnYes);
        driver.findElement(activeSessionBttnYes).click();
        waitForElement(backlogPage);
    }

    public void waitForElement(final By element) {
        // Waiting for 5 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(5L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);
        WebElement find = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(element);
            }
        });
    }

}
