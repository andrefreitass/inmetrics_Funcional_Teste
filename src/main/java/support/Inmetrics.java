package support;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import org.apache.commons.lang.ArrayUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;
import manager.driver.DriverManagerFactory;
import pageObject.PageGenerics;
import uteis.AutomacaoBusinessException;
import uteis.Log;
import uteis.ReportListener;
import uteis.SoftAssert;

public class Inmetrics{

	protected PageGenerics pageGeneric;
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<SoftAssert> threadSoft = new ThreadLocal<SoftAssert>();
    protected static WebDriverWait wait;

	public Inmetrics() {
		pageGeneric = new PageGenerics();
	}

	/**
	 * 
	 * @param baseline
	 * @param casoTeste
	 * Metodo para validacao de campos obrigatorios
	 */
	
	public Inmetrics validaObrigatoriedadeCampos(String baseline, String casoTeste) {
		aguardaElemento(pageGeneric.LISTA_DADOS_OBRIGATORIOS.get(0));
		for (int i = 0; i < pageGeneric.LISTA_DADOS_OBRIGATORIOS.size(); i++) {
			//pontoVerificacao(pageGeneric.LISTA_DADOS_OBRIGATORIOS.get(i), "innerText", baseline);
			anexaEvidencia(casoTeste);
		}
		return this;
	}
	
	public Inmetrics limparCampo(WebElement campo) {
		click(campo);
		acoesTeclado(Keys.chord(Keys.CONTROL, "a"));
		acoesTeclado(Keys.BACK_SPACE);
		return this;
	}
	
	/**
	 * Metodo para simular teclas do teclado fisico
	 * @param keys
	 */
	protected void acoesTeclado(Keys... keys) {
		for (Keys key : keys) {
			anexaDescricao("Interagindo com a tecla: " + key.getCodePoint());
			new Actions(getDriver()).sendKeys(key).build().perform();
		}
	}
	
	/**
	 * Metodo para simular teclas do teclado fisico combinadas simultaneamente
	 * @param Keys.chord
	 * @see
	 * Exemplo: <br>acoesTeclado(Keys.chord(Keys.CONTROL, "a"));
	 */
	protected void acoesTeclado(String charSequence) {
			anexaDescricao("Interagindo com a(s) tecla(s): " + charSequence);
			new Actions(getDriver()).sendKeys(charSequence).build().perform();
	}

	/**
	 * Metodo para simular o movimento do mouse ato o elemento informado
	 * @param elemento WebElement
	 */
	protected void mouseMoveTo(WebElement elemento) {
		new Actions(getDriver()).moveToElement(aguardaElemento(elemento)).perform();
	}


	/**
	 * Acessa a aplicao conforme URL predefinida com 
	 * @param caso seja informado os dados de login é realizado o login caso contrario é feito o cadastro
	 */
	public static void acessaAplicacao() {
		getDriver().get(DriverManagerFactory.PROPS.getProperty("web.url"));			
		getDriver().manage().window().maximize();
	}
	
	/**
	 * Metodo que valida texto em poup-up
	 * @param valorCheck
	 */
	protected void pontoVerificacao(Object valorCheck) {
		String alertMessage= getDriver().switchTo().alert().getText();
		pontoVerificacao(alertMessage.contains((String) valorCheck),
                "Validação da poup-up: " + alertMessage + "</br><b>Valor atual:</b> "
                        + alertMessage + "</br><b>Valor esperado:</b> " + valorCheck);
		getDriver().switchTo().alert().accept();
	}
	
