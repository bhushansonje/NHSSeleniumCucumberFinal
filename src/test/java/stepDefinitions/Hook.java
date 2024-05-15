package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import common.Page_BasePage;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Hook extends Page_BasePage {

	private Properties properties;

	public Hook() {
		properties = new Properties();
		try {
			FileInputStream input = new FileInputStream("config.properties");
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void launchBrowser() {
		System.out.println(" BEFORE HOOK");
		String browser = properties.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			options.addArguments("--disable-notifications");
			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(ChromeOptions.CAPABILITY,options);
			options.merge(cp);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Invalid browser specified in config.properties");
		}
	}

	@After
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
			System.out.println(" AFTER HOOK");
		}
	}
}
