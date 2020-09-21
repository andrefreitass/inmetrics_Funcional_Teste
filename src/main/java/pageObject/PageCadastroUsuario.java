package pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageCadastroUsuario extends PageGenerics{

	@FindBy(css = "a.txt2.bo1")
	public WebElement BOTAO_CADASTRASE_RODAPE;

	@FindBy(xpath = "//a[@class='nav-link text-uppercase font-weight-bold'][(text()='Cadastre-se')]")
	public WebElement BOTAO_CADASTRASE_CABECALHO;
	
	@FindBy(xpath = "//div[contains(text(),'Senhas não combinam')]")
	public WebElement SENHAS_NAO_COMBINAM;

	@FindBy(xpath = "//div[contains(text(),'Usuário já cadastrado')]")
	public WebElement USUARIO_CADASTRADO;
	
	
	
	
	
	
}
