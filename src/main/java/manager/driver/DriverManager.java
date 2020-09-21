package manager.driver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import uteis.Diretorios;
import uteis.LeitorProperties;

public abstract class DriverManager {


	protected abstract WebDriver createDriver(boolean local);
	protected DesiredCapabilities capabilities = new DesiredCapabilities();
	public static Properties PROPS = new LeitorProperties(new Diretorios().getPathArquivo("config.properties")).getProperties();


	public WebDriver buildDriver(boolean local) {
			return createDriver(local);
		}
}


