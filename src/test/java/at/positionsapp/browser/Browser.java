package at.positionsapp.browser;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Locale;
import java.util.function.Function;

public class Browser {

    public static WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Temp variables for login
    final String user = "iss@agilethought.com";
    final String pwd = "NewPassword!";

    // Locators
    By emailTextBox = By.xpath("//input[@type='email']");
    By emailNextBttn = By.xpath("//input[@type='submit']");
    By atLogo = By.id("companyLogo");
    By pwdTextBox = By.xpath("//input[@type='password']");
    By pwdLoginBttn = By.xpath("//span[@class='submit']");
    By doNotShowAgainBox = By.xpath("//input[@name='DontShowAgain']");
    By activeSessionBttnNo = By.xpath("//input[@type='button']");
    By activeSessionBttnYes = By.xpath("//input[@type='submit']");
    By backlogPageHeader = By.xpath("//h2[text()='BACKLOG']");

    public void launchBrowser(String browser) throws Exception {
        switch (browser.toLowerCase(Locale.ROOT)){
            case("chrome"):
                System.setProperty("webdriver.chrome.driver",
                        "./src/test/resources/drivers/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case("firefox"):
                System.setProperty("webdriver.gecko.driver",
                        "./src/test/resources/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case("edge"):
                System.setProperty("webdriver.edge.driver",
                        "./src/test/resources/drivers/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Browser not supported!");
                break;
        }
        driver.manage().window().maximize();
        driver.get("https://positionsapp-uat.azurewebsites.net/#");
    }

    /*
    Manage login email prompt
     */
    public void emailLogin() throws InterruptedException {
        waitForElement(emailTextBox);
        find(emailTextBox).sendKeys(user);
        find(emailNextBttn).click();
    }

    /*
    Manage login password prompt
     */
    public void pwdLogin() throws InterruptedException {
        waitForElement(atLogo);
        find(pwdTextBox).sendKeys(pwd);
        find(pwdLoginBttn).click();
    }

    /*
    Manage keep active session prompt.
    promptAgain: to select the "Do not show again" check box.
    keepSessionActive: to select Yes or No options to keep session active.
     */
    public void activeSessionPrompt(boolean promptAgain, boolean keepSessionActive) throws InterruptedException {
        waitForElement(activeSessionBttnYes);
        if (promptAgain) {
            find(doNotShowAgainBox).click();
        }
        if (keepSessionActive) {
            find(activeSessionBttnYes).click();
        } else {
            find(activeSessionBttnNo).click();
        }
        waitForElement(backlogPageHeader);
    }

    public void waitForElement(final By element) {
        // Waiting for 5 seconds for an element to be present on the page, checking
        // for its presence once every 1 second.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15L))
                .pollingEvery(Duration.ofSeconds(1L))
                .ignoring(NoSuchElementException.class);
        WebElement find = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return find(element);
            }
        });
    }

    public void scrollToElement(WebElement element) {
        Actions scroll = new Actions(driver);
        scroll.moveToElement(element).build().perform();
    }

    public WebElement find(By element) throws NotFoundException {
        return driver.findElement(element);
    }

}
