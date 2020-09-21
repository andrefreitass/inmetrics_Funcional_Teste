package pageObject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import support.Inmetrics;

public class PageGenerics {

	public PageGenerics() {
		PageFactory.initElements(Inmetrics.getDriver(), this);
	}

	@FindBy(name =  "username")
    public WebElement CAMPO_USUARIO;
	
	@FindBy(name = "pass")
    public WebElement CAMPO_SENHA;
	
	@FindBy(name = "confirmpass")
    public WebElement CAMPO_CONFIRMACAO_SENHA;
	
	@FindBy(css = ".login100-form-btn")
    public WebElement BOTAO_CADASTRAR;

	@FindBy(css = "span.login100-form-title.p-b-1")
    public WebElement TITULO_LOGIN;
	
	@FindBy(css = ".alert-validate")
	public List<WebElement> LISTA_DADOS_OBRIGATORIOS;
	
	@FindBy(css = "div.alert.alert-danger.alert-dismissible.fade.show")
    public WebElement MENSAGEM_ERRO;	
	
	@FindBy(xpath = "//a[text()='Sair']")
    public WebElement BOTAO_SAIR;
	
	@FindBy(css=".alert-validate input")
	public List<WebElement> campo_obrigatorio;
	
	
	
	
	
	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * BOTOES
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	@FindBy(xpath = "//button[text()='Consultar']")
	public WebElement BOTAO_CONSULTAR;

	@FindBy(css = "sc-actionbar button[type='submit'][class='ss-btn ss-btn-success']")
	public WebElement BOTAO_CONFIRMAR;

	@FindBy(xpath = "//button[text()='Excluir']")
	public WebElement BOTAO_EXCLUIR;

	@FindBy(xpath = "//button[text()='Limpar']")
	public WebElement BOTAO_LIMPAR;

	@FindBy(xpath = "//button[text()='Cancelar']")
	public WebElement BOTAO_CANCELAR;

	@FindBy(css = "span.ss-btnclose")
	public WebElement BOTAO_FECHAR;

	@FindBy(css = "a[aria-label='go to next page'] i")
	public WebElement BOTAO_PROXIMA_PAGINA;

	@FindBy(css = "a[aria-label='go to previous page'] i")
	public WebElement BOTAO_PAGINA_ANTERIOR;

	@FindBy(css = "i[class^='ss-table-action']")
	public List<WebElement> BOTAO_EDITAR_REGISTRO;

	@FindBy(css = "div[class^='ss-modal-'] button[class='ss-btn ss-btn-success']")
	public WebElement BOTAO_CONFIRMAR_MODAL;

	@FindBy(css = "div[class^='ss-modal-'] button[class='ss-btn ss-btn-default']")
	public WebElement BOTAO_CANCELAR_MODAL;

	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * INPUT
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * CHECK AND RADIO
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * LABEL
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * TABELAS
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	@FindBy(css = "datatable-header-cell span span")
	public List<WebElement> LISTA_CABECALHO;

	@FindBy(css = "datatable-body-cell:not(:last-child)")
	public List<WebElement> LISTA_RESULTADO;

	@FindBy(css = "sc-empty-state")
	public WebElement LISTA_RESULTADO_VAZIO;

	@FindBy(css = "datatable-body")
	public WebElement TABELA_RESULTADOS;

	/**
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * SELECT
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	@FindBy(css = "span[class='ng-option-label']")
	public List<WebElement> CAMPO_OPCAO_COMBOBOX;

	@FindBy(css = "[class='ng-dropdown-panel-items scroll-host']")
	public WebElement CAMPO_LISTA_COMBOBOX;

	@FindBy(css = "ng-dropdown-panel")
	public WebElement SELECT_CORPO_PADRAO;

	@FindBy(css = "ng-dropdown-panel div.ng-option span")
	public List<WebElement> SELECT_ITENS_PADRAO;

	/***
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 * LABELS, MENSAGENS, VALIDACOES
	 * --------------------------------------------------------------------------------------------------------------------------------------
	 */

	@FindBy(css = "div[class='alert-message']")
	public WebElement ALERT_MESSAGE;

	@FindBy(css = "div[class='alert-message'] p span")
	public WebElement ALERT_MESSAGE_TEXT;

	@FindBy(css = "div[class='empty-state-wrapper']")
	public WebElement TELA_SEM_RESULTADO;

	@FindBy(css = "div[class='page-count']")
	public WebElement PAGE_COUNT;

	@FindBy(css = "sc-modal[headertitle='Atenção']")
	public WebElement MODAL_EXCLUSAO_ALTERACAO;

	@FindBy(css = "sc-modal[headertitle='Atenção'] .content-wrapper")
	public WebElement MENSAGEM_MODAL_EXCLUSAO_ALTERACAO;
}