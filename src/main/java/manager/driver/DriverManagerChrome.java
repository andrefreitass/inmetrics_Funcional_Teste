package manager.driver;

import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import uteis.AutomacaoBusinessException;
import uteis.Diretorios;

public class DriverManagerChrome extends DriverManager{

	@Override
	protected WebDriver createDriver(boolean local) {
		System.setProperty("webdriver.chrome.driver", new Diretorios().getPathArquivo("chromedriver.exe"));
		capabilities = DesiredCapabilities.chrome();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		ChromeOptions chOptions = new ChromeOptions();
		chOptions.addArguments("--incognito");
		chOptions.addArguments("--ignore-certificate-errors");
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", PROPS.getProperty("download.dir"));
		chOptions.setExperimentalOption("prefs", chromePrefs);
		capabilities.setCapability(ChromeOptions.CAPABILITY, chOptions);
		capabilities.setCapability(ChromeOptions.CAPABILITY, chOptions);
		if(local) {
			return new ChromeDriver(chOptions);

		}else{
			try {
				return new RemoteWebDriver(new URL(PROPS.getProperty("node.url.web")),capabilities);
			} catch (Exception e) {
				throw new AutomacaoBusinessException("Houve um problema no carregamento: " + e);
			}
		}
	}
}