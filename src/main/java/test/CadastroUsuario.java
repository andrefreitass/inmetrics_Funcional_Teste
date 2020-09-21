package test;

import java.util.Random;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.HelperCadastroUsuario;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import manager.driver.DriverManagerFactory;
import uteis.ReportListener;

@Listeners({ ReportListener.class })
@Epic("Cadastro de Usuário")
@Feature("Realiza o cadastro de usuário no sistema")
@Severity(SeverityLevel.NORMAL)
@Test(groups = { "Normal" })
public class CadastroUsuario extends DriverManagerFactory {

	private HelperCadastroUsuario helper;
	private String usuario = "AUTOMACAO";
	private Random gerador;

	@BeforeClass
	public void init() {
		helper = new HelperCadastroUsuario();
		gerador = new Random();
		usuario = usuario + String.valueOf(gerador.nextInt(100));
	}

	@Story("Dado que não informo dos dados de preenchimento obrigatório quando realizar o cadastro então o sistema vai criticar os campos obrigatórios")
	@Test(groups = { "Fluxo alternativo" })
	public void CEN01_ValidaDadosObrigatorios() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.acionarBotaoCadastraSeRodape()
				  .preencheUsuario(dp.getString("usuario"))
				  .preencheSenha(dp.getString("senha"))
				  .preencheConfirmacaoSenha(dp.getString("confirmacaoSenha"))
				  .acionarBotaoCadastrar()
				  .validaCamposObrigatorios(dp.getString("baseline"));
		}
	}

	@Story("Dado que ao tentar cadastrar um novo usuário com o valor menor que o pré-definido então o sistema vai criticar o campo usuário")
	@Test(groups = { "Fluxo alternativo" })
	public void CEN02_ValidaCampoUsuario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.acionarBotaoCadastraSeCabecalho()
					.preencheUsuario(dp.getString("usuario"))
					.preencheSenha(dp.getString("senha"))
					.preencheConfirmacaoSenha(dp.getString("confirmacaoSenha"))
					.acionarBotaoCadastrar()
					.validaDadosInvalidos(dp.getString("baseline"));
		}
	}

	@Story("Dado que ao tentar cadastrar um novo usuário e prencher a senha e a senha de confirmação com valores diferentes estão o sistema vai informar uma mensagem informando que as senhas estão diferentes")
	@Test(groups = { "Fluxo alternativo" })
	public void CEN03_ValidaSenhasDiferentes() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.acionarBotaoCadastraSeCabecalho()
					.preencheUsuario(dp.getString("usuario"))
					.preencheSenha(dp.getString("senha"))
					.preencheConfirmacaoSenha(dp.getString("confirmacaoSenha"))
					.acionarBotaoCadastrar()
					.validaSenhasNaoCombinam(dp.getString("baseline"));
		}
	}
	@Story("Dado que ao tentar realizar o cadastro de um usuário já existente então o sistema vai criticar o cadastro do novo usuário")
	@Test(groups = { "Fluxo básico" })
	public void CEN04_UsuarioJaCadastrado() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.acionarBotaoCadastraSeCabecalho()
					.preencheUsuario(dp.getString("usuario"))
					.preencheSenha(dp.getString("senha"))
					.preencheConfirmacaoSenha(dp.getString("confirmacaoSenha"))
					.acionarBotaoCadastrar()
					.validaUsuarioCadastrado(dp.getString("baseline"));
		}
	}

	@Story("Dado que ao acesso o menu cadastra-se quando informar dados válidos então o sistema realiza o cadastro com sucesso.")
	@Test(groups = { "Fluxo básico" })
	public void CEN05_CadastroUsuario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acessaAplicacao();
			helper.acionarBotaoCadastraSeCabecalho()
					.preencheUsuario(usuario)
					.preencheSenha(dp.getString("senha"))
					.preencheConfirmacaoSenha(dp.getString("confirmacaoSenha"))
					.acionarBotaoCadastrar()
					.validaLoginSucesso();
		}
	}

}
