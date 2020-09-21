package manager.driver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import support.Inmetrics;
import uteis.AutomacaoBusinessException;
import uteis.Diretorios;
import uteis.LeitorProperties;
import uteis.Log;
import uteis.ReportListener;
import uteis.SoftAssert;

public class DriverManagerFactory {
	protected static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
	public static ThreadLocal<SoftAssert> threadSoft = new ThreadLocal<SoftAssert>();
	public static Properties PROPS = new LeitorProperties(new Diretorios().getPathArquivo("config.properties")).getProperties();

	public enum Platform {
		Chrome("manager.driver.DriverManagerChrome");

		private final String driverName;

		Platform(String drv) {
			driverName = drv;
		}

		private String getClassName() {
			return driverName;
		}
	}

	@BeforeTest
	@Parameters({"platform", "local"})
	public void getDriver(Platform platform, boolean local){
		Log.info("Carregando as configurações: " + platform);
		try {			
			if (null == threadDriver.get() || (!threadDriver.get().toString().contains(platform.toString().toLowerCase()))) {
				threadDriver.set(((DriverManager)Class.forName(platform.getClassName()).newInstance()).buildDriver(local));
				setDependencies();
			}
		} catch (Exception e) {
			throw new AutomacaoBusinessException("Houve um problema no carregamento: " + e);
		}
	}

	@AfterTest(alwaysRun = true)
	public void quit() {
		threadDriver.get().quit();
		threadDriver.remove();
	}

	/**
	 * Define as dependencias de ReportListener, ActionUI e SoftAssert
	 * Propagando o driver para as camadas de projeto
	 */
	private void setDependencies() {
		threadSoft.set(new SoftAssert());
		ReportListener.setDriver(threadDriver.get());
		ReportListener.setAsserts(threadSoft.get());
		Inmetrics.setDriver(threadDriver);
		Inmetrics.setSoft(threadSoft);
		Inmetrics.setWait(threadDriver);
	}
}

