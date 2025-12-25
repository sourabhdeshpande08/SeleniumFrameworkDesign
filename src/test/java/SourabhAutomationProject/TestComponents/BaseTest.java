package SourabhAutomationProject.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import SourabhAutomationProject.PageObjectModel.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;

	public LandingPage landingPage;
	String URL = "https://rahulshettyacademy.com/client/";

	@Test
	public WebDriver InitializeDriver() throws IOException {

		

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "/src/test/java/SourabhAutomationProject/Resources/GlobalProperties.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {

			
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeoptions = new ChromeOptions();
			
			if(browserName.contains("headless")) {
				
				chromeoptions.addArguments("--headless=new");
				chromeoptions.addArguments("--window-size=1920,1080");	
			}
			
			driver = new ChromeDriver(chromeoptions);
			driver.manage().window().setSize(new Dimension(1440,900));

		}

		else if (browserName.contains("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			
			if(browserName.contains("headless")) {
				
				firefoxoptions.addArguments("--headless=new");
				firefoxoptions.addArguments("--window-size=1920,1080");
			}
			
			driver = new FirefoxDriver(firefoxoptions);
			driver.manage().window().setSize(new Dimension(1440,900));

		}

		else if (browserName.contains("edge")) {

			WebDriverManager.edgedriver().setup();
			EdgeOptions edgeoptions = new EdgeOptions();
			if(browserName.contains("headless")) {
				
				edgeoptions.addArguments("--headless=new");
				edgeoptions.addArguments("--window-size=1920,1080");
			}
			driver = new EdgeDriver(edgeoptions);
			driver.manage().window().setSize(new Dimension(1440,900));

		}

		driver.manage().window().maximize();

		return driver;

	}
	
	

	@Test
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

		// Convert Json data to String

		String jsonContent = FileUtils.readFileToString(
				new File(
						System.getProperty("user.dir") + "/src/test/java/SourabhAutomationProject/Resources/data.json"),
				StandardCharsets.UTF_8);

		// Convert String data to HashMap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {

		driver = InitializeDriver();

		landingPage = new LandingPage(driver);
		landingPage.goToURL(URL);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}

	}

	@Test
	public String getScreenshot(String failedTestCase) throws IOException {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File destination = new File(System.getProperty("user.dir") + "/screenshots/" + failedTestCase + ".jpeg");

		FileUtils.copyFile(source, destination);

		return System.getProperty("user.dir") + "/screenshots/" + failedTestCase + ".jpeg";

	}

}
