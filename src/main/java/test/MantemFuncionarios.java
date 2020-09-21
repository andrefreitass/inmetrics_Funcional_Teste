package test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import helper.HelperLogin;
import helper.HelperMantemFuncionarios;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import manager.driver.DriverManagerFactory;
import uteis.ReportListener;

@Listeners({ReportListener.class})
@Epic("Mantem Funcionarios")
@Feature("Realiza o cadastro, pesquisa, altera��o e exclus�o de funcionarios")
@Severity(SeverityLevel.NORMAL)
@Test(groups = { "Normal" })
public class MantemFuncionarios extends DriverManagerFactory{
	
	private HelperLogin login;
	private HelperMantemFuncionarios helper;
	private String usuario,senha;
	
	@BeforeClass
	public void init() {
		login = new HelperLogin();
		helper = new HelperMantemFuncionarios();
		usuario = helper.dp.getListTestCases().iterator().next().get("usuario").toString();
		senha = helper.dp.getListTestCases().iterator().next().get("senha").toString();
	}

	@Story("Dado que ao tentar realizar o cadastro de funcion�rios sem preencher os dados obrigat�rios ent�o o sistema ir� criticar os campos obrigat�rios")
	@Test(groups = {"Fluxo alternativo"})
	public void CEN01_ValidaCamposObrigatorios() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acionarBotaoNovoFuncionario()
				  .preencheCampoNome(dp.getString("nome"))
				  .preencheCampoCPF(dp.getString("cpf"))
				  .selecionarSexo(dp.getString("sexo"))
				  .preencheCampoAdmissao(dp.getString("admissao"))
				  .preencheCampoCargo(dp.getString("cargo"))
				  .preencheCampoSalario(dp.getString("salario"))
				  .selecionarTipoContratacao(dp.getString("tipoContratacao"))
				  .acionarBotaoEnviar()
				  .validaCamposObrigatorios();

		}
	}
	
	@Story("Dado que ao tentar realizar o cadastro de funcion�rios com dados inv�lidos ent�o o sistema ir� criticar os campos inv�lidos")
	@Test(groups = {"Fluxo alternativo"})
	public void CEN02_ValidaCpfInvalido() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acionarBotaoNovoFuncionario()
				  .preencheCampoNome(dp.getString("nome"))
				  .preencheCampoCPF(dp.getString("cpf"))
				  .acionarBotaoEnviar()
				  .validaMensagemPoupUp(dp.getString("baseline"));
		}
	}

	@Story("Dado que ao preencher o formul�rio de cadastro quando clicar no bot�o cancelar ent�o o sistema deve limpar o formul�rio e sair da tela de cadastro")
	@Test(groups = {"Fluxo b�sico"})
	public void CEN03_CancelamentoCadastroFuncionario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acionarBotaoNovoFuncionario()
				  .preencheCampoNome(dp.getString("nome"))
				  .preencheCampoCPF(dp.getString("cpf"))
				  .selecionarSexo(dp.getString("sexo"))
				  .preencheCampoAdmissao(dp.getString("admissao"))
				  .preencheCampoCargo(dp.getString("cargo"))
				  .preencheCampoSalario(dp.getString("salario"))
				  .selecionarTipoContratacao(dp.getString("tipoContratacao"))
				  .acionarBotaoCancelar();
		}
	}
	@Story("Dado que ao preencher o formul�rio de cadastro quando clicar no bot�o enviar ent�o o sistema deve cadastrar com sucesso e exibir a mensagem informando que o funcion�rio foi cadastrado")
	@Test(groups = {"Fluxo b�sico"})
	public void CEN04_CadastraFuncionario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.acionarBotaoNovoFuncionario()
				  .preencheCampoNome(dp.getString("nome"))
				  .preencheCampoCPF(dp.getString("cpf"))
				  .selecionarSexo(dp.getString("sexo"))
				  .preencheCampoAdmissao(dp.getString("admissao"))
				  .preencheCampoCargo(dp.getString("cargo"))
				  .preencheCampoSalario(dp.getString("salario"))
				  .selecionarTipoContratacao(dp.getString("tipoContratacao"))
				  .acionarBotaoEnviar()
				  .validaMensagem(dp.getString("baseline"));
		}
	}

	@Story("Dado que ao preencher o campo de pesquisa e clicar no bot�o alterar, quando alterar os dados do formul�rio e clicar em enviar o cadastro do funcion�rio deve ser alterado e exibido a mensagem informando a altera��o")
	@Test(groups = {"Fluxo b�sico"})
	public void CEN05_AlterarFuncionario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.pesquisar(dp.getString("filtroPesquisa"))
				  .acionarBotaoEditar()
				  .preencheCampoNome(dp.getString("nome"))
				  .preencheCampoCPF(dp.getString("cpf"))
				  .selecionarSexo(dp.getString("sexo"))
				  .preencheCampoAdmissao(dp.getString("admissao"))
				  .preencheCampoCargo(dp.getString("cargo"))
				  .preencheCampoSalario(dp.getString("salario"))
				  .selecionarTipoContratacao(dp.getString("tipoContratacao"))
				  .acionarBotaoEnviar()
				  .validaMensagem(dp.getString("baseline"));
		}
	}
	
	@Story("Dado que ao preencher o campo de pesquisa e clicar no bot�o excluir, ent�o deve ser alterado e exibido a mensagem informando a exclus�o")
	@Test(groups = {"Fluxo b�sico"})
	public void CEN06_ExcluirFuncionario() {
		for (JsonPath dp : helper.dp.getTestCasesToJsonPath()) {
			login.realizarLogin(usuario,senha);
			helper.setCasoTeste(dp.getString("CT"));
			helper.anexaDescricao(helper.getCasoTeste());
			helper.pesquisar(dp.getString("filtroPesquisa"))
				  .acionarBotaoExcluir()
				  .validaMensagem(dp.getString("baseline"));
		}
	}

	


}
