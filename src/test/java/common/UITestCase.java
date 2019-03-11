package common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;

public class UITestCase {

	public String runId = String.valueOf(System.currentTimeMillis()).substring(5, 12);
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeClass
	public void beforeClass(ITestContext context) {
		String browser = context.getCurrentXmlTest().getParameter("browser");
		if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		wait= new WebDriverWait(driver, 10);
		Reporter.log("Test Started with runID= " + runId + " at " + browser + " browser.", true);

	}
	
	public WebElement waitAndGetElement(By by) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
}
