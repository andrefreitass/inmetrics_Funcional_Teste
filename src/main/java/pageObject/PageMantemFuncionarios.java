package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageMantemFuncionarios extends PageGenerics{
	
	@FindBy(xpath = "//a[text()='Novo Funcionário']")
    public WebElement BOTAO_NOVO_FUNCIONARIO;
	
	@FindBy(xpath = "//a[text()='Funcionários']")
    public WebElement BOTAO_FUNCIONARIOS;

	@FindBy(xpath = "//label[text()='Pesquisar:']//input")
    public WebElement CAMPO_PESQUISAR;
	
	@FindBy(css = "#delete-btn")
    public WebElement BOTAO_EXCLUIR;
	
	@FindBy(css = ".btn.btn-warning")
    public WebElement BOTAO_EDITAR;
	
	
	@FindBy(css = "#inputNome")
    public WebElement CAMPO_NOME;
	
	@FindBy(css = "#cpf")
    public WebElement CAMPO_CPF;
	
	@FindBy(css = "#slctSexo")
    public WebElement COMBOBOX_SEXO;
	
	@FindBy(xpath = "//option[text()='Indiferente']")
    public WebElement OPCAO_INDIFERENTE;
	
	@FindBy(xpath = "//option[text()='Feminino']")
    public WebElement OPCAO_FEMININO;
	
	@FindBy(xpath = "//option[text()='Masculino']")
    public WebElement OPCAO_MASCULINO;
	
	@FindBy(css = "#inputAdmissao")
    public WebElement CAMPO_ADMISSAO;
	
	@FindBy(css = "#inputCargo")
    public WebElement CAMPO_CARGO;
	
	@FindBy(css = "#dinheiro")
    public WebElement CAMPO_SALARIO;
	
	@FindBy(css = "#clt")
    public WebElement OPCAO_CLT;
	
	@FindBy(css = "#pj")
    public WebElement OPCAO_PJ;

	@FindBy(css = ".cadastrar-form-btn")
    public WebElement BOTAO_ENVIAR;
	
	@FindBy(css = ".cancelar-form-btn")
    public WebElement BOTAO_CANCELAR;

	@FindBy(xpath = "//div[@class='alert alert-success alert-dismissible fade show']")
    public WebElement MENSAGEM;
	
	
	
}
