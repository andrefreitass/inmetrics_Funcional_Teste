package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.HelperLogin;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import manager.driver.DriverManagerFactory;
import uteis.ReportListener;

@Listeners({ReportListener.class})
@Epic("Login")
@Feature("Realiza Login no sistema")
@Severity(SeverityLevel.NORMAL)
@Test(groups = { "Normal" })
public class Login extends DriverManagerFactory{
	
	private HelperLogin helper;
	
	@BeforeClass
	public void init() {
		helper = new HelperLogin();
	}
	
	@Story("Dado que ao tentar realizar o login sem preencher os dados obrigatórios então o sistema irá criticar os campos obrigatórios")
	@Test(groups = {"Fluxo alternativo"})
	public void CEN01_ValidaCamposObrigatorios() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.preencheUsuario(dp.getString("usuario"))
				  .preencheSenha(dp.getString("senha"))
				  .acionarBotaoEntre()
				  .validaCamposObrigatorios(dp.getString("baseline"));
		}
	}
	
	@Story("Dado que ao tentar realizar o login com dados inválidos então o sistema irá criticar os campos inválidos")
	@Test(groups = {"Fluxo alternativo"})
	public void CEN02_ValidaDadosInvalidos() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.preencheUsuario(dp.getString("usuario"))
				  .preencheSenha(dp.getString("senha"))
				  .acionarBotaoEntre()
				  .validaMensagem(dp.getString("baseline"));
		}
	}
	
	@Story("Dado que ao tentar realizar o login com dados válidos então o sistema irá realizar o login com sucesso")
	@Test(groups = {"Fluxo básico"})
	public void CEN03_RealizaLogin() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.preencheUsuario(dp.getString("usuario"))
				  .preencheSenha(dp.getString("senha"))
				  .acionarBotaoEntre()
				  .validaLoginSucesso();
		}
	}
	
	@Story("Dado que esteja logado na aplicação quando clicar no botão sair deve ser realizado o logout do sistema")
	@Test(groups = {"Fluxo alternativo"})
	public void CEN04_RealizaLogout() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.preencheUsuario(dp.getString("usuario"))
				  .preencheSenha(dp.getString("senha"))
				  .acionarBotaoEntre()
				  .validaLoginSucesso()
				  .acionarBotaoSair()
				  .validaLogout();
		}
	}
	
}
