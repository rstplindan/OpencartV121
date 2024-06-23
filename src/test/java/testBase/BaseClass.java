package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;//Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Master","Reression","DataDriven"})
	@Parameters({"os","browser"})
	public void setup(String os , String br) throws IOException {
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		DesiredCapabilities capabilites = new DesiredCapabilities();
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{	
			if(os.equalsIgnoreCase("windows")) {
				capabilites.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilites.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")) {
				capabilites.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os");
				return;
			}
		
		//browser
		
		switch(br.toLowerCase())
		{
		case "chrome" : capabilites.setBrowserName("chrome"); break;
		case  "edge"  : capabilites.setBrowserName("MicrosoftEdge"); break;
		case  "firefox"  : capabilites.setBrowserName("firefox"); break;
		default  : System.out.println("no matching browser"); return;
		}
		
		driver = new RemoteWebDriver(new URL("http://192.168.76.138:4444/wd/hub"),capabilites);
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase())
			{
			case "chrome" : capabilites.setBrowserName("chrome"); break;
			case  "edge"  : capabilites.setBrowserName("MicrosoftEdge"); break;
			case  "firefox"  : capabilites.setBrowserName("firefox"); break;
			default  : System.out.println("no matching browser"); return;
			}
		}
		
		driver=new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
		
	}
	@AfterClass(groups= {"Sanity","Master","Reression","DataDriven"})
	public void teardown() {
		driver.quit();
		}

}