	 /**
     * Click no elemento
     * @param elemento
     */
    protected void click(WebElement elemento) {
        anexaDescricao("Realizando clique no elemento: " + getReferenceNameElement(elemento));
        aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).click();
    }

    /**
     * Preenche campo com sendKeys
     * @param elemento
     * @param valor
     */
    protected void preencheCampo(WebElement elemento, Object valor) {
        anexaDescricao("Preenchendo campo: " + getReferenceNameElement(elemento) + " com o valor: " + valor);
        aguardaElemento(ExpectedConditions.elementToBeClickable(elemento)).sendKeys((String) valor);
    }

    /**
     * Define descricao no report
     * @param descricao
     */
    public void anexaDescricao(Object descricao) {
        Log.info((String) descricao);
        ReportListener.setSteps((String) descricao);
    }

    /**
     * Define evidencias no report
     * @param casoDeTeste
     */
    public void anexaEvidencia(Object casoDeTeste) {
        Log.info("Anexando evidencia");
        Allure.addAttachment((String) casoDeTeste,
                new ByteArrayInputStream(((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES)));
    }

    /***
     * Ponto de verificacao geral
     * @param validacao
     * @param check
     */
    protected void pontoVerificacao(boolean validacao, Object check) {
        Log.info("Status: " + validacao + " | " + check);
        getSoft().assertTrue(validacao, (String) check);
    }

    /**
     * Aguardo o Elemento ser carregado visivel. Tempo definido por padrao no
     * CrossBrowser.
     * @param elemento
     */
    protected WebElement aguardaElemento(WebElement elemento) {
        Log.info("Aguardando elemento: " + getReferenceNameElement(elemento));
        try {
            return aguardaElemento(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(elemento)));
        } catch (Exception e) {
            throw new AutomacaoBusinessException("Não foi encontrado o elemento: " + getReferenceNameElement(elemento));
        }
    }

    /**
     * Aguardo o Elemento ser carregado conforme uma expectativa. Tempo definido por
     * padrao .
     * @param expect - ExpectedCondition<'WebElement'>
     */
    protected WebElement aguardaElemento(ExpectedCondition<WebElement> expect) {
        try {
            return wait.until(ExpectedConditions.refreshed(expect));
        } catch (Exception e) {
            throw new AutomacaoBusinessException("Não foi encontrado o elemento conforme expectativa: " + expect, e);
        }
    }

    /**
     * Metodo para Validação dinamica de objetos
     * @param elemento   - elemento alvo da Validação
     * @param atributo   - propriedade do objeto para Validação
     * @param valorCheck - valor a ser validado
     */
    protected void pontoVerificacao(WebElement elemento, String atributo, Object valorCheck) {
        try {
            if (atributo.equals("custom")) {
                pontoVerificacao(elemento.getText().contains((String) valorCheck),
                        "Validação do campo: " + getReferenceNameElement(elemento) + "</br><b>Valor atual:</b> "
                                + elemento.getText() + "</br><b>Valor esperado:</b> " + valorCheck);
            } else {
                pontoVerificacao(elemento.getAttribute(atributo).contains((String) valorCheck),
                        "Validação do campo: " + getReferenceNameElement(elemento) + "</br><b>Valor atual:</b> "
                                + elemento.getAttribute(atributo) + "</br><b>Valor esperado:</b> " + valorCheck);
            }
        } catch (Exception e) {
            pontoVerificacao(false, "Erro ao tentar localizar objeto - " + getReferenceNameElement(elemento));
        }
    }


    /**
     * Retorna o nome referencial do objeto definido no pageObject
     * @param elemento
     * @return String com nome do elemento definido na PageObject
     */
    protected String getReferenceNameElement(WebElement elemento) {
    	Field[] fields = (Field[]) ArrayUtils.addAll(
    			getClass().getSuperclass().getDeclaredFields(),
                getClass().getDeclaredFields());
    	
    	for (Field field : fields) {
        	if(field.getType().getName().toLowerCase().contains("menu")) {
        		for (Field fieldMenu: field.getType().getDeclaredFields()) {
                    if (fieldMenu.getType().getName().toString().contains("pageObject")) {
                    	fields = (Field[]) ArrayUtils.add(fields,fieldMenu);
                    }					
				}	
        	}
		}
    	return "<b>" + getFieldFinder(fields, elemento)  + "</b>";
    }

    private String getFieldFinder(Field[] fields, WebElement elemento) {
        String locator = elemento.toString().replaceAll("(.*?-> |.*\\{By.Android|.*\\{By.|.*'By.)(.*?)( ?(S|s)elector)?: (.*a?)(.*\\}\\)|\\'|])", "$5").toLowerCase(),
        	   by = elemento.toString().replaceAll("(.*?-> |.*\\{By.Android|.*\\{By.|.*'By.)(.*?)( ?(S|s)elector)?: (.*a?)(.*\\}\\)|\\'|])", "$2").toLowerCase(),
        	   annotation;    
       
        for (Field field : fields) {
            try {
                if (field.getType().getPackage().toString().contains("pageObject")) {
                    for (Field fieldSubclass : field.getType().getDeclaredFields()) {
                    	annotation = fieldSubclass.getDeclaredAnnotations()[0].toString().toLowerCase();
                        if (annotation.contains(locator) && annotation.contains(by)) {
                            return fieldSubclass.getName();
                        }
                    }
                    for (Field fieldSuperClass : field.getType().getSuperclass().getDeclaredFields()) {
                    	annotation = fieldSuperClass.getDeclaredAnnotations()[0].toString().toLowerCase();
                        if (annotation.contains(locator) && annotation.contains(by)) {
                            return fieldSuperClass.getName();
                        }
                    }
                }
            } catch (Exception e) {
                Log.warn(">> Field incompativel com extrator de nome de elementos");
            }

        }
        return "[Dynamic Element: " + locator + "]";
    }

    
	public Inmetrics atributoRequirePresente(WebElement elemento) {
		Boolean result = false;
		try {
			anexaDescricao(getReferenceNameElement(elemento));
			String value = elemento.getAttribute("required");
			if (value != null) {
				result = true;
			}
		} catch (Exception e) {
		}
		pontoVerificacao(result, "true");
		return this;
	}


    /**
     * @return the driver
     */
    public static WebDriver getDriver() {
        return Inmetrics.threadDriver.get();
    }
    
    /**
     * * @return SoftAssert
     */
    public SoftAssert getSoft() {
        return Inmetrics.threadSoft.get();
    }

    /**
     * Define o driver
     * @param driver
     */
    public static void setDriver(ThreadLocal<WebDriver> driver) {
    	Inmetrics.threadDriver = driver;
    }

    /**
     * Define o soft.
     * @param soft
     */
    public static void setSoft(ThreadLocal<SoftAssert> soft) {
        Inmetrics.threadSoft = soft;
    }

    /**
     * Define wait
     * @param driver
     */
    public static void setWait(ThreadLocal<WebDriver> driver) {
    	Inmetrics.wait = new WebDriverWait(driver.get(), 30);
    }
    
}